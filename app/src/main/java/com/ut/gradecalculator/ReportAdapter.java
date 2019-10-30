package com.ut.gradecalculator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ReportAdapter extends ArrayAdapter<Subject> {

    private Activity activity;
    private ArrayList<Subject> subject;
    private static LayoutInflater inflater = null;
    private final static String TAG = "GradeCalculator";

    public ReportAdapter(Activity activity, int resource, ArrayList<Subject>subject) {
        super(activity, resource,subject);
        Log.v(TAG, "This is verbose log for ReportAdapter Constructor of ReportAdapter class");
        try{
            this.activity = activity;
            this.subject = subject;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        catch(Exception e){

        }
    }

    public int getCount(){
        Log.v(TAG, "This is verbose log for getCount() of ReportAdapter class");
        return subject.size();
    }

    public Subject getItem(Subject position){
        Log.v(TAG, "This is verbose log for getItem() of ReportAdapter class");
        return position;
    }

    public long getItemId(int position){
        Log.v(TAG, "This is verbose log for getItemId() of ReportAdapter class");
        return position;
    }

    public static class ViewHolder{
        public TextView subName;
        public TextView subGrade;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Log.v(TAG, "This is verbose log for getView() of ReportAdapter class");
        View vi = convertView;
        final ViewHolder holder;
        try{
            if (convertView == null) {
                vi = inflater.inflate(R.layout.adapter_report_layout, null);
                holder = new ViewHolder();

                holder.subName = (TextView) vi.findViewById(R.id.textViewName);
                holder.subGrade = (TextView) vi.findViewById(R.id.textViewGrade);


                vi.setTag(holder);
            }
            else{
                holder = (ViewHolder)vi.getTag();
            }
            holder.subName.setText(subject.get(position).getName());
            holder.subGrade.setText(String.valueOf(subject.get(position).getGrade()));
        }
        catch(Exception e){

        }
        return vi;
    }
}
