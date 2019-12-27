package com.lxins.httpurlconnectiondemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Created by pc on 2019/12/27.
 */

public class GetData {
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("URL request failure");
        }

        InputStream inputStream = conn.getInputStream();
        byte[] bytes = StreamTool.read(inputStream);
        inputStream.close();
        return bytes;
    }

    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            byte[] data = StreamTool.read(inputStream);

            return new String(data, "UTF-8");
        }

        return null;
    }
}
