package com.devcrewchallange.presenter;

import android.content.Context;
import android.widget.Toast;

import com.devcrewchallange.data.Product;
import com.devcrewchallange.model.DatabaseHandler;
import com.devcrewchallange.view.ShowProductView;

import java.util.List;


public class ShowProductPresenter {


    Context context;
    ShowProductView view;


    private List<Product> productList;


    DatabaseHandler databaseHandler;

    public ShowProductPresenter(Context context, ShowProductView view) {
        this.context = context;
        this.view = view;
        databaseHandler = new DatabaseHandler(context);
    }

    public List<Product> getAllProducts() {

        productList = databaseHandler.getAllProduct();
        return productList;

    }


}
