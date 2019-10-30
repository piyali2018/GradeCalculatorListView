package com.ut.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static ListView list_view;
    private SubjectListAdapter adapter;
    public static ArrayList<Subject> mSubjectList;
    private static Button btnMin;
    private static Button btnMax;
    private static Button btnAvg;
    private String[] subjectList = new String[]{"IOS", "Android", "Swift", "JAVA"};
    private final static String TAG = "GradeCalculator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "This is verbose log for onCreate() of MainActivity class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView();
        onClickButtonListener();

    }

    public void listView(){
        Log.v(TAG, "This is verbose log for listView() of MainActivity class for populating ListView");
        list_view = (ListView)findViewById(R.id.listView);

        //add Sample data for list
        mSubjectList = getSubject();

        //Init Adapter
        adapter =new SubjectListAdapter(getApplicationContext());
        list_view.setAdapter(adapter);
    }

    private ArrayList<Subject> getSubject() {
        Log.v(TAG, "This is verbose log for getSubject() of MainActivity class for populating ListView with SubjectList");
        ArrayList<Subject> list = new ArrayList<>();
        for (int i = 0; i < subjectList.length; i++) {

            Subject subject = new Subject();
            subject.setId(i+1);
            subject.setName(subjectList[i]);
            list.add(subject);
        }
        return list;
    }

    public void onClickButtonListener() {
        btnMin = (Button)findViewById(R.id.buttonMin);
        btnMax = (Button)findViewById(R.id.buttonMax);
        btnAvg = (Button)findViewById(R.id.buttonAvg);

        btnMin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Min button Clicked of MainActivity class");
                        String min= getMin();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Min", min);
                        startActivity(intent);
                    }
                }

        );

        btnMax.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Max button Clicked of MainActivity class");
                        String max= getMax();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Max", max);
                        startActivity(intent);
                    }
                }

        );

        btnAvg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Avg button Clicked of MainActivity class");
                        String avg= getAvg();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Avg", avg);
                        startActivity(intent);
                    }
                }

        );
        /*View inflatedView = getLayoutInflater().inflate(R.layout.adapter_view_layout, null);
        EditText editText =(EditText)inflatedView.findViewById(R.id.editText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });*/

    }

    public void hideKeyboard(View view) {
        Log.v(TAG, "This is verbose log for hideKeyboard() of MainActivity class");
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getMin(){
        Log.v(TAG, "This is verbose log for getMin()of MainActivity class");
        Subject minGrade = Collections.min(mSubjectList, new SubComp());
        int minVal= minGrade.getGrade();
        String min = String.valueOf(minVal);
        return min;
    }

    private String getMax(){
        Log.v(TAG, "This is verbose log for getMax()of MainActivity class");
        Subject maxGrade = Collections.max(mSubjectList, new SubComp());
        int maxVal= maxGrade.getGrade();
        String max = String.valueOf(maxVal);
        return max;
    }

    private String getAvg(){
        Log.v(TAG, "This is verbose log for getAvg()of MainActivity class");
        String avg="";
        int sum =0, avrg=0;
        for (Subject sub : mSubjectList){
            int gradeSum = sub.getGrade();
            sum += gradeSum;
        }
        avrg= sum/4;
        avg = String.valueOf(avrg);
        return avg;
    }

}
