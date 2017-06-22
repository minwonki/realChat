package com.example.wkimin.realchat.chat;

import com.example.wkimin.realchat.BasePresenter;
import com.example.wkimin.realchat.BaseView;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

class ChatContract {
    interface View extends BaseView<Presenter> {
        void appendChat(String chat_name, String chat_msg);
    }

    interface Presenter extends BasePresenter {
        void sendMessage(String userName, String userMsg);
    }
}
