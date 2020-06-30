package com.example.webapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final TextView mTextView = findViewById(R.id.text);

        String URL = "http://192.168.0.108:45455/api/assignment/test/8";

//        JsonObjectRequest objectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                URL,
//                null,
//                new Response.Listener<JSONObject>()
//                {
//                    @Override
//                    public void onResponse(JSONObject response)
//                    {
//                        Log.e("Rest Response",response.toString());
//                        TextView t = findViewById(R.id.text);
//                        t.setText(response.toString());
//                        Toast.makeText(MainActivity.this, "Response : "+response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        Log.e("Error : ", error.toString());
//                        Toast.makeText(MainActivity.this, "Error : "+error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );

//        requestQueue.add(objectRequest);

        URL ="http://192.168.0.108:45455/api/assignment/getcourses/8";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try
                        {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++)
                            {
                                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                                // Get current json object
                                JSONObject assignment = response.getJSONObject(i);

                                // Get the current student (json object) data
                                int courseID = assignment.getInt("CourseID");
                                int courseOfferedID = assignment.getInt("CourseOfferedID");
                                String courseName = assignment.getString("CourseName");
                                String courseCode= assignment.getString("CourseCode");


//                                String resubmissionAllowed = assignment.getString("ReSubmissionAllowed");
//                                int fsid = assignment.getInt("FSID");
                                // Display the formatted json data in text view
                                mTextView.append("Course ID : "+courseID);
//                                mTextView.append("Assignment ID :  "+cour+"\nAssignmentName"+assignmentName
//                                        + "\nAttachmentLink : " + attachmentLink+"\nDueDateTime : "+date+"\nReSubmissionAllowed : "
//                                        +resubmissionAllowed+"\nFSID : "+fsid);
                                mTextView.append("\n\n");
                            }
                        } catch (JSONException e)
                        {
                            Toast.makeText(MainActivity.this, "ParseException : "+e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
