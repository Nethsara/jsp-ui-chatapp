package me.siyum.client;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloController {
    public TextArea messagePreview;
    public TextField messageText;

    ServerSocket serverSocket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    BufferedReader bufferedReader;
    Socket socket;

    public void initialize() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket("localhost", 5001);
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                String message;
                try {
                    message = dataInputStream.readUTF();
                    messagePreview.appendText("Client : " + message + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }


    public void sendMessage() {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String msg = messageText.getText();
            messagePreview.appendText("Me : " + msg + "\n");
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}