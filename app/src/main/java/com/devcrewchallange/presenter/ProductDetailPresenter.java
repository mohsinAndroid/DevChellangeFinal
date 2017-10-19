package com.devcrewchallange.presenter;

import android.content.Context;
import android.widget.Toast;

import com.devcrewchallange.data.Product;
import com.devcrewchallange.model.DatabaseHandler;
import com.devcrewchallange.view.ProductDetailView;
import com.devcrewchallange.view.ShowProductView;

import java.util.List;


public class ProductDetailPresenter {


    Context context;
    ProductDetailView view;


    private Product product;


    DatabaseHandler databaseHandler;

    public ProductDetailPresenter(Context context, ProductDetailView view) {
        this.context = context;
        this.view = view;
        databaseHandler = new DatabaseHandler(context);
    }

    public Product getProducts(int id) {

        product = databaseHandler.getProduct(id);
        return product;
    }

    public void deleteProduct(Product product)  {

        databaseHandler. deleteProduct(product) ;

    }

}
