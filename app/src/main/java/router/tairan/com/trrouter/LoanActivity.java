package router.tairan.com.trrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trc.android.router.annotation.interceptor.RouterInterceptor;
import com.trc.android.router.annotation.uri.RouterUri;

import router.tairan.com.trrouter.interceptor.RealNameOauthInterceptor;

@RouterUri(Pages.LOAN)
@RouterInterceptor(RealNameOauthInterceptor.class)//跳转该页面需要登录且经过实名认证
public class LoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
    }
}
