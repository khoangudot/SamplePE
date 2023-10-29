package com.example.samplepe.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

public class UpdateProductActivity extends AppCompatActivity {

    Context context;
    EditText txtProductName, txtPrice, txtQuantity, txtSupplier;
    Button updateBtn, deleteBtn;
    String productName, supplier;
    float price;
    int quantity, productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        txtProductName = findViewById(R.id.update_productName);
        txtPrice = findViewById(R.id.update_Price);
        txtQuantity = findViewById(R.id.update_Quantity);
        txtSupplier = findViewById(R.id.update_Supplier);
        updateBtn = findViewById(R.id.update_btnUpdate);
        deleteBtn = findViewById(R.id.update_btnDelete);

        getIntentData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(UpdateProductActivity.this);

                String productName = txtProductName.getText().toString().trim();
                String priceStr = txtPrice.getText().toString().trim();
                String quantityStr = txtQuantity.getText().toString().trim();
                String supplier = txtSupplier.getText().toString().trim();

                // Kiểm tra dữ liệu đầu vào
                if (productName.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty() || supplier.isEmpty()) {
                    Toast.makeText(UpdateProductActivity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    float price = Float.parseFloat(priceStr);
                    int quantity = Integer.parseInt(quantityStr);

                    if (price <= 0 || quantity < 0) {
                        Toast.makeText(UpdateProductActivity.this, "Giá và số lượng phải lớn hơn 0.", Toast.LENGTH_SHORT).show();
                    } else {

                        long result =  db.UpdateProduct(productId,productName,price,quantity,supplier);
                        if (result == -1) {
                            Toast.makeText(UpdateProductActivity.this, "Cập nhật sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateProductActivity.this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateProductActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }



            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });

    }

    public void confirmDialog(){
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ productName + " ? " );
        builder.setMessage("Are  you sure you want to delete " + productName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(UpdateProductActivity.this);
                long result =  db.DeleteProduct(productId);
                if (result == -1) {
                    Toast.makeText(UpdateProductActivity.this, "xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateProductActivity.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public  void getIntentData(){
        if(getIntent().hasExtra("ProductName") && getIntent().hasExtra("Quantity")&& getIntent().hasExtra("Price")&& getIntent().hasExtra("Supplier")){

            productId = Integer.parseInt(getIntent().getStringExtra("ProductId"));
            productName = getIntent().getStringExtra("ProductName");
            price = Float.parseFloat(getIntent().getStringExtra("Price"));
            quantity = Integer.parseInt(getIntent().getStringExtra("Quantity"));
            supplier = getIntent().getStringExtra("Supplier");

            txtProductName.setText(productName);
            txtPrice.setText(String.valueOf(price));
            txtQuantity.setText(String.valueOf(quantity));
            txtSupplier.setText(supplier);
        }else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}