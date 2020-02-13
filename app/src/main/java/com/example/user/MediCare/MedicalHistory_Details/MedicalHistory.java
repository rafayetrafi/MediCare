package com.example.user.MediCare.MedicalHistory_Details;

public class MedicalHistory {

    private int id;
    private String doctorName;
    private String details;
    private String date;
    private String image;

    public MedicalHistory(int id, String doctorName, String details, String date,String image) {
        this.id = id;
        this.doctorName = doctorName;
        this.details = details;
        this.date = date;
        this.image = image;

    }

    public MedicalHistory(String doctorName, String details, String date,String image) {
        this.doctorName = doctorName;
        this.details = details;
        this.date = date;
        this.image = image;
    }

    public MedicalHistory() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
