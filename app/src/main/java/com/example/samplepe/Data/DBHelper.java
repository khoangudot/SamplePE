package com.example.samplepe.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.samplepe.Models.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final  String DATABASE_NAME = "ProductsManager";
    private static  final int VERSION = 1;

    private static final String TABLE_NAME = "Products";
    private static final String COLUMN_ProductId = "ProductId";
    private static final String COLUMN_ProductName = "ProductName";
    private static final String COLUMN_ProductPrice = "Price";
    private static final String COLUMN_ProductQuantity = "Quantity";
    private static final String COLUMN_ProductCreatedDate = "CreatedDate";
    private static final String COLUMN_ProductSupplier = "Supplier";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_ProductId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ProductName + " TEXT, " +
                COLUMN_ProductPrice + " INTEGER, " +
                COLUMN_ProductQuantity + " INTEGER, " +
                COLUMN_ProductCreatedDate + " TEXT, " +
                COLUMN_ProductSupplier + " TEXT);";


        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i!=i1){
            db.execSQL("Drop Table If Exists " +  TABLE_NAME);
            onCreate(db);
        }
    }

    // Create
    public long InsertProduct(String productName, float price, int quantity, String createdDate, String supplier){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ProductName, productName);
        values.put(COLUMN_ProductPrice, price);
        values.put(COLUMN_ProductQuantity, quantity);
        values.put(COLUMN_ProductCreatedDate, createdDate);

        values.put(COLUMN_ProductSupplier, supplier);

        long result = db.insert(TABLE_NAME, null, values);

        return result;
    }

    public ArrayList<Product> getAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int productId = cursor.getInt(0);
            String productName = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            String createDate = cursor.getString(4);
            String supplier = cursor.getString(5);

            products.add(new Product(productId,productName,price,quantity,createDate,supplier));
            cursor.moveToNext();
        }
        cursor.close();
        return  products;
    }

        //Update
    public long UpdateProduct(int productId, String productName, float price, int quantity, String supplier){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ProductName, productName);
        contentValues.put(COLUMN_ProductPrice, price);
        contentValues.put(COLUMN_ProductQuantity, quantity);
        contentValues.put(COLUMN_ProductSupplier, supplier);

        return db.update(TABLE_NAME,contentValues,"ProductId = ? ", new String[]{String.valueOf(productId)});
    }
    public long DeleteProduct(int productId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ProductId = ? ", new String[]{String.valueOf(productId)});
    }
    public void DeleteAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
