package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static final int PORT = 3003;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try{

            serverSocket = new ServerSocket(PORT);



            while (true){
                System.out.println("waiting for connection");
                Socket socket = serverSocket.accept();//waiting for client

                System.out.println("connection Received");
                new ClientThread(socket).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}