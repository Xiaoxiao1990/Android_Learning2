package com.lxins.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int ENABLE_BT_REQ_CODE = 0;
    private static final int BLUETOOTH_STATUS_OPEN = 1;
    private static final int BLUETOOTH_STATUS_CLOSE = 0;
    private Button open_close;
    private int open_close_btn_state;
    private BluetoothAdapter bluetoothAdapter;
    private Button scan;
    private ListView listView;
    private ArrayList<BtInfo> btInfos = new ArrayList<>();
    private BluetoothInfoAdapter list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        open_close = findViewById(R.id.open_close_btn);
        scan = findViewById(R.id.scan_btn);
        listView = findViewById(R.id.bt_list);
        list_adapter = new BluetoothInfoAdapter(MainActivity.this, R.layout.my_listview, btInfos);
        listView.setAdapter(list_adapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (null == bluetoothAdapter) {
            Toast.makeText(this,  "Your device do not support Bluetooth!", Toast.LENGTH_LONG).show();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                open_close.setText(R.string.open_close_open);
                open_close_btn_state = BLUETOOTH_STATUS_CLOSE;
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, ENABLE_BT_REQ_CODE);
            } else {
                open_close.setText(R.string.open_close_close);
                open_close_btn_state = BLUETOOTH_STATUS_OPEN;
            }

            //open_close.setOnClickListener(MainActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ENABLE_BT_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Bluetooth enabled!", Toast.LENGTH_SHORT).show();
                    open_close.setText(R.string.open_close_close);
                    open_close_btn_state = BLUETOOTH_STATUS_OPEN;
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Bluetooth disabled!", Toast.LENGTH_SHORT).show();
                    open_close.setText(R.string.open_close_open);
                    open_close_btn_state = BLUETOOTH_STATUS_CLOSE;
                } else
                    Toast.makeText(this, "Unexpected status!", Toast.LENGTH_SHORT).show();

                break;
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }

    public void OpenCloseClick(View view) {
        switch (view.getId()) {
            case R.id.open_close_btn:
                if (open_close_btn_state == BLUETOOTH_STATUS_OPEN) {
                    if (bluetoothAdapter.disable()) {
                        open_close_btn_state = BLUETOOTH_STATUS_CLOSE;
                        open_close.setText(R.string.open_close_open);
                    }
                } else {
                    if (bluetoothAdapter.enable()) {
                        open_close_btn_state = BLUETOOTH_STATUS_OPEN;
                        open_close.setText(R.string.open_close_close);
                    }
                }
        }
    }

    public void QueryPairedBT(View view) {
        //btInfos.clear();

//        Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
//        if (pairedDevice.size() <= 0) {
//            Toast.makeText(this, "No paired device.", Toast.LENGTH_SHORT).show();
//
//        } else {
//            for (BluetoothDevice bluetoothDevice : pairedDevice) {
//                btInfos.add(new BtInfo(bluetoothDevice.getName(), bluetoothDevice.getAddress()));
//
//            }
//        }


        //list_adapter.notifyDataSetInvalidated();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver(){
        public void onReceive(Context context,Intent intent){
            String action = intent.getAction();
            // 当 Discovery 发现了一个设备
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                // 从 Intent 中获取发现的 BluetoothDevice
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 将名字和地址放入要显示的适配器中
                list_adapter.add(new BtInfo(device.getName() , device.getAddress()));
            }
        }
    };
    // 注册这个 BroadcastReceiver
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    //registerReceiver(mReiver,filter);

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
