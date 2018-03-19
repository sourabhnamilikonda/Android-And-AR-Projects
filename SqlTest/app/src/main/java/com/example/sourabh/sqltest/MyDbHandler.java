package com.example.sourabh.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sourabh on 10-Mar-18.
 */

public class MyDbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME="products.db";
    public static final String TABLE_PRODUCTS="products";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_PRODUCTNAME="productname";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_PRODUCTS+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRODUCTNAME+" VARCHAR(255)" +");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Products product)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }

    public void deleteProduct(String productname)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PRODUCTS+" where "+ COLUMN_PRODUCTNAME+"=\""+productname+"\";");
        db.close();
    }

    public String databaseToString()
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
    }
}
