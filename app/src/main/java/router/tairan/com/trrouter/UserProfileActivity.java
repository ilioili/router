package router.tairan.com.trrouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trc.android.router.annotation.interceptor.RouterInterceptor;
import com.trc.android.router.annotation.uri.RouterUri;

import router.tairan.com.trrouter.interceptor.LoginInterceptor;

@RouterUri(Pages.USER_PROFILE)
@RouterInterceptor(LoginInterceptor.class)
public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("name", "Hunter");
        setResult(RESULT_OK, intent);
        super.finish();
    }

}
