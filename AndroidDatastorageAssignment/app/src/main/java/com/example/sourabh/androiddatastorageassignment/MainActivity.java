package com.example.sourabh.androiddatastorageassignment;

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

    public void onInsertClick(View view)
    {
        Intent i = new Intent(getApplicationContext(),ProductInsertActivity.class);
        startActivity(i);
    }

    public void onSearchClick(View view)
    {
        Intent i=new Intent(getApplicationContext(),ProductSearchActivity.class);
        startActivity(i);
    }
}
