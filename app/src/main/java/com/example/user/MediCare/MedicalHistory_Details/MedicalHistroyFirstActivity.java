package com.example.user.MediCare.MedicalHistory_Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.MediCare.Doctor_Details.DoctorList_Activity;
import com.example.user.MediCare.R;

public class MedicalHistroyFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_histroy_first);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        setTitle("  Hospital Category");


    }

    public void AddHistory(View view) {

        startActivity(new Intent(MedicalHistroyFirstActivity.this, MedicalHistory_Form_Activity.class));
    }


    public void HistoryList(View view) {
        startActivity(new Intent(MedicalHistroyFirstActivity.this, MedicalHistoryList_Activity.class));

    }

    public void Update(View view) {
        startActivity(new Intent(MedicalHistroyFirstActivity.this, MedicalHistoryList_Activity.class));

    }

    public void MDoctorList(View view) {
        Intent intent =new Intent(MedicalHistroyFirstActivity.this, DoctorList_Activity.class);
        startActivity(intent);

    }

    public void Help(View view) {
    }

    public void Advice(View view) {
    }
}
