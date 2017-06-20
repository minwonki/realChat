package com.example.wkimin.realchat.data.source;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatRepository {
    private static ChatRepository INSTANCE = null;
    private final Realm realm;

    public ChatRepository() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();
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
}
