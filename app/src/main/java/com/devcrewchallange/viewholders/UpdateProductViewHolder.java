package com.devcrewchallange.viewholders;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;


public class UpdateProductViewHolder extends BaseViewHolder {

    private EditText productNameEditText;
    private EditText productDescriptionEditText;
    private EditText regularPriceEditText;
    private EditText salePriceEditText;
    private ImageView imageView;
    private CheckBox redCheckBox;
    private CheckBox bluekCheckBox;
    private CheckBox wallMartCheckBox;
    private CheckBox metroCheckBox;
    private Button updateProductButton;
    private Button selectImageButton;


    public UpdateProductViewHolder(BaseActivity view) {
        super(view);
        productNameEditText = (EditText) view.findViewById(R.id.update_name_edittext);
        productDescriptionEditText = (EditText) view.findViewById(R.id.update_description_edittext);
        regularPriceEditText = (EditText) view.findViewById(R.id.update_regular_price_edittext);
        salePriceEditText = (EditText) view.findViewById(R.id.update_sale_price_edittext);
        imageView = (ImageView) view.findViewById(R.id.update_image_ImageView);
        redCheckBox = (CheckBox) view.findViewById(R.id.update_red_checkbox);
        bluekCheckBox = (CheckBox) view.findViewById(R.id.update_blue_checkbox);
        wallMartCheckBox = (CheckBox) view.findViewById(R.id.update_wall_mart_checkbox);
        metroCheckBox = (CheckBox) view.findViewById(R.id.update_metro_checkbox);
        updateProductButton = (Button) view.findViewById(R.id.update_update_product_button);
        selectImageButton = (Button) view.findViewById(R.id.update_select_image_Button);
    }

    public EditText getProductNameEditText() {
        return productNameEditText;
    }

    public EditText getProductDescriptionEditText() {
        return productDescriptionEditText;
    }

    public EditText getRegularPriceEditText() {
        return regularPriceEditText;
    }

    public EditText getSalePriceEditText() {
        return salePriceEditText;
    }

    public CheckBox getRedCheckBox() {
        return redCheckBox;
    }

    public CheckBox getBluekCheckBox() {
        return bluekCheckBox;
    }

    public Button getUpdateProductButton() {
        return updateProductButton;
    }

    public CheckBox getWallMartCheckBox() {
        return wallMartCheckBox;
    }

    public CheckBox getMetroCheckBox() {
        return metroCheckBox;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Button getSelectImageButton() {
        return selectImageButton;
    }
}
