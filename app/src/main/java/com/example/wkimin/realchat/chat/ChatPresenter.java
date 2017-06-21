package com.example.wkimin.realchat.chat;

import com.example.wkimin.realchat.data.source.ChatRepository;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Iterator;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View chatView;
    private ChatRepository chatRepository;

    ChatPresenter(ChatContract.View chatActivity, ChatRepository instance) {
        this.chatView = chatActivity;
        this.chatRepository = instance;
        this.chatView.setPresenter(this);
        setChildEvent();
    }

    @Override
    public void setChildEvent() {

        chatRepository.getFireBaseRoot().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void append_chat(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            String chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            String chat_name = (String) ((DataSnapshot)i.next()).getValue();
            chatView.appendChat(chat_name, chat_msg);
        }
    }

    @Override
    public void sendMessage(String userName, String userMsg) {
        chatRepository.sendMsg(userName, userMsg);
    }
}
