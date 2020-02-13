package com.example.user.MediCare.MedicalHistory_Details;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.MediCare.R;

public class MedicalHistory_Data_Rewrite_Activity extends AppCompatActivity {

    private TextView all_Data_textView;
    private  int rowId;
    private String doctorName,details,date;
    private MedicalHistory_DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__data__rewrite_);

        setTitle("  Data update ");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);





        manager = new MedicalHistory_DBManager(this);

        all_Data_textView = findViewById(R.id.patientShowForReWrite_id);

        rowId = getIntent().getIntExtra("id",0);

        doctorName = getIntent().getStringExtra("doctorName");
        details = getIntent().getStringExtra("details");
        date = getIntent().getStringExtra("date");

        all_Data_textView.setText(doctorName+"\n"+details+"\n"+date);

    }

    public void Update(View view) {

        Intent intent = new Intent(MedicalHistory_Data_Rewrite_Activity.this,MedicalHistory_Form_Activity.class);

        intent.putExtra("id",rowId);

        intent.putExtra("doctorName",doctorName);
        intent.putExtra("details",details);
        intent.putExtra("date",date);
        startActivity(intent);

    }

    public void Delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure to Delete Data");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isDelete = manager.Delete_patient_data(rowId);
                if (isDelete){
                    Toast.makeText(MedicalHistory_Data_Rewrite_Activity.this, " Successfully Deleted ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MedicalHistory_Data_Rewrite_Activity.this,MedicalHistoryList_Activity.class));
                }else {
                    Toast.makeText(MedicalHistory_Data_Rewrite_Activity.this, " Field ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();
    }
}
