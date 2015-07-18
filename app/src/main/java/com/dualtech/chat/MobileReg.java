package com.dualtech.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class MobileReg extends Activity implements View.OnClickListener {

    public static final String PROPERTY_REG_ID = "registration_id";
    EditText mobileNum;
    TextView etmsg;
    Button reg_next;
    String regId;
    GoogleCloudMessaging gcm;
    SharedPreferences prefs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        setContentView(R.layout.registration);
        mobileNum = (EditText) findViewById(R.id.phone_num);
        reg_next = (Button) findViewById(R.id.bt_next);
        etmsg = (TextView)findViewById(R.id.msg);
        reg_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                ApplicationInit.setMobile_number(String.valueOf(mobileNum.getText()));
                // If there is no registration ID, the app isn't registered.
                getRegId();
                Intent openMain = new Intent("com.dualtech.chat.MAINACTIVITY");
                startActivity(openMain);

        }
    }
    String msg;
    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    regId = gcm.register(ApplicationInit.getProjectNO());
                    ApplicationInit.setREGISTRATION_KEY(regId);// Save the regid for future use - no need to register again.
                    Log.d("in async task", regId);

                    // try
                    msg = "Device registered, registration ID=" + regId;

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(PROPERTY_REG_ID, regId);
                    editor.apply();

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }
            @Override
             protected void onPostExecute(String msg) {
                etmsg.setText(msg + "\n");
            }
        }.execute();
    }
}
