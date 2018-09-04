package router.tairan.com.trrouter.interceptor;

import android.widget.Toast;

import com.trc.android.router.Chain;
import com.trc.android.router.Interceptor;
import com.trc.android.router.Router;
import com.trc.android.router.annotation.interceptor.RunInMainThread;

import java.util.Map;

import router.tairan.com.trrouter.Pages;

@RunInMainThread
public class LoginInterceptor implements Interceptor {
    public static boolean isLogin;

    @Override
    public void handle(final Router router, final Chain chain) {
        if (!isLogin) {
            Toast.makeText(router.getContext(), "需要先登录", Toast.LENGTH_SHORT).show();
            Router.Callback loginCallback = new Router.Callback() {
                @Override
                public void onResult(boolean succeed, Map bundle) {
                    if (succeed) {
                        chain.proceed(router);
                    } else {
                        Toast.makeText(router.getContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Router.from(router.getContext()).setCallback(loginCallback).to(Pages.LOGIN);
        } else {
            chain.proceed(router);
        }
    }
}
