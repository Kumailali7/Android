package com.example.android.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity
{
    EditText emailId,password;
    Button signup;
    TextView signIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId= findViewById(R.id.signupEmail);
        password=findViewById(R.id.signupPassword);
        signup =findViewById(R.id.signupSubmit);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter password!");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(Signup.this,"Fields are empty",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(Signup.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task)
                        {
                            if(!task.isSuccessful())
                            {
                                //Toast.makeText(Signup.this,"SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Signup.this,
                                        "Login unsuccessful: " + task.getException().getMessage(), //ADD THIS
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(Signup.this, Login.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Signup.this,"Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void openLogin(View v)
    {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}
