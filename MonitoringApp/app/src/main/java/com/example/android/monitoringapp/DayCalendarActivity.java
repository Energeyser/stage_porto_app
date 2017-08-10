package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;

import static com.example.android.monitoringapp.CalendarActivity.DayDate;
import static com.example.android.monitoringapp.MonthSummaryActivity.pbDetected;
import static com.example.android.monitoringapp.R.id.title_day_calendar;

public class DayCalendarActivity extends AppCompatActivity {

    Data data = new Data();
    DataBDD dataBDD = new DataBDD(this);

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
        setContentView(R.layout.activity_day_calendar);

        boolean pbDet = MonthSummaryActivity.problemDetected();
        //if we arrived here because of a problem (see more button)
        if(pbDet){
            //display the date of the issue
            String dayDate = CalendarActivity.returnDayDate();
            TextView titleDayDate = (TextView) findViewById(R.id.title_day_calendar);
            titleDayDate.setText(dayDate);
        }
        else {
            String dayDate = CalendarActivity.returnDayDate();
            TextView titleDayDate = (TextView) findViewById(R.id.title_day_calendar);
            titleDayDate.setText(dayDate);
        }

        //look in the database if there is an alert
        String dayDate = CalendarActivity.returnDayDate();
        System.out.println(dayDate);
        dataBDD.open();
        data = dataBDD.getDataWithDate(dayDate);
        dataBDD.close();

        heartMin = (TextView) findViewById(R.id.value_min_heart_monitor);
        heartMax =(TextView) findViewById(R.id.value_max_heart_monitor);
        heartMean =(TextView) findViewById(R.id.value_mean_heart_monitor);

        heartMin.setText(Integer.toString(data.getMinimum_hr()));
        heartMax.setText(Integer.toString(data.getMaximum_hr()));
        heartMean.setText(Integer.toString(data.getAverage_hr()));

        respiratoryMin = (TextView) findViewById(R.id.value_min_respiratory_rate);
        respiratoryMax =(TextView) findViewById(R.id.value_max_respiratory_rate);
        respiratoryMean =(TextView) findViewById(R.id.value_mean_respiratory_rate);

        respiratoryMin.setText(Integer.toString(data.getMinimum_resp()));
        respiratoryMax.setText(Integer.toString(data.getMaximum_resp()));
        respiratoryMean.setText(Integer.toString(data.getAverage_resp()));

        oxygenMin = (TextView) findViewById(R.id.value_min_oxygen_saturation);
        oxygenMax =(TextView) findViewById(R.id.value_max_oxygen_saturation);
        oxygenMean =(TextView) findViewById(R.id.value_mean_oxygen_saturation);

        oxygenMin.setText(Integer.toString(data.getMinimum_oxy()));
        oxygenMax.setText(Integer.toString(data.getMaximum_oxy()));
        oxygenMean.setText(Integer.toString(data.getAverage_oxy()));

        intraThoracicFluidMin = (TextView) findViewById(R.id.value_min_intra_thoracic_fluid_content);
        intraThoracicFluidMax =(TextView) findViewById(R.id.value_max_intra_thoracic_fluid_content);
        intraThoracicFluidMean =(TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content);

        intraThoracicFluidMin.setText(Integer.toString(data.getMinimum_thoracic_fluid_content()));
        intraThoracicFluidMax.setText(Integer.toString(data.getMaximum_thoracic_fluid_content()));
        intraThoracicFluidMean.setText(Integer.toString(data.getAverage_thoracic_fluid_content()));

        wholeBodyFluidMin = (TextView) findViewById(R.id.value_min_whole_body_fluid_content);
        wholeBodyFluidMax =(TextView) findViewById(R.id.value_max_whole_body_fluid_content);
        wholeBodyFluidMean =(TextView) findViewById(R.id.value_mean_whole_body_fluid_content);

        wholeBodyFluidMin.setText(Integer.toString(data.getMinimum_body_fluid_content()));
        wholeBodyFluidMax.setText(Integer.toString(data.getMaximum_body_fluid_content()));
        wholeBodyFluidMean.setText(Integer.toString(data.getAverage_body_fluid_content()));

        systolicBloodMin =(TextView) findViewById(R.id.value_min_blood_pressure_systolic);
        systolicBloodMax =(TextView) findViewById(R.id.value_max_blood_pressure_systolic);
        systolicBloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure_systolic);

        systolicBloodMin.setText(Integer.toString(data.getMinimum_systolic_blood_pressure()));
        systolicBloodMax.setText(Integer.toString(data.getMaximum_systolic_blood_pressure()));
        systolicBloodMean.setText(Integer.toString(data.getAverage_systolic_blood_pressure()));

        diastolicBloodMin =(TextView) findViewById(R.id.value_min_blood_pressure_diastolic);
        diastolicBloodMax =(TextView) findViewById(R.id.value_max_blood_pressure_diastolic);
        diastolicBloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure_diastolic);

        diastolicBloodMin.setText(Integer.toString(data.getMinimum_diastolic_blood_pressure()));
        diastolicBloodMax.setText(Integer.toString(data.getMaximum_diastolic_blood_pressure()));
        diastolicBloodMean.setText(Integer.toString(data.getAverage_diastolic_blood_pressure()));

        salinityMin =(TextView) findViewById(R.id.value_min_sodium_chloride);
        salinityMax =(TextView) findViewById(R.id.value_max_sodium_chloride);
        salinityMean =(TextView) findViewById(R.id.value_mean_sodium_chloride);

        salinityMin.setText(Integer.toString(data.getMinimum_sodium_chloride()));
        salinityMax.setText(Integer.toString(data.getMaximum_sodium_chloride()));
        salinityMean.setText(Integer.toString(data.getAverage_sodium_chloride()));
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

    public void openWeekCalendar(View view){
        Intent i = new Intent(this, WeekCalendarActivity.class);
        startActivity(i);
    }

}
