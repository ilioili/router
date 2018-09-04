package router.tairan.com.trrouter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.trc.android.router.util.LifeCircleCallback;
import com.trc.android.router.util.LifeCircleUtil;
import com.trc.android.router.Router;
import com.trc.android.router.annotation.interceptor.RouterInterceptor;
import com.trc.android.router.annotation.uri.RouterUri;

import router.tairan.com.trrouter.interceptor.LoginInterceptor;
import router.tairan.com.trrouter.interceptor.RealNameOauthInterceptor;

@RouterUri(Pages.REAL_NAME_OAUTH)
@RouterInterceptor(LoginInterceptor.class)
public class RealNameOauthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_oauth);
    }

    public void onClickOauth(View view) {
        RealNameOauthInterceptor.isRealNameOauthPast = true;
        Toast.makeText(this, "完成了实名认证", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    public static void start(final Router router) {
        Context context = router.getContext();
        Intent intent = new Intent(context, RealNameOauthActivity.class);
        final Router.Callback callback = router.getCallback();
        if (context instanceof FragmentActivity && callback != null) {
            LifeCircleUtil.startActivity(context, intent, new LifeCircleCallback() {
                @Override
                protected void onActivityResult(int resultCode, Intent data) {
                    if (resultCode == RESULT_OK) {
                        callback.onResult(true, null);
                    } else {
                        callback.onResult(false, null);
                    }
                }
            });
        } else {
            context.startActivity(intent);
        }
    }
}
