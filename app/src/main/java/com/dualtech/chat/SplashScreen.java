package com.dualtech.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class SplashScreen extends Activity {
    public static final String PROPERTY_REG_ID = "registration_id";
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make sure the app is registered with GCM and with the server
       prefs = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        setContentView(R.layout.main);


        gcm = GoogleCloudMessaging.getInstance(this);
        ApplicationInit.setREGISTRATION_KEY(prefs.getString(PROPERTY_REG_ID, null));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openMain = new Intent("com.dualtech.chat.MAINACTIVITY");
                // If there is no registration ID, the app isn't registered.
                    if (ApplicationInit.getREGISTRATION_KEY()== null){
                        openMain = new Intent("com.dualtech.chat.REG");
                        }
                    startActivity(openMain);
                }
            }
        };
        timer.start();
    }

}
