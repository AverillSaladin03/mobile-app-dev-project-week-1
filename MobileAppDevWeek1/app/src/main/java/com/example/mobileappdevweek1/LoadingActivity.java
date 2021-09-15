package com.example.mobileappdevweek1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

public class LoadingActivity {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingActivity (Activity activity) {this.activity = activity; }

    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.activity_loading, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();

    }

    public void dismiss() { alertDialog.dismiss(); }

}