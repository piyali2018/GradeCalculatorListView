package com.ut.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    ReportAdapter reportAdapter;
    private final static String TAG = "GradeCalculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "This is verbose log for onCreate() of Report class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ListView lv = (ListView) findViewById(R.id.listViewResult);
        reportAdapter = new ReportAdapter(Report.this, 0, MainActivity.mSubjectList);
        lv.setAdapter(reportAdapter);


        TextView minMaxAvg = (TextView)findViewById(R.id.MinMaxAvg);
        TextView minMaxAvgVal = (TextView)findViewById(R.id.MinMaxAvgVal);
        Intent intent = getIntent();
        String value ="";
        if (intent.hasExtra("Min")) {
            value = intent.getExtras().getString("Min");
            minMaxAvg.setText("MIN");
        }
        if (intent.hasExtra("Max")) {
            value = intent.getExtras().getString("Max");
            minMaxAvg.setText("MAX");
        }
        if(intent.hasExtra("Avg")) {
            value = intent.getExtras().getString("Avg");
            minMaxAvg.setText("AVG");
        }
        minMaxAvgVal.setText(value);


        /*
        //Result is shown in Textview..
        report = (TextView) findViewById(R.id.Result);
        for (int i = 0; i < MainActivity.mSubjectList.size(); i++) {
            String text = report.getText().toString();
            report.setText(text + MainActivity.mSubjectList.get(i).getName()+"            "+MainActivity.mSubjectList.get(i).getGrade()+"\n\n");
        }*/
    }
}
