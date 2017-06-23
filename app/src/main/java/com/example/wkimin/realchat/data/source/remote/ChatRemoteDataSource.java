package com.example.wkimin.realchat.data.source.remote;

import android.util.Log;

import com.example.wkimin.realchat.data.ChatMessage;
import com.example.wkimin.realchat.data.source.ChatDataSource;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public class ChatRemoteDataSource implements ChatDataSource.Remote {

    private final String TAG = getClass().getSimpleName();
    private static ChatRemoteDataSource INSTANCE;
    private final DatabaseReference fireBaseRef;
    private boolean isOnline = false;

    private ChatRemoteDataSource() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        fireBaseRef = database.getReference().child("Android");
        checkOnline();
    }

    private void checkOnline() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("connected");
                    isOnline = true;
                } else {
                    isOnline = false;
                    System.out.println("not connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
    }

    public static ChatRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getChatMsg(final addChatCallback addChatCallback) {
        Log.i(TAG,"getChatMsg:");
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
                String msgKey = dataSnapshot.getKey();
                while (i.hasNext()) {
                    String chat_msg = (String) ((DataSnapshot)i.next()).getValue();
                    String chat_name = (String) ((DataSnapshot)i.next()).getValue();
                    ChatMessage chatMessage = new ChatMessage(msgKey, chat_name, chat_msg);
                    addChatCallback.onChatAdd(chatMessage);
                }
            }
        });
    }

    @Override
    public void addChatMsg(String name, String msg) {
        Log.i(TAG,"addChatMsg:"+name+":"+msg);
        Map<String, Object> temp_map = new HashMap<>();
        String temp_key = fireBaseRef.push().getKey();
        fireBaseRef.updateChildren(temp_map);

        DatabaseReference msg_root = fireBaseRef.child(temp_key);
        Map<String, Object> msg_map = new HashMap<>();
        msg_map.put("name", name);
        msg_map.put("msg", msg);
        msg_root.updateChildren(msg_map);
    }

    @Override
    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public void addMsg(String name, String msg) {
        addChatMsg(name, msg);
    }

    @Override
    public void getMsg(addChatCallback addChatCallback) {
        getChatMsg(addChatCallback);
    }
}
