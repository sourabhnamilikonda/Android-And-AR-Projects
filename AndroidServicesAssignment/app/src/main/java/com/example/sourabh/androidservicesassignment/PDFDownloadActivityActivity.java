package com.example.sourabh.androidservicesassignment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

public class PDFDownloadActivityActivity extends AppCompatActivity {
    EditText etlink1, etlink2, etlink3, etlink4, etlink5;
    FileDownloadService myService;
    boolean isServiceBound;
    ServiceConnection serviceConnection;
    Intent iServiceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfdownload_activity);
        //Log.i("tag1","PDFDownloadActivity onCreate: thread:"+Thread.currentThread().getId());
        etlink1=findViewById(R.id.etLink1);
        etlink2=findViewById(R.id.etLink2);
        etlink3=findViewById(R.id.etLink3);
        etlink4=findViewById(R.id.etLink4);
        etlink5=findViewById(R.id.etLink5);
    }

    public void onStartDownloadClick(View v)
    {
        if(mValidationCheck(etlink1) && mValidationCheck(etlink2) && mValidationCheck(etlink3)
                && mValidationCheck(etlink4) && mValidationCheck(etlink5))
        {
            iServiceIntent = new Intent(getApplicationContext(), FileDownloadService.class);
            iServiceIntent.putExtra("link1", etlink1.getText().toString().trim());
            iServiceIntent.putExtra("link2", etlink2.getText().toString().trim());
            iServiceIntent.putExtra("link3", etlink3.getText().toString().trim());
            iServiceIntent.putExtra("link4", etlink4.getText().toString().trim());
            iServiceIntent.putExtra("link5", etlink5.getText().toString().trim());
            startService(iServiceIntent);
        }
    }

    public void onGetStatusClick(View v)
    {
        //Intent i = new Intent(getApplicationContext(), FileDownloadService.class);
        //stopService(i);

        bindService();
        getStatus();
        unbindService();
    }

    private void bindService()
    {
        if(serviceConnection==null)
        {
            serviceConnection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    FileDownloadService.MyServiceBinder myServiceBinder=(FileDownloadService.MyServiceBinder)iBinder;
                    myService=myServiceBinder.getService();
                    isServiceBound=true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound=false;
                }
            };
        }
        bindService(iServiceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void getStatus()
    {
        if(isServiceBound)
        {
            Toast.makeText(this, myService.mRetStatus(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Service not bound.",Toast.LENGTH_SHORT).show();
        }
    }

    private void unbindService()
    {
        if(isServiceBound)
        {
            unbindService(serviceConnection);
            isServiceBound=false;
        }
    }

    private boolean mValidationCheck(EditText et) {
        if(et.getText().toString().trim().length()<=0)
        {
            Toast.makeText(PDFDownloadActivityActivity.this,"URL cannot be empty: "+et.getId(),Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String URL = et.getText().toString().trim();
            if(URLUtil.isValidUrl(URL))
                return true;
            else {
                Toast.makeText(PDFDownloadActivityActivity.this, "Enter valid URL: "+et.getId(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
