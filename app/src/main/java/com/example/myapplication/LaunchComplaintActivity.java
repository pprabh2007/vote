package com.example.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import util.Complaint;
import util.User;

public class LaunchComplaintActivity extends AppCompatActivity {

    private EditText title_edittext;
    private EditText description_edittext;
    private Spinner category_spinner;
    private ArrayAdapter<String> category_spinner_adapter;
    private Complaint newComplaint;
    private Button register_button;
    private LinearLayout message;
    private AlertDialog.Builder alert_builder;

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    private boolean title_ok;
    private boolean description_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_complaint);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},00);


        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        message=(LinearLayout)findViewById(R.id.message);
        title_edittext=(EditText)findViewById(R.id.title_input);
        description_edittext=(EditText)findViewById(R.id.description_input);

        category_spinner=(Spinner)findViewById(R.id.category_input);
        String domains[]={"Water", "Electricity", "Infrastructure", "Health"};
        category_spinner_adapter=new ArrayAdapter<String>(category_spinner.getContext(), R.layout.spinner_item, domains);
        category_spinner.setAdapter(category_spinner_adapter);

        register_button=findViewById(R.id.register);

        newComplaint=new Complaint();
        newComplaint.setUser_name_launcher(THIS_USER_OBJECT.getUser_name());
        newComplaint.setConstituency(THIS_USER_OBJECT.getConstituency());

        title_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b)
                {
                    newComplaint.setTitle(title_edittext.getText().toString());

                    if(newComplaint.getTitle().equals("")) {

                        title_ok=false;
                    }
                    else {
                        title_ok=true;
                    }
                }

            }
        });

        description_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b)
                {
                    newComplaint.setDescription(description_edittext.getText().toString());

                    if(newComplaint.getDescription().equals("")) {

                       description_ok=false;
                    }
                    else {
                        description_ok=true;
                    }
                }

            }
        });

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String domain=adapterView.getItemAtPosition(i).toString();
                newComplaint.setDomain(domain);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        register_button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(b) {

                    if(title_ok && description_ok) {

                        message.setVisibility(View.INVISIBLE);
                        DBHelper.addComplaint(newComplaint);

                        alert_builder=new AlertDialog.Builder(LaunchComplaintActivity.this);
                        alert_builder.setTitle("Important!");
                        alert_builder.setMessage("Your Reference ID is: \n"+newComplaint.getID()+"\nPlease note it for all future references.");
                        alert_builder.setCancelable(false);
                        alert_builder.setPositiveButton(R.string.button1,

                                new DialogInterface.OnClickListener(){


                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Toast.makeText(getApplicationContext(), "Successfully Lodged!", Toast.LENGTH_SHORT).show();
                                        LaunchComplaintActivity.this.finish();

                                    }
                                });
                        /*
                        alert_builder.setNegativeButton(R.string.button2,

                                new DialogInterface.OnClickListener(){


                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {


                                        Toast.makeText(getApplicationContext(), "Successfully Lodged!", Toast.LENGTH_SHORT).show();
                                        LaunchComplaintActivity.this.finish();

                                    }
                                });
                            */
                        AlertDialog alert=alert_builder.create();
                        alert.show();




                    }
                    else {

                        message.setVisibility(View.VISIBLE);

                    }



                }


            }
        });




    }

    protected void onStop()
    {
        super.onStop();
        //Log.e("HELLO", "HELLO");
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

