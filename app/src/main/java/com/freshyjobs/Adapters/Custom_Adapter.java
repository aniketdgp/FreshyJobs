package com.freshyjobs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.freshyjobs.Models.List_Data;
import com.freshyjobs.R;
import com.freshyjobs.ViewJob;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Custom_Adapter extends BaseAdapter{
    Context c;
    ArrayList<List_Data> listdata;
    LayoutInflater inflater;


    public Custom_Adapter(Context c, ArrayList<List_Data> listdata) {
        this.c = c;
        this.listdata = listdata;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.listdata,viewGroup,false);


        //Linking the Layout variabel
        ListView lv=(ListView)view.findViewById(R.id.list_view);
        LinearLayout uniqueJobBlock = (LinearLayout)view.findViewById(R.id.unique_job_block);
        TextView companyname = (TextView)view.findViewById(R.id.companyname);
        TextView jobprofile = (TextView)view.findViewById(R.id.jobprofile);
        TextView ctc = (TextView)view.findViewById(R.id.ctc);
        TextView joblocation = (TextView)view.findViewById(R.id.joblocation);

        //GetDate from listData and perform Date Logic for the job update timestamp
        String timestamp = listdata.get(i).getTimestamp().substring(0,10);
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
                uniqueJobBlock.setBackgroundColor(Color.parseColor("#808080"));
            }else{
                //uniqueJobBlock.setBackgroundColor(Color.parseColor("#000000"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }




        //SetText in TextView
        companyname.setText(listdata.get(i).getCompanyName());
        jobprofile.setText(listdata.get(i).getJobProfile());
        ctc.setText(listdata.get(i).getCTC());
        joblocation.setText((listdata.get(i).getJobLocation()));

        ImageView img=(ImageView)view.findViewById(R.id.image_view);
        final String imageurl=listdata.get(i).getImageurl();

        Picasso.get()
                .load(imageurl)
                .into(img);





        return view;
    }
}
