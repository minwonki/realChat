package com.example.wkimin.realchat.data.source;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatRepository {
    private static ChatRepository INSTANCE = null;
    private final Realm realm;
    private final DatabaseReference fireBaseRef;

    private ChatRepository() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        fireBaseRef = database.getReference().child("Android");
    }

    public static ChatRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void sendMsg(String userName, String userMsg) {
        Map<String, Object> temp_map = new HashMap<>();
        String temp_key = fireBaseRef.push().getKey();
        fireBaseRef.updateChildren(temp_map);

        DatabaseReference msg_root = fireBaseRef.child(temp_key);
        Map<String, Object> msg_map = new HashMap<>();
        msg_map.put("name", userName);
        msg_map.put("msg", userMsg);
        msg_root.updateChildren(msg_map);
    }

    public DatabaseReference getFireBaseRoot() {
        return fireBaseRef;
    }
}
