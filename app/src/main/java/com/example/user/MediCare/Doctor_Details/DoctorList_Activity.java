package com.example.user.MediCare.Doctor_Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.MediCare.R;

import java.util.ArrayList;

public class DoctorList_Activity extends AppCompatActivity {

    private ListView doctorListView;

    DoctorAdapter doctorAdapter;

    ArrayList<Doctor> doctorList;

    Doctor_DBManger doctor_dbManger;
    private  int rowId;
    private String name,details,date,phoneNo,email;

    private String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        setTitle("  Doctors List");


        doctorListView = findViewById(R.id.doctor_listView);


        doctor_dbManger = new Doctor_DBManger(getApplicationContext());

        doctorList = doctor_dbManger.Get_All_Doctor_List();



        doctorAdapter = new DoctorAdapter(DoctorList_Activity.this,doctorList);
        doctorListView.setAdapter(doctorAdapter);




            doctorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    rowId = doctorList.get(position).getId();

                    name = doctorList.get(position).getUserName();
                    details = doctorList.get(position).getDetails();
                    date = doctorList.get(position).getAppionmentDate();
                    phoneNo = doctorList.get(position).getPhoneNo();
                    email = doctorList.get(position).getEmail();

                    Intent intent = new Intent(DoctorList_Activity.this,DoctorDataUpdateActivity.class);

                    intent.putExtra("id",rowId);

                    intent.putExtra("name",name);
                    intent.putExtra("details",details);
                    intent.putExtra("date",date);
                    intent.putExtra("phoneNo",phoneNo);
                    intent.putExtra("email",email);
                    startActivity(intent);

                }
            });





    }



}
