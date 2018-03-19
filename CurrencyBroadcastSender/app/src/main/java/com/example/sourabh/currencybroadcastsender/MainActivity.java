package com.example.sourabh.currencybroadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spCurrency=findViewById(R.id.spinnerCurrency);
        String[] arraySpinner = new String[] {
                "British Pound", "Euro", "Indian Rupee",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCurrency.setAdapter(adapter);

        Button btnConvert=findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(this);

        Button btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

        IntentFilter intentFilter = new IntentFilter("com.example.sourabh.currencybroadcastreceiver");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg=intent.getStringExtra("msg");
                TextView tv=findViewById(R.id.tvFinalInformation);
                tv.setText(msg);
            }
        };
        this.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy()
    {
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnConvert:
                EditText txtAmount=findViewById(R.id.etDollarAmount);
                if(txtAmount.getText().toString().trim().length()<=0)
                    Toast.makeText(MainActivity.this,"Amount cannot be empty",Toast.LENGTH_SHORT).show();
                else
                {

                    double dAmount = Double.parseDouble(txtAmount.getText().toString().trim());
                    Spinner spCurrency=findViewById(R.id.spinnerCurrency);
                    String strCurrency = spCurrency.getSelectedItem().toString();
                    Intent i=new Intent();
                    i.setAction("com.example.sourabh.currencybroadcastsender");
                    i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    i.putExtra("amount", dAmount);
                    i.putExtra("currency",strCurrency);
                    sendBroadcast(i);
                }
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
