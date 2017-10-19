package com.devcrewchallange.presenter;

import android.content.Context;
import android.widget.Toast;

import com.devcrewchallange.data.Product;
import com.devcrewchallange.model.DatabaseHandler;
import com.devcrewchallange.view.CreateProductView;
import com.devcrewchallange.view.UpdateProductView;

import java.util.HashMap;


public class UpdateProductPresenter {

    private Context context;
    private UpdateProductView view;
    DatabaseHandler databaseHandler;
    private Product product;

    public UpdateProductPresenter(Context context, UpdateProductView view) {
        this.context = context;
        this.view = view;
        databaseHandler = new DatabaseHandler(context);
    }

    public void updateProduct(int id,String name, String description, String regularPrice, String salePrice, byte[] image, String[] colors, HashMap<String, String> stores) {

        databaseHandler.updateProduct(new Product(id,name, description, Double.parseDouble(regularPrice), Double.parseDouble(salePrice), image, colors, stores));
    }
    public Product getProducts(int id) {

        product = databaseHandler.getProduct(id);
        Toast.makeText(context, product.getName(), Toast.LENGTH_LONG).show();
        return product;
    }

}
