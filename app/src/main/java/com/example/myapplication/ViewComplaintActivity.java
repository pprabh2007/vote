package com.example.myapplication;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.ViewComplaintAdapter;
import util.Complaint;

public class ViewComplaintActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewComplaintAdapter viewComplaintAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);
               Databasehelper DBHelper = new Databasehelper(this);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewComplaintAdapter=new ViewComplaintAdapter(this, DBHelper.getAllComplaints());
        recyclerView.setAdapter(viewComplaintAdapter);


    }
}
