package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.ContractorViewComplaintAdapter;
import adapters.ContractorViewComplaintAdapterNew;
import util.User;

public class ContractorTabTwo extends Fragment {

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.contractor_tab_two,container,false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);


        DBHelper = new Databasehelper(this.getActivity());
        extras=getActivity().getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        ContractorViewComplaintAdapterNew adapter = new ContractorViewComplaintAdapterNew( this.getActivity(),DBHelper.filterComplaints(THIS_USER_OBJECT,2)  );
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }
}