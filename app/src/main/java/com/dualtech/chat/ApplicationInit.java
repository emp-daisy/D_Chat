package com.dualtech.chat;

import android.app.Application;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class ApplicationInit extends Application {

    private static final String API_KEY = "AIzaSyDZ60w-JN-RzBHk1litPqzKtzqThmZnpaY";
    private static final String PROJECT_ID = "dual-digital-000";
    private static final String PROJECT_NO = "25515784135";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_MOB_ID = "mobile_number";
    public static final String SHARED_PREF = "SharedPref";
    private static String REGISTRATION_KEY;

    private static String mobile_number;

    public static String getApi(){
        return API_KEY;
    }

    public static String getProjectNO(){ return PROJECT_NO;}

    public static void setMobile_number(String m){
        mobile_number = m;
    }

    public static String getMobile_number(){
        return mobile_number;
    }

    public static void setREGISTRATION_KEY(String r){
        REGISTRATION_KEY  = r;
    }

    public static String getREGISTRATION_KEY(){
        return REGISTRATION_KEY;
    }
}
