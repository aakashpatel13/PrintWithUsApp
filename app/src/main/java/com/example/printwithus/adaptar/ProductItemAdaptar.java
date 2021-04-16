package com.example.printwithus.adaptar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.printwithus.NewSubProductActivity;
import com.example.printwithus.ProductsSubActivity;
import com.example.printwithus.R;
import com.example.sampleapp.model.Product;
import com.example.sampleapp.model.ProductItem;
import com.example.sampleapp.utils.CheckPermissions;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ProductItemAdaptar extends RecyclerView.Adapter<ProductItemAdaptar.ViewHolder> {
    private List<ProductItem> productList;
    public OrderCountListener onClickListener;
    public Context mctx;

    public interface OrderCountListener {
        void orderType(View v, int position);
    }

    public ProductItemAdaptar(List<ProductItem> productList, Context mctx, OrderCountListener listener) {
        this.productList = productList;
        this.mctx = mctx;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        boolean resultCamera = CheckPermissions.checkCameraPermission(mctx);
        boolean resultStorage = CheckPermissions.checkReadExternalStoragePermission(mctx);
        boolean resultWriteStorage = CheckPermissions.checkWriteExternalStoragePermission(mctx);

        holder.productName.setText(productList.get(position).getItem_name());
//        holder.productDesc.setText(productList.get(position).getDescription());
//        holder.productPrice.setText(productList.get(position).getPrice());
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
//        , productDesc, productPrice;
//        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.productName);
//            this.productDesc = itemView.findViewById(R.id.productDesc);
//            this.productPrice = itemView.findViewById(R.id.productPrice);
//            this.imageView = itemView.findViewById(R.id.productImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.orderType(v, getAdapterPosition());
                }
            });
        }
    }
}
