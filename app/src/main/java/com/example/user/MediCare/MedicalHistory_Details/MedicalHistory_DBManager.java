package com.example.user.MediCare.MedicalHistory_Details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MedicalHistory_DBManager {

    private MedicalHistory_DBHelper dbHelper;
    private SQLiteDatabase db;


    public MedicalHistory_DBManager(Context context) {
        dbHelper = new MedicalHistory_DBHelper(context);
    }

    public boolean Save_All_Patient_Data(MedicalHistory medicalHistory){


        db = dbHelper.getWritableDatabase();
        if (medicalHistory == null)
            return false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_NAME, medicalHistory.getDoctorName());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_DETAILS, medicalHistory.getDetails());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_date, medicalHistory.getDate());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_IMAGE, medicalHistory.getImage());

        Long isInsert = db.insert(MedicalHistory_DBHelper.MEDICAL_HISTORY_TABLE,null,contentValues);
        db.close();

        if (isInsert>0){
            return true;
        }else {
            return false;
        }
    }


    public ArrayList<MedicalHistory> Get_All_patient_Data(){
        ArrayList<MedicalHistory> medicalHistoryList = new ArrayList<>();

        db = dbHelper.getReadableDatabase();
        String quary = "SELECT * FROM "+ MedicalHistory_DBHelper.MEDICAL_HISTORY_TABLE+";";

        Cursor cursor = db.rawQuery(quary,null);
        if (cursor.moveToFirst()){

            do {
                int id = cursor.getInt(cursor.getColumnIndex(MedicalHistory_DBHelper.PATIENT_KEY_ID));
                String doctorName = cursor.getString(cursor.getColumnIndex(MedicalHistory_DBHelper.PATIENT_KEY_NAME));
                String details = cursor.getString(cursor.getColumnIndex(MedicalHistory_DBHelper.PATIENT_KEY_DETAILS));
                String date = cursor.getString(cursor.getColumnIndex(MedicalHistory_DBHelper.PATIENT_KEY_date));
                String image = cursor.getString(cursor.getColumnIndex(MedicalHistory_DBHelper.PATIENT_KEY_IMAGE));

                MedicalHistory medicalHistory = new MedicalHistory(id,doctorName,details,date,image);
                medicalHistoryList.add(medicalHistory);

            }while (cursor.moveToNext());
            db.close();

        }
        return medicalHistoryList;

    }

    public boolean Delete_patient_data(int rowId){

        db = dbHelper.getWritableDatabase();
        int isDelete = db.delete(MedicalHistory_DBHelper.MEDICAL_HISTORY_TABLE, MedicalHistory_DBHelper.PATIENT_KEY_ID+" = ?",new String[]{Integer.toString(rowId)});
        db.close();
        if (isDelete>0){
            return true;
        }else {
            return false;
        }

    }
    public  boolean Data_Update (MedicalHistory medicalHistory){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_NAME, medicalHistory.getDoctorName());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_DETAILS, medicalHistory.getDetails());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_date, medicalHistory.getDate());
        contentValues.put(MedicalHistory_DBHelper.PATIENT_KEY_IMAGE, medicalHistory.getDate());

        int isUpdate = db.update(MedicalHistory_DBHelper.MEDICAL_HISTORY_TABLE,contentValues, MedicalHistory_DBHelper.PATIENT_KEY_ID+" = ?",new String[]{Integer.toString(medicalHistory.getId())});

        if (isUpdate>0){
            return true;
        }else {
            return false;
        }
    }



}
