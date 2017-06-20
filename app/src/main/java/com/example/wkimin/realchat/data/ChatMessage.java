package com.example.wkimin.realchat.data;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatMessage {
    private String userName;
    private String userMessage;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String userMessage) {
        this.userName = userName;
        this.userMessage = userMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
