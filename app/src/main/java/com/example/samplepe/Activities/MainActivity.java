package com.example.samplepe.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.samplepe.Adapters.ProductAdapter;
import com.example.samplepe.Data.DBHelper;

import com.example.samplepe.Models.Product;
import com.example.samplepe.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    ArrayList<Product>products;
    RecyclerView proRecyclerView;
    ProductAdapter productAdapter;

    Button createProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();
        proRecyclerView = findViewById(R.id.main_RecyclerView);
        createProduct = findViewById(R.id.main_btnCreate);

        DBHelper dbHelper =  new DBHelper(this);
        proRecyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        products = dbHelper.getAllProducts();
        proRecyclerView.setHasFixedSize(true);
        productAdapter =  new ProductAdapter(products,MainActivity.this);
        proRecyclerView.setAdapter(productAdapter);
        createProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, CreateProductActivity.class);
                startActivity(intent);
            }
        });
    }
}