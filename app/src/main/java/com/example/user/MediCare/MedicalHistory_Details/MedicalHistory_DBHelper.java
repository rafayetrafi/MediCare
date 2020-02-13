package com.example.user.MediCare.MedicalHistory_Details;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MedicalHistory_DBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String MEDICAL_HISTORY ="Patient_DB";
    public  static final int VERSION_NO =2;

    public static final String MEDICAL_HISTORY_TABLE = "Patient_table";
    public static final String PATIENT_KEY_ID = "Id";
    public static final String PATIENT_KEY_NAME = "Name";
    public static final String PATIENT_KEY_DETAILS = "Age";
    public static final String PATIENT_KEY_date = "Location";
    public static final String PATIENT_KEY_IMAGE = "Image";

    private static final String Patient_Create_Table = " CREATE TABLE "+ MEDICAL_HISTORY_TABLE + "("+PATIENT_KEY_ID+" INTEGER PRIMARY KEY, " +
            ""+PATIENT_KEY_NAME+" TEXT, "+ PATIENT_KEY_DETAILS +" TEXT, "+ PATIENT_KEY_date +" TEXT, "+ PATIENT_KEY_IMAGE +" TEXT)";




    public MedicalHistory_DBHelper(Context context) {
        super(context, MEDICAL_HISTORY, null, VERSION_NO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Patient_Create_Table);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception is created", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEDICAL_HISTORY_TABLE);
        onCreate(db);

    }
}
