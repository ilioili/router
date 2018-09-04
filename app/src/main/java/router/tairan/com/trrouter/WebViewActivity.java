package router.tairan.com.trrouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.trc.android.router.Router;
import com.trc.android.router.annotation.uri.RouterUri;

@RouterUri({Pages.HTTP, Pages.HTTPS})
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    public static void start(Router router) {
        Intent it = new Intent(router.getContext(), WebViewActivity.class);
        it.putExtra("url", router.toUriStr());
        router.getContext().startActivity(it);
    }
}
