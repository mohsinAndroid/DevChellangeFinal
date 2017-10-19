package com.devcrewchallange.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.devcrewchallange.R;
import com.devcrewchallange.data.Product;
import com.devcrewchallange.model.DatabaseHandler;
import com.devcrewchallange.util.DbBitmapUtility;
import com.devcrewchallange.view.MainView;

import java.util.HashMap;
import java.util.Map;


public class MainPresenter {
    Context context;
    MainView mainView;
    public MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    public void addDemoPorducts() {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "Wall Mart");
        hashMap.put("b", "Metro");

        //images for inintial products

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.samsung);

        Product product1 = new Product("Samsung", "Founded back in 1969 as Samsung Electric Industries, Suwon, South Korea-headquartered Samsung Electronics today makes everything from televisions to semiconductors. It released its first Android smartphone in 2009, and can be credited with the launch of the first Android tablet back in 2010. The company is among the biggest players in the smartphone market in the world. It has recently developed smartphones running Tizen OS, as an alternative to its Android-based smartphones.", 700.0, 800.0, DbBitmapUtility.getBytes(image), new String[]{"red","blue"}, hashMap);
        Product product2 = new Product("LG G4", "LG G4 smartphone was launched in April 2015. The phone comes with a 5.50-inch touchscreen display with a resolution of 1440 pixels by 2560 pixels at a PPI of 538 pixels per inch.", 600.0, 700.0, DbBitmapUtility.getBytes(image), new String[]{"red","blue"}, hashMap);
        Product product3 = new Product("Samsungn Note 8", "Founded back in 1969 as Samsung Electric Industries, Suwon, South Korea-headquartered Samsung Electronics today makes everything from televisions to semiconductors. It released its first Android smartphone in 2009, and can be credited with the launch of the first Android tablet back in 2010. The company is among the biggest players in the smartphone market in the world. It has recently developed smartphones running Tizen OS, as an alternative to its Android-based smartphones.", 200.0, 300.0, DbBitmapUtility.getBytes(image), new String[]{"red","blue"}, hashMap);

        databaseHandler.addProduct(product1);
        databaseHandler.addProduct(product2);
        databaseHandler.addProduct(product3);
    }


}
