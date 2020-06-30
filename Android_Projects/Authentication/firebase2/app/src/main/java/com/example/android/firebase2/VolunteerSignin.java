package com.example.android.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VolunteerSignin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signin);

        Button signupSubmit = (Button) findViewById(R.id.vSigninSubmit);

        signupSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openVolunteerHome(view);
            }
        });
    }

    void openVolunteerHome(View v)
    {
        Intent i = new Intent(this,VolunteerHome.class);
        startActivity(i);
    }
}
