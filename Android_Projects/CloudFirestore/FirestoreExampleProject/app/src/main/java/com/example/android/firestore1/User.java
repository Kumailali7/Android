package com.example.android.firestore1;

public class User
{
    public String name, email, phone,password;

    public User()
    {

    }

    public User(String name, String phone, String email,String password)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password=password;
    }
}