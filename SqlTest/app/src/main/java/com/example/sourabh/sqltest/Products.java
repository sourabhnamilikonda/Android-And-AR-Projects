package com.example.sourabh.sqltest;

/**
 * Created by sourabh on 10-Mar-18.
 */

public class Products {

    private int _id;
    private String _productname;

    public Products()
    {

    }

    public Products(String name)
    {
        _productname=name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return _productname;
    }
}
