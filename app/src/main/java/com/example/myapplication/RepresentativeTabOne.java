package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import adapters.RepresentativeViewComplaintAdapter;
import adapters.ViewComplaintAdapter;
import util.User;






public class RepresentativeTabOne extends Fragment {


    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.representative_tab_one,container,false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        //TODO
        //Change below code for tab one ,two & three

        DBHelper = new Databasehelper(this.getActivity());
        extras=getActivity().getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        RepresentativeViewComplaintAdapter adapter = new RepresentativeViewComplaintAdapter( this.getActivity(),DBHelper.filterComplaints(THIS_USER_OBJECT,4)  );
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }


}