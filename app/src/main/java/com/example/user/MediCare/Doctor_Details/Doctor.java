package com.example.user.MediCare.Doctor_Details;

public class Doctor {
    private int id;
    private String userName;
    private String details;
    private String appionmentDate;
    private String phoneNo;
    private String email;

    public Doctor(int id, String userName, String details, String appionmentDate, String phoneNo, String email) {
        this.id = id;
        this.userName = userName;
        this.details = details;
        this.appionmentDate = appionmentDate;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public Doctor(String userName, String details, String appionmentDate, String phoneNo, String email) {
        this.userName = userName;
        this.details = details;
        this.appionmentDate = appionmentDate;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAppionmentDate() {
        return appionmentDate;
    }

    public void setAppionmentDate(String appionmentDate) {
        this.appionmentDate = appionmentDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}