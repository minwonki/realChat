package com.example.wkimin.realchat.chat;

import com.example.wkimin.realchat.BasePresenter;
import com.example.wkimin.realchat.BaseView;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class ChatContract {
    interface View extends BaseView<Presenter> {
        void refreshChat();
        void showMessage();
    }

    interface Presenter extends BasePresenter {
        void sendMessage();
    }
}
