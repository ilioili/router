package com.trc.android.router;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.trc.android.router.annotation.interceptor.RouterInterceptor;
import com.trc.android.router.annotation.interceptor.RunInChildThread;
import com.trc.android.router.annotation.interceptor.RunInMainThread;
import com.trc.android.router.annotation.uri.RouterUri;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class RouterManager {
    private static final String TARGET_CLASS_START_METHOD_NAME = "start";
    private static HashMap<String, Class> classCacheMap;

    /**
     * @return true 是否找到了处理Router的类
     */
    static boolean route(Router router) {
        router = adapt(router);
        Class<?> clazz = getMatchedClass(router);
        if (null != clazz) {
            LinkedList<Class<? extends Interceptor>> list = getInterceptorClasses(router, clazz);
            resolveByInterceptor(clazz, router, list.iterator());
            return true;
        } else {
            TargetLostListener targetLostListener = router.getTargetLostListener();
            if (null == targetLostListener)
                targetLostListener = RouterConfig.getInstance().getTargetLostListener();
            if (null != targetLostListener) {
                targetLostListener.onTargetLost(router);
            } else {
                Router.Callback callback = router.getCallback();
                if (callback != null) {
                    callback.onResult(false, null);
                }
            }
            return false;
        }
    }

    private static Router adapt(Router router) {
        Router currentRouter = router;
        RedirectAdapter redirectAdapter = RouterConfig.getInstance().getRedirectAdapter();
        int redirectTime = 0;
        do {
            router = currentRouter;
            currentRouter = redirectAdapter.adapt(router);
            redirectTime++;
            if (redirectTime > 100) {
                logWarning("Router config is probably running into a dead loop:" + router.toUriStr() + "   <-- * -->   " + currentRouter.toUriStr());
                //简单处理可能存在到死循环，继续往下走
                break;
            }
        } while (!currentRouter.equals(router));
        return router;
    }

    static Class getTarget(Router router) {
        router = adapt(router);
        return getMatchedClass(router);
    }

    @NonNull
    private static LinkedList<Class<? extends Interceptor>> getInterceptorClasses(Router router, Class<?> clazz) {
        LinkedList<Class<? extends Interceptor>> list = new LinkedList<>(RouterConfig.getInstance().getInterceptorClasses());
        if (null != router.getInterceptorClasses())
            list.addAll(router.getInterceptorClasses());
        if (clazz.isAnnotationPresent(RouterInterceptor.class)) {
            @SuppressWarnings("unchecked") Class<? extends Interceptor>[] value = clazz.getAnnotation(RouterInterceptor.class).value();
            list.addAll(Arrays.asList(value));
        }
        return list;
    }

    private static void resolveByInterceptor(final Class<?> targetClass, final Router router, final Iterator<Class<? extends Interceptor>> iterator) {
        try {
            if (iterator.hasNext()) {
                final Class<? extends Interceptor> interceptorClass = iterator.next();
                if (interceptorClass.isAnnotationPresent(RunInChildThread.class) && Looper.myLooper() == Looper.getMainLooper()) {//如果要求在子线程运行
                    new Thread(() -> handleByInterceptor(interceptorClass, router, targetClass, iterator)).start();
                } else if (interceptorClass.isAnnotationPresent(RunInMainThread.class) && Looper.myLooper() != Looper.getMainLooper()) {//要求在主线程
                    new Handler(Looper.getMainLooper()).post(() -> handleByInterceptor(interceptorClass, router, targetClass, iterator));
                } else {
                    handleByInterceptor(interceptorClass, router, targetClass, iterator);
                }
            } else {
                try {
                    Method method = targetClass.getMethod(TARGET_CLASS_START_METHOD_NAME, Router.class);
                    method.invoke(targetClass, router);
                } catch (NoSuchMethodException e) {
                    //如果是Activity，则直接跳转过去
                    if (Activity.class.isAssignableFrom(targetClass)) {
                        Context context = router.getContext();
                        Intent intent = new Intent(context, targetClass);
                        if (context instanceof Activity) {
                            ((Activity) context).startActivityForResult(intent, 0);
                        } else {
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                }
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void handleByInterceptor(Class<? extends Interceptor> interceptorClass, Router router, final Class<?> targetClass, final Iterator<Class<? extends Interceptor>> iterator) {
        try {
            interceptorClass.newInstance().handle(router, routerAfterIntercepting -> resolveByInterceptor(targetClass, routerAfterIntercepting, iterator));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    //返回匹配程度最高的Class
    static Class getMatchedClass(Router router) {
        String uri = router.toUriStr();
        RouterConfig routerConfig = RouterConfig.getInstance();
        if (classCacheMap == null) {
            classCacheMap = new HashMap<>(routerConfig.getClasses().length);
        }
        Class targetClass = classCacheMap.get(uri);
        if (null == targetClass) {
            int lastMatch = 0;
            for (Class<?> clazz : routerConfig.getClasses()) {
                RouterUri annotation = clazz.getAnnotation(RouterUri.class);
                if (null == annotation) {
                    logWarning("-\nClass:" + clazz.getName() + " isn't annotated with RouterUri, " +
                            "but it is scanned and compiled in AddressList.class. Try to clean the " +
                            "whole project and rebuild it;");
                    continue;
                }
                String[] uris = annotation.value();
                int match = match(uri, uris);
                if (match > lastMatch) {
                    lastMatch = match;
                    targetClass = clazz;
                } else if (match != 0 && match == lastMatch) {
                    logWarning("-\nThere are two Classes declared with a same annotation which "
                            + "may result in upexpected problem:"
                            + "\n@RouterUri:" + uri
                            + "\nClass1:" + targetClass.getName()
                            + "\nClass2:" + clazz.getName());
                }
            }
            if (null != targetClass) classCacheMap.put(uri, targetClass);
        }
        return targetClass;
    }

    private static void logWarning(String msg) {
        Log.e("Router Warning", msg);
    }

    /**
     * @return 0表示不匹配，>0表示匹配程度，匹配程度越高该值越大
     */
    static int match(String targetUri, String[] uris) {
        //进行URI包含(startsWith)匹配，返回匹配的程度
        int match = 0;
        for (String item : uris) {
            if (targetUri.startsWith(item)) {
                if (match < item.length()) {
                    match = item.length();
                }
            }
        }
        return match;
    }

}
