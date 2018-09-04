package router.tairan.com.trrouter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.trc.android.router.util.LifeCircleCallback;
import com.trc.android.router.util.LifeCircleUtil;
import com.trc.android.router.Router;
import com.trc.android.router.annotation.uri.RouterUri;

import router.tairan.com.trrouter.interceptor.LoginInterceptor;

@RouterUri(Pages.LOGIN)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View v) {
        LoginInterceptor.isLogin = true;
        setResult(RESULT_OK);
        finish();
    }

    public static void start(final Router router) {
        Context context = router.getContext();
        Intent intent = new Intent(context, LoginActivity.class);
        final Router.Callback callback = router.getCallback();
        LifeCircleUtil.startActivity(context, intent, new LifeCircleCallback() {
            @Override
            protected void onActivityResult(int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    callback.onResult(true, null);
                } else {
                    callback.onResult(false, null);
                }
                removeCallback();//删除Context添加的BridgeFragment
            }
        });
    }
}
