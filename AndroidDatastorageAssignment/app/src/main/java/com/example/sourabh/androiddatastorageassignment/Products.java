package com.example.sourabh.androiddatastorageassignment;

/**
 * Created by sourabh on 11-Mar-18.
 */

public class Products {
    private int _id;
    private String _productname;
    private String _productdescription;
    private Double _productprice;
    private String _productReview;

    public Products()
    {

    }

    Products(String productname, String productdescription, Double productprice, String productReview) {
        this._productname = productname;
        this._productdescription = productdescription;
        this._productprice = productprice;
        this._productReview = productReview;
    }

    public String toString()
    {
        return _productname.toString();
    }

    int get_id() {
        return _id;
    }

    String get_productname() {
        return _productname;
    }

    String get_productdescription() {
        return _productdescription;
    }

    Double get_productprice() {
        return _productprice;
    }

    String get_productReview() {
        return _productReview;
    }

    void set_id(int _id) {
        this._id = _id;
    }

    void set_productname(String _productname) {
        this._productname = _productname;
    }

    void set_productdescription(String _productdescription) {
        this._productdescription = _productdescription;
    }

    void set_productprice(Double _productprice) {
        this._productprice = _productprice;
    }

    void set_productReview(String _productReview) {
        this._productReview = _productReview;
    }
}
