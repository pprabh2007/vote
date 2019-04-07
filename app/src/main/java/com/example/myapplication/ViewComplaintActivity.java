package com.example.myapplication;

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

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Complaint> list=new ArrayList<Complaint>();
        list.add(new Complaint("W3010871", "Pipeline Leakage", "Water", 1, 1, 2019, "Pipeline Leakage at Yamuna Nagar, West Block"));
        list.add(new Complaint("E2373232", "Transformer Blown", "Electricity", 2, 2, 2019, "Transformer of Vasant Kunj, Lane No. 2 malfunctioned during yesterday's Voltage Fluctuations"));
        list.add(new Complaint("R3982329", "Uncovered Potholes", "Infrastructure", 3, 3, 2019, "Road Near Gol Bazaar has too many dangerous potholes leading to a number of accidents everydat. Needs Urgent Repair"));

        viewComplaintAdapter=new ViewComplaintAdapter(this, list);
        recyclerView.setAdapter(viewComplaintAdapter);


    }
}
