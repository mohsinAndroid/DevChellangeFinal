package com.devcrewchallange.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.devcrewchallange.R;
import com.devcrewchallange.adapters.ProductAdapter;
import com.devcrewchallange.data.Product;
import com.devcrewchallange.presenter.ProductDetailPresenter;
import com.devcrewchallange.presenter.ShowProductPresenter;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.util.Utils;
import com.devcrewchallange.view.ProductDetailView;
import com.devcrewchallange.view.ShowProductView;
import com.devcrewchallange.viewholders.ProductDetailViewHolder;
import com.devcrewchallange.viewholders.ShowProductViewHolder;

import java.util.HashMap;

public class ProductDetailActivity extends BaseActivity implements ProductDetailView, View.OnClickListener {

    private ProductDetailViewHolder holder;
    private ProductDetailPresenter presenter;
    Product product;
    Bundle bundle;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        holder = new ProductDetailViewHolder(this);
        presenter = new ProductDetailPresenter(this, this);
        ShowProduct();
        setListeners();
    }

    private void ShowProduct() {
        bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        product = presenter.getProducts(id);
        holder.getProductName().setText(product.getName());
        holder.getProductDescriptionTextView().setText(product.getDescription());
        holder.getSalePriceTextView().setText(product.getSalePrice().toString() + " $");
        holder.getRegularPriceTextView().setText(product.getRegularPrice().toString() + " $");
        holder.getColorsTextView().setText(Utils.getInstance().convertArrayToString(product.getColors()));
        holder.getStoresTextView().setText(Utils.getInstance().map_to_comma_string(product.getStores()));
        holder.getProductimageView().setImageBitmap(DbBitmapUtility.getImage(product.getImage()));


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pDetail_delete_button:
                Utils.getInstance().messageBox("Delete", this);
                presenter.deleteProduct(product);
                Utils.getInstance().callAndMakeTopIntent("com.devcrewchallange.activities.MainActivity", ProductDetailActivity.this);

                break;
            case R.id.pDetail_update_button:
                Utils.getInstance().messageBox("Update", this);
                bundle.putInt("id",bundle.getInt("id"));
                Utils.getInstance().callIntent("com.devcrewchallange.activities.UpdateProductActivity", ProductDetailActivity.this,bundle);

                break;
        }
    }

    private void setListeners()

    {
        holder.getUpdateButton().setOnClickListener(this);
        holder.getDeleteButton().setOnClickListener(this);

    }

}
