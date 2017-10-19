package com.devcrewchallange.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devcrewchallange.data.Product;

import java.util.ArrayList;
import java.util.List;

import static com.devcrewchallange.util.Utils.convertArrayToString;
import static com.devcrewchallange.util.Utils.convertStringToArray;
import static com.devcrewchallange.util.Utils.mapToString;
import static com.devcrewchallange.util.Utils.stringToMap;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProductManager";

    // Product table name
    private static final String TABLE_PRODUCT = "product_table";

    // Product Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_REGULAR_PRICE = "regular_price";
    private static final String KEY_SALE_PRICE = "sale_price";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_COLORS = "colors";
    private static final String KEY_STORES = "stores";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_REGULAR_PRICE + " TEXT,"
                + KEY_SALE_PRICE + " TEXT,"
                + KEY_IMAGE + " BLOB,"
                + KEY_COLORS + " TEXT,"
                + KEY_STORES + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Product
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName()); // Product Name
        values.put(KEY_DESCRIPTION, product.getDescription()); // Product Description
        values.put(KEY_REGULAR_PRICE, product.getRegularPrice()); // Product Regular Price
        values.put(KEY_SALE_PRICE, product.getSalePrice()); // Product Sale price
        values.put(KEY_IMAGE, product.getImage()); // Product image
        values.put(KEY_COLORS, convertArrayToString(product.getColors())); // Product Colors
        values.put(KEY_STORES, mapToString(product.getStores())); // Contact Phone*/
        // Inserting Row
        db.insert(TABLE_PRODUCT, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT, new String[]{KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_REGULAR_PRICE, KEY_SALE_PRICE, KEY_IMAGE, KEY_COLORS, KEY_STORES}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        Product product = new Product();
        product.setId(Integer.parseInt(cursor.getString(0)));
        product.setName(cursor.getString(1));
        product.setDescription(cursor.getString(2));
        product.setRegularPrice(Double.parseDouble(cursor.getString(3)));
        product.setSalePrice(Double.parseDouble(cursor.getString(4)));
        product.setImage(cursor.getBlob(5));
        product.setColors(convertStringToArray(cursor.getString(6)));
        product.setStores(stringToMap(cursor.getString(7)));


        return product;
                /*new Product(cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)), Double.parseDouble(cursor.getString(4)), cursor.getString(5), convertStringToArray(cursor.getString(6)), stringToMap(cursor.getString(7)));
*/
    }

    // Getting All product
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setRegularPrice(Double.parseDouble(cursor.getString(3)));
                product.setSalePrice(Double.parseDouble(cursor.getString(4)));
                product.setImage(cursor.getBlob(5));
                product.setColors(convertStringToArray(cursor.getString(6)));
                product.setStores(stringToMap(cursor.getString(7)));

                // Adding product to list
                productList.add(product);
            }
            while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // Updating single product
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName()); // Product Name
        values.put(KEY_DESCRIPTION, product.getDescription()); // Product Description
        values.put(KEY_REGULAR_PRICE, product.getRegularPrice()); // Product Regular Price
        values.put(KEY_SALE_PRICE, product.getSalePrice()); // Product Sale price
        values.put(KEY_IMAGE, product.getImage()); // Product image
        values.put(KEY_COLORS, convertArrayToString(product.getColors())); // Product Colors
        values.put(KEY_STORES, mapToString(product.getStores())); // Contact Phone*/

        // updating row
        return db.update(TABLE_PRODUCT, values, KEY_ID + " = ?", new String[]{String.valueOf(product.getId())});
    }

    // Deleting single product
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, KEY_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
    }


    // Getting product Count
    public int getProductCounts() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
