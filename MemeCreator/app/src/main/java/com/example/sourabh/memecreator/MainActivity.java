package com.example.sourabh.memecreator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TopSectionFragment.TopSectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void createMeme(String top,String bottom){
        BottomSectionFragment bsf= (BottomSectionFragment) getFragmentManager().findFragmentById(R.id.fragment2);
        bsf.setMemeText(top, bottom);
    }
}
