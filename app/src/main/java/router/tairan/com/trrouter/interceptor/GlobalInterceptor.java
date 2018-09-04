package router.tairan.com.trrouter.interceptor;

import android.widget.Toast;

import com.trc.android.router.Chain;
import com.trc.android.router.Interceptor;
import com.trc.android.router.Router;
import com.trc.android.router.annotation.interceptor.RunInMainThread;

@RunInMainThread//代码执行到此，如果不是主线程，会切换到主线程然后执行回调中的代码
public class GlobalInterceptor implements Interceptor {
    @Override
    public void handle(Router router, Chain chain) {
        Toast.makeText(router.getContext(), "我是全局拦截器额", Toast.LENGTH_SHORT).show();
        chain.proceed(router);
    }
}
