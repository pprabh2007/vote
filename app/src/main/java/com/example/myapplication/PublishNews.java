package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import util.News;
import util.User;

public class PublishNews extends AppCompatActivity {

    Databasehelper DBHelper;
    User THIS_USER_OBJECT;
    Bundle extras;

    News newNews;

    EditText title_edittext;
    EditText description_edittext;
    Button register_button;

    boolean title_ok;
    boolean description_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_news);

        DBHelper = new Databasehelper(this);
        extras = getIntent().getExtras();
        THIS_USER_OBJECT = (User) extras.getSerializable("THIS_USER_OBJECT");

        title_edittext = findViewById(R.id.title_input);
        description_edittext = findViewById(R.id.description_input);
        register_button=findViewById(R.id.register);

        newNews=new News();
        newNews.setPublisher(THIS_USER_OBJECT.getUser_name());
        newNews.setConstituency(THIS_USER_OBJECT.getConstituency());

        title_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    newNews.setTitle(title_edittext.getText().toString());

                    if (newNews.getTitle().equals("")) {

                        title_ok = false;
                    } else {
                        title_ok = true;
                    }
                }

            }
        });


        description_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    newNews.setDescription(description_edittext.getText().toString());

                    if (newNews.getDescription().equals("")) {

                        description_ok = false;
                    } else {
                        description_ok = true;
                    }
                }
            }
        });

        register_button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {

                    if (title_ok && description_ok) {

                        Toast.makeText(PublishNews.this, "Success!" , Toast.LENGTH_SHORT).show();
                        DBHelper.addNews(newNews);
                        finish();
                    } else {

                        Toast.makeText(PublishNews.this, "Failure! All Fields are Mandatory" , Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
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