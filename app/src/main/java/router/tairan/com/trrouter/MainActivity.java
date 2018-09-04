package router.tairan.com.trrouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.trc.android.router.Router;
import com.trc.android.router.annotation.uri.RouterUri;

@RouterUri("tlkj://main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(Router router) {
        router.getContext().startActivity(new Intent(router.getContext(), MainActivity.class));
    }

    public void onClickToBaidu(View view) {
        Router.from(this).to(Pages.BAIDU);
    }

    public void onClickToWangyi(View view) {
        //    路由配置如下
        //    static {
        //        redirectMap.put(Pages.SINA, Pages.BAIDU);
        //        redirectMap.put(Pages.BAIDU, Pages.WANGYI);
        //    }
        Router.fromCurrentContext().to(Pages.SINA);
    }

    public void onClickToUserProfile(View view) {
        Router.from(this).to(Pages.USER_PROFILE);
    }


    public void onClickToLoan(View view) {
        Router.from(this).to(Pages.LOAN);
    }


    public void addRemoteView(View view) {
        View v = (View) Router.from(this).setUri("test://custom_view").transform();
        if (null != v) {
            LinearLayout linearLayout = findViewById(R.id.root);
            linearLayout.addView(v);
        }
    }

    public void onClickDefaultRouter1(View view) {
        Router.fromCurrentContext().to(Pages.TEST_DEFAULT_SCHEME);
    }

    public void onClickDefaultRouter2(View view) {
        Router.fromCurrentContext().to(Pages.Scheme.DEFAULT + "://" + Pages.TEST_DEFAULT_SCHEME);
    }
}
