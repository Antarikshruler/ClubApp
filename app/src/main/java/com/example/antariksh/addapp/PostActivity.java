package com.example.antariksh.addapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
public class PostActivity extends AppCompatActivity {

    private EditText mPostClub;
    private EditText mPostEvent;
    private EditText mPostVenue;
    private EditText mPostDate;
    private EditText mPostTime;
    private EditText mPostDescription;
    private Button mSaveButton;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Club");

        mPostClub = (EditText) findViewById(R.id.editText);
        mPostEvent = (EditText) findViewById(R.id.editText2);
        mPostVenue = (EditText) findViewById(R.id.editText5);
        mPostDate = (EditText) findViewById(R.id.editText6);
        mPostTime = (EditText) findViewById(R.id.editText7);
        mPostDescription = (EditText) findViewById(R.id.editText3);
        mSaveButton = (Button) findViewById(R.id.button2);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }
    private void startPosting()
    {
        final String Club_val=mPostClub.getText().toString().trim();
        final String Event_val=mPostEvent.getText().toString().trim();
        final String Venue_val=mPostVenue.getText().toString().trim();
        final String Date_val=mPostDate.getText().toString().trim();
        final String Time_val=mPostTime.getText().toString().trim();
        final String Description_val=mPostDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(Club_val) && !TextUtils.isEmpty(Event_val) && !TextUtils.isEmpty(Venue_val) && !TextUtils.isEmpty(Date_val) && !TextUtils.isEmpty(Time_val) && !TextUtils.isEmpty(Description_val)) {

            DatabaseReference newPost = mDatabase.push();
            newPost.child("Club").setValue(Club_val);
            newPost.child("Event").setValue(Event_val);
            newPost.child("Venue").setValue(Venue_val);
            newPost.child("Date").setValue(Date_val);
            newPost.child("Time").setValue(Time_val);
            newPost.child("Description").setValue(Description_val);
        }
        Intent mainIntent=new Intent(PostActivity.this,MainActivity.class);
        startActivity(mainIntent);
    }
    }
