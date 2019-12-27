package com.lxins.httpurlconnectiondemo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by pc on 2019/12/27.
 */

public class StreamTool {
    public static byte[] read(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        inputStream.close();

        return outputStream.toByteArray();
    }
}
