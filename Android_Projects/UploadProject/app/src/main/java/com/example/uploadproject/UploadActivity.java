package com.example.uploadproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class UploadActivity extends AppCompatActivity
{
    StorageReference storageReference;
    DatabaseReference databaseReference;

    EditText editText;
    String name="";

    FirebaseFirestore db;
    DocumentReference uploadsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editText=findViewById(R.id.text2);
        name= editText.getText().toString();

        Button uploadBtn = findViewById(R.id.uploadBtn);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        db = FirebaseFirestore.getInstance();
        uploadsRef = db.collection("Teacher").document("uploads"+System.currentTimeMillis());

        uploadBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectPdf();
            }
        });

    }

    private void selectPdf()
    {
        Toast.makeText(this, "Select PDF", Toast.LENGTH_SHORT).show();
        Intent i  = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select PDF File"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        Toast.makeText(this, "OnActivityResult", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            uploadPDFFile(data.getData());
        }
        else
        {
            if(requestCode!=1)
            {
                Toast.makeText(this, "Request code!=1", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode!=RESULT_OK)
            {
                Toast.makeText(this, "Result code!=RESULT_OK", Toast.LENGTH_SHORT).show();
            }
            else if(data==null)
            {
                Toast.makeText(this, "Data == null", Toast.LENGTH_SHORT).show();
            }
            else if(data.getData()==null)
            {
                Toast.makeText(this, "data.getData == null", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadPDFFile(Uri data)
    {
        Toast.makeText(this, "uploadpdf file", Toast.LENGTH_SHORT).show();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploads/"+System.currentTimeMillis()+".pdf");

        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete());
                Uri url = uri.getResult();

                final uploadPDF uploadPDF = new uploadPDF(name,url.toString());

                databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);

                uploadsRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        uploadsRef.set(uploadPDF);
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                    }
                });

                Toast.makeText(UploadActivity.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot)
            {
                double progress =(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();

                progressDialog.setMessage("Uploaded : "+(int)progress+"%");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadActivity.this, "uploadPDFFIle failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}