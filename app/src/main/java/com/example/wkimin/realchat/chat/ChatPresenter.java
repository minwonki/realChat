package com.example.wkimin.realchat.chat;

import com.example.wkimin.realchat.data.ChatMessage;
import com.example.wkimin.realchat.data.source.ChatDataSource;
import com.example.wkimin.realchat.data.source.ChatRepository;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View chatView;
    private ChatDataSource chatRepository;

    ChatPresenter(ChatContract.View chatActivity, ChatRepository instance) {
        this.chatView = chatActivity;
        this.chatRepository = instance;
        this.chatView.setPresenter(this);
    }

    @Override
    public void setChildEvent() {
        chatRepository.getMsg(new ChatDataSource.addChatCallback(){
            @Override
            public void onChatAdd(ChatMessage chatMessage) {
                chatView.appendChat(chatMessage.getName(), chatMessage.getMessage());
            }
        });
    }

    @Override
    public void sendMessage(String userName, String userMsg) {
        chatRepository.addMsg(userName, userMsg);
    }
}
