package com.production.hometech.jsonparsingdemo;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Arpit on 17-May-17.
 */

public class MyApplication extends Application {

   static ProgressDialog progressDialog;

    public static void showProgress(Context context){

        progressDialog = new ProgressDialog(context , R.style.AppTheme);
        progressDialog.setMessage("Please wait....");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

    }
    public static void stopProgress(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
