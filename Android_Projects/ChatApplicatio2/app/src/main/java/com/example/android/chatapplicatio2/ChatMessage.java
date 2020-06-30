package com.example.android.chatapplicatio2;

public class ChatMessage
{
    String name,message,timeStamp;

    public ChatMessage()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ChatMessage(String name, String msg, String timeStamp)
    {
        this.name=name;
        this.message=msg;
        this.timeStamp=timeStamp;
    }

}
