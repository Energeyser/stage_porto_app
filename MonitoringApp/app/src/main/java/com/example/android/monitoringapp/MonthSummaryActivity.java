package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.android.monitoringapp.Alert.Alert;
import com.example.android.monitoringapp.Data.DataBDD;
import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;


public class MonthSummaryActivity extends AppCompatActivity {

    static int pbDetected = 0;
    static boolean pb = false;

    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();

    DataBDD dataBDD = new DataBDD(this);
    Data dataMonth = new Data();
    Data dataTest = new Data();

    TextView namePatient;
    TextView namePatientInitials;

    //initialized TextView with data of the month
    TextView heartMean;
    TextView heartMin;
    TextView heartMax;

    TextView respiratoryMean;
    TextView respiratoryMin;
    TextView respiratoryMax;

    TextView intraThoracicFluidMean;
    TextView intraThoracicFluidMin;
    TextView intraThoracicFluidMax;

    TextView wholeBodyFluidMean;
    TextView wholeBodyFluidMin;
    TextView wholeBodyFluidMax;

    TextView systolicBloodMean;
    TextView systolicBloodMin;
    TextView systolicBloodMax;

    TextView diastolicBloodMean;
    TextView diastolicBloodMin;
    TextView diastolicBloodMax;

    TextView salinityMean;
    TextView salinityMin;
    TextView salinityMax;

    TextView oxygenMean;
    TextView oxygenMin;
    TextView oxygenMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_summary);
        //boolean messageSent;
        //Alert alert = new Alert();

        //messageSent = alert.SendMessage("+33673579282","Test");

        patientBDD.open();
        patient = patientBDD.getPatient();
        patientBDD.close();

        namePatient = (TextView) findViewById(R.id.name_patient);
        namePatient.setText(patient.getName());

        //get first two letters of the name (initiales)
        String str = patient.getName();
        String [] tab = str.split(" ");
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }

        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
        namePatientInitials.setText(tmp);


        dataBDD.open();
        dataMonth = dataBDD.getLastMonth();
        dataBDD.close();

        pbDetected = dataMonth.getAlert();

        if(pbDetected != 0) {
            LinearLayout pbView = (LinearLayout) findViewById(R.id.probleme_detected);
            pbView.setVisibility(View.VISIBLE);
        }

        heartMin = (TextView) findViewById(R.id.value_min_heart_monitor);
        heartMax =(TextView) findViewById(R.id.value_max_heart_monitor);
        heartMean =(TextView) findViewById(R.id.value_mean_heart_monitor);

        heartMin.setText(Integer.toString(dataMonth.getMinimum_hr()));
        heartMax.setText(Integer.toString(dataMonth.getMaximum_hr()));
        heartMean.setText(Integer.toString(dataMonth.getAverage_hr()));

        respiratoryMin = (TextView) findViewById(R.id.value_min_respiratory_rate);
        respiratoryMax =(TextView) findViewById(R.id.value_max_respiratory_rate);
        respiratoryMean =(TextView) findViewById(R.id.value_mean_respiratory_rate);

        respiratoryMin.setText(Integer.toString(dataMonth.getMinimum_resp()));
        respiratoryMax.setText(Integer.toString(dataMonth.getMaximum_resp()));
        respiratoryMean.setText(Integer.toString(dataMonth.getAverage_resp()));

        oxygenMin = (TextView) findViewById(R.id.value_min_oxygen_saturation);
        oxygenMax =(TextView) findViewById(R.id.value_max_oxygen_saturation);
        oxygenMean =(TextView) findViewById(R.id.value_mean_oxygen_saturation);

        oxygenMin.setText(Integer.toString(dataMonth.getMinimum_oxy()));
        oxygenMax.setText(Integer.toString(dataMonth.getMaximum_oxy()));
        oxygenMean.setText(Integer.toString(dataMonth.getAverage_oxy()));

        intraThoracicFluidMin = (TextView) findViewById(R.id.value_min_intra_thoracic_fluid_content);
        intraThoracicFluidMax =(TextView) findViewById(R.id.value_max_intra_thoracic_fluid_content);
        intraThoracicFluidMean =(TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content);

        intraThoracicFluidMin.setText(Integer.toString(dataMonth.getMinimum_thoracic_fluid_content()));
        intraThoracicFluidMax.setText(Integer.toString(dataMonth.getMaximum_thoracic_fluid_content()));
        intraThoracicFluidMean.setText(Integer.toString(dataMonth.getAverage_thoracic_fluid_content()));

        wholeBodyFluidMin = (TextView) findViewById(R.id.value_min_whole_body_fluid_content);
        wholeBodyFluidMax =(TextView) findViewById(R.id.value_max_whole_body_fluid_content);
        wholeBodyFluidMean =(TextView) findViewById(R.id.value_mean_whole_body_fluid_content);

        wholeBodyFluidMin.setText(Integer.toString(dataMonth.getMinimum_body_fluid_content()));
        wholeBodyFluidMax.setText(Integer.toString(dataMonth.getMaximum_body_fluid_content()));
        wholeBodyFluidMean.setText(Integer.toString(dataMonth.getAverage_body_fluid_content()));

        systolicBloodMin =(TextView) findViewById(R.id.value_min_blood_pressure_systolic);
        systolicBloodMax =(TextView) findViewById(R.id.value_max_blood_pressure_systolic);
        systolicBloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure_systolic);

        systolicBloodMin.setText(Integer.toString(dataMonth.getMinimum_systolic_blood_pressure()));
        systolicBloodMax.setText(Integer.toString(dataMonth.getMaximum_systolic_blood_pressure()));
        systolicBloodMean.setText(Integer.toString(dataMonth.getAverage_systolic_blood_pressure()));

        diastolicBloodMin =(TextView) findViewById(R.id.value_min_blood_pressure_diastolic);
        diastolicBloodMax =(TextView) findViewById(R.id.value_max_blood_pressure_diastolic);
        diastolicBloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure_diastolic);

        diastolicBloodMin.setText(Integer.toString(dataMonth.getMinimum_diastolic_blood_pressure()));
        diastolicBloodMax.setText(Integer.toString(dataMonth.getMaximum_diastolic_blood_pressure()));
        diastolicBloodMean.setText(Integer.toString(dataMonth.getAverage_diastolic_blood_pressure()));

        salinityMin =(TextView) findViewById(R.id.value_min_sodium_chloride);
        salinityMax =(TextView) findViewById(R.id.value_max_sodium_chloride);
        salinityMean =(TextView) findViewById(R.id.value_mean_sodium_chloride);

        salinityMin.setText(Double.toString(dataMonth.getMinimum_sodium_chloride()));
        salinityMax.setText(Double.toString(dataMonth.getMaximum_sodium_chloride()));
        salinityMean.setText(Double.toString(dataMonth.getAverage_sodium_chloride()));

    }


    public static boolean problemDetected(){
        if(pbDetected != 0) {
            pb = true;
        }
        return pb;
    }

    public void openSeeMorePb(View view){
        if(pbDetected != 0) {
            Intent i = new Intent(this, CalendarActivity.class);
            startActivity(i);
        }
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

    public void openPatientInformation(View view){
        Intent i = new Intent(this, PatientInformationsActivity.class);
        startActivity(i);
    }

}
