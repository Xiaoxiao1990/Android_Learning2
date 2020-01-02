package com.lxins.socketdemo;

import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextIP;
    private EditText editTextPort;
    private EditText sendMsg;
    private TextView receiveMsg;

    private String serverIP;
    private int serverPort;
    private SocketClient socketClient;
    private boolean connection_state = false;
    private Button connect_btn;
    private Button send_btn;
    private CheckBox hex_or_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_btn:
                if (connection_state) {
                    Toast.makeText(MainActivity.this, "Server connected!", Toast.LENGTH_SHORT).show();
                    socketClient.disconnect();
                    connect_btn.setText(R.string.connect_btn);
                    connection_state = false;
                } else {

                    socketClient.connect();
                    Toast.makeText(MainActivity.this, "Server[" + serverIP + ":" + serverPort + "]", Toast.LENGTH_SHORT).show();
                    connection_state = true;
                    connect_btn.setText(R.string.disconnect_btn);
                }
                break;
            case R.id.send_btn:
                if (connection_state) {
                    socketClient.send("OK!");
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Do not connected!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hex_or_string:
                if (hex_or_string.isChecked()) {
                    Toast.makeText(MainActivity.this, "Hex", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "String", Toast.LENGTH_SHORT).show();
                }

                sendMsg.setSelection(sendMsg.getText().length());
                break;
        }
    }

    private void initViews() {
        editTextIP = findViewById(R.id.server_ip_etxt);
        editTextIP.setSelection(editTextIP.getText().length());
        serverIP = editTextIP.getText().toString();
        editTextIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                serverIP = editTextIP.getText().toString();
            }
        });

        editTextPort = findViewById(R.id.server_port_etxt);
        serverPort = Integer.parseInt(editTextPort.getText().toString());
        editTextPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                serverPort = Integer.parseInt(editTextPort.getText().toString());
            }
        });


        connect_btn = findViewById(R.id.connect_btn);
        if (connection_state) {
            connect_btn.setText(R.string.disconnect_btn);
        } else {
            connect_btn.setText(R.string.connect_btn);
        }

        socketClient = new SocketClient(serverIP, serverPort, MainActivity.this);

        connect_btn.setOnClickListener(this);

        send_btn = findViewById(R.id.send_btn);
        send_btn.setOnClickListener(this);

        hex_or_string = findViewById(R.id.hex_or_string);
        hex_or_string.setOnClickListener(this);

        sendMsg = findViewById(R.id.send_msg);
        sendMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        receiveMsg = findViewById(R.id.receive_msg);
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        //String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        String regEx = "[^a-fA-F0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
