package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_report);

        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        setupPieChart();

    }

    protected void setupPieChart()
    {
        HashMap<String, Integer> performaceValues=DBHelper.getPerformanceData(THIS_USER_OBJECT.getConstituency());

        List<PieEntry> pieEntries=new ArrayList<>();
        for(Map.Entry<String, Integer> ordered_pair: performaceValues.entrySet())
        {
            String key=ordered_pair.getKey();
            Integer value=ordered_pair.getValue();

            pieEntries.add(new PieEntry(value, key));
        }

        PieDataSet pieDataSet=new PieDataSet(pieEntries, "");
        pieDataSet.setColors(CONSTANTS.PIE_RED, CONSTANTS.PIE_ORANGE, CONSTANTS.PIE_GREEN);

        PieData pieData=new PieData(pieDataSet);

        PieChart pc=findViewById(R.id.pie_chart);
        pc.setData(pieData);
        pc.invalidate();

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
