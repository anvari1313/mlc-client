package com.mlc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import com.mlc.server.Message;

/**
 * Created by ahmad on 7/1/16.
 */
public class SocketListener extends Thread {
    private Socket socket;
    private int port = 6066;
    private boolean isClientConneted;
    private ChatForm chatForm;
    private byte[] serverAddress;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public SocketListener(){
        serverAddress = new byte[]{(byte)192,(byte)168,(byte)131,(byte)243};
        try {
            socket = new Socket(InetAddress.getByAddress(serverAddress),port);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        isClientConneted = true;
        start();
    }

    @Override
    public void run() {
        while (isClientConneted){
            com.mlc.server.Message recievedMessage;
            try {
                recievedMessage = ((Message) input.readObject());
                chatForm.recieveMessage(recievedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        try {
            output.writeObject(new Message(message,"Ahmad"));
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChatForm(ChatForm chatForm){
        this.chatForm = chatForm;
    }

    public void closeConnection(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
