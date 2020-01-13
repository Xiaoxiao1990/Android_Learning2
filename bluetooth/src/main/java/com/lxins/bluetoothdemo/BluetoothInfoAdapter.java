package com.lxins.bluetoothdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 2019/12/24.
 */

public class BluetoothInfoAdapter extends ArrayAdapter<BtInfo> {
    private int resourceId;

    public BluetoothInfoAdapter(Context context, int textViewResourceId, List<BtInfo> btInfos) {
        super(context, textViewResourceId, btInfos);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BtInfo btInfo = getItem(position);//获取当前项的Weather实例
        //LayoutInflater的inflate()方法接收3个参数：需要实例化布局资源的id、ViewGroup类型视图组对象、false
        //false表示只让父布局中声明的layout属性生效，但不会为这个view添加父布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        //获取实例
        TextView name = view.findViewById(R.id.bt_name);
        TextView addr = view.findViewById(R.id.bt_address);
        //设置图片和文字
        name.setText(btInfo.getName());
        addr.setText(btInfo.getAddr());
        return view;
    }
}
