package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adapters.ViewUserAdapter;
import util.User;

public class ViewUserActivity extends AppCompatActivity {
    private ViewUserAdapter ViewuserAdapter;
    private RecyclerView recyclerView;


    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        DBHelper=new Databasehelper(this);
        extras=getIntent().getExtras();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        String constituency_to_get=THIS_USER_OBJECT.getConstituency();
        String category_to_get=extras.getString("category_to_get");

        List<User> list=DBHelper.getUsers(constituency_to_get, category_to_get);

        ViewuserAdapter=new ViewUserAdapter(this, list);
        recyclerView.setAdapter(ViewuserAdapter);


    }

    @Override
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
