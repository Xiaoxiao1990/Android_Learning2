package com.lxins.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by pc on 2019/12/27.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "android.intent.action.AIRPLANE_MODE";

    @Override
    public void onReceive(Context context, Intent intent) {
        //if (ACTION_BOOT.equals(intent.getAction())) {
            Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        //}
    }
}
