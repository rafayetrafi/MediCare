package com.example.user.MediCare.Doctor_Details;

import android.content.Context;
import android.content.SharedPreferences;

public class DoctorsLogin {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public DoctorsLogin (Context context){

        this.context = context;
        preferences = context.getSharedPreferences("UserLogin",Context.MODE_PRIVATE);
        editor = preferences.edit();


    }
    public  void UserLogIn(String email,String password){

        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();

    }
    public void Delete(){
        editor.clear();
        editor.commit();
    }

    public boolean isUserLogin(){

        boolean isEmailEmpty = preferences.getString("Email","").isEmpty();
        boolean isPassordEmpty = preferences.getString("Password","").isEmpty();
        return isEmailEmpty || isPassordEmpty;

    }

}
