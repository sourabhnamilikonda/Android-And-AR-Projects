package com.example.sourabh.listviewapp;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] foods={"a", "b", "c", "d", "e", "f"};
        ListAdapter testAdapter=new CustomAdapter(this, foods);
        ListView lv=findViewById(R.id.lvItems);
        lv.setAdapter(testAdapter);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this, String.valueOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
