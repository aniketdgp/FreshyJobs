package com.freshyjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.freshyjobs.SavedData.Bookmarks;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewJob extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    String BookmarkData;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Declaring BookMark Buttons
        ImageButton bookmark = (ImageButton) findViewById(R.id.bookmark);
        //bookmark.setVisibility(View.GONE);



        ImageButton menu = (ImageButton) findViewById(R.id.dropdown_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ViewJob.this, v);
                popup.setOnMenuItemClickListener(ViewJob.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });



        Bundle extras = getIntent().getExtras();
        String timestamp = extras.getString("timestamp");
        String CompanyName = extras.getString("companyname");
        String CompanyLogoUrl = extras.getString("companylogourl");
        String JobProfile = extras.getString("jobprofile");
        String CTC = extras.getString("ctc");
        String Experience = extras.getString("experience");
        String Qualification = extras.getString("qualification");
        String JobDescription = extras.getString("jobdescription");
        String DriveLocation = extras.getString("drivelocation");
        String JobLocation = extras.getString("joblocation");
        String BlogLink = extras.getString("bloglink");
        String ApplyLink = extras.getString("applylink");


        //Alert for Job where posting Date > 7 Days
        timestampCheck(timestamp);



        //gets Bookmark
        final Bookmarks bm = new Bookmarks(ViewJob.this);
        BookmarkData = bm.getJobID();
        int BookmarkData_length = BookmarkData.length();
        if(BookmarkData_length !=0){
            String UpdatedBookMarkData = BookmarkData.substring(1);
            String BookMarksArray[] = UpdatedBookMarkData.split("@");


            for(int i=0;i<BookMarksArray.length;i++){

                //If BookMark Exists then execute this
                if(BookMarksArray[i].equals(timestamp)){

                    bookmark.setVisibility(View.GONE);

                }else{
                    //bookmark.setVisibility(View.VISIBLE);
                    bookmark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ViewJob.this,"Bookmark This Job",Toast.LENGTH_LONG).show();
                            bm.setJobID(timestamp);
                            bookmark.setVisibility(View.GONE);
                        }
                    });
                }
            }

        }else{
            bookmark.setVisibility(View.VISIBLE);
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ViewJob.this,"Bookmark This Job",Toast.LENGTH_LONG).show();
                    bm.setJobID(timestamp);
                    bookmark.setVisibility(View.GONE);
                }
            });
            //Toast.makeText(ViewJob.this,"Not BookMarked",Toast.LENGTH_LONG).show();
        }






        //Linking UI with CODE
        ImageButton share = (ImageButton)findViewById(R.id.vj_share);
        ImageView Companylogourl = (ImageView)findViewById(R.id.vj_companylogo);
        TextView companyname = (TextView)findViewById(R.id.vj_companyname);
        TextView jobprofile = (TextView)findViewById(R.id.vj_jobprofile);
        TextView ctc = (TextView)findViewById(R.id.vj_ctc);
        TextView experience = (TextView)findViewById(R.id.vj_jobexperience);
        TextView qualification = (TextView)findViewById(R.id.vj_jobqualification);
        TextView jobdescription = (TextView)findViewById(R.id.vj_jobdescription);
        TextView drivelocation = (TextView)findViewById(R.id.vj_drivelocation);
        TextView joblocation = (TextView)findViewById(R.id.vj_joblocation);
        Button applylink = (Button) findViewById(R.id.vj_applylink);

        //setting Text Received from extras
        companyname.setText(CompanyName);
        jobprofile.setText(JobProfile);
        ctc.setText(CTC);
        experience.setText(Experience);
        qualification.setText(Qualification);
        jobdescription.setText(JobDescription);
        drivelocation.setText(DriveLocation);
        joblocation.setText(JobLocation);
        Picasso.get()
                .load(CompanyLogoUrl)
                .into(Companylogourl);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Freshy Jobs");
                    String shareMessage= "\nDownload FreshyJobs App to Get latest Job Notifications:\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    String Message = CompanyName+" recruitment for "+JobProfile+".\n\nTo know more visit :\n"+BlogLink+"\n\n"+shareMessage;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, Message);
                    startActivity(Intent.createChooser(shareIntent, "Share Job"));
                } catch(Exception e) {
                    //e.toString();
                }



            }
        });

        applylink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApplyLink));
                startActivity(browserIntent);
            }
        });




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

    //Checks the timestamp if greater then 7 days will notify a warning about job may not be available
    private void timestampCheck(String timestamppass){
        TextView jobexpirywarning = (TextView)findViewById(R.id.vj_job_expiry_warning);
        //GetDate from listData and perform Date Logic for the job update timestamp
        String timestamp = timestamppass.substring(0,10);
        String DateArr[] = timestamp.split("/");
        String JobPostedDateString = DateArr[1]+"/"+DateArr[0]+"/"+DateArr[2]; //Job Posted DateStamp
        //GetDate from Current time
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String CurrentDateString = formatter.format(date);//Current DateStamp

        try {
            Date JobPostedDate = formatter.parse(JobPostedDateString);
            Date CurrentDate = formatter.parse(CurrentDateString);

            int diffInDays = (int)( (CurrentDate.getTime() - JobPostedDate.getTime())
                    / (1000 * 60 * 60 * 24) );

            String dif = String.valueOf(diffInDays);
            if (diffInDays > 7){
                jobexpirywarning.setVisibility(View.VISIBLE);
            }else{
                //other cases
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void bookmarks(){
        Intent n = new Intent(ViewJob.this, BookMarkedJobs.class);
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
        Intent n = new Intent(ViewJob.this, About.class);
        startActivity(n);
    }

    private void privacyPolicy(){
        Intent n = new Intent(ViewJob.this, Privacy.class);
        startActivity(n);
    }

    private void disclaimer(){
        Intent n = new Intent(ViewJob.this, Disclaimer.class);
        startActivity(n);
    }

    private void RemoveBookMarkString(String BookmarkID){
        String data;
        final Bookmarks bm = new Bookmarks(ViewJob.this);
        data = bm.getJobID();
        int BookmarkData_length = data.length();
        if(BookmarkData_length !=0) {
            String UpdatedBookMarkData = BookmarkData.substring(1);
            String BookMarksArray[] = UpdatedBookMarkData.split("@");
            String List = "";
            bm.remove();
//            for(int j=0;j<BookMarksArray.length;j++){
//
//                if(BookMarksArray[j].equals(BookmarkID)){
//                    List = List;
//                }else {
//                    List = List+"@"+BookMarksArray[j];
//                }
//
//            }
//            bm.updateJobID(List);

        }

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