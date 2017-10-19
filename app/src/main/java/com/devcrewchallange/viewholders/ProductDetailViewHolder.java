package com.devcrewchallange.viewholders;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;


public class ProductDetailViewHolder extends BaseViewHolder {

    private TextView productName;
    private TextView productDescriptionTextView;
    private TextView regularPriceTextView;
    private TextView salePriceTextView;
    private ImageView productimageView;
    private TextView colorsTextView;
    private TextView storesTextView;
    private Button updateButton;
    private Button deleteButton;

    public ProductDetailViewHolder(BaseActivity view) {
        super(view);

        productName = (TextView) view.findViewById(R.id.pDetail_name_textView);
        productDescriptionTextView = (TextView) view.findViewById(R.id.pDetail_description_textView);
        regularPriceTextView = (TextView) view.findViewById(R.id.pDetail_rgPrice_textView);
        salePriceTextView = (TextView) view.findViewById(R.id.pDetail_salePrice_textView);
        productimageView= (ImageView) view.findViewById(R.id.pDetail_image_imageView);
        colorsTextView = (TextView) view.findViewById(R.id.pDetail_colors_textView);
        storesTextView = (TextView) view.findViewById(R.id.pDetail_stores_textView);
        updateButton = (Button) view.findViewById(R.id.pDetail_update_button);
        deleteButton = (Button) view.findViewById(R.id.pDetail_delete_button);
    }

    public TextView getProductName() {
        return productName;
    }

    public TextView getProductDescriptionTextView() {
        return productDescriptionTextView;
    }

    public TextView getRegularPriceTextView() {
        return regularPriceTextView;
    }

    public TextView getSalePriceTextView() {
        return salePriceTextView;
    }

    public ImageView getProductimageView() {
        return productimageView;
    }

    public TextView getColorsTextView() {
        return colorsTextView;
    }

    public TextView getStoresTextView() {
        return storesTextView;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
