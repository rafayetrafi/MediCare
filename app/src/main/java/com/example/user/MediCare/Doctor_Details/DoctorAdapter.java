package com.example.user.MediCare.Doctor_Details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.MediCare.R;

import java.util.ArrayList;


public class DoctorAdapter extends ArrayAdapter<Doctor> {
    ArrayList<Doctor>doctorList= new ArrayList<>();
    Context mContext;

    public DoctorAdapter(@NonNull Context context, @NonNull ArrayList<Doctor>doctorList) {
        super(context, R.layout.view_model, doctorList);
        mContext  = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.view_model,parent,false);

        TextView usernameTV = convertView.findViewById(R.id.view_userName_id);
        TextView detailsTV = convertView.findViewById(R.id.view_Details_id);
        TextView phoneNoTV = convertView.findViewById(R.id.view_Appointment_Date_id);
        TextView appointmentDateTV = convertView.findViewById(R.id.view_PhoneNo_id);
        TextView emailTV = convertView.findViewById(R.id.view_Email_id);


        Doctor  doctor = doctorList.get(position);


        usernameTV.setText(doctor.getUserName());
        detailsTV.setText(doctor.getDetails());
        appointmentDateTV.setText(doctor.getAppionmentDate());
        phoneNoTV.setText(doctor.getPhoneNo());
        emailTV.setText(doctor.getEmail());

        return convertView;
    }
}
