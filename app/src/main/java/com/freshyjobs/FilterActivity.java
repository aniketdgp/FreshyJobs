package com.freshyjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FilterActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView Screename = (TextView)findViewById(R.id.screen_name);
        Screename.setText("Filter");

        //Searchbar
        ImageButton searchbtn = (ImageButton)findViewById(R.id.ad_search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchtext = (EditText)findViewById(R.id.search_text);
                String indtype = searchtext.getText().toString();
                if(indtype.length()>0){
                    Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                    n.putExtra("IndustryType",indtype);
                    startActivity(n);
                }else{
                    Toast.makeText(FilterActivity.this,"Enter Search Keyword",Toast.LENGTH_LONG).show();
                }

            }
        });




        //Setting Up links between button and Java Class
        Button IT_software = (Button)findViewById(R.id.IT_software);
        IT_software.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","IT_SOFTWARE");
                startActivity(n);
            }
        });
        Button Mechanical = (Button)findViewById(R.id.mechanical);
        Mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","MECHANICAL");
                startActivity(n);
            }
        });
        Button Civil = (Button)findViewById(R.id.civil);
        Civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","CIVIL");
                startActivity(n);
            }
        });

        Button Electronics = (Button)findViewById(R.id.electronics);
        Electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","ELECTRONICS");
                startActivity(n);
            }
        });
        Button Government_jobs = (Button)findViewById(R.id.goverment);
        Government_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","GOVERNMENT");
                startActivity(n);
            }
        });
        Button Internships = (Button)findViewById(R.id.internships);
        Internships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","INTERNSHIPS");
                startActivity(n);
            }
        });

        Button Accounting = (Button)findViewById(R.id.accounting);
        Accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","ACCOUNTING");
                startActivity(n);
            }
        });
        Button Finance = (Button)findViewById(R.id.finanace);
        Finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","FINANCE");
                startActivity(n);
            }
        });
        Button Law = (Button)findViewById(R.id.law);
        Law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","LAW");
                startActivity(n);
            }
        });

        Button Healthcare = (Button)findViewById(R.id.healthcare);
        Healthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","HEALTHCARE");
                startActivity(n);
            }
        });
        Button Marketing = (Button)findViewById(R.id.marketing);
        Marketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","MARKETING");
                startActivity(n);
            }
        });
        Button HR = (Button)findViewById(R.id.hr);
        HR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","HR");
                startActivity(n);
            }
        });

        Button CreativeArt = (Button)findViewById(R.id.creativeart);
        CreativeArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","CREATIVEART");
                startActivity(n);
            }
        });
        Button Charity = (Button)findViewById(R.id.charity);
        Charity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","CHARITY");
                startActivity(n);
            }
        });
        Button Media = (Button)findViewById(R.id.media);
        Media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FilterActivity.this, SearchActivity.class);
                n.putExtra("IndustryType","MEDIA");
                startActivity(n);
            }
        });





    }



}