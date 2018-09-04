package com.trc.android.router.util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class BridgeFragment extends Fragment {
    private LifeCircleCallback callback;
    private Intent intent;
    public static final int REQUEST_CODE = 100;
    boolean isFirstResume = true;

    public void setCallback(LifeCircleCallback callback, Intent intent) {
        this.callback = callback;
        this.intent = intent;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            startActivityForResult(intent, REQUEST_CODE);
            isFirstResume = false;
        } else {
            if (null != callback && null != intent) {
                callback.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != callback) {
            callback.onPause();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != callback) {
            if (requestCode == REQUEST_CODE)
                callback.onActivityResult(resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (null != callback) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            callback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}

