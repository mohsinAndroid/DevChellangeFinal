package com.devcrewchallange.viewholders;

import android.widget.Button;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;


public class MainViewHolder extends BaseViewHolder {

    private Button showProductButton;
    private Button createButton;

    public MainViewHolder(BaseActivity view) {
        super(view);
        showProductButton = (Button) view.findViewById(R.id.show_product_button);
        createButton = (Button) view.findViewById(R.id.create_product_button);

    }

    public Button getShowProductButton() {
        return showProductButton;
    }

    public Button getCreateButton() {
        return createButton;
    }
}
