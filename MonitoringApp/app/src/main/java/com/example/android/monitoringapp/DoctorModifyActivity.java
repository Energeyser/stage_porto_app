package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorModifyActivity extends AppCompatActivity {

    EditText phoneDoctor;
    EditText emailDoctor;
    EditText usernameDoctor;
    EditText passwordDoctor;

    Button Click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_modify);

        //if(phoneDoctor.getText().toString().equals("") || emailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")){
            //Initialized every EditText
            phoneDoctor = (EditText) findViewById(R.id.phone_doctor);
            phoneDoctor.setText("+351 22 508 1400");

            emailDoctor = (EditText) findViewById(R.id.mail_doctor);
            emailDoctor.setText("carla.martins@gmail.com");

            usernameDoctor = (EditText) findViewById(R.id.username_doctor);
            usernameDoctor.setText("c_martins");

            passwordDoctor = (EditText) findViewById(R.id.password_doctor);
            passwordDoctor.setText("blablablablabla");
       // }
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

    //Verify email address
  /* public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }*/

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
        tmp_modification = emailDoctor.getText().toString();
        mailValid = isEmailValid(tmp_modification);

        //phone ok ?
        tmp_modification = phoneDoctor.getText().toString();
        phoneValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (phoneDoctor.getText().toString().equals("") || emailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if(mailValid && phoneValid){
            tmp_modification = phoneDoctor.getText().toString();
            phoneDoctor.setText(tmp_modification);
            tmp_modification = emailDoctor.getText().toString();
            emailDoctor.setText(tmp_modification);
            tmp_modification = usernameDoctor.getText().toString();
            usernameDoctor.setText(tmp_modification);
            tmp_modification = passwordDoctor.getText().toString();
            passwordDoctor.setText(tmp_modification);
            Toast.makeText(getBaseContext(), "Validation of the changes have been made." , Toast.LENGTH_SHORT).show();
            openMonthSummary(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Email or phone number are wrong, please correct the field" , Toast.LENGTH_LONG).show();
        }
    }
}
