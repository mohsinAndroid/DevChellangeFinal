package com.devcrewchallange.presenter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.devcrewchallange.R;
import com.devcrewchallange.data.Product;
import com.devcrewchallange.model.DatabaseHandler;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.view.CreateProductView;

import java.util.HashMap;


public class CreateProductPresenter {

    private Context context;
    private CreateProductView view;
    DatabaseHandler databaseHandler;

    public CreateProductPresenter(Context context, CreateProductView view) {
        this.context = context;
        this.view = view;
        databaseHandler = new DatabaseHandler(context);
    }

    public void createProduct(String name, String description, String regularPrice, String salePrice, byte[] image, String[] colors, HashMap<String, String> stores) {

        databaseHandler.addProduct(new Product(name, description, Double.parseDouble(regularPrice), Double.parseDouble(salePrice), image, colors, stores));




    }


}
