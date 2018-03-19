package com.example.sourabh.androiddatastorageassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sourabh on 11-Mar-18.
 */

public class MyDbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="products.db";
    private static final String TABLE_PRODUCTS="products";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_PRODUCTNAME="productname";
    private static final String COLUMN_PRODUCTDESCRIPTION="productdescription";
    private static final String COLUMN_PRODUCTPRICE="productprice";
    private static final String COLUMN_PRODUCTREVIEW="productreview";

    MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_PRODUCTS+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRODUCTNAME+" VARCHAR(255) NOT NULL UNIQUE, "+
                COLUMN_PRODUCTDESCRIPTION+" VARCHAR(255), "+
                COLUMN_PRODUCTPRICE+" DECIMAL(7,2), "+
                COLUMN_PRODUCTREVIEW+" VARCHAR(255)"+");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    String addProduct(Products product)
    {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCTNAME, product.get_productname());
            values.put(COLUMN_PRODUCTDESCRIPTION, product.get_productdescription());
            values.put(COLUMN_PRODUCTPRICE,product.get_productprice());
            values.put(COLUMN_PRODUCTREVIEW,product.get_productReview());
            SQLiteDatabase db = getWritableDatabase();
            long x= db.insert(TABLE_PRODUCTS, null, values);
            db.close();
            if(x==-1)
                return "Cannot insert. Product with that name already present.";
            return "";
        }
        catch (Exception e)
        {
            Log.e("db exception",e.getMessage());
            return e.getMessage();
        }
    }

    /*Use this also if you want to delete
    public String deleteProduct(String productname)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " where " + COLUMN_PRODUCTNAME + "=\"" + productname + "\";");
            db.close();
            return "";
        }
        catch (Exception e)
        {
            Log.e("db exception",e.getMessage());
            return e.getMessage();
        }
    }*/

    List<Products> searchProduct(String searchTerm, String filter)
    {
        try {
                List<Products> pdts=new ArrayList<>();
                SQLiteDatabase db=getWritableDatabase();
                String query="SELECT * FROM "+TABLE_PRODUCTS+" where product";
                if(filter.equals("Price"))
                    query+=filter.toLowerCase()+"="+searchTerm;
                else
                    query+=filter.toLowerCase()+" like \"%"+searchTerm+"%\"";
                Cursor c=db.rawQuery(query, null);
                c.moveToFirst();
                while(!c.isAfterLast())
                {
                    if(c.getString(c.getColumnIndex("productname"))!=null)
                    {
                        String strname=c.getString(c.getColumnIndex(COLUMN_PRODUCTNAME));
                        Double dprice=c.getDouble(c.getColumnIndex(COLUMN_PRODUCTPRICE));
                        String strdesc=c.getString(c.getColumnIndex(COLUMN_PRODUCTDESCRIPTION));
                        String strreview=c.getString(c.getColumnIndex(COLUMN_PRODUCTREVIEW));
                        Products p=new Products(strname,strdesc,dprice,strreview);
                        pdts.add(p);
                    }
                    c.moveToNext();
                }
                c.close();
                db.close();
                return pdts;
        }
        catch (Exception e)
        {
            Log.e("db exception",e.getMessage());
            return null;
        }
    }

    /*public String databaseToString()
    {
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_PRODUCTS+" where 1";
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("productname"))!=null)
            {
                dbString+=c.getString(c.getColumnIndex("productname"));
                dbString+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }*/
}
