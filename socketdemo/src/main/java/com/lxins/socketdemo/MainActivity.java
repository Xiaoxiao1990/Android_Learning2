package com.lxins.socketdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextIP;
    private EditText editTextPort;

    private String serverIP;
    private String serverPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        editTextIP = findViewById(R.id.server_ip_etxt);
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
        serverPort = editTextPort.getText().toString();
        editTextPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                serverPort = editTextPort.getText().toString();
            }
        });

        findViewById(R.id.connect_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.connect_btn:
                        Toast.makeText(MainActivity.this, "Server[" + serverIP + ":" + serverPort + "]", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
