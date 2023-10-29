//package com.example.samplepe.Data;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.samplepe.Models.Product;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class ProductDAO {
//    private  DBHelper dbHelper;
//    private SQLiteDatabase sqLiteDatabase;
//    public ProductDAO(Context context){
//        dbHelper =  new DBHelper(context);
//        sqLiteDatabase = dbHelper.getWritableDatabase();
//    }
//    // Create
//    public long InsertProduct(Product product){
//
//        ContentValues values = new ContentValues();
//
//        values.put("ProductName", product.getProductName());
//        values.put("Price", product.getProductPrice());
//        values.put("Quantity", product.getProductQuantity());
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = dateFormat.format(new Date());
//        values.put("CreteDate", formattedDate);
//
//        values.put("Supplier", product.getSupplier());
//
//        long check = sqLiteDatabase.insert("Products", null, values);
//        return check;
//    }
//
//    public ArrayList<Product> getAllProducts(){
//        ArrayList<Product> products = new ArrayList<>();
//        Cursor cursor =sqLiteDatabase.rawQuery("Select *from Products", null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            int productId = cursor.getInt(0);
//            String productName = cursor.getString(1);
//            int price = cursor.getInt(2);
//            int quantity = cursor.getInt(3);
//            String createDate = cursor.getString(4);
//            String supplier = cursor.getString(5);
//
//            products.add(new Product(productId,productName,price,quantity,supplier));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return  products;
//    }
//
//    public Product getProductById(int Id){
//        Product product = null;
//        Cursor cursor = sqLiteDatabase.rawQuery("Select *from Products where ProductId = ?", new String[]{Id + ""});
//
//        if(cursor.getCount() > 0 ){
//            cursor.moveToFirst();
//            int productId = cursor.getInt(0);
//            String productName = cursor.getString(1);
//            int price = cursor.getInt(2);
//            int quantity = cursor.getInt(3);
//            String createDate = cursor.getString(4);
//            String supplier = cursor.getString(5);
//
//            product =  new Product(productId,productName,price,quantity,createDate,supplier);
//        }
//        cursor.close();
//        return  product;
//    }
//
//    //Update
//    public void UpdateProduct(Product product){
//        sqLiteDatabase.execSQL("UPDATE Products SET ProductName = ?, Price = ?, Quantity = ?, Supplier = ? WHERE ProductId = ?",
//                new String[]{product.getProductName(), product.getProductPrice()+"", product.getProductQuantity()+"", product.getSupplier()});
//    }
//
//
//    //Delete
//    public void DeleteProduct(int Id){
//
//        sqLiteDatabase.execSQL("DELETE FROM Products WHERE ProductId = ?", new String[]{String.valueOf(Id)} );
//    }
//
//}
