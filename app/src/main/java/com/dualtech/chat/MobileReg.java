package com.dualtech.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class MobileReg extends Activity implements View.OnClickListener {

    public static final String PROPERTY_REG_ID = "registration_id";
    EditText mobileNum;
    Button reg_next;
    GoogleCloudMessaging gcm;
    SharedPreferences prefs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(SplashScreen.class.getSimpleName(),
                Context.MODE_PRIVATE);
        setContentView(R.layout.registration);
        mobileNum = (EditText) findViewById(R.id.phone_num);
        reg_next = (Button) findViewById(R.id.bt_next);
        reg_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:

                ApplicationInit.setMobile_number(String.valueOf(mobileNum.getText()));

                gcm = GoogleCloudMessaging.getInstance(this);
                Intent openMain = new Intent("com.dualtech.chat.MAINACTIVITY");
                // If there is no registration ID, the app isn't registered.
                        try {
                            ApplicationInit.setREGISTRATION_KEY(gcm.register(ApplicationInit.getMobile_number()));// Save the regid for future use - no need to register again.
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString(PROPERTY_REG_ID, ApplicationInit.getREGISTRATION_KEY());
                            editor.apply();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                startActivity(openMain);

        }
    }
}
