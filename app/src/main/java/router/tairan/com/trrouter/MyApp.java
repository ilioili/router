package router.tairan.com.trrouter;

import android.app.Application;

import com.trc.android.router.annotation.compile.RouterAppModule;

@RouterAppModule
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       RouterHelper.init(this);
    }


}
