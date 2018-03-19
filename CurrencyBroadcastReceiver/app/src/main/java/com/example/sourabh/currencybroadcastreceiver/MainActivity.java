package com.example.sourabh.currencybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BroadcastReceiver mReceiver;
    double amt;
    String curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("com.example.sourabh.currencybroadcastsender");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                amt = intent.getDoubleExtra("amount", 0);
                TextView tv=findViewById(R.id.tvDollars);
                tv.setText("$ "+ amt);

                curr=intent.getStringExtra("currency");
                TextView tvCurr=findViewById(R.id.tvCurrency);
                tvCurr.setText(curr);
            }
        };
        this.registerReceiver(mReceiver, intentFilter);

        Button btnApply=findViewById(R.id.btnApply);
        btnApply.setOnClickListener(this);

        Button btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnApply:
                if(curr==null || curr.trim().length()<=0)
                    Toast.makeText(MainActivity.this,"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                else
                {
                    double converted=0;
                    switch(curr)
                    {
                        case "British Pound":
                            converted=amt*0.71;
                            break;
                        case "Euro":
                            converted=amt*0.81;
                            break;
                        case "Indian Rupee":
                            converted=amt*64.39;
                            break;
                    }
                    Intent i=new Intent();
                    i.setAction("com.example.sourabh.currencybroadcastreceiver");
                    i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    i.putExtra("msg", "Dollar amount $ "+amt +" converted to "+ converted+" "+curr+".");
                    sendBroadcast(i);
                }
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
