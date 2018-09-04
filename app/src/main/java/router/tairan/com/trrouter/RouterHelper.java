package router.tairan.com.trrouter;

import android.app.Application;

import com.trc.android.router.RedirectAdapter;
import com.trc.android.router.Router;
import com.trc.android.router.RouterConfig;
import com.trc.android.router.TargetLostListener;

import java.util.HashMap;

import router.tairan.com.trrouter.interceptor.GlobalInterceptor;

public class RouterHelper {
    public static HashMap<String, String> redirectMap = new HashMap<>();
    public static RedirectAdapter redirectAdapter = new RedirectAdapter() {
        @Override
        public Router adapt(Router router) {
            String url = router.toUriStr();
            for (String targetUrl : redirectMap.keySet()) {//如果有匹配的，则创建新的Router
                if (url.startsWith(targetUrl)) {
                    return Router.from(router.getContext()).setUri(redirectMap.get(targetUrl));
                }
            }
            return router;
        }
    };
    private static TargetLostListener targetLostListener = new TargetLostListener() {
        @Override
        public void onTargetLost(Router router) {
            //Do something 例如利用隐士Intent交给系统处理
        }
    };

    static {
        redirectMap.put(Pages.SINA, Pages.BAIDU);
        redirectMap.put(Pages.BAIDU, Pages.WANGYI);
    }

    public static void init(Application application) {
        RouterConfig.getInstance()
                .init(application)
                .addInterceptor(GlobalInterceptor.class)
                .setRedirectAdapter(redirectAdapter);
    }
}
