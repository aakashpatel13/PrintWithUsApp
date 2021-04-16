package com.example.printwithus.adaptar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.printwithus.ProductsSubActivity;
import com.example.printwithus.R;
import com.example.sampleapp.model.Product;
import com.example.sampleapp.model.ProductItem;

import java.util.List;


public class ProductAdaptar extends RecyclerView.Adapter<ProductAdaptar.ViewHolder> {
    private List<Product> productList;
    public OrderCountListener onClickListener;
    public Context mctx;

    public ProductAdaptar(List<ProductItem> productList, ProductsSubActivity mctx, ProductItemAdaptar.OrderCountListener price) {
    }

    public interface OrderCountListener {
        void orderType(View v, int position);
    }

    public ProductAdaptar(List<Product> productList, Context mctx, OrderCountListener listener) {
        this.productList = productList;
        this.mctx = mctx;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getProduct_name());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public ViewHolder(View itemView) {
            super(itemView);
            this.productName = (TextView) itemView.findViewById(R.id.productName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.orderType(v, getAdapterPosition());
                }
            });
        }
    }
}
