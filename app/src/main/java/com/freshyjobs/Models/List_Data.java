package com.freshyjobs.Models;

public class List_Data {
    private String imageurl;
    private String CompanyName;
    private String JobProfile;
    private String CTC;
    private String Experience;
    private String Qualification;
    private String JobDescription;
    private String DriveLocation;
    private String JobLocation;
    private String BlogLink;
    private String ApplyLink;
    private String JobCategory;
    private String IndustryType;
    private String Author;
    private String JobValidity;
    private String Timestamp;

    public List_Data(String CompanyName,String imageurl ,String JobProfile,String CTC ,String Experience ,String Qualification ,String JobDescription ,String DriveLocation ,String JobLocation ,String BlogLink ,String ApplyLink ,String JobCategory ,String IndustryType ,String Author ,String JobValidity ,String Timestamp
    ) {
        this.CompanyName = CompanyName;
        this.imageurl = imageurl;
        this.JobProfile = JobProfile;
        this.CTC = CTC;
        this.Experience = Experience;
        this.Qualification = Qualification;
        this.JobDescription = JobDescription;
        this.DriveLocation = DriveLocation;
        this.JobLocation = JobLocation;
        this.BlogLink = BlogLink;
        this.ApplyLink = ApplyLink;
        this.JobCategory = JobCategory;
        this.IndustryType = IndustryType;
        this.Author = Author;
        this.JobValidity = JobValidity;
        this.Timestamp = Timestamp;

    }

    public String getImageurl() {
        return imageurl;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getJobProfile() {
        return JobProfile;
    }

    public String getCTC() {
        return CTC;
    }

    public String getExperience() {
        return Experience;
    }

    public String getQualification() {
        return Qualification;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public String getDriveLocation() {
        return DriveLocation;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public String getBlogLink() {
        return BlogLink;
    }

    public String getApplyLink() {
        return ApplyLink;
    }

    public String getJobCategory() {
        return JobCategory;
    }

    public String getIndustryType() {
        return IndustryType;
    }

    public String getAuthor() {
        return Author;
    }

    public String getJobValidity() {
        return JobValidity;
    }

    public String getTimestamp() {
        return Timestamp;
    }
}

