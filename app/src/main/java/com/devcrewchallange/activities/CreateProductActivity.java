package com.devcrewchallange.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devcrewchallange.R;
import com.devcrewchallange.presenter.CreateProductPresenter;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.util.Utils;
import com.devcrewchallange.view.CreateProductView;
import com.devcrewchallange.viewholders.CreateProductViewHolder;

import java.io.IOException;
import java.util.HashMap;

public class CreateProductActivity extends BaseActivity implements CreateProductView, View.OnClickListener {

    private CreateProductViewHolder viewHolder;
    private CreateProductPresenter presenter;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        viewHolder = new CreateProductViewHolder(this);
        presenter = new CreateProductPresenter(this, this);

        setListeners();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_product_button:
                createProduct();
                Toast.makeText(this, "Product Added", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.select_image_Button:

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
        viewHolder.getAddProductButton().setOnClickListener(this);
        viewHolder.getSelectImageButton().setOnClickListener(this);
    }


    private void createProduct() {


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

        if (viewHolder.getWallMartCheckBox().isChecked()) {
            stores.put("b", "Metro");
        }

        Bitmap image =((BitmapDrawable)viewHolder.getImageView().getDrawable()).getBitmap();

        presenter.createProduct(viewHolder.getProductNameEditText().getText().toString(), viewHolder.getProductDescriptionEditText().getText().toString(), viewHolder.getRegularPriceEditText().getText().toString(), viewHolder.getSalePriceEditText().getText().toString(), DbBitmapUtility.getBytes(image), colors, stores);

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
