package com.example.android.chatapplicatio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button openList,sendButton;
    private EditText newMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        newMessage = findViewById(R.id.newMessage);
        sendButton = findViewById(R.id.messageSend);

        final ArrayList<ChatMessage> list = new ArrayList<ChatMessage>();

        list.add(new ChatMessage("Adnan","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Anas","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Berry","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Faraz","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Hamza Bhai","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Kumail","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Okesh","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Saad","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Shakeeb","Hum pagal hein pagal!","11/29/2019"));
        list.add(new ChatMessage("Usama","Hum pagal hein pagal!","11/29/2019"));
        final ChatMessageAdapter listAdapter = new ChatMessageAdapter(this,list,R.color.darkblue);
        final ListView listView = (ListView)findViewById(R.id.messageList);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String msg = newMessage.getText().toString();
                list.add(new ChatMessage("kumail",msg,"11/29/2019"));
                listView.setAdapter(listAdapter);
            }
        });


        listView.setAdapter(listAdapter);

    }
}
