package com.lxins.socketdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by pc on 2020/1/2.
 */

public class SocketClient {
    private static final String TAG = "SocketClient";
    private String host;
    private int port;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private AppCompatActivity uiAcitivity;

    public SocketClient(String host, int port, AppCompatActivity uiAcitivty) {
        this.host = host;
        this.port = port;
        this.uiAcitivity = uiAcitivty;
        mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
    }

    public void connect() {
        mExecutorService.execute(new connectService());
    }

    public void send(String sendMsg) {
        mExecutorService.execute(new sendService(sendMsg));
    }

    public void disconnect() {
        mExecutorService.execute(new sendService("0"));
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(host, port);
                //socket.setSoTimeout(10000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = bufferedReader.readLine()) != null) {
                    Log.d(TAG, "receiveMsg: " + receiveMsg);
                    uiAcitivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = uiAcitivity.findViewById(R.id.receive_msg);
                            textView.setText(receiveMsg);
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}
