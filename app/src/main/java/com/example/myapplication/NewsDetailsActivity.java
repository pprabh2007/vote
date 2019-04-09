package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailsActivity extends AppCompatActivity {

    ImageView DetCom_close;
    TextView DetCom_title, DetCom_description, Detcom_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        DetCom_close=(ImageView)findViewById(R.id.Det_News_close);
        DetCom_title=(TextView)findViewById(R.id.Det_News_title);
        DetCom_description=(TextView)findViewById(R.id.Det_news_description);
        Detcom_date=(TextView)findViewById(R.id.Det_news_date);

        Bundle extras=getIntent().getExtras();
        DetCom_title.setText(extras.getString("title"));
        DetCom_description.setText(extras.getString("description"));
        Detcom_date.setText(extras.getString("date"));
        DetCom_close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                            }
                                        }
        );
    }
}
