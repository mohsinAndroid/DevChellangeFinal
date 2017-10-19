package com.devcrewchallange.data;

import java.util.HashMap;
import java.util.Map;


public class Product {

    //private variables

    int _id;
    String _name;
    String _description;
    Double _regularPrice;
    Double _salePrice;
    byte[]  _image;
    String[] _colors;
    Map<String, String> _stores;


    // Empty constructor
    public Product() {

    }
    // constructor
    public Product(int id,String name, String description, Double regularPrice, Double salePrice, byte[]  image, String[] colors, Map<String, String> stores) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._regularPrice = regularPrice;
        this._salePrice = salePrice;
        this._image = image;
        this._colors = colors;
        this._stores = stores;

    }
    // constructor
    public Product(String name, String description, Double regularPrice, Double salePrice, byte[]  image, String[] colors, Map<String, String> stores) {
        this._name = name;
        this._description = description;
        this._regularPrice = regularPrice;
        this._salePrice = salePrice;
        this._image = image;
        this._colors = colors;
        this._stores = stores;

    }

    // getting ID
    public int getId() {
        return _id;
    }

    // setting ID
    public void setId(int _id) {
        this._id = _id;
    }

    // getting NAME
    public String getName() {
        return _name;
    }

    // setting NAME
    public void setName(String _name) {
        this._name = _name;
    }

    // getting DESCRIPTION
    public String getDescription() {
        return _description;
    }

    // setting DESCRIPTION
    public void setDescription(String _description) {
        this._description = _description;
    }

    // getting REGULAR PRICE
    public Double getRegularPrice() {
        return _regularPrice;
    }

    // setting REGULAR PRICE
    public void setRegularPrice(Double _regularPrice) {
        this._regularPrice = _regularPrice;
    }

    // getting SALE PRICE
    public Double getSalePrice() {
        return _salePrice;
    }

    // setting SALE PRICE
    public void setSalePrice(Double _salePrice) {
        this._salePrice = _salePrice;
    }

    // getting IMAGE
    public byte[]  getImage() {
        return _image;
    }

    // setting IMAGE
    public void setImage(byte[]  _image) {
        this._image = _image;
    }

    // getting COLORS
    public String[] getColors() {
        return _colors;
    }

    // setting COLORS
    public void setColors(String[] _colors) {
        this._colors = _colors;
    }

    // getting STORES
    public Map<String, String> getStores() {
        return _stores;
    }

    // setting STORES
    public void setStores(Map<String, String> _stores) {
        this._stores = _stores;
    }
}
