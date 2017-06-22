package com.example.wkimin.realchat.data.source.local;

import android.util.Log;

import com.example.wkimin.realchat.data.source.ChatDataSource;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public class ChatLocalDataSource implements ChatDataSource {

    private final String TAG = getClass().getSimpleName();
    private static ChatLocalDataSource INSTANCE;
    private final Realm realm;

    private ChatLocalDataSource() {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    public static ChatLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getChatEvent(addChatCallback addChatCallback) {
        Log.i(TAG,"getChatEvent:");
    }

    @Override
    public void sendMsg(String name, String msg) {
        Log.i(TAG,"sendMsg:"+name+":"+msg);
    }
}
