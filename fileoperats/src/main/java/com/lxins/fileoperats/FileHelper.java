package com.lxins.fileoperats;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by pc on 2019/12/27.
 */

public class FileHelper {
    private Context mContext;

    public FileHelper() {

    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void save(String filename, String content) throws Exception {
        FileOutputStream outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    public String read(String filename) throws Exception {
        FileInputStream inputStream =mContext.openFileInput(filename);
        byte[] bytes = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder("");
        int len = 0;

        while ((len = inputStream.read(bytes)) > 0) {
            stringBuilder.append(new String(bytes, 0, len));
        }

        inputStream.close();

        return stringBuilder.toString();
    }
}
