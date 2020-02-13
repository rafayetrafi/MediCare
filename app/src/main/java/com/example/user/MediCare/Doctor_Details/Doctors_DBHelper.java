package com.example.user.MediCare.Doctor_Details;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Doctors_DBHelper extends SQLiteOpenHelper{
    private Context context;

    public static final String DB_NAME = "Doctor_db";
    public static final int VERSION = 3;

    public static final String DOCTORS_TABLE = "Doctor_Details";

    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_DETAILS = "Details";
    public static final String KEY_APPIONTMENT_DATE = "Date";
    public static final String KEY_PHONE_NO = "PhoneNo";
    public static final String KEY_EMAIL = "email";


    private static final String Create_Table = "CREATE TABLE "+DOCTORS_TABLE+"( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_NAME+" TEXT, "+KEY_DETAILS+" TEXT, "+KEY_APPIONTMENT_DATE+" TEXT, "+KEY_PHONE_NO+" TEXT, "+KEY_EMAIL+" TEXT)";



    public Doctors_DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Create_Table);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DOCTORS_TABLE);

        onCreate(db);


    }
}
