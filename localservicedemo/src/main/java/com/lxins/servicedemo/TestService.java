package com.lxins.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pc on 2020/1/10.
 */

public class TestService extends Service {
    private final static String TAG = "TestService";
    private int count;
    private boolean quit;

    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind called.");
        return binder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate called.");
        super.onCreate();

        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind called.");
        return true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand called.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy called.");
        super.onDestroy();
        this.quit = true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind called.");
        super.onRebind(intent);
    }
}
