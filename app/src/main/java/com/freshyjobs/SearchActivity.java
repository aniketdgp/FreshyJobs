package com.freshyjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity{

    private static final String HI = "https://raw.githubusercontent.com/freshyjobs/AppData/main/jobs.json";
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
        setContentView(R.layout.activity_search);

        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        lv=(ListView)findViewById(R.id.list_view);
        list_data=new ArrayList<>();



        Bundle extras = getIntent().getExtras();
        String companyname = extras.getString("companyname");
        String industrytype = extras.getString("IndustryType");

        if(companyname == null){
            if(industrytype.toLowerCase().equals("it")||industrytype.toLowerCase().equals("software") || industrytype.toLowerCase().equals("it/software")){
                industrytype =  "IT_SOFTWARE";
            }

            if(industrytype.toLowerCase().equals("government jobs") || industrytype.toLowerCase().equals("internship") || industrytype.toLowerCase().equals("government")){
                getJobCategory(industrytype);
            }else{
                getIndustryType(industrytype);
            }
        }else{
            getdata(companyname);
        }



    }

    //Search by Company Name
    private void getdata(String cn) {

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


                            if (cn.equals("Undefined")) {
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


                            if (CompanyName.toLowerCase().equals(cn.toLowerCase())) {
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

    //Search by IndustryType
    private void getIndustryType(String indtype) {

        list_data.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {
                        JSONArray MasterArray = response.getJSONArray("values");
                        for(int i=MasterArray.length()-1;i>0;i--) {
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


                            if (indtype.equals("Undefined")) {
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


                            if (IndustryType.toLowerCase().equals(indtype.toLowerCase())) {
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

    //Search by IndustryType
    private void getJobCategory(String jobcategory) {

        list_data.clear();
        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        jsonObject=response.getJSONObject(i);

                        String CompanyName = jsonObject.getString("CompanyName");
                        String CompanyLogoUrl = jsonObject.getString("CompanyLogoURL");
                        String JobProfile = jsonObject.getString("JobProfile");
                        String CTC = jsonObject.getString("CTC");
                        String Experience = jsonObject.getString("Experience");
                        String Qualification = jsonObject.getString("Qualification");
                        String JobDescription = jsonObject.getString("JobDescription");
                        String DriveLocation = jsonObject.getString("DriveLocation");
                        String JobLocation = jsonObject.getString("JobLocation");
                        String BlogLink = jsonObject.getString("BlogLink");
                        String ApplyLink = jsonObject.getString("ApplyLink");
                        String JobCategory = jsonObject.getString("JobCategory");
                        String IndustryType = jsonObject.getString("IndustryType");
                        String Author = jsonObject.getString("Author");
                        String JobValidity = jsonObject.getString("JobValidity");
                        String Timestamp = jsonObject.getString("Timestamp");



                        if(jobcategory.equals("Undefined")){
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


                        if(JobCategory.toLowerCase().equals(jobcategory.toLowerCase())) {
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
        requestQueue.add(request);

    }



    private void setupData(ArrayList<List_Data> list_data) {
        adapter=new Custom_Adapter(getApplicationContext(),list_data);
        if(list_data.isEmpty()){
            Bundle extras = getIntent().getExtras();
            String companyname = extras.getString("companyname");
            String industrytype = extras.getString("IndustryType");
            if(companyname == null){
                Toast.makeText(SearchActivity.this,"No Results Found for "+industrytype,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(SearchActivity.this,"No Results Found for "+companyname,Toast.LENGTH_LONG).show();
            }

        }
        lv.setAdapter(adapter);
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


                Intent n = new Intent(SearchActivity.this, ViewJob.class);
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




    //Close Activity on Back Pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }


}

