package com.devcrewchallange.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.afollestad.materialdialogs.BuildConfig;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.devcrewchallange.R;
import com.devcrewchallange.listeners.IDialogListener;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to provide static common functionality to other classes.
 */
public class Utils {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static Utils utils;
    public static String strSeparator = " , ";

    public static Utils getInstance() {
        if (utils == null) return new Utils();
        return utils;
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }

        } else {
            // not connected to the internet
            return false;
        }

        return false;
    }


    public boolean emailvalidation(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /***
     * @param activityName Activity Name to call
     * @param context      Activity context
     * @param milliseconds Time to wait in milliseconds e.g (1 sec equals 1000 millis)
     */
    public void callIntentWithTime(final String activityName, final Activity context, final int milliseconds) {

        final boolean _alive = true;

        Thread splashThread = new Thread() {

            @Override
            public void run() {
                try {
                    int splashTime = 0;
                    while (_alive && (splashTime < milliseconds)) {
                        sleep(100);
                        if (_alive) {
                            splashTime += 100;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    context.finish();
                    callIntent(activityName, context);
                }

            }

        };
        splashThread.start();

    }

    /**
     * This method call the intent and delete rest of activities from the
     * activity stack to make the current activity on top.
     */
    public void callAndMakeTopIntent(String activityName, Activity context) {
        String pacakageName = context.getPackageName();

        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivity(i);
    }

    /**
     * This method call the intent and delete rest of activities from the
     * activity stack to make the current activity on top.
     */
    public void callAndMakeTopIntent(String activityName, Activity context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtras(bundle);
        i.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivity(i);
    }

    public void callIntent(String activityName, Activity context) {
        Intent i = new Intent();
        String pacakageName = context.getPackageName();
        i.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivity(i);
    }


    public void callIntent(String activityName, Activity context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivity(intent);
    }

    public void callIntentWithResult(String activityName, Activity context, Bundle bundle, int requestCode) {
        String pacakageName = context.getPackageName();
        Intent intent = new Intent();

        if (bundle != null) intent.putExtras(bundle);

        intent.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivityForResult(intent, requestCode);
    }


    public void showDialog(Context context, String message, final IDialogListener listener) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (listener != null) listener.onClickOk(true);
            }
        }).positiveText(context.getString(R.string.ok));
        materialDialog.show();
    }

    public void showDialog(Context context, String title, String message, String ok, String cancel, final IDialogListener listener) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.title(title).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                if (listener != null) listener.onClickOk(true);
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                if (listener != null) listener.onClickOk(false);
            }
        }).positiveText(ok).negativeText(cancel);
        // materialDialog.titleGravity(GravityEnum.CENTER);
        // materialDialog.contentGravity(GravityEnum.CENTER);

        materialDialog.show();
    }


    public void messageBox(String message, Activity context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void openWebURL(String inURL, Activity context) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
        context.startActivity(browse);
    }

    public void showDialog7(Context context, String title, String message, String ok, final IDialogListener listener) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            materialDialog.title(title).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);

        } else {
            materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);
        }
        materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                if (listener != null) listener.onClickOk(true);
            }
        });
        // materialDialog.titleGravity(GravityEnum.CENTER);
        // materialDialog.contentGravity(GravityEnum.CENTER);

        materialDialog.show();
    }

    public void showDialog(Context context, String message, String ok, String cancel, final IDialogListener listener) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok).negativeText(cancel);

        materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                if (listener != null) listener.onClickOk(true);
            }
        });

        materialDialog.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                if (listener != null) listener.onClickOk(false);
            }
        });

        materialDialog.show();
    }


    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    private String getOsVersion() {
        return String.valueOf(Build.VERSION.RELEASE);
    }


    public int getAndroidScreenWidth(Activity context) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }


    public String getDeviceDetails() {
        StringBuilder sbDeviceDetails = new StringBuilder();
        sbDeviceDetails.append(Build.MODEL);
        sbDeviceDetails.append(" - ");
        sbDeviceDetails.append("Android OS ");
        sbDeviceDetails.append(Build.VERSION.RELEASE);
        sbDeviceDetails.append(" - App Version ");
        sbDeviceDetails.append(BuildConfig.VERSION_NAME);
        return sbDeviceDetails.toString();
    }

    public static String convertArrayToString(String[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i];
            // Do not append comma at the end of last element
            if (i < array.length - 1) {
                str = str + strSeparator;
            }
        }
        return str;
    }

    public static String[] convertStringToArray(String str) {
        String[] arr = str.split(strSeparator);
        return arr;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }

    public static Map<String, String> stringToMap(String input) {
        Map<String, String> map = new HashMap<String, String>();

        String[] nameValuePairs = input.split("&");
        for (String nameValuePair : nameValuePairs) {
            String[] nameValue = nameValuePair.split("=");
            try {
                map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(nameValue[1], "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return map;
    }

    public void startActivity(Context context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    public String map_to_comma_string(Map<String, String> map) {

        String separator = ", ";


        StringBuffer buffer = new StringBuffer();

        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet()
                .iterator();

        while (entryIterator.hasNext()) {

            Map.Entry<String, String> entry = entryIterator.next();


            buffer.append(entry.getValue());

            if (entryIterator.hasNext()) {
                buffer.append(separator);
            }
        }
        return buffer.toString();
    }

    public String getPath(Uri contentUri, Context context) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            ;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public Bitmap getBitmapFromUri(Uri uri, Context context) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public boolean colorStatus(String[] colors, String color) {

        for (String s : colors) {
            int i = s.indexOf(color);
            if (i >= 0) {
                // found a match to "software" at offset i
                return true;
            }
        }

        return false;
    }
}
