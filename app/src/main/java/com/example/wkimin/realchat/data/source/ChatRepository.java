package com.example.wkimin.realchat.data.source;

import com.example.wkimin.realchat.data.source.local.ChatLocalDataSource;
import com.example.wkimin.realchat.data.source.remote.ChatRemoteDataSource;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatRepository implements ChatDataSource {
    private static ChatRepository INSTANCE = null;
    private final ChatLocalDataSource chatLocalDataSource;
    private final ChatRemoteDataSource chatRemoteDataSource;

    private ChatRepository() {
        chatLocalDataSource = ChatLocalDataSource.getInstance();
        chatRemoteDataSource = ChatRemoteDataSource.getInstance();
    }

    public static ChatRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getChatEvent(final addChatCallback addChatCallback) {
        chatRemoteDataSource.getChatEvent(addChatCallback);
        chatLocalDataSource.getChatEvent(addChatCallback);
    }

    @Override
    public void sendMsg(String name, String msg) {
        chatRemoteDataSource.sendMsg(name, msg);
        chatLocalDataSource.sendMsg(name, msg);
    }
}
