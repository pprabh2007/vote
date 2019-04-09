package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.ViewUserAdapter;
import util.User;

public class ViewUserActivity extends AppCompatActivity {
    private ViewUserAdapter ViewuserAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> list=new ArrayList<User>();

        ViewuserAdapter=new ViewUserAdapter(this, list);
        recyclerView.setAdapter(ViewuserAdapter);


    }
}
