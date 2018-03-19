package com.example.sourabh.lifecycleexplorationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.dialogcolor));

        Button btnClose=findViewById(R.id.btnCloseDialog);
        btnClose.setOnClickListener(this);
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
            case R.id.btnCloseDialog:
                finish();
                break;
        }
    }
}
