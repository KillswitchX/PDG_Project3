package com.icesi.pdg_project.View;

import android.view.View;
import android.widget.TextView;

import com.icesi.pdg_project.R;


public class ViewHolder {
    public TextView mTextView;
    public ViewHolder(View view) {
        mTextView = view.findViewById(R.id.textView);
    }
}