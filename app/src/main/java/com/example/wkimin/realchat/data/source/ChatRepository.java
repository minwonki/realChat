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
    public void addMsg(String name, String msg) {
        chatRemoteDataSource.addChatMsg(name, msg);
    }

    @Override
    public void getMsg(addChatCallback addChatCallback) {
        // TODO : online, offline  상태 구분해서 기능 개발 필요.
        //  offline 경우에  Local Database 데이터 가져 오기
        if (!chatRemoteDataSource.isOnline()) {
            chatLocalDataSource.getAllMessage(addChatCallback);
        }
        chatRemoteDataSource.getChatMsg(addChatCallback);
    }
}
