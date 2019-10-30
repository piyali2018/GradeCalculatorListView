package com.ut.gradecalculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class SubjectListAdapter extends BaseAdapter {
    private Context mContext;
    private final static String TAG = "GradeCalculator";

    public SubjectListAdapter(Context mContext) {
        Log.v(TAG, "This is verbose log for SubjectListAdapter Constructor of SubjectListAdapter class");
        this.mContext = mContext;
    }

    @Override
    public int getViewTypeCount() {
        Log.v(TAG, "This is verbose log for getViewTypeCount() of SubjectListAdapter class");
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        Log.v(TAG, "This is verbose log for getItemViewType() of SubjectListAdapter class");
        return position;
    }

    @Override
    public int getCount() {
        Log.v(TAG, "This is verbose log for getCount() of SubjectListAdapter class");
        return MainActivity.mSubjectList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.v(TAG, "This is verbose log for getItem() of SubjectListAdapter class");
        return MainActivity.mSubjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.v(TAG, "This is verbose log for getItemId() of SubjectListAdapter class");
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.v(TAG, "This is verbose log for getView() of SubjectListAdapter class");
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_view_layout, null, true);

            holder.subName = (TextView)convertView.findViewById(R.id.textView);
            holder.subGrade = (EditText)convertView.findViewById(R.id.editText);

            convertView.setTag(holder);
        }
        else{
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.subName.setText(MainActivity.mSubjectList.get(position).getName());
        holder.subGrade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //When user enter any text value, above method will be called.
                MainActivity.mSubjectList.get(position).setGrade(Integer.parseInt(holder.subGrade.getText().toString()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return convertView;
    }





    private class ViewHolder {

        private TextView subName, subGrade;

    }
}
