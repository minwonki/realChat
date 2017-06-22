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
        setChildEvent();
    }

    @Override
    public void setChildEvent() {
        chatRepository.getChatEvent(new ChatDataSource.addChatCallback(){
            @Override
            public void onChatAdd(ChatMessage chatMessage) {
                chatView.appendChat(chatMessage.getUserName(), chatMessage.getUserMessage());
            }
        });
    }

    @Override
    public void sendMessage(String userName, String userMsg) {
        chatRepository.sendMsg(userName, userMsg);
    }
}
