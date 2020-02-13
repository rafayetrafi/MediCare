package com.example.user.MediCare.Doctor_Details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Doctor_DBManger {
    Doctors_DBHelper dbHelper;
    SQLiteDatabase db;

    public Doctor_DBManger(Context context) {
       dbHelper = new Doctors_DBHelper(context);

    }
    public Boolean Save_All_Data(Doctor doctor){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Doctors_DBHelper.KEY_NAME,doctor.getUserName());
        contentValues.put(Doctors_DBHelper.KEY_DETAILS,doctor.getDetails());
        contentValues.put(Doctors_DBHelper.KEY_APPIONTMENT_DATE,doctor.getAppionmentDate());
        contentValues.put(Doctors_DBHelper.KEY_PHONE_NO,doctor.getPhoneNo());
        contentValues.put(Doctors_DBHelper.KEY_EMAIL,doctor.getEmail());


        long isInsert = db.insert(Doctors_DBHelper.DOCTORS_TABLE,null,contentValues);
        db.close();
        if (isInsert > 0)
        {
            return true;
        }else {
            return  false;
            }
        }

    public ArrayList<Doctor>Get_All_Doctor_List(){

        ArrayList<Doctor>doctorList = new ArrayList<>();

        db = dbHelper.getReadableDatabase();
        String quary = "SELECT * FROM "+Doctors_DBHelper.DOCTORS_TABLE;

        Cursor cursor = db.rawQuery(quary,null);

        if (cursor.moveToFirst()){

            do{
                int id = cursor.getInt(cursor.getColumnIndex(Doctors_DBHelper.KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(Doctors_DBHelper.KEY_NAME));
                String details = cursor.getString(cursor.getColumnIndex(Doctors_DBHelper.KEY_DETAILS));
                String date = cursor.getString(cursor.getColumnIndex(Doctors_DBHelper.KEY_APPIONTMENT_DATE));
                String phoneNo = cursor.getString(cursor.getColumnIndex(Doctors_DBHelper.KEY_PHONE_NO));
                String email = cursor.getString(cursor.getColumnIndex(Doctors_DBHelper.KEY_EMAIL));

                Doctor doctor = new Doctor(id,name,details,date,phoneNo,email);
                doctorList.add(doctor);


            }while (cursor.moveToNext());
            db.close();
        }
        return doctorList;
        }


    public boolean Delete_Doctors_Data (int rowId) {

        db = dbHelper.getWritableDatabase();
        int isDeleted = db.delete(Doctors_DBHelper.DOCTORS_TABLE,Doctors_DBHelper.KEY_ID+" = ?",new String[]{Integer.toString(rowId)});
        db.close();
        if (isDeleted > 0)
        {
            return true;
        }else {
            return  false;
        }
    }

    public Boolean UpdateData (Doctor doctor,int rowId){

        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Doctors_DBHelper.KEY_NAME,doctor.getUserName());
        contentValues.put(Doctors_DBHelper.KEY_DETAILS,doctor.getDetails());
        contentValues.put(Doctors_DBHelper.KEY_APPIONTMENT_DATE,doctor.getAppionmentDate());
        contentValues.put(Doctors_DBHelper.KEY_PHONE_NO,doctor.getPhoneNo());
        contentValues.put(Doctors_DBHelper.KEY_EMAIL,doctor.getEmail());

        int isUpdate = db.update(Doctors_DBHelper.DOCTORS_TABLE,contentValues,Doctors_DBHelper.KEY_ID+" = ? ",new String[]{Integer.toString(rowId)});
        db.close();
        if (isUpdate > 0)
        {
            return true;
        }else {
            return  false;
        }


    }

    public  ArrayList<String> DoctorNameList(){

        ArrayList<String>doctorNameList = new ArrayList<>();

        db = dbHelper.getReadableDatabase();

        String quary = "SELECT "+Doctors_DBHelper.KEY_NAME+" FROM "+Doctors_DBHelper.DOCTORS_TABLE;

        Cursor cursor = db.rawQuery(quary,null);

        if (cursor.moveToFirst()){

            do{
                String email = cursor.getString(cursor.getColumnIndex(dbHelper.KEY_NAME));
                doctorNameList.add(email);

            }while (cursor.moveToNext());

        }
        db.close();
        return doctorNameList;
    }

}
