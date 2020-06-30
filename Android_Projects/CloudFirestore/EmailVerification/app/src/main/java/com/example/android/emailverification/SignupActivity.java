package com.example.android.emailverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity
{
    EditText emailId,password;
    Button signup;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailId = findViewById(R.id.signupEmail);
        password=findViewById(R.id.signupPassword);
        signup=findViewById(R.id.signupSubmit);
        mFirebaseAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addUser(view);
            }
        });

    }

    void addUser(View view)
    {
        String em = emailId.getText().toString();
        String pwd = password.getText().toString();
        final User user = new User(em,pwd);

        if(em.isEmpty())
        {
            emailId.setError("Please enter email id");
            emailId.requestFocus();
        }
        else if(pwd.isEmpty())
        {
            password.setError("Please enter password!");
            password.requestFocus();
        }
        else if(em.isEmpty() && pwd.isEmpty())
        {
            Toast.makeText(SignupActivity.this,"Fields are empty",Toast.LENGTH_SHORT);
        }
        else if(!(em.isEmpty() && pwd.isEmpty()))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(em,pwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        mFirebaseAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(SignupActivity.this,
                                            "Verify email!", //ADD THIS
                                            Toast.LENGTH_SHORT).show();
                                    emailId.setText("");
                                    password.setText("");
                                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(SignupActivity.this,
                                            "Signup unsuccessful : " + task.getException().getMessage(), //ADD THIS
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this,
                                "Signup unsuccessful: " + task.getException().getMessage(), //ADD THIS
                                Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
        else
        {
            Toast.makeText(SignupActivity.this,"Error Occurred!", Toast.LENGTH_SHORT).show();
        }



    }

}