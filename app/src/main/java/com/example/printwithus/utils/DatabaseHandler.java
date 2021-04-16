package com.example.sampleapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sampleapp.model.Product;
import com.example.sampleapp.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productsManager";
    private static final String TABLE_PRODUCTS = "product";
    private static final String PKEY_NAME = "product_name";
    private static final String PKEY_ID = "product_id";
    private static final String TABLE_PRODUCTITEM = "product_item";
    private static final String PIKEY_ID="product_item_id";
    private static final String PIKEY_NAME="item_name";
    private static final String PIKEY_COMPATIBILITY="compatibility";
    private static final String PIKEY_IMAGEURL="image_url";
    private static final String PIKEY_DESCRIPTION="description";
    private static final String PIKEY_PRICE="price";
    private static final String PIKEY_SUBPRODID="sub_product_id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + PKEY_ID + " INTEGER PRIMARY KEY," + PKEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        String CREATE_CONTACTS_TABLE_PI = "CREATE TABLE " + TABLE_PRODUCTITEM + "("
                + PIKEY_ID + " INTEGER PRIMARY KEY," + PIKEY_NAME + " TEXT,"
                + PIKEY_IMAGEURL + " TEXT," + PIKEY_DESCRIPTION + " TEXT,"
                + PIKEY_PRICE + " TEXT," + PIKEY_SUBPRODID + " INTEGER"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE_PI);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTITEM);


        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PKEY_NAME, product.getProduct_name()); // Contact Name

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to add the new contact
    public void addProductItem(ProductItem prodItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PIKEY_NAME, prodItem.getItem_name());
        values.put(PIKEY_IMAGEURL, prodItem.getImage_url());
        values.put(PIKEY_DESCRIPTION, prodItem.getDescription());
        values.put(PIKEY_PRICE, prodItem.getPrice());
        values.put(PIKEY_SUBPRODID, prodItem.getSub_product_id());

        // Inserting Row
        db.insert(TABLE_PRODUCTITEM, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { PKEY_ID,
                        PKEY_ID }, PKEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return product;
    }

    // code to get all contacts in a list view
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProduct_id(Integer.parseInt(cursor.getString(0)));
                product.setProduct_name(cursor.getString(1));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // code to get all contacts in a list view
    public List<ProductItem> getAllProductItem(int id) {
        List<ProductItem> productList = new ArrayList<ProductItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTITEM+" where "+PIKEY_SUBPRODID+" ='"+id+"'";

        SQLiteDatabase db = this.getWritableDatabase();

//        Cursor cursor = db.query(TABLE_PRODUCTITEM, new String[] { PIKEY_SUBPRODID,
//                        PIKEY_SUBPRODID }, PIKEY_SUBPRODID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductItem product = new ProductItem();

                product.setProduct_item_id(Integer.parseInt(cursor.getString(0)));
                product.setItem_name(cursor.getString(1));
                product.setImage_url(cursor.getString(2));
                product.setDescription(cursor.getString(3));
                product.setPrice(cursor.getString(4));
                product.setSub_product_id(Integer.parseInt(cursor.getString(5)));

                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }


    // code to update the single contact
    public int updateContact(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PKEY_NAME, product.getProduct_name());

        // updating row
        return db.update(TABLE_PRODUCTS, values, PKEY_ID + " = ?",
                new String[] { String.valueOf(product.getProduct_id()) });
    }

    // Deleting single contact
    public void deleteContact(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, PKEY_ID + " = ?",
                new String[] { String.valueOf(product.getProduct_id()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + PKEY_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}