package com.example.antariksh.addapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteActivity extends AppCompatActivity {

    private String mPost_Key=null;
    private DatabaseReference mDatabase;

    private TextView printClub;
    private TextView printEvent;
    private TextView printVenue;
    private TextView printDate;
    private TextView printTime;
    private TextView printDescription;

    private Button mRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Club");

        mPost_Key=getIntent().getExtras().getString("list_Id");

        printClub =(TextView) findViewById(R.id.textView1);
        printEvent =(TextView) findViewById(R.id.textView2);
        printVenue =(TextView) findViewById(R.id.textView3);
        printDate =(TextView) findViewById(R.id.textView4);
        printTime =(TextView) findViewById(R.id.textView5);
        printDescription =(TextView) findViewById(R.id.textView6);

        mRemove= (Button) findViewById(R.id.remove);


        Toast.makeText(DeleteActivity.this,mPost_Key,Toast.LENGTH_LONG).show();

        mDatabase.child(mPost_Key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_Club=(String) dataSnapshot.child("club").getValue();
                String post_Event=(String) dataSnapshot.child("event").getValue();
                String post_Venue=(String) dataSnapshot.child("venue").getValue();
                String post_Date=(String) dataSnapshot.child("date").getValue();
                String post_Time=(String) dataSnapshot.child("event_time").getValue();
                String post_Description=(String) dataSnapshot.child("description").getValue();

                printClub.setText(post_Club);
                printDescription.setText(post_Description);
                printDate.setText(post_Date);
                printVenue.setText(post_Venue);
                printTime.setText(post_Time);
                printEvent.setText(post_Event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(mPost_Key).removeValue();

                Intent mainIntent=new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}
