package com.example.samplepe.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplepe.Activities.UpdateProductActivity;
import com.example.samplepe.Models.Product;
import com.example.samplepe.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ProductId.setText(String.valueOf(products.get(position).getProductId()));
        holder.ProductName.setText(products.get(position).getProductName());
        holder.Quantity.setText(String.valueOf(products.get(position).getProductQuantity()));
        holder.Price.setText(String.format("%,.2f",products.get(position).getProductPrice()));
        holder.CreatedDate.setText(products.get(position).getCreateDate());
        holder.Supplier.setText(products.get(position).getSupplier());

        holder.product_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, UpdateProductActivity.class);
                intent.putExtra("ProductId", String.valueOf(products.get(position).getProductId()));
                intent.putExtra("ProductName", products.get(position).getProductName());
                intent.putExtra("Quantity", String.valueOf(products.get(position).getProductQuantity()));
                intent.putExtra("Price", String.valueOf(products.get(position).getProductPrice()));
                intent.putExtra("Supplier", products.get(position).getSupplier());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ProductId, ProductName, Quantity, Price, Supplier, CreatedDate;
        CardView product_item_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductId = itemView.findViewById(R.id.main_productId);
            ProductName = itemView.findViewById(R.id.main_productName);
            Quantity = itemView.findViewById(R.id.main_productQuantity);
            Price = itemView.findViewById(R.id.main_productPrice);
            CreatedDate = itemView.findViewById(R.id.main_productCreateDate);
            Supplier = itemView.findViewById(R.id.main_productSupplier);
            product_item_layout  = itemView.findViewById(R.id.product_item_layout);
        }
    }
}
