package com.example.sourabh.implicitintentexploration;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUrl=findViewById(R.id.btnURL);
        btnUrl.setOnClickListener(this);
        Button btnPhone=findViewById(R.id.btnRing);
        btnPhone.setOnClickListener(this);
        Button btnClose=findViewById(R.id.btnCloseApp);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnURL:
                EditText txtUrl=findViewById(R.id.etURL);
                if(txtUrl.getText().toString().trim().length()<=0)
                    Toast.makeText(MainActivity.this,"URL cannot be empty",Toast.LENGTH_SHORT).show();
                else
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    String URL = txtUrl.getText().toString().trim();
                    if(URLUtil.isValidUrl(URL))
                    {
                        browserIntent.setData(Uri.parse(URL));
                        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                        startActivity(browserChooserIntent);
                    }
                    else
                        Toast.makeText(MainActivity.this,"Enter valid URL",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRing:
                EditText txtPhone=findViewById(R.id.etPhoneNumber);
                if(txtPhone.getText().toString().trim().length()<=0)
                    Toast.makeText(MainActivity.this,"Phone number cannot be empty",Toast.LENGTH_SHORT).show();
                else
                {
                    if(android.util.Patterns.PHONE.matcher(txtPhone.getText().toString().trim()).matches())
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+txtPhone.getText().toString().trim()));
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(MainActivity.this,"Enter valid Phone number",Toast.LENGTH_SHORT).show();
                }
                    break;
            case R.id.btnCloseApp:
                finish();
                break;
        }
    }
}
