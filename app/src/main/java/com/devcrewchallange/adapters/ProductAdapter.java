package com.devcrewchallange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devcrewchallange.R;
import com.devcrewchallange.data.Product;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.util.Utils;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> productList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, regularPrice, salePrice, colors, stores;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.product_name_textview);
            description = (TextView) view.findViewById(R.id.product_description);
            regularPrice = (TextView) view.findViewById(R.id.regular_price);
            salePrice = (TextView) view.findViewById(R.id.sale_price);
            colors = (TextView) view.findViewById(R.id.colors);
            stores = (TextView) view.findViewById(R.id.stores);
            image= (ImageView) view.findViewById(R.id.product_imageview);
        }
    }


    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_product, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.salePrice.setText(product.getSalePrice().toString()+" $");
        holder.regularPrice.setText(product.getRegularPrice().toString()+ " $");
        holder.colors.setText(Utils.getInstance().convertArrayToString(product.getColors()));
        holder.stores.setText(Utils.getInstance().map_to_comma_string(product.getStores()));
        holder.image.setImageBitmap(DbBitmapUtility.getImage(product.getImage()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
