package com.devcrewchallange.viewholders;

import android.support.v7.widget.RecyclerView;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;


public class ShowProductViewHolder extends BaseViewHolder {


    private RecyclerView showProductRecyclerView;

    public ShowProductViewHolder(BaseActivity view) {
        super(view);
        showProductRecyclerView = (RecyclerView) view.findViewById(R.id.show_product_recycler_view);
    }

    public RecyclerView getShowProductRecyclerView() {
        return showProductRecyclerView;
    }
}
