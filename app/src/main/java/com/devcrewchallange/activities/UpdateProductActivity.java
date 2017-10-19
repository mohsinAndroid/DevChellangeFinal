package com.devcrewchallange.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devcrewchallange.R;
import com.devcrewchallange.data.Product;
import com.devcrewchallange.presenter.CreateProductPresenter;
import com.devcrewchallange.presenter.UpdateProductPresenter;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.util.Utils;
import com.devcrewchallange.view.CreateProductView;
import com.devcrewchallange.view.UpdateProductView;
import com.devcrewchallange.viewholders.CreateProductViewHolder;
import com.devcrewchallange.viewholders.UpdateProductViewHolder;

import java.io.IOException;
import java.util.HashMap;

public class UpdateProductActivity extends BaseActivity implements UpdateProductView, View.OnClickListener {

    private UpdateProductViewHolder viewHolder;
    private UpdateProductPresenter presenter;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    Product product;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        viewHolder = new UpdateProductViewHolder(this);
        presenter = new UpdateProductPresenter(this, this);
        setProduct();
        setListeners();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_update_product_button:
                updateProduct();
                Toast.makeText(this, "Product Updated", Toast.LENGTH_LONG).show();

                Utils.getInstance().callAndMakeTopIntent("com.devcrewchallange.activities.MainActivity", UpdateProductActivity.this);

                break;
            case R.id.update_select_image_Button:

                Toast.makeText(this, "select image", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);


                break;
        }

    }

    private void setListeners() {
        viewHolder.getUpdateProductButton().setOnClickListener(this);
        viewHolder.getSelectImageButton().setOnClickListener(this);
    }


    private void updateProduct() {


        String[] colors = new String[]{"", ""};
        HashMap<String, String> stores = new HashMap<>();

        if (viewHolder.getRedCheckBox().isChecked()) {
            colors[0] = "red";
        }

        if (viewHolder.getBluekCheckBox().isChecked()) {
            colors[1] = "blue";
        }


        if (viewHolder.getWallMartCheckBox().isChecked()) {
            stores.put("a", "Wall Mart");
        }

        if (viewHolder.getMetroCheckBox().isChecked()) {
            stores.put("b", "Metro");
        }

        Bitmap image = ((BitmapDrawable) viewHolder.getImageView().getDrawable()).getBitmap();

        presenter.updateProduct(bundle.getInt("id"),viewHolder.getProductNameEditText().getText().toString(), viewHolder.getProductDescriptionEditText().getText().toString(), viewHolder.getRegularPriceEditText().getText().toString(), viewHolder.getSalePriceEditText().getText().toString(), DbBitmapUtility.getBytes(image), colors, stores);

    }

    private void setProduct() {
         bundle = getIntent().getExtras();

        product = presenter.getProducts(bundle.getInt("id"));

        if (Utils.getInstance().colorStatus(product.getColors(), "red")) {
            viewHolder.getRedCheckBox().setChecked(true);
        }

        if (Utils.getInstance().colorStatus(product.getColors(), "blue")) {
            viewHolder.getBluekCheckBox().setChecked(true);
        }


        if (product.getStores().containsValue("Wall Mart")) {

            viewHolder.getWallMartCheckBox().setChecked(true);
        }
        if (product.getStores().containsValue("Metro")) {

            viewHolder.getMetroCheckBox().setChecked(true);
        }
        viewHolder.getProductNameEditText().setText(product.getName());
        viewHolder.getProductDescriptionEditText().setText(product.getDescription());
        viewHolder.getSalePriceEditText().setText(product.getSalePrice().toString());
        viewHolder.getRegularPriceEditText().setText(product.getRegularPrice().toString());
        viewHolder.getImageView().setImageBitmap(DbBitmapUtility.getImage(product.getImage()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();


                //MEDIA GALLERY
                selectedImagePath = Utils.getInstance().getPath(selectedImageUri, this);

                //DEBUG PURPOSE - you can delete this if you want
                if (selectedImagePath != null)
                    System.out.println(selectedImagePath);
                else System.out.println("selectedImagePath is null");

                //NOW WE HAVE OUR WANTED STRING
                if (selectedImagePath != null) {
                    System.out.println("selectedImagePath is the right one for you!");

                    try {
                        viewHolder.getImageView().setImageBitmap(Utils.getInstance().getBitmapFromUri(selectedImageUri, this));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    System.out.println("filemanagerstring is the right one for you!");
            }
        }
    }
}
