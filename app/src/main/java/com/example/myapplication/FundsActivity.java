package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import util.Complaint;
import util.User;

public class FundsActivity extends AppCompatActivity {

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    TextView taskwisedata;
    TextView totaldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds);

        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        totaldata=findViewById(R.id.total_expense_data);
        taskwisedata=findViewById(R.id.taskwise_data);

        List<Complaint> complaints=DBHelper.filterComplaints(THIS_USER_OBJECT, 7);

        int total_expense=0;
        String display="";

        for(Complaint a: complaints)
        {
            display+=a.getID()+"       ₹"+a.getBid_amt()+"\n";
            total_expense=total_expense+a.getBid_amt();
        }
        //Log.e("DIPLAY", display);

        totaldata.setText(totaldata.getText().toString()+"\t₹ "+total_expense);
        taskwisedata.setText(display);

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
