package com.dualtech.chat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jesz on 18-Jul-15.
 */
public class ContactView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("This is Contact tab");
        setContentView(R.layout.contact_list);
    }
}
