package com.lxins.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by pc on 2019/12/27.
 */

public class MyBRReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Network state changed.", Toast.LENGTH_SHORT).show();
    }
}
