package com.example.wkimin.realchat.data.source;

import com.example.wkimin.realchat.data.ChatMessage;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public interface ChatDataSource {
    void getChatEvent(addChatCallback addChatCallback);

    interface addChatCallback {
        void onChatAdd(ChatMessage chatMessage);
    }

    void sendMsg(String name, String msg);
}
