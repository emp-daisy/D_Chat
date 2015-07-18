package com.dualtech.chat;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class GCMClientBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //react to event

        /*if (intent.getExtras ().get("from").equals (ApplicationInit.getProjectId()){
            ComponentName comp = new ComponentName(
                    IntentService.class.getPackage().getName(),
                    IntentService.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
            // abort the broadcast
            setResultCode(Activity.RESULT_CANCEL);
        } else{
        // don't abort the broadcast
        setResultCode(Activity.RESULT_OK);
    }*/
    }

}
