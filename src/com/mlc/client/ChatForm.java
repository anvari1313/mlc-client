package com.mlc.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.mlc.server.Message;

/**
 * Created by ahmad on 7/1/16.
 */
public class ChatForm extends JFrame {
    private JTextArea chatListText;
    private JTextField messageTextField;

    private SocketListener socketListener;

    public ChatForm(){
        setSize(450,600);
        setLayout(new BorderLayout());
        setTitle("MyLittleChatter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatListText = new JTextArea("");
        chatListText.setEditable(false);
        messageTextField = new JTextField("");

        add(chatListText,BorderLayout.CENTER);
        add(messageTextField,BorderLayout.SOUTH);
        setKeyListener();
        setWindowListener();
    }

    private void setWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                socketListener.closeConnection();
            }
        });
    }

    private void setKeyListener(){
        messageTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    socketListener.sendMessage(messageTextField.getText());
                    messageTextField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public void recieveMessage(Message message){
        chatListText.setText(chatListText.getText() + "\n" + message.getSayerName() + " : " + message.getText());
    }

    public void setSocketListener(SocketListener socketListener) {
        this.socketListener = socketListener;
    }
}
