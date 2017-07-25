package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.Doctor;
import com.example.android.monitoringapp.Data.DoctorBDD;


public class DoctorModifyActivity extends AppCompatActivity {

    EditText nameDoctor;
    EditText phoneDoctor;
    EditText mailDoctor;
    EditText usernameDoctor;
    EditText passwordDoctor;
    TextView nameDoctorInitials;

    Button Click;

    DoctorBDD doctorBDD = new DoctorBDD(this);
    Doctor doctor = new Doctor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_modify);

        doctorBDD.open();
        doctor = doctorBDD.getDoctor();
        doctorBDD.close();

        nameDoctor = (EditText) findViewById(R.id.name_doctor);
        nameDoctor.setText(doctor.getName());

        phoneDoctor = (EditText) findViewById(R.id.phone_doctor);
        phoneDoctor.setText(doctor.getPhone());

        mailDoctor = (EditText) findViewById(R.id.mail_doctor);
        mailDoctor.setText(doctor.getMail());

        usernameDoctor = (EditText) findViewById(R.id.username_doctor);
        usernameDoctor.setText(doctor.getUsername());

        passwordDoctor = (EditText) findViewById(R.id.password_doctor);
        passwordDoctor.setText(doctor.getPassword());

        String str = doctor.getName();
        String [] tab = str.split(" ");
        //get first two letters of the name (initiales)
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }
        nameDoctorInitials = (TextView) findViewById(R.id.name_doctor_initials);
        nameDoctorInitials.setText(tmp);

    }

    public void openMonthSummary(View view){
        Intent i = new Intent(this, MonthSummaryActivity.class);
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

   public boolean isEmailValid(String email) {
        if (email == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPhoneValid(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //verify if EditTexts are not empty
    public void onClickChanges(View v) {
        String tmp_modification="";
        boolean mailValid = false;
        boolean phoneValid = false;

        //email ok ?
        tmp_modification = mailDoctor.getText().toString();
        mailValid = isEmailValid(tmp_modification);

        //phone ok ?
        tmp_modification = phoneDoctor.getText().toString();
        phoneValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (phoneDoctor.getText().toString().equals("") || mailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if(mailValid && phoneValid){
            tmp_modification = phoneDoctor.getText().toString();
            phoneDoctor.setText(tmp_modification);
            tmp_modification = mailDoctor.getText().toString();
            mailDoctor.setText(tmp_modification);
            tmp_modification = usernameDoctor.getText().toString();
            usernameDoctor.setText(tmp_modification);
            tmp_modification = passwordDoctor.getText().toString();
            passwordDoctor.setText(tmp_modification);
            Toast.makeText(getBaseContext(), "Update successful." , Toast.LENGTH_SHORT).show();
            updateDoctor();
            openDoctorInformation(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Email or phone number are wrong, please correct the field" , Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Get user input from editor and update the doctor info into database.
     */
    private void updateDoctor() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        this.doctor.setName(nameDoctor.getText().toString().trim());
        this.doctor.setPhone(phoneDoctor.getText().toString().trim());
        this.doctor.setMail(mailDoctor.getText().toString().trim());
        this.doctor.setUsername(usernameDoctor.getText().toString().trim());
        this.doctor.setPassword(passwordDoctor.getText().toString().trim());

        try {
            this.doctorBDD.open();
            this.doctorBDD.updateDoctor(doctor);
            this.doctorBDD.close();
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            // If the row ID is < 1, then there was an error with update.
            Toast.makeText(this, "Error with updating doctor", Toast.LENGTH_SHORT).show();
        }
    }
}
