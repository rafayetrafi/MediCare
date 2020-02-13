package com.example.user.MediCare.Doctor_Details;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.MediCare.FirstActivity;
import com.example.user.MediCare.R;

import java.util.Calendar;

public class Add_Doctor_Activity extends AppCompatActivity {

    EditText nameEditText, detailsEditText, appointmentEditText, phoneNoEditText, emailEditText;


   Doctor_DBManger manger;

   private Button saveButton,deleteButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DoctorsLogin doctorsLogin;


   private int rowId;
   private String doctor_name,doctor_details,doctor_appointmentDate,doctor_phonNo,doctor_email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);


        setTitle("MediCare");

        doctorsLogin = new DoctorsLogin(this);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);




        manger = new Doctor_DBManger(this);


        nameEditText = findViewById(R.id.addDoctor_Name_id);
        detailsEditText = findViewById(R.id.addDoctor_Details_id);
        appointmentEditText = findViewById(R.id.addDoctor_AppointmentDate_id);
        phoneNoEditText = findViewById(R.id.addDoctor_phone_id);
        emailEditText = findViewById(R.id.addDoctor_email_id);

        saveButton = findViewById(R.id.saveButton_id);



        rowId = getIntent().getIntExtra("id",0);

        /*if (rowId > 0){
            setTitle("  Update Doctor");

        }else {
            setTitle("  Add Doctor");

        }*/


        doctor_name = getIntent().getStringExtra("name");
        doctor_details = getIntent().getStringExtra("details");
        doctor_appointmentDate = getIntent().getStringExtra("date");
        doctor_phonNo = getIntent().getStringExtra("phoneNo");
        doctor_email = getIntent().getStringExtra("email");

        nameEditText.setText(doctor_name);
        detailsEditText.setText(doctor_details);
        appointmentEditText.setText(doctor_appointmentDate);
        phoneNoEditText.setText(doctor_phonNo);
        emailEditText.setText(doctor_email);


        if (rowId>0){
            saveButton.setText("Update Button");

            deleteButton = findViewById(R.id.doctorDataDelete_id);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isDeleted = manger.Delete_Doctors_Data(rowId);
                    if (isDeleted){
                        Toast.makeText(Add_Doctor_Activity.this, "Data is Deleted Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Doctor_Activity.this,DoctorList_Activity.class));
                    }else {
                        Toast.makeText(Add_Doctor_Activity.this, "Data Delete Field ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }

        appointmentEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Add_Doctor_Activity.this,android.R.style.
                        Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth + "/" +month+ "/" +year;

                appointmentEditText.setText(date);


            }
        };

    }

    public void Save_Data(View view) {

        Save_info();

    }

    public void Show_All(View view) {
        startActivity(new Intent(Add_Doctor_Activity.this,DoctorList_Activity.class));
    }

    public void Save_info(){
        String name = nameEditText.getText().toString().trim();
        String details = detailsEditText.getText().toString().trim();
        String appointmentDate = appointmentEditText.getText().toString().trim();
        String phoneNo = phoneNoEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty()){
            nameEditText.setError("Please insert data");
        }else if (details.isEmpty()){
            detailsEditText.setError("Please insert data");
        }else if (appointmentDate.isEmpty()){
            detailsEditText.setError("Please insert data");
        }else if (phoneNo.isEmpty()){
            phoneNoEditText.setError("Please insert data");
        }else if (email.isEmpty()){
            emailEditText.setError("Please insert data");
        }else {

            if (rowId>0){

                Doctor doctor = new Doctor(name,details,appointmentDate,phoneNo,email);
                Boolean isUpdate = manger.UpdateData(doctor,rowId);

                if (isUpdate){
                    Toast.makeText(this, "Data Update successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Add_Doctor_Activity.this,DoctorList_Activity.class));
                }else {
                    Toast.makeText(this, " Data Update field ", Toast.LENGTH_SHORT).show();
                }



            }else {
                Doctor doctor = new Doctor(name,details,appointmentDate,phoneNo,email);
                Boolean isInsert = manger.Save_All_Data(doctor);

                if (isInsert){
                    Toast.makeText(this, "Data insert successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Add_Doctor_Activity.this,DoctorList_Activity.class));
                }else {
                    Toast.makeText(this, " Data insert field ", Toast.LENGTH_SHORT).show();
                }

            }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout_doctors, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            /*case R.id.ok_saveData_id:
                Save_info();
                startActivity(new Intent(Add_Doctor_Activity.this, DoctorList_Activity.class));
                break;*/
            case R.id.Home_id:
                startActivity(new Intent(Add_Doctor_Activity.this, FirstActivity.class));
                break;
            case R.id.logOut_id:
                doctorsLogin.Delete();
                /*startActivity(new Intent(Add_Doctor_Activity.this, FirstActivity.class));*/
                Toast.makeText(this, "Sorry !! We will update it very soom", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Sorry Try Again", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);

    }


}