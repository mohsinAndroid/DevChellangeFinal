package com.devcrewchallange.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.devcrewchallange.R;
import com.devcrewchallange.adapters.ProductAdapter;
import com.devcrewchallange.listeners.RecyclerItemClickListener;
import com.devcrewchallange.logger.Log;
import com.devcrewchallange.presenter.ShowProductPresenter;
import com.devcrewchallange.util.Utils;
import com.devcrewchallange.view.ShowProductView;
import com.devcrewchallange.viewholders.ShowProductViewHolder;

public class ShowProductActivity extends BaseActivity implements ShowProductView {

    private ShowProductViewHolder holder;
    private ShowProductPresenter presenter;
    private Context context;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        holder = new ShowProductViewHolder(this);
        presenter = new ShowProductPresenter(this, this);
        setUpProductRecyclerView();
        context = ShowProductActivity.this;
        setListeners();

    }


    private void setUpProductRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ProductAdapter productAdapter = new ProductAdapter(presenter.getAllProducts());

        holder.getShowProductRecyclerView().setLayoutManager(linearLayoutManager);
        holder.getShowProductRecyclerView().setAdapter(productAdapter);


    }

    private void setListeners() {
        holder.getShowProductRecyclerView().addOnItemTouchListener(
                new RecyclerItemClickListener(this, holder.getShowProductRecyclerView(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Log.d("RecyclerView", "onItemClick position: " + presenter.getAllProducts().get(position));
                        // do whatever
                        Bundle bundle=new Bundle();
                        bundle.putInt("id",presenter.getAllProducts().get(position).getId());
                        Utils.getInstance().callIntent("com.devcrewchallange.activities.ProductDetailActivity", ShowProductActivity.this,bundle);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }
}
