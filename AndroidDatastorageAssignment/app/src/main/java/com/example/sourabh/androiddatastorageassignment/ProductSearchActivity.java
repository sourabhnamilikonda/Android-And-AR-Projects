package com.example.sourabh.androiddatastorageassignment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class ProductSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
    }

    public void onSearchClick(View view)
    {
        EditText et=findViewById(R.id.etSearchTerm);
        if(et.getText().length()<1)
            Toast.makeText(this,"Field cannot be blank",Toast.LENGTH_SHORT).show();
        else
        {
            Spinner sp=findViewById(R.id.spFilter);
            String strFilter=sp.getSelectedItem().toString();
            String strterm=et.getText().toString();
            if(strFilter.equals("Price"))
            {
                try{
                    double x=Double.parseDouble(et.getText().toString());
                    strterm=String.format(Locale.getDefault(),"%.2f", x);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, "Only numerics for price filter",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            MyDbHandler hdl=new MyDbHandler(this,null, null, 1);
            List<Products> ans=hdl.searchProduct(strterm, strFilter);
            Products[] ans1 = ans.toArray(new Products[ans.size()]);

            //create list here
            ListAdapter testAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ans1);
            ListView lv=findViewById(R.id.lvSearchResult);
            lv.setAdapter(testAdapter);

            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(ProductSearchActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                            Products s= ((Products)adapterView.getItemAtPosition(i));
                            String op="Name: "+ s.get_productname()+"\nDesc: "+s.get_productdescription()+"\nPrice: "+s.get_productprice()+"\nReview: "+s.get_productReview();
                            builder.setMessage(op).show();
                            //Toast.makeText(ProductSearchActivity.this, String.valueOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
