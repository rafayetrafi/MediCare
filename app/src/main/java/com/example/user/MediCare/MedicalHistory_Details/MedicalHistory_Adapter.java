package com.example.user.MediCare.MedicalHistory_Details;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.MediCare.R;

import java.util.ArrayList;

public class MedicalHistory_Adapter extends ArrayAdapter<MedicalHistory>{

    ArrayList<MedicalHistory> medicalHistoryList = new ArrayList<>();
    Context mContext;



    public MedicalHistory_Adapter(@NonNull Context context, @NonNull ArrayList<MedicalHistory> medicalHistoryList) {
        super(context, R.layout.patient_view_model, medicalHistoryList);
        mContext = context;
        this.medicalHistoryList = medicalHistoryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.patient_view_model,parent,false);

        TextView doctorNameTV = convertView.findViewById(R.id.view_doctor_Name_id);
        TextView detailsTV = convertView.findViewById(R.id.view_details_id);
        TextView dateTV = convertView.findViewById(R.id.view_date_id);

        ImageView imageView = convertView.findViewById(R.id.view_prescription_image_id);

        MedicalHistory medicalHistory = medicalHistoryList.get(position);

        Bitmap imageBitmap = decodeBase64(medicalHistoryList.get(position).getImage());

        doctorNameTV.setText(medicalHistory.getDoctorName());
        detailsTV.setText(medicalHistory.getDetails());
        dateTV.setText(medicalHistory.getDate());
        imageView.setImageBitmap(imageBitmap);



        return convertView;
    }

    public static Bitmap decodeBase64(String input) {

        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
