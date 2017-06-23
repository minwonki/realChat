package com.example.wkimin.realchat.data.source;

import com.example.wkimin.realchat.data.ChatMessage;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public interface ChatDataSource {

    interface addChatCallback {
        void onChatAdd(ChatMessage chatMessage);
    }

    interface Local extends  ChatDataSource{
        void saveLocalDatabase(ChatMessage chatMessage);
        void getAllMessage(addChatCallback addChatCallback);
    }

    interface Remote extends  ChatDataSource{
        void getChatMsg(addChatCallback addChatCallback);
        void addChatMsg(String name, String msg);

        boolean isOnline();
    }

    void addMsg(String name, String msg);
    void getMsg(addChatCallback addChatCallback);
}
