package com.example.sourabh.lifecycleexplorationapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.backgroundcolor));

        TextView tv = findViewById(R.id.tvInfoDesc);
        tv.setMovementMethod(new ScrollingMovementMethod());

        Button btnFinishB=findViewById(R.id.btnFinishB);
        btnFinishB.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Integer iCurrentCount = ((GlobalClass) this.getApplication()).getThreadCount();
        ((GlobalClass) this.getApplication()).setThreadCount(iCurrentCount + 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnFinishB:
                finish();
                break;
        }
    }
}
