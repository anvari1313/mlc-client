package com.mlc.client;

import java.awt.*;

/**
 * Created by ahmad on 7/1/16.
 */
public class MLCClient {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatForm chatForm = new ChatForm();
                chatForm.setVisible(true);

                SocketListener socketListener = new SocketListener();
                chatForm.setSocketListener(socketListener);
                socketListener.setChatForm(chatForm);

            }
        });
    }
}
