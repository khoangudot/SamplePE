package com.example.samplepe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samplepe.Data.DBHelper;
import com.example.samplepe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateProductActivity extends AppCompatActivity {
    EditText txtProductName, txtPrice, txtQuantity, txtSupplier;
    Button createBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        txtProductName = findViewById(R.id.create_productName);
        txtPrice = findViewById(R.id.create_Price);
        txtQuantity = findViewById(R.id.create_Quantity);
        txtSupplier = findViewById(R.id.create_Supplier);
        createBtn = findViewById(R.id.create_btnCreate);
        backBtn = findViewById(R.id.create_btnBackToList);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = txtProductName.getText().toString().trim();
                String priceStr = txtPrice.getText().toString().trim();
                String quantityStr = txtQuantity.getText().toString().trim();
                String supplier = txtSupplier.getText().toString().trim();

                // Kiểm tra dữ liệu đầu vào
                if (productName.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty() || supplier.isEmpty()) {
                    Toast.makeText(CreateProductActivity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    float price = Float.parseFloat(priceStr);
                    int quantity = Integer.parseInt(quantityStr);

                    if (price <= 0 || quantity < 0) {
                        Toast.makeText(CreateProductActivity.this, "Giá và số lượng phải lớn hơn 0.", Toast.LENGTH_SHORT).show();
                    } else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String createdDate = dateFormat.format(new Date());

                        DBHelper dbHelper = new DBHelper(CreateProductActivity.this);
                        long result = dbHelper.InsertProduct(productName, price, quantity, createdDate, supplier);

                        if (result == -1) {
                            Toast.makeText(CreateProductActivity.this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateProductActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateProductActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
