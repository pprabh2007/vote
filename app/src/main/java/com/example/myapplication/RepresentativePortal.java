package com.example.myapplication;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import util.User;

public class RepresentativePortal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final int REQUEST_CODE=1;

    User THIS_USER_OBJECT;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_portal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        extras = getIntent().getExtras();

        if(extras!=null) {
            THIS_USER_OBJECT=(User)(extras.getSerializable("THIS_USER_OBJECT"));
        }

        else if(savedInstanceState!=null)
        {
            THIS_USER_OBJECT=(User)(savedInstanceState.getSerializable("THIS_USER_OBJECT"));
        }
        else
        {
            //Do Nothing
        }

       // Log.e("OBJECT", THIS_USER_OBJECT.getUser_name());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.representative_portal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.rep_home_nav_bar) {
            // Handle the camera action
        } else if (id == R.id.rep_view_complaints_nav_bar) {
            Intent intent=new Intent(RepresentativePortal.this, ViewComplaintActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            startActivityForResult(intent, REQUEST_CODE);

        } else if (id == R.id.rep_funds_nav_bar) {
            Intent intent=new Intent(RepresentativePortal.this , FundsActivity.class);
            startActivity(intent);

        } else if (id == R.id.rep_contractor_list) {
            Context context = getApplicationContext();

            Intent intent=new Intent(RepresentativePortal.this, ViewUserActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            intent.putExtra("category_to_get", "C");
            startActivityForResult(intent, REQUEST_CODE);

        }
        else if(id==R.id.rep_broadcastnews_nav_bar){
            Intent intent=new Intent(RepresentativePortal.this, PublishNews.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            intent.putExtra("category_to_get", "C");
            startActivityForResult(intent, REQUEST_CODE);

        }else if (id == R.id.rep_voter_list) {
            Intent intent=new Intent(RepresentativePortal.this, ViewUserActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            intent.putExtra("category_to_get", "V");
            startActivityForResult(intent, REQUEST_CODE);

        } else if (id == R.id.rep_logout_nav_bar) {
            Context context = getApplicationContext();
            CharSequence text = "Logged out";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                THIS_USER_OBJECT=(User)data.getExtras().getSerializable("THIS_USER_OBJECT");
            }
        }
        Log.e("OBJECT", THIS_USER_OBJECT.getUser_name());
        Log.e("OBJECT", "dfnj");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putSerializable("THIS_USER_OBJECT", THIS_USER_OBJECT);
        super.onSaveInstanceState(savedInstanceState);
    }
}
