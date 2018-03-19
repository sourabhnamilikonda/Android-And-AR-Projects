package com.example.sourabh.androidservicesassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPDFDownloadActivityButton(View v)
    {
        Intent i=new Intent(this, PDFDownloadActivityActivity.class);
        startActivity(i);
    }

    public void onCloseButton(View v)
    {
        finish();
    }
}
