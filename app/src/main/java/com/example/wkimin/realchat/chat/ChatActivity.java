package com.example.wkimin.realchat.chat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkimin.realchat.R;
import com.example.wkimin.realchat.data.source.ChatRepository;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private final String TAG = getClass().getSimpleName();
    private ChatContract.Presenter mPresenter;

    private EditText et_msg;
    private TextView tv_chat;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindingUI();
        createUserName();
        new ChatPresenter(this, ChatRepository.getInstance());
        Log.i(TAG, "onCreate");
    }

    private void bindingUI() {
        Button bt_send = (Button) findViewById(R.id.btn_send);
        et_msg = (EditText) findViewById(R.id.et_msg);
        tv_chat = (TextView) findViewById(R.id.tv_chat);

        bt_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userName = name;
                String userMsg = et_msg.getText().toString();
                mPresenter.sendMessage(userName, userMsg);
            }
        });
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void refreshChat() {
    }

    @Override
    public void showMessage() {
    }

    @Override
    public void appendChat(String chat_name, String chat_msg) {
        tv_chat.append(chat_name + ":" + chat_msg + "\n");
        et_msg.setText("");
    }

    private void createUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter user name:");
        final EditText input_user_name = new EditText(this);
        builder.setView(input_user_name);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input_user_name.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                createUserName();
            }
        });
        builder.show();
    }
}
