package com.example.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import util.User;

public class Registration extends AppCompatActivity {

    private Button registrationButton;

    private final int MAX_AGE=100;
    private final int VOTING_AGE=18;

    private LinearLayout temp_layout_1;
    private LinearLayout temp_layout_2;

    private EditText name_edittext;
    private EditText email_edittext;
    private EditText password_edittext;
    private EditText confirmpassword_edittext;
    private EditText voterid_edittext;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private Spinner categorySpinner;
    private Spinner constituencySpinner;
    private EditText constituency_edittext;
    private RadioGroup sex_radiogroup;

    private User newUser;
    Databasehelper DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        DBHelper=new Databasehelper(this);

        name_edittext=findViewById(R.id.name_input);
        email_edittext=findViewById(R.id.email_input);
        password_edittext=findViewById(R.id.password_input);
        confirmpassword_edittext=findViewById(R.id.confirm_input);
        voterid_edittext=findViewById(R.id.voter_input);
        constituency_edittext=findViewById(R.id.constituency_text_input);
        sex_radiogroup=findViewById(R.id.radio);
        constituency_edittext=findViewById(R.id.constituency_text_input);
        constituencySpinner=findViewById(R.id.constituency_list_input);

        newUser=new User();

        constituency_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String constituency=constituency_edittext.getText().toString();
                newUser.setConstituency(constituency);
                temp_layout_1=(LinearLayout)findViewById(R.id.constituency_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.constituency_verified);
                if(!newUser.getConstituency().equals(""))
                {
                    temp_layout_2.setVisibility(View.VISIBLE);
                    temp_layout_1.setVisibility(View.GONE);

                }
                else
                {
                    temp_layout_2.setVisibility(View.GONE);
                    temp_layout_1.setVisibility(View.VISIBLE);
                }

            }
        });


        sex_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.radio_male:
                        newUser.setSex("Male");
                        break;

                    case R.id.radio_female:
                        newUser.setSex("Female");
                        break;
                    case R.id.radio_other:
                        newUser.setSex("Other");
                        break;

                }
            }
        });

        name_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String name=name_edittext.getText().toString();
                newUser.setName(name);
                temp_layout_1=(LinearLayout)findViewById(R.id.name_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.name_verified);
                if(name.equals("")==true)
                {
                    temp_layout_1.setVisibility(View.VISIBLE);
                    temp_layout_2.setVisibility(View.GONE);

                }
                else
                {

                    temp_layout_1.setVisibility(View.GONE);
                    temp_layout_2.setVisibility(View.VISIBLE);

                }

            }
        });

        password_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String password=password_edittext.getText().toString();
                newUser.setPassword(password);
                temp_layout_1=(LinearLayout)findViewById(R.id.password_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.password_verified);
                if(newUser.validatePassword())
                {
                    temp_layout_2.setVisibility(View.VISIBLE);
                    temp_layout_1.setVisibility(View.GONE);

                }
                else
                {

                    temp_layout_2.setVisibility(View.GONE);
                    temp_layout_1.setVisibility(View.VISIBLE);

                }

            }
        });

        confirmpassword_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String password=confirmpassword_edittext.getText().toString();

                temp_layout_1=(LinearLayout)findViewById(R.id.confirm_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.confirm_verified);
                if(newUser.validateConfirmPassword(password))
                {
                    temp_layout_2.setVisibility(View.VISIBLE);
                    temp_layout_1.setVisibility(View.GONE);

                }
                else
                {
                    temp_layout_2.setVisibility(View.GONE);
                    temp_layout_1.setVisibility(View.VISIBLE);
                }

            }
        });

        email_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String email=email_edittext.getText().toString();
                newUser.setUser_name(email);
                temp_layout_1=(LinearLayout)findViewById(R.id.email_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.email_verified);
                if(newUser.validateEmail())
                {
                    temp_layout_2.setVisibility(View.VISIBLE);
                    temp_layout_1.setVisibility(View.GONE);

                }
                else
                {
                    temp_layout_2.setVisibility(View.GONE);
                    temp_layout_1.setVisibility(View.VISIBLE);
                }

            }
        });

        voterid_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String voterid=voterid_edittext.getText().toString();
                newUser.setVoterID(voterid);
                temp_layout_1=(LinearLayout)findViewById(R.id.voterid_not_verified);
                temp_layout_2=(LinearLayout)findViewById(R.id.voterid_verified);
                if(!voterid.equals(""))
                {
                    temp_layout_2.setVisibility(View.VISIBLE);
                    temp_layout_1.setVisibility(View.GONE);

                }
                else
                {
                    temp_layout_2.setVisibility(View.GONE);
                    temp_layout_1.setVisibility(View.VISIBLE);
                }

            }
        });



        //int current_year = Year.now().getValue();
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int current_year = now.get(Calendar.YEAR);
        Integer[] years=new Integer[MAX_AGE-VOTING_AGE+1];
        for(int i=0; i<years.length; i++)
        {
            years[i]=new Integer(current_year-(VOTING_AGE+i));
        }

        yearSpinner=(Spinner)findViewById(R.id.dob_y_input);
        ArrayAdapter<Integer> yearSpinnerAdapter=new ArrayAdapter<Integer>(yearSpinner.getContext() , R.layout.spinner_item, years);
        yearSpinner.setAdapter(yearSpinnerAdapter);



        String[] months={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthSpinner=(Spinner)findViewById(R.id.dob_m_input);
        ArrayAdapter<String> monthSpinnerAdapter=new ArrayAdapter<String>(monthSpinner.getContext(), R.layout.spinner_item, months);
        monthSpinner.setAdapter(monthSpinnerAdapter);


        daySpinner=(Spinner)findViewById(R.id.dob_d_input);
        final MutableInt month_pos=new MutableInt();

        final int days_in_month[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selected_year=(Integer)parent.getItemAtPosition(position);
                newUser.setB_year(selected_year);
                int prev_value;
                if(isLeap(selected_year))
                {
                    prev_value=days_in_month[1];
                    days_in_month[1]=29;
                }
                else
                {
                    prev_value=days_in_month[1];
                    days_in_month[1]=28;
                }
                if(prev_value!=days_in_month[1] && month_pos.getValue()==1)
                {
                    int RANGE = days_in_month[month_pos.getValue()];
                    Integer dates_list[] = new Integer[RANGE];
                    for (int i = 0; i < RANGE; i++) {
                        dates_list[i] = new Integer(i + 1);
                    }
                    ArrayAdapter<Integer> daySpinnerAdapter = new ArrayAdapter<Integer>(daySpinner.getContext(), R.layout.spinner_item, dates_list);
                    daySpinner.setAdapter(daySpinnerAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int RANGE=days_in_month[position];
                month_pos.setValue(position);
                newUser.setB_month(position+1);
                Integer dates_list[]=new Integer[RANGE];
                for(int i=0; i<RANGE; i++)
                {
                    dates_list[i]=new Integer(i+1);
                }
                ArrayAdapter<Integer> daySpinnerAdapter=new ArrayAdapter<Integer>(daySpinner.getContext(), R.layout.spinner_item, dates_list);
                daySpinner.setAdapter(daySpinnerAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selected_day=(Integer)adapterView.getItemAtPosition(i);
                newUser.setB_day(selected_day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        String[] category_list={"Representative", "Resident Voter", "Contractor"};
        categorySpinner=(Spinner)findViewById(R.id.category_input);
        ArrayAdapter<String> categorySpinnerAdapter=new ArrayAdapter<String>(categorySpinner.getContext(), R.layout.spinner_item, category_list);
        categorySpinner.setAdapter(categorySpinnerAdapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    newUser.setCategory("R");
                    findViewById(R.id.constituency_text_box).setVisibility(View.VISIBLE);
                    findViewById(R.id.constituency_list_box).setVisibility(View.GONE);
                }

                if(position==1 || position==2)
                {
                    if(position==1)
                        newUser.setCategory("V");
                    if(position==2)
                        newUser.setCategory("C");

                    findViewById(R.id.constituency_text_box).setVisibility(View.GONE);
                    findViewById(R.id.constituency_list_box).setVisibility(View.VISIBLE);

                    ArrayAdapter<String> constituencySpinnerAdapter=new ArrayAdapter<String>(constituencySpinner.getContext(), R.layout.spinner_item, DBHelper.getAllConstituencies());
                    constituencySpinner.setAdapter(constituencySpinnerAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registrationButton = (Button) findViewById(R.id.register);




        registrationButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Context context = getApplicationContext();
                CharSequence text = "Successfully registered";

                DBHelper.addUser(newUser);

                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });

    }



    public boolean isLeap(int year)
    {
        if(year%400==0)
        {
            return true;
        }
        else if(year%100==0)
        {
            return false;
        }
        else
        {
            return year%4==0;
        }
    }
}

