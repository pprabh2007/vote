package com.example.myapplication;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class LaunchComplaintActivity extends AppCompatActivity {

    private EditText title_edittext;
    private EditText description_edittext;
    private Spinner category_spinner;
    private ArrayAdapter<String> category_spinner_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_complaint);

        title_edittext=(EditText)findViewById(R.id.title_input);
        description_edittext=(EditText)findViewById(R.id.description_input);

        category_spinner=(Spinner)findViewById(R.id.category_input);
        String domains[]={"Water", "Electricity", "Infrastructure", "Health"};
        category_spinner_adapter=new ArrayAdapter<String>(category_spinner.getContext(), R.layout.spinner_item, domains);

    }
}
