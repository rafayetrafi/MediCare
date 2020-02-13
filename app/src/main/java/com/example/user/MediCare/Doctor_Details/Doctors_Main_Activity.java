package com.example.user.MediCare.Doctor_Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.user.MediCare.FirstActivity;
import com.example.user.MediCare.R;

public class Doctors_Main_Activity extends AppCompatActivity {

    DoctorsLogin doctorsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_main_menu);

        doctorsLogin = new DoctorsLogin(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(false);


        setTitle("  Hospital Doctors ");


    }

    public void AddDoctor(View view) {

        startActivity(new Intent(Doctors_Main_Activity.this, Add_Doctor_Activity.class));
    }


    public void DoctorList(View view) {
        startActivity(new Intent(Doctors_Main_Activity.this, DoctorList_Activity.class));

    }

    public void Update(View view) {
        startActivity(new Intent(Doctors_Main_Activity.this, DoctorList_Activity.class));

    }

    public void Help(View view) {
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
            case R.id.Home_id:
                startActivity(new Intent(Doctors_Main_Activity.this, FirstActivity.class));
                break;
            case R.id.logOut_id:
                doctorsLogin.Delete();
                /*startActivity(new Intent(Doctors_Main_Activity.this, FirstActivity.class));*/
                Toast.makeText(this, "Sorry !! We will update it very soon", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Please Choose an Option", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);

    }

}
