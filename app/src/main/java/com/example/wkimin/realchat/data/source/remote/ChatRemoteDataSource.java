package com.example.wkimin.realchat.data.source.remote;

import android.util.Log;

import com.example.wkimin.realchat.data.ChatMessage;
import com.example.wkimin.realchat.data.source.ChatDataSource;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public class ChatRemoteDataSource implements ChatDataSource {

    private final String TAG = getClass().getSimpleName();
    private static ChatRemoteDataSource INSTANCE;
    private final DatabaseReference fireBaseRef;

    private ChatRemoteDataSource() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        fireBaseRef = database.getReference().child("Android");
    }

    public static ChatRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getChatEvent(final addChatCallback addChatCallback) {
        Log.i(TAG,"getChatEvent:");
        fireBaseRef.addChildEventListener(new ChildEventListener() {
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

            private void append_chat(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    String chat_msg = (String) ((DataSnapshot)i.next()).getValue();
                    String chat_name = (String) ((DataSnapshot)i.next()).getValue();
                    ChatMessage chatMessage = new ChatMessage(chat_name, chat_msg);
                    addChatCallback.onChatAdd(chatMessage);
                }
            }
        });
    }

    @Override
    public void sendMsg(String name, String msg) {
        Log.i(TAG,"sendMsg:"+name+":"+msg);
        Map<String, Object> temp_map = new HashMap<>();
        String temp_key = fireBaseRef.push().getKey();
        fireBaseRef.updateChildren(temp_map);

        DatabaseReference msg_root = fireBaseRef.child(temp_key);
        Map<String, Object> msg_map = new HashMap<>();
        msg_map.put("name", name);
        msg_map.put("msg", msg);
        msg_root.updateChildren(msg_map);
    }
}
