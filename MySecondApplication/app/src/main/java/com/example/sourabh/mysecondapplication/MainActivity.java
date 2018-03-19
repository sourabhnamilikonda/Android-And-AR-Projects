package com.example.sourabh.mysecondapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        RelativeLayout rellay = new RelativeLayout(this);
        rellay.setBackgroundColor(Color.GREEN);

        EditText username = new EditText(this);
        username.setId((int)2);

        Button btn = new Button(this);
        btn.setId((int)1);
        btn.setText("click");
        btn.setBackgroundColor(Color.RED);

        RelativeLayout.LayoutParams usernameDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        usernameDetails.addRule(RelativeLayout.ABOVE, btn.getId());
        usernameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        usernameDetails.setMargins(0,0,0,50);

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        Resources r = getResources();
        int pixels = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        username.setWidth(pixels);

        rellay.addView(username, usernameDetails);
        rellay.addView(btn, btnDetails);
        setContentView(rellay);
    }
}
