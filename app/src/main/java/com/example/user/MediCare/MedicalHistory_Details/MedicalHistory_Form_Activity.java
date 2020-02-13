package com.example.user.MediCare.MedicalHistory_Details;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.MediCare.Doctor_Details.DoctorList_Activity;
import com.example.user.MediCare.Doctor_Details.Doctor_DBManger;
import com.example.user.MediCare.FirstActivity;
import com.example.user.MediCare.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MedicalHistory_Form_Activity extends AppCompatActivity {

    private EditText details_editText,date_editText;
    private Button save_button,show_button;
    private TextView tvTitle;

    private ImageView image_View;
    private MedicalHistory_DBManager manager;
    private Doctor_DBManger doctor_dbManger;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public static final  int REQUEST_CAPTURE = 100,SELECT_FILE = 0;
    private String mPhotoData;
    private Bitmap secondBitmap;

    private  int history_rowId;
    private String patient_doctorName,patient_details,patient_date;
    MedicalHistoryLogin historyLogin;
    private String doctors_Name;
    private Spinner spinner;
    ArrayList<String> doctorNameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalhistory_);


        getCameraPermission();

        historyLogin = new MedicalHistoryLogin(this);
        doctor_dbManger = new Doctor_DBManger(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_medical);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        manager = new MedicalHistory_DBManager(this);

        spinner = findViewById(R.id.spannerDoctorNameList_id);
        details_editText =findViewById(R.id.patient_Details_id);
        date_editText =findViewById(R.id.patient_Date_id);

        tvTitle = findViewById(R.id.tvTitleAddHealthCenter);


        image_View = findViewById(R.id.prescription_image_id);

        save_button = findViewById(R.id.patient_Save_id);
        show_button = findViewById(R.id.patient_ShowAllData_id);

        setTitle("MediCare");


        image_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });



        date_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MedicalHistory_Form_Activity.this,android.R.style.
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

                date_editText.setText(date);
            }
        };


        history_rowId = getIntent().getIntExtra("id",0);
        patient_doctorName = getIntent().getStringExtra("doctorName");
        patient_details = getIntent().getStringExtra("details");
        patient_date = getIntent().getStringExtra("date");

        if (history_rowId >0){
            //setTitle("  Update Health Center ");
            tvTitle.setText("Update Health Center");

        }else {

            //setTitle("  Add Health Center ");
            tvTitle.setText("Add Health Center");
        }



        doctorNameList = doctor_dbManger.DoctorNameList();
        details_editText.setText(patient_details);
        date_editText.setText(patient_date);

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doctorNameList);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctors_Name = doctorNameList.get(position) ;
                Toast.makeText(MedicalHistory_Form_Activity.this, " Doctor Name "+doctors_Name, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (history_rowId >0){

            save_button.setText("Update Button ");
        }
        if (!hasCamara()){
            image_View.setEnabled(false);

        }




        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();

            }
        });
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicalHistory_Form_Activity.this,MedicalHistoryList_Activity.class));

            }
        });

    }
    public void SaveData(){

        String docName = doctors_Name.toString();

        String details = details_editText.getText().toString();
        String date = date_editText.getText().toString();

       if (details.isEmpty()){
            details_editText.setError("please insert data");
        }else if (date.isEmpty()){
            date_editText.setError("please insert data");
        }else {

            if (history_rowId >0){


                    MedicalHistory medicalHistory = new MedicalHistory(docName,details,date,mPhotoData);
                    boolean isUpdate = manager.Data_Update(medicalHistory);
                    if (isUpdate){

                        Toast.makeText(this, "Update is Successful ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MedicalHistory_Form_Activity.this,MedicalHistoryList_Activity.class));
                    }else {
                        Toast.makeText(this, "Update Field ", Toast.LENGTH_SHORT).show();
                    }




            }else {

                MedicalHistory medicalHistory = new MedicalHistory(docName,details,date,mPhotoData);
                boolean isdataInsert = manager.Save_All_Patient_Data(medicalHistory);
                if (isdataInsert){

                    Toast.makeText(this, "Data Insert Successfully ", Toast.LENGTH_SHORT).show();
                    /*startActivity(new Intent(MedicalHistory_Form_Activity.this,MedicalHistoryList_Activity.class));*/
                }else {
                    Toast.makeText(this, "Data Insert Failed ", Toast.LENGTH_SHORT).show();
                }

            }

        }





        }



    public Boolean hasCamara(){


        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }

    public void SelectImage(){
        final CharSequence [] items = {"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MedicalHistory_Form_Activity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Camera")){

                    try{
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,REQUEST_CAPTURE);

                    }catch (Exception e){

                        Toast.makeText(MedicalHistory_Form_Activity.this, "Some missing", Toast.LENGTH_SHORT).show();

                    }

                }else if (items[i].equals("Gallery")){

                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"),SELECT_FILE);
                }else if (items[i].equals("Cancel")){
                    dialog.dismiss();

                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK ){

            if (requestCode ==REQUEST_CAPTURE) {
                Bundle bundle = data.getExtras();
                Bitmap photo = (Bitmap) bundle.get("data");
                mPhotoData = encodeToBase64(photo,Bitmap.CompressFormat.JPEG,100);
                //------- Before Database
               // secondBitmap = decodeBase64(mPhotoData);
                //------- After Database
                image_View.setImageBitmap(photo);


            }else if (requestCode == SELECT_FILE){

                Uri selectImage_Uri = data.getData();
                image_View.setImageURI(selectImage_Uri);
            }
        }


    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.ok_saveData_id:
                SaveData();
                break;
            case R.id.doctorList_id:
                startActivity(new Intent(MedicalHistory_Form_Activity.this, DoctorList_Activity.class));
                break;
                case R.id.logOut_id:
                  historyLogin.Delete();
                startActivity(new Intent(MedicalHistory_Form_Activity.this, FirstActivity.class));
                break;
            default:
                Toast.makeText(this, "Sorry Try Again", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);

      }
    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAPTURE);
            }
        }
    }
}