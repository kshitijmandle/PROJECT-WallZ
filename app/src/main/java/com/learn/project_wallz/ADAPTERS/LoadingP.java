package com.learn.project_wallz.ADAPTERS;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.learn.project_wallz.R;

public class LoadingP {
    Activity activity;
    AlertDialog alertDialog;
    public LoadingP(Activity activity){
           this.activity = activity;

    }
    public void startloading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progressbar,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void loadstop(){
        alertDialog.dismiss();
    }
}
