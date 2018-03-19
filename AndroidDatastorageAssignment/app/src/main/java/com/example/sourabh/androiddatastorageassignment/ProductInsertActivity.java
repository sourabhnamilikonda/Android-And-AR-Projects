package com.example.sourabh.androiddatastorageassignment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductInsertActivity extends AppCompatActivity {

    EditText etName,etDesc,etPrice,etReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_insert);
        etName=findViewById(R.id.etName);
        etDesc=findViewById(R.id.etDescription);
        etPrice=findViewById(R.id.etPrice);
        etReview=findViewById(R.id.etReview);

    }

    public void onAddClick(View view)
    {
        if(etName.getText().length()<1 ||etPrice.getText().length()<1) {
            Toast.makeText(this,"Values cannot be blank.",Toast.LENGTH_SHORT).show();
        }
        else {
            Products product = new Products(etName.getText().toString(), etDesc.getText().toString(), Double.valueOf(etPrice.getText().toString()), etReview.getText().toString());
            MyDbHandler hdl=new MyDbHandler(this,null, null, 1);
            String msg=hdl.addProduct(product);
            if(!msg.equals("")) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setMessage(msg).show();
            }
            else {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setMessage("Insertion successful.").show();
                resetAct();
            }
        }
    }

    public void onCancelClick(View view)
    {
        finish();
    }

    private void resetAct() {
        etName.setText("");
        etDesc.setText("");
        etPrice.setText("");
        etReview.setText("");
    }
}
