package com.devcrewchallange.data;

import com.google.gson.Gson;

import java.io.Serializable;


/***
 * Every model class will be inherited from this base class.
 */
public class Base implements Serializable
{
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
