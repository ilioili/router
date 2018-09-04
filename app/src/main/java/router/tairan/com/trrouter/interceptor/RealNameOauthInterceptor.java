package router.tairan.com.trrouter.interceptor;

import android.widget.Toast;

import com.trc.android.router.Chain;
import com.trc.android.router.Interceptor;
import com.trc.android.router.Router;
import com.trc.android.router.annotation.interceptor.RunInMainThread;

import java.util.Map;

import router.tairan.com.trrouter.Pages;

@RunInMainThread
public class RealNameOauthInterceptor implements Interceptor {
    public static boolean isRealNameOauthPast;

    @Override
    public void handle(final Router router, final Chain chain) {
        Toast.makeText(router.getContext(), "请求服务器，获取实名认证状态", Toast.LENGTH_SHORT).show();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isRealNameOauthPast) {
                chain.proceed(router);
            }else{
                Router.Callback oauthCallback = (succeed, bundle) -> {
                    if (succeed) {
                        chain.proceed(router);
                    }else {
                        Toast.makeText(router.getContext(), "认证失败", Toast.LENGTH_SHORT).show();
                    }
                };
                Router.from(router.getContext()).setCallback(oauthCallback).to(Pages.REAL_NAME_OAUTH);
            }
        }).start();
    }
}
