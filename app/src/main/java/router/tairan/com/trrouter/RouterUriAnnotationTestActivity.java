package router.tairan.com.trrouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trc.android.router.annotation.uri.RouterUri;

@RouterUri("tlkj://uri_test/a/b/c")
public class RouterUriAnnotationTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_scheme);
    }
}
