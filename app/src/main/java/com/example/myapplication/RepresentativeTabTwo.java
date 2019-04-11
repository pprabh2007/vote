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

import adapters.RepresentativeViewComplaintAdapter;
import adapters.RepresentativeViewComplaintAdapterNew;
import util.User;

public class RepresentativeTabTwo extends Fragment {

    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.representative_tab_two,container,false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);


        DBHelper = new Databasehelper(this.getActivity());
        extras=getActivity().getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

<<<<<<< HEAD
        RepresentativeViewComplaintAdapterNew adapter = new RepresentativeViewComplaintAdapterNew( this.getActivity(),DBHelper.filterComplaints(THIS_USER_OBJECT,6)  );
=======
        RepresentativeViewComplaintAdapter adapter = new RepresentativeViewComplaintAdapter( this.getActivity(),DBHelper.filterComplaints(THIS_USER_OBJECT,6)  );
>>>>>>> d06752ec45b10cb9daa53767fb9a1713b1565862
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }
}