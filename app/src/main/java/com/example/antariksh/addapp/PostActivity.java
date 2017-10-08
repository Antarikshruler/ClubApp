package com.example.antariksh.addapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
public class PostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String Club = "";
    private EditText mPostClub;
    private EditText mPostEvent;
    private EditText mPostVenue;
    private EditText mPostDate;
    private EditText mPostTime;
    private EditText mPostDescription;
    private Spinner mSpinner;
    private Button mSaveButton;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mDatabase= FirebaseDatabase.getInstance().getReference();

        mSpinner = (Spinner) findViewById(R.id.club_spinner);
        mPostEvent = (EditText) findViewById(R.id.editText2);
        mPostVenue = (EditText) findViewById(R.id.editText5);
        mPostDate = (EditText) findViewById(R.id.editText6);
        mPostTime = (EditText) findViewById(R.id.editText7);
        mPostDescription = (EditText) findViewById(R.id.editText3);
        mSaveButton = (Button) findViewById(R.id.button2);
        mSpinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("EDC");
        categories.add("Nature Club");
        categories.add("TEDx");
        categories.add("Dedsoc");
        categories.add("Aero Club");
        categories.add("NSS");
        categories.add("19A");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }
    private int random1;

    private void startPosting(){
        Random random = new Random();
        random1 =random.nextInt(80000)+11111;
        Event eventObj = new Event();
        eventObj.club = Club;
        eventObj.event = mPostEvent.getText().toString().trim();
        eventObj.venue = mPostVenue.getText().toString().trim();
        eventObj.date = mPostDate.getText().toString().trim();
        eventObj.event_time = mPostTime.getText().toString().trim();
        eventObj.description = mPostDescription.getText().toString().trim();

        String Key = eventObj.date+eventObj.event_time+ random1;
        eventObj.key = Key;

        if(!TextUtils.isEmpty(eventObj.club) && !TextUtils.isEmpty(eventObj.event) && !TextUtils.isEmpty(eventObj.venue) && !TextUtils.isEmpty(eventObj.date) && !TextUtils.isEmpty(eventObj.event_time) && !TextUtils.isEmpty(eventObj.description)) {

            mDatabase.child("Club").child(Key).setValue(eventObj);
        }
        else
        {
            Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show();
        }
        Intent mainIntent=new Intent(PostActivity.this,MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Club = item;
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
