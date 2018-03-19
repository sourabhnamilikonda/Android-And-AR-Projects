package com.example.sourabh.lifecycleexplorationapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.backgroundcolor));

        Button btnStartB=findViewById(R.id.btnStartActvtB);
        btnStartB.setOnClickListener(this);
        Button btnDialog=findViewById(R.id.btnDialogShow);
        btnDialog.setOnClickListener(this);
        Button btnCloseApp=findViewById(R.id.btnCloseApp);
        btnCloseApp.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tvCurrent=findViewById(R.id.tvInfoDesc);
        tvCurrent.setText("Thread Count: " + ((GlobalClass) this.getApplication()).getThreadCount().toString());
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
        //((GlobalClass) this.getApplication()).setThreadCount(0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnStartActvtB:
                Intent intentActvtB = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intentActvtB);
                break;
            case R.id.btnDialogShow:
                Intent intentActvtDialog = new Intent(getApplicationContext(), DialogActivity.class);
                startActivity(intentActvtDialog);
                //Dialog dialog = new Dialog(MainActivity.this);
                //dialog.setContentView(R.layout.dialogbrand_layout);
                //dialog.setTitle("Hello");
                //TextView textViewUser = (TextView) dialog.findViewById(R.id.textBrand);
                //textViewUser.setText("Hi");
                //dialog.show();
                break;
            case R.id.btnCloseApp:
                finish();
                break;
        }
    }
}
