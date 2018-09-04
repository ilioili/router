package com.trc.android.router.util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public abstract class LifeCircleCallback {
    private Fragment hostFragment;

    protected void onResume() {
    }

    protected void onPause() {
    }

    protected void onActivityResult(int resultCode, Intent data) {
    }

    protected void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    final void setHostFragment(Fragment fragment) {
        hostFragment = fragment;
    }

    final public void removeCallback() {
        hostFragment.getFragmentManager().beginTransaction().remove(hostFragment).commit();
    }
}
