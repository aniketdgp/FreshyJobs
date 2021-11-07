package com.freshyjobs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.freshyjobs.Adapters.Custom_Adapter;
import com.freshyjobs.Models.List_Data;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends  AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final String URL = "https://sheets.googleapis.com/v4/spreadsheets/1Aq8-X_C65M-n0TL20tQ0hp5l1p2NKmSil8X3YuIwOVY/values/op?key=AIzaSyCXqLB7tiuO7YiorcgzVJ9CbF5sHvSha2U";
    private ArrayList<List_Data>list_data;
    private ListView lv;
    private Custom_Adapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);


        ImageButton menu = (ImageButton) findViewById(R.id.dropdown_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });



        lv=(ListView)findViewById(R.id.list_view);
        list_data=new ArrayList<>();
        getJsonDataViaSheet();


        Button filter = (Button)findViewById(R.id.btn_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(n);
            }
        });

        //Filter is hidden for development purposes.
        filter.setVisibility(View.GONE);


        ImageButton search = (ImageButton)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this); // Context, this, etc.
                dialog.setContentView(R.layout.searchmenu);
                dialog.show();

                ImageButton alertDialog_search = (ImageButton)dialog.findViewById(R.id.ad_search);
                alertDialog_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(MainActivity.this,"FilterActivity",Toast.LENGTH_LONG).show();
                        EditText searchtext = (EditText)dialog.findViewById(R.id.search_text);
                        String cn = searchtext.getText().toString();
                        if(cn.length()>0){
                            Intent n = new Intent(MainActivity.this, SearchActivity.class);
                            n.putExtra("companyname",cn);
                            startActivity(n);
                        }else{
                            Toast.makeText(MainActivity.this,"Enter Search Keyword",Toast.LENGTH_LONG).show();
                        }

                        dialog.hide();

                    }
                });

            }
        });




    }

    private void getJsonDataViaSheet() {

        list_data.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray MasterArray = response.getJSONArray("values");
                    for(int i=MasterArray.length()-1;i>0;i--){
                        JSONArray SingleDataSet = MasterArray.getJSONArray(i);
                        String Timestamp = SingleDataSet.getString(0);
                        String CompanyName = SingleDataSet.getString(1);
                        String CompanyLogoUrl = SingleDataSet.getString(2);
                        String JobProfile = SingleDataSet.getString(3);
                        String CTC = SingleDataSet.getString(4);
                        String Experience = SingleDataSet.getString(5);
                        String Qualification = SingleDataSet.getString(6);
                        String JobDescription = SingleDataSet.getString(7);
                        String DriveLocation = SingleDataSet.getString(8);
                        String JobLocation = SingleDataSet.getString(9);
                        String BlogLink = SingleDataSet.getString(10);
                        String ApplyLink = SingleDataSet.getString(11);
                        String JobCategory = SingleDataSet.getString(12);
                        String IndustryType = SingleDataSet.getString(13);
                        String Author = SingleDataSet.getString(14);
                        String JobValidity = SingleDataSet.getString(15);

                        List_Data listData = new List_Data(
                                CompanyName,
                                CompanyLogoUrl,
                                JobProfile,
                                CTC,
                                Experience,
                                Qualification,
                                JobDescription,
                                DriveLocation,
                                JobLocation,
                                BlogLink,
                                ApplyLink,
                                JobCategory,
                                IndustryType,
                                Author,
                                JobValidity,
                                Timestamp
                        );
                        list_data.add(listData);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setupData(list_data);
                settingListViewClick(list_data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void setupData(ArrayList<List_Data> list_data) {
        adapter=new Custom_Adapter(getApplicationContext(),list_data);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        //Toast.makeText(this, "Selected Item: " +menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.bookmarks_item:
                // do your code
                bookmarks();
                return true;
            case R.id.share_item:
                // do your code
                shareApp();
                return true;
            case R.id.privacy_policy:
                // do your code
                privacyPolicy();
                return true;
            case R.id.disclaimer:
                // do your code
                disclaimer();
                return true;
            case R.id.about_item:
                // do your code
                aboutApp();
                return true;
            default:
                return false;
        }
    }

    private void bookmarks(){
        Intent n = new Intent(MainActivity.this, BookMarkedJobs.class);
        startActivity(n);
    }

    private void shareApp(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Freshy Jobs");
        String shareMessage= "Download FreshyJobs App to Get latest Job Notifications:\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share Job"));
    }


    private void aboutApp(){
        Intent n = new Intent(MainActivity.this, About.class);
        startActivity(n);
    }

    private void privacyPolicy(){
        Intent n = new Intent(MainActivity.this, Privacy.class);
        startActivity(n);
    }

    private void disclaimer(){
        Intent n = new Intent(MainActivity.this, Disclaimer.class);
        startActivity(n);
    }

    private void settingListViewClick(ArrayList<List_Data> list_data){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                int p = position;
                String CompanyName = list_data.get(position).getCompanyName();
                String imageurl = list_data.get(position).getImageurl();
                String JobProfile = list_data.get(position).getJobProfile();
                String CTC = list_data.get(position).getCTC();
                String Experience = list_data.get(position).getExperience();
                String Qualification = list_data.get(position).getQualification();
                String JobDescription = list_data.get(position).getJobDescription();
                String DriveLocation = list_data.get(position).getDriveLocation();
                String JobLocation = list_data.get(position).getJobLocation();
                String BlogLink = list_data.get(position).getBlogLink();
                String ApplyLink = list_data.get(position).getApplyLink();
                String JobCategory = list_data.get(position).getJobCategory();
                String IndustryType = list_data.get(position).getIndustryType();
                String Author = list_data.get(position).getAuthor();
                String JobValidity = list_data.get(position).getJobValidity();
                String Timestamp = list_data.get(position).getTimestamp();


                Intent n = new Intent(MainActivity.this, ViewJob.class);
                //n.addFlags(FLAG_ACTIVITY_NO_ANIMATION);

                n.putExtra("timestamp",Timestamp);
                n.putExtra("companyname",CompanyName);
                n.putExtra("companylogourl",imageurl);
                n.putExtra("jobprofile",JobProfile);
                n.putExtra("ctc",CTC);
                n.putExtra("experience",Experience);
                n.putExtra("qualification",Qualification);
                n.putExtra("jobdescription",JobDescription);
                n.putExtra("drivelocation",DriveLocation);
                n.putExtra("joblocation",JobLocation);
                n.putExtra("bloglink",BlogLink);
                n.putExtra("applylink",ApplyLink);

                startActivity(n);



            }
        });
    }


}