package router.tairan.com.trrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trc.android.router.annotation.uri.RouterUri;

@RouterUri("hello")
public class DefautSchemeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaut_scheme_test);
    }
}
