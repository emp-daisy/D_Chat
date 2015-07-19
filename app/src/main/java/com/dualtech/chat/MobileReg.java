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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class MobileReg extends Activity implements View.OnClickListener {

    EditText mobileNum;
    Button reg_next;
    String regId, phnNo;
    String msg;
    ProgressBar spinner;
    GoogleCloudMessaging gcm;
    SharedPreferences prefs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(ApplicationInit.SHARED_PREF, Context.MODE_PRIVATE);
        setContentView(R.layout.registration);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mobileNum = (EditText) findViewById(R.id.phone_num);
        mobileNum.setText(VerifyNumber.getNO());
        reg_next = (Button) findViewById(R.id.bt_reg);
        reg_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_reg:
                phnNo = ApplicationInit.getMobile_number();
                // If there is no registration ID, the app isn't registered.
                spinner.setVisibility(View.VISIBLE);
                getRegId();

        }
    }

    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    regId = gcm.register(ApplicationInit.getProjectNO());
                    Log.d("in async task", regId);

                    msg = "Device registered, registration ID=" + regId;

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(ApplicationInit.PROPERTY_REG_ID, regId);
                    editor.putString(ApplicationInit.PROPERTY_MOB_ID, phnNo);
                    editor.apply();

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }
            @Override
             protected void onPostExecute(String msg) {
                //execute when background app is done

                ApplicationInit.setMobile_number(phnNo);
                ApplicationInit.setREGISTRATION_KEY(regId);// Save the regid for future use - no need to register again.

                spinner.setVisibility(View.GONE);
                if (regId != null){
                    Intent openMain = new Intent("com.dualtech.chat.MAINACTIVITY");
                    startActivity(openMain);
                    finish();
                }else{
                    Toast.makeText(MobileReg.this,"Cannot activation -- Try again",Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
}
