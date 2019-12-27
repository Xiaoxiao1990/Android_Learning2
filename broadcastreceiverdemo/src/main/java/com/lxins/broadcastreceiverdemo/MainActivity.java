package com.lxins.broadcastreceiverdemo;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
//    MyBRReceiver myBRReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        myBRReceiver = new MyBRReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(myBRReceiver, intentFilter);
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(myBRReceiver);
//    }
}
