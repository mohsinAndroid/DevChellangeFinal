package com.devcrewchallange.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;


public class SharedPreferenceHelper {

    static SharedPreferenceHelper sharedPreferenceHelper = null;

    public static SharedPreferenceHelper getInstance() {
        if (sharedPreferenceHelper == null)
            return sharedPreferenceHelper = new SharedPreferenceHelper();
        else return sharedPreferenceHelper;
    }

    private final static String PREF_FILE = "MyPrefs";

    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        String value = settings.getString(key, defValue);

        return value;
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        int value = settings.getInt(key, defValue);

        return value;
    }

    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        boolean value = settings.getBoolean(key, defValue);

        return value;
    }


    public void setSharedPreferenceList(Context context, String key, List<Object> objectList) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();

        String json = gson.toJson(objectList);

        editor.putString(key, json);
        editor.apply();
    }

    public List<Object> getSharedPreferenceList(Context context, String key, Type type) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        Gson gson = new Gson();
        String json = settings.getString(key, null);
        return gson.fromJson(json, type);
    }

    public void setSharedPreferenceObject(Context context, String key, Object object) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();

        String json = gson.toJson(object);

        editor.putString(key, json);
        editor.apply();
    }

//    public Object getSharedPreferenceObject(Context context, String key, Object object) {
//        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
//        Gson gson = new Gson();
//        String json = settings.getString(key, null);
//        return gson.fromJson(json, object.getClass());
//    }

    public void clearAllData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

}
