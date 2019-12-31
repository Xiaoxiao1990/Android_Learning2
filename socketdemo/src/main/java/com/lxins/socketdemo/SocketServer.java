package com.lxins.socketdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc on 2019/12/31.
 */

public class SocketServer {
    private static final String TAG = "SocketServer";
    private static final int PORT = 9999;
    private ArrayList<Socket> mList = new ArrayList<>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private String sendMsg;

//    public static void main(String [] args) {
//        new SocketServer();
//    }

    public SocketServer() {
        try {
            server = new ServerSocket(PORT);
            mExecutorService = Executors.newCachedThreadPool();
            System.out.println("Server started...");
            Socket client = null;

            while (true) {
                client = server.accept();
                mList.add(client);
                mExecutorService.execute(new Service(client));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter = null;

        public Service(Socket socket) {
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.println("Connected!");
                System.out.println("Connected!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (receiveMsg.equals("0")) {
                        System.out.println("Client close socket.");
                        printWriter.println("Server close socket.");
                        mList.remove(socket);
                        in.close();
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
