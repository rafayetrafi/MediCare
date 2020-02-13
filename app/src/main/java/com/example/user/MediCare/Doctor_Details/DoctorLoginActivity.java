package com.example.user.MediCare.Doctor_Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.MediCare.R;

public class DoctorLoginActivity extends AppCompatActivity {

    private EditText email_editText,password_editText;
    DoctorsLogin doctorsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        setTitle("Doctor LogIn Page ");


        email_editText = findViewById(R.id.logInEmail_id);
        password_editText = findViewById(R.id.logInPassword_id);
        doctorsLogin = new DoctorsLogin(this);

        if (!doctorsLogin.isUserLogin()){
            startActivity(new Intent(DoctorLoginActivity.this, Doctors_Main_Activity.class));
            finish();
        }


    }

    public void Login(View view) {

        String email = email_editText.getText().toString();
        String password = password_editText.getText().toString();

        if (email.isEmpty()){


        }else if (password.isEmpty()){


        }else {
            try{
                doctorsLogin.UserLogIn(email,password);
                startActivity(new Intent(DoctorLoginActivity.this, Doctors_Main_Activity.class));
                Toast.makeText(this, "Log In Successfully ", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this, "Exception is created ", Toast.LENGTH_SHORT).show();


            }

        }






    }
}
