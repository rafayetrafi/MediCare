package com.example.user.MediCare.Doctor_Details;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.MediCare.R;

public class DoctorDataUpdateActivity extends AppCompatActivity {

    private TextView doctorNameTV,doctorDetailsTV,doctorAppointmentTV,doctorPhoneNoTextView,doctorEmailTV;

    private int rowId;
    private String doctor_name,doctor_details,doctor_appointmentDate,doctor_phonNo,doctor_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_data_update);


        setTitle("  MediCare");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        doctorNameTV = findViewById(R.id.doctorNameUpdateTV_id);
        doctorDetailsTV = findViewById(R.id.doctorDetailsUpdateTV_id);
        doctorAppointmentTV = findViewById(R.id.doctorAppointmentUpdateTV_id);
        doctorPhoneNoTextView = findViewById(R.id.doctorPhoneNoUpdateTV_id);
        doctorEmailTV = findViewById(R.id.doctorEmailUpdateTV_id);


        rowId = getIntent().getIntExtra("id",0);




        doctor_name = getIntent().getStringExtra("name");
        doctor_details = getIntent().getStringExtra("details");
        doctor_appointmentDate = getIntent().getStringExtra("date");
        doctor_phonNo = getIntent().getStringExtra("phoneNo");
        doctor_email = getIntent().getStringExtra("email");

        doctorNameTV.setText(doctor_name);
        doctorDetailsTV.setText(doctor_details);
        doctorAppointmentTV.setText(doctor_appointmentDate);

        doctorPhoneNoTextView.setText(doctor_phonNo);

        doctorEmailTV.setText(doctor_email);


        doctorPhoneNoTextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               call(doctor_phonNo);
           }
       });



    }

    public void Update(View view) {

        Intent intent = new Intent(DoctorDataUpdateActivity.this,Add_Doctor_Activity.class);

        intent.putExtra("id",rowId);

        intent.putExtra("name",doctor_name);
        intent.putExtra("details",doctor_details);
        intent.putExtra("date",doctor_appointmentDate);
        intent.putExtra("phoneNo",doctor_phonNo);
        intent.putExtra("email",doctor_email);
        startActivity(intent);

    }


    public void CallToDoctor(View view) {
        call(doctor_phonNo);

    }

    private void call(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 456);
            return;
        }
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
