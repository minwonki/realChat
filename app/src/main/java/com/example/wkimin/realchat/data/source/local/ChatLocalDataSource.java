package com.example.wkimin.realchat.data.source.local;

import android.util.Log;

import com.example.wkimin.realchat.data.ChatMessage;
import com.example.wkimin.realchat.data.source.ChatDataSource;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by wkimin on 2017-06-22.
 *
 */

public class ChatLocalDataSource implements ChatDataSource.Local {

    private final String TAG = getClass().getSimpleName();
    private static ChatLocalDataSource INSTANCE;
    private final Realm realm;

    private ChatLocalDataSource() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
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
    public void saveLocalDatabase(final ChatMessage chatMessage) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(chatMessage);
            }
        });
    }

    @Override
    public void getAllMessage(addChatCallback addChatCallback) {
        RealmResults<ChatMessage> results = realm.where(ChatMessage.class).findAll();
        if (results.isLoaded()) {
            for (ChatMessage msg : results) {
                addChatCallback.onChatAdd(msg);
            }
        }
    }

    @Override
    public void addMsg(String name, String msg) {
        // Do not work!!
    }

    @Override
    public void getMsg(addChatCallback addChatCallback) {
        // Do not work!!
    }
}
