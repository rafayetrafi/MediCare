package com.example.user.MediCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.MediCare.Doctor_Details.Doctors_Main_Activity;
import com.example.user.MediCare.MedicalHistory_Details.MedicalHistroyFirstActivity;
import com.example.user.MediCare.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        setTitle("  MediCare ");




    }

    public void Doctors(View view) {
        startActivity(new Intent(FirstActivity.this, Doctors_Main_Activity.class));
    }

    public void MedicalHistory(View view) {
        startActivity(new Intent(FirstActivity.this, MedicalHistroyFirstActivity.class));
    }
}
