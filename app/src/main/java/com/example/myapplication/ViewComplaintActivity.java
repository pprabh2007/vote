package com.example.myapplication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adapters.ViewComplaintAdapter;
import util.Complaint;
import util.User;

public class ViewComplaintActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewComplaintAdapter viewComplaintAdapter;

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);

        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewComplaintAdapter=new ViewComplaintAdapter(this, DBHelper.getAllComplaints(THIS_USER_OBJECT.getConstituency()));
        recyclerView.setAdapter(viewComplaintAdapter);


    }

    protected void onStop()
    {
        super.onStop();
        Log.e("HELLO", "HELLO");
        Intent returnIntent=getIntent();
        returnIntent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
        setResult(RESULT_OK, returnIntent);
        finish();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent returnIntent=getIntent();
                returnIntent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
                setResult(RESULT_OK, returnIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

