package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import util.Complaint;
import util.User;

public class MainActivity extends AppCompatActivity {

    public static long back_pressed;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private Button login_button;
    private EditText username_edittext;
    private EditText password_edittext;
    Databasehelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_edittext=(EditText)findViewById(R.id.input_username);
        password_edittext=(EditText)findViewById(R.id.input_password);

        DBHelper=new Databasehelper(this);

        Log.e("A", "IS");

        for (User a: DBHelper.getUsers("sad", "R"))
        {
            Log.e("A", "IS"+a.getUser_name());
        }

        login_button=(Button)findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=username_edittext.getText().toString();
                String password=password_edittext.getText().toString();
                String category=DBHelper.getCategoryOf(username, password);
                if(category.equals("R"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this, RepresentativePortal.class);
                    intent.putExtra("THIS_USER_OBJECT", DBHelper.getDetails(username));
                    startActivity(intent);
                    finish();
                }
                else if(category.equals("V")){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("THIS_USER_OBJECT", DBHelper.getDetails(username));
                    startActivity(intent);
                    finish();}
                else if(category.equals("C"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this, ContractorPortal.class);
                    intent.putExtra("THIS_USER_OBJECT", DBHelper.getDetails(username));
                    startActivity(intent);
                    finish();
                }
                else if(category.equals("NA"))
                {
                    Toast.makeText(MainActivity.this, "Wrong Credentials! Login Failed!", Toast.LENGTH_SHORT).show();
                    username_edittext.getText().clear();
                    password_edittext.getText().clear();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Wrong Credentials! Login Failed!", Toast.LENGTH_SHORT).show();
                    username_edittext.getText().clear();
                    password_edittext.getText().clear();
                }
            }
            });




    }

    public void launchRegistration(View view)
    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis())
        {
            finishAffinity();
            System.exit(0);
        }
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}