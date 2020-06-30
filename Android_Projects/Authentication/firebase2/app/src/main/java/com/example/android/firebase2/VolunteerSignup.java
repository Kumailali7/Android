package com.example.android.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VolunteerSignup extends AppCompatActivity
{
    EditText phoneNumber, vCode;
    FirebaseAuth mFirebaseAuth;
    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signup);

//        phoneNumber=findViewById(R.id.vPhoneNumber);
//        vCode=findViewById(R.id.verificationCode);
//        mFirebaseAuth = FirebaseAuth.getInstance();
//
//        findViewById(R.id.vVerificationCodeSubmit).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                sendVerificationCode();
//            }
//        });
//
//        findViewById(R.id.vSignupSubmit).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                verifySignupCode();
//            }
//        });
//
    }
//
//    void sendVerificationCode()
//    {
//        String number = phoneNumber.getText().toString();
//        if(number.isEmpty())
//        {
//            phoneNumber.setError("Enter Phone Number");
//            phoneNumber.requestFocus();
//            return;
//        }
////        if(number.length()<11)
////        {
////            phoneNumber.setError("Enter Correct Phone Number");
////            phoneNumber.requestFocus();
////            return;
////        }
//        else
//        {
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    number,        // Phone number to verify
//                    60,                 // Timeout duration
//                    TimeUnit.SECONDS,   // Unit of timeout
//                    this,               // Activity (for callback binding)
//                    mCallbacks);
//        }
//    }
//
//
//    void verifySignupCode()
//    {
//        String code = vCode.getText().toString();
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent,code);
//        signInWithPhoneAuthCredential(credential);
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
//    {
//        mFirebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
//                {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task)
//                    {
//                        if (task.isSuccessful())
//                        {
//                            Toast.makeText(VolunteerSignup.this,"Login Successful", Toast.LENGTH_LONG).show();
//                            Intent i = new Intent(VolunteerSignup.this,VolunteerSignin.class);
//                            startActivity(i);
//                        }
//                        else
//                            {
//                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                    Toast.makeText(VolunteerSignup.this,"Login Successful", Toast.LENGTH_LONG).show();
//                                }
//                        }
//                    }
//                });
//    }
//
//    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
//        {
//
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e)
//        {
//
//        }
//
//        @Override
//        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            codeSent = s;
//        }
//    };
//
//    void openVolunteerLogin(View v)
//    {
//        Intent i = new Intent(this,VolunteerSignin.class);
//        startActivity(i);
//    }

}
