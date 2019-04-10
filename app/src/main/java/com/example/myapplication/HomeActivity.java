package com.example.myapplication;

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

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static long back_pressed;
    public final int REQUEST_CODE=1;
    User THIS_USER_OBJECT;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_iconmonstr_newspaper_13);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ViewNewsActivity.class);
                intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_voter) {
            Intent intent=new Intent(HomeActivity.this, MyProfile.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            startActivityForResult(intent, REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_nav_bar) {
            // Handle the camera action
        }
        else if(id==R.id.home_lodge_complaint)
        {
            Intent intent=new Intent(HomeActivity.this, LaunchComplaintActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            startActivityForResult(intent, REQUEST_CODE);

        }
        else if (id == R.id.view_complaints_nav_bar) {

            Intent intent=new Intent(HomeActivity.this, ViewComplaintActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            startActivityForResult(intent, REQUEST_CODE);

        } else if (id == R.id.track_nav_bar) {

            Intent intent=new Intent(HomeActivity.this, TrackComplaintActivity.class);
            intent.putExtra("THIS_USER_OBJECT", THIS_USER_OBJECT);
            startActivityForResult(intent, REQUEST_CODE);

        } else if (id == R.id.funds_nav_bar) {
            Intent intent=new Intent(HomeActivity.this , FundsActivity.class);
            startActivity(intent);

        }
        else if(id==R.id.logout_nav_bar)
        {
            Context context = getApplicationContext();
            CharSequence text = "Logged out";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent=new Intent(HomeActivity.this , MainActivity.class);
            startActivity(intent);
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

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
        else Toast.makeText(getBaseContext(), "Press once again to logout!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();

    }
}
