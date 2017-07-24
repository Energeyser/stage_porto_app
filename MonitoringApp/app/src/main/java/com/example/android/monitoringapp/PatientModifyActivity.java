package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;


public class PatientModifyActivity extends AppCompatActivity {

    EditText namePatient;
    EditText phonePatient;
    EditText addressPatient;
    EditText namePersonTrustPatient;
    EditText phonePersonTrustPatient;
    TextView namePatientInitials;

    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();

    private Button Click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);

        patientBDD.open();
        patient = patientBDD.getPatient();
        patientBDD.close();

        //Initialized every EditText
        namePatient = (EditText) findViewById(R.id.name_patient);
        namePatient.setText(patient.getName());
        phonePatient = (EditText) findViewById(R.id.phone_patient);
        phonePatient.setText(patient.getPhone());
        addressPatient = (EditText) findViewById(R.id.address_patient);
        addressPatient.setText(patient.getAddress());
        namePersonTrustPatient = (EditText) findViewById(R.id.name_person_trust);
        namePersonTrustPatient.setText(patient.getPot_name());
        phonePersonTrustPatient = (EditText) findViewById(R.id.phone_person_trust);
        phonePersonTrustPatient.setText(patient.getPot_phone());
        //get first two letters of the name (initiales)
        String str = patient.getName();
        String [] tab = str.split(" ");
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }
        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
        namePatientInitials.setText(tmp);

    }


    public void openMonthSummary(View view){
        Intent i = new Intent(this, MonthSummaryActivity.class);
        startActivity(i);
    }

    public void openPatientInformation(View view){
        Intent i = new Intent(this, PatientInformationsActivity.class);
        startActivity(i);
    }

    public void openCalendar(View view){
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
    }

    public void openExport(View view){
        Intent i = new Intent(this, ExportActivity.class);
        startActivity(i);
    }

    public void openDoctorInformation(View view){
        Intent i = new Intent(this, DoctorInformationActivity.class);
        startActivity(i);
    }

    public boolean isPhoneValid(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //verify if EditTexts are not empty
    public void verifyChanges(View v) {
        String tmp_modification="";
        boolean phonePatientValid = false;
        boolean phonePersonTrustValid = false;

        //phone ok ?
        tmp_modification = phonePatient.getText().toString();
        phonePatientValid = isPhoneValid(tmp_modification);
        tmp_modification = phonePersonTrustPatient.getText().toString();
        phonePersonTrustValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (namePatient.getText().toString().equals("") || phonePatient.getText().toString().equals("") || addressPatient.getText().toString().equals("") || namePersonTrustPatient.getText().toString().equals("") || phonePersonTrustPatient.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if (phonePatientValid && phonePersonTrustValid){
            tmp_modification = namePatient.getText().toString();
            namePatient.setText(tmp_modification);
            tmp_modification = phonePatient.getText().toString();
            phonePatient.setText(tmp_modification);
            tmp_modification = addressPatient.getText().toString();
            addressPatient.setText(tmp_modification);
            tmp_modification = phonePersonTrustPatient.getText().toString();
            phonePersonTrustPatient.setText(tmp_modification);
            tmp_modification = namePersonTrustPatient.getText().toString();
            namePersonTrustPatient.setText(tmp_modification);
            //Toast.makeText(getBaseContext(), "Validation of the changes have been made." , Toast.LENGTH_SHORT).show();
            updatePatient();
            openPatientInformation(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Phone number is wrong, please correct the field" , Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get user input from editor and update the patient info into database.
     */
    private void updatePatient() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        this.patient.setId(patient.getID());
        this.patient.setName(namePatient.getText().toString().trim());
        this.patient.setPhone(phonePatient.getText().toString().trim());
        this.patient.setAddress(addressPatient.getText().toString().trim());
        this.patient.setPot_name(phonePersonTrustPatient.getText().toString().trim());
        this.patient.setPot_phone(namePersonTrustPatient.getText().toString().trim());


        this.patientBDD.open();
        this.patientBDD.updatePatient(this.patient);
        this.patientBDD.close();
    }
}
