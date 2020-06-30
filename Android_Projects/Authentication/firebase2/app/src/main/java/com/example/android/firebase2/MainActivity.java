package com.example.android.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView login = (TextView) findViewById(R.id.loginButton);
        TextView signup = (TextView) findViewById(R.id.signupButton);
        TextView vLogin = (TextView) findViewById(R.id.vLoginButton);
        TextView vSignup = (TextView) findViewById(R.id.vSignupButton);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openLogin(view);
            }
        });
        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openSignup(view);
            }
        });
        vLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openVolunteerSignin(view);
            }
        });
        vSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openVolunteerSignup(view);
            }
        });

    }

    void openLogin(View view)
    {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
    void openSignup(View view)
    {
        Intent i = new Intent(this,Signup.class);
        startActivity(i);
    }
    void openVolunteerSignup(View view)
    {
        Intent i = new Intent(this,VolunteerSignup.class);
        startActivity(i);
    }
    void openVolunteerSignin(View view)
    {
        Intent i = new Intent(this,VolunteerSignin.class);
        startActivity(i);
    }
}
