package com.example.antariksh.addapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Club");

        mList=(RecyclerView) findViewById(R.id.list);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<List, ListViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<List, ListViewHolder>(

                List.class,
                R.layout.list_row,
                ListViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ListViewHolder viewHolder, List model, int position) {

                final String post_Key=getRef(position).getKey();

                viewHolder.setClub(model.getClub());
                viewHolder.setEvent(model.getEvent());
                viewHolder.setVenue(model.getVenue());
                viewHolder.setDate(model.getDate());
                viewHolder.setTime(model.getTime());
                viewHolder.setDescription(model.getDescription());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent DeleteActivity=new Intent(MainActivity.this, DeleteActivity.class);
                        DeleteActivity.putExtra("list_Id",post_Key);
                        startActivity(DeleteActivity);
                    }
                });
            }
        };
        mList.setAdapter(firebaseRecyclerAdapter);
    }
public static class ListViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ListViewHolder(View itemView) {
        super(itemView);

        mView=itemView;
    }

    public void setClub(String Club){

        TextView post_Club=(TextView)mView.findViewById(R.id.post_Club);
        post_Club.setText(Club);
    }
    public void setEvent(String Event){

        TextView post_Event=(TextView)mView.findViewById(R.id.post_Event);
        post_Event.setText(Event);
    }
    public void setVenue(String Venue){

        TextView post_Venue=(TextView)mView.findViewById(R.id.post_Venue);
        post_Venue.setText(Venue);
    }
    public void setDate(String Date){

        TextView post_Date=(TextView)mView.findViewById(R.id.post_Date);
        post_Date.setText(Date);
    }
    public void setTime(String Time){

        TextView post_Time=(TextView)mView.findViewById(R.id.post_Time);
        post_Time.setText(Time);
    }
    public void setDescription(String Description){
        TextView post_Description=(TextView)mView.findViewById(R.id.post_Description);
        post_Description.setText(Description);
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_add)
            startActivity(new Intent(MainActivity.this,PostActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
