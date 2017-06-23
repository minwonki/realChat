package com.example.wkimin.realchat.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatMessage extends RealmObject {
    @PrimaryKey
    private String msgKey;
    private String name;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(String msgKey, String userName, String userMessage) {
        this.msgKey = msgKey;
        this.name = userName;
        this.message = userMessage;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
