package com.freshyjobs.SavedData;

import android.content.Context;
import android.content.SharedPreferences;

public class Bookmarks {

    private  String JobID;
    Context context;
    SharedPreferences sharedPreferences;


    public void remove(){

        sharedPreferences.edit().clear().commit();

    }


    public String getJobID() {
        JobID = sharedPreferences.getString("bookmarks","");
        return JobID;

    }

    public void setJobID(String JobID) {
        String JobList = this.getJobID() + "@" +JobID;
        //this.JobID = JobList;
        sharedPreferences.edit().putString("bookmarks",JobList).apply();
    }

    public void updateJobID(String JobID) {
        this.JobID = JobID;
        sharedPreferences.edit().putString("bookmarks",JobID).apply();
    }




    public Bookmarks(Context context){

        this.context = context;
        sharedPreferences  = context.getSharedPreferences("user_bookmarks",Context.MODE_PRIVATE);

    }



}