package com.example.naledi;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingCard {

    private Activity mActivity;
    private AlertDialog dialog;

    LoadingCard(Activity activity){
        mActivity = activity;
    }

    public void startLoading() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.mainloading, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void stopLoading() {
        dialog.dismiss();
    }
}
