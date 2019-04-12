package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.CONSTANTS;
import util.User;

public class RepReport extends AppCompatActivity {

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    TextView per_launched;
    TextView per_completed;
    TextView per_assigned;
    TextView constituency_name;
    TextView representative_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_report);

        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        constituency_name=findViewById(R.id.constituency_title);
        representative_name=findViewById(R.id.person_title);

        constituency_name.setText(THIS_USER_OBJECT.getConstituency());
        //representative_name.setText(THIS_USER_OBJECT.getName());
        representative_name.setText("");

        setupPieChartAndData();

    }

    protected void setupPieChartAndData()
    {
        HashMap<String, Integer> performaceValues=DBHelper.getPerformanceData(THIS_USER_OBJECT.getConstituency());

        int total_complaints=0;

        List<PieEntry> pieEntries=new ArrayList<>();
        for(Map.Entry<String, Integer> ordered_pair: performaceValues.entrySet())
        {
            String key=ordered_pair.getKey();
            Integer value=ordered_pair.getValue();

            total_complaints+=value;

            pieEntries.add(new PieEntry(value, key));
        }

        PieDataSet pieDataSet=new PieDataSet(pieEntries, "");
        pieDataSet.setColors(CONSTANTS.PIE_RED, CONSTANTS.PIE_ORANGE, CONSTANTS.PIE_GREEN);

        PieData pieData=new PieData(pieDataSet);

        PieChart pc=findViewById(R.id.pie_chart);
        pc.setData(pieData);
        pc.animateY(1000);
        pc.invalidate();


        per_assigned=findViewById(R.id.per_assigned);
        per_completed=findViewById(R.id.per_completed);
        per_launched=findViewById(R.id.per_launched);

        per_assigned.setText(per_assigned.getText().toString()+ ((performaceValues.get("Assigned")*100.0)/total_complaints));
        per_completed.setText(per_completed.getText().toString()+ ((performaceValues.get("Completed")*100.0)/total_complaints));
        per_launched.setText(per_launched.getText().toString()+ ((performaceValues.get("Launched")*100.0)/total_complaints));
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
