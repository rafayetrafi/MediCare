package com.example.user.MediCare.MedicalHistory_Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.MediCare.R;

public class MedicalHistoryLoginActivity extends AppCompatActivity {

    private EditText email_editText,password_editText;
    MedicalHistoryLogin historyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history_login);

        setTitle("  LogIn Page ");


        email_editText = findViewById(R.id.logInEmail_id);
        password_editText = findViewById(R.id.logInPassword_id);
        historyLogin = new MedicalHistoryLogin(this);

        if (!historyLogin.isUserLogin()){
            startActivity(new Intent(MedicalHistoryLoginActivity.this, MedicalHistroyFirstActivity.class));
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
                historyLogin.UserLogIn(email,password);
                startActivity(new Intent(MedicalHistoryLoginActivity.this, MedicalHistory_Form_Activity.class));
                Toast.makeText(this, "Log In Successfully ", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this, "Exception is created ", Toast.LENGTH_SHORT).show();


            }

        }






    }
}

