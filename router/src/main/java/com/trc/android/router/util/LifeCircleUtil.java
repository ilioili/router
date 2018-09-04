package com.trc.android.router.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.trc.android.router.RouterConfig;

public class LifeCircleUtil {


    public static void startActivity(Context context, final Intent intent, final LifeCircleCallback lifeCircleCallback) {
        if (null == context) context = RouterConfig.getCurrentActivity();
        if (context instanceof FragmentActivity) {
            BridgeFragment fragment = new BridgeFragment();
            lifeCircleCallback.setHostFragment(fragment);
            fragment.setCallback(lifeCircleCallback, intent);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().add(Window.ID_ANDROID_CONTENT, fragment).commit();
        } else if (null != RouterConfig.getCurrentActivity() && RouterConfig.getCurrentActivity() instanceof FragmentActivity) {
            BridgeFragment fragment = new BridgeFragment();
            fragment.setCallback(lifeCircleCallback, intent);
            ((FragmentActivity) RouterConfig.getCurrentActivity()).getSupportFragmentManager().beginTransaction().add(Window.ID_ANDROID_CONTENT, fragment).commit();
        } else {
            ((Activity) context).startActivityForResult(intent, 0);
        }
    }
}

