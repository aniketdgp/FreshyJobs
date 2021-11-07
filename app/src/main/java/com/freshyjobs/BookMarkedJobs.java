package com.freshyjobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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
import com.freshyjobs.SavedData.Bookmarks;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookMarkedJobs extends AppCompatActivity{

    String BookmarkData;
    private static final String URL = "https://sheets.googleapis.com/v4/spreadsheets/1Aq8-X_C65M-n0TL20tQ0hp5l1p2NKmSil8X3YuIwOVY/values/op?key=AIzaSyCXqLB7tiuO7YiorcgzVJ9CbF5sHvSha2U";
    private ArrayList<List_Data> list_data;
    private ListView lv;
    private Custom_Adapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_book_marked_jobs);


        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        //Declaring Relation in buttons
        ImageButton delete_bookmark = (ImageButton)findViewById(R.id.bookmark_remove);
        TextView Screename = (TextView)findViewById(R.id.screen_name);
        Screename.setText("Bookmarks");

        lv=(ListView)findViewById(R.id.list_view);
        list_data=new ArrayList<>();

        //gets Bookmark
        final Bookmarks bm = new Bookmarks(BookMarkedJobs.this);
        BookmarkData = bm.getJobID();
        int BookmarkData_length = BookmarkData.length();
        if(BookmarkData_length !=0){
            String UpdatedBookMarkData = BookmarkData.substring(1);
            String BookMarksArray[] = UpdatedBookMarkData.split("@");
            //Toast.makeText(BookMarkedJobs.this,BookmarkData,Toast.LENGTH_LONG).show();
            getdata(BookMarksArray);
        }else{
            delete_bookmark.setVisibility(View.GONE);
            Toast.makeText(BookMarkedJobs.this,"No Bookmarks Found",Toast.LENGTH_LONG).show();
        }


        //Image Button DeleteBookmarks
        delete_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteDialog();

            }
        });



    }

    private void getdata(String[] BookMarkPassed) {

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

                        for(int p=0; p<BookMarkPassed.length;p++) {

                            if (Timestamp.equals(BookMarkPassed[p])) {
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
        if(list_data.isEmpty()){
            Bundle extras = getIntent().getExtras();
            String companyname = extras.getString("companyname");
            Toast.makeText(BookMarkedJobs.this,"No Results Found for "+companyname,Toast.LENGTH_LONG).show();
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


                Intent n = new Intent(BookMarkedJobs.this, ViewJob.class);
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
                finish();



            }
        });
    }


    private void DeleteDialog(){
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Delete All Bookmarks")
                //set message
                .setMessage("Are you sure you want to delete all bookmarks ?")
                //set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        final Bookmarks bm = new Bookmarks(BookMarkedJobs.this);
                        bm.remove();
                        Toast.makeText(getApplicationContext(),"All bookmarks have been deleted",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                //set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        //Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();

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