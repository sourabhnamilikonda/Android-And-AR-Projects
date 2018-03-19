package com.example.sourabh.listviewapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sourabh on 10-Mar-18.
 */

class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(@NonNull Context context, String[] resource) {
        super(context, R.layout.customrow_layout ,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater infl=LayoutInflater.from(getContext());
        View cv=infl.inflate(R.layout.customrow_layout, parent, false);

        String singlefood=getItem(position);
        TextView tv=cv.findViewById(R.id.tvName);
        ImageView iv=cv.findViewById(R.id.imageView);
        tv.setText(singlefood);
        iv.setImageResource(R.drawable.ntitled);
        return cv;
    }
}
