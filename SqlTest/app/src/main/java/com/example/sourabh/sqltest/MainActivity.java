package com.example.sourabh.sqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    MyDbHandler hdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editText);
        tv=findViewById(R.id.textView2);
        hdl=new MyDbHandler(this, null, null,1);
        printDatabase();
    }

    public void onAddClick(View view)
    {
        Products product=new Products(et.getText().toString());
        hdl.addProduct(product);
        printDatabase();
    }

    public void onDeleteClick(View view)
    {
        hdl.deleteProduct(et.getText().toString());
        printDatabase();
    }

    public void printDatabase()
    {
        String str=hdl.databaseToString();
        tv.setText(str);
        et.setText("");
    }
}
