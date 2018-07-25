package com.sukocybercustom.ecommerce.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sukocybercustom.ecommerce.R;
import com.sukocybercustom.ecommerce.activity.MainActivity;
import com.sukocybercustom.ecommerce.activity.ProductDetailActivity;
import com.sukocybercustom.ecommerce.model.Product;


import java.sql.BatchUpdateException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> listproduk;
    Product product;
    Context context;
    private int counter=0;


    public ProductAdapter(Context context, ArrayList<Product> listproduk) {
        this.listproduk = listproduk;
        this.context = context;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ShowDialogAddProduct(listproduk.get(position).getName());
            }
        });
        holder.tvJudul.setText(listproduk.get(position).getName());
        holder.tvPrice.setText(listproduk.get(position).getPrice());
        Picasso.with(context).load(listproduk.get(position).getImage()).error(R.mipmap.ic_launcher).into(holder.ivProduk);
        holder.ivProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id",listproduk.get(position).getProduct_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listproduk.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView) ImageView ivProduk;
        @BindView(R.id.tvJudul) TextView tvJudul;
        @BindView(R.id.btnAddToWishList) Button btnAddToWishList;
        @BindView(R.id.btnBeli) Button btnBeli;
        @BindView(R.id.tvPrice) TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void ShowDialogAddProduct(String nama_barang){
        View view = View.inflate(context,R.layout.layout_customdialog_addtocart,null);
        TextView tvNamaBarangDialog = (TextView)view.findViewById(R.id.tvNamaBarangDialog);
        Button btnAdd = (Button)view.findViewById(R.id.btnAddToCartDialog);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        tvNamaBarangDialog.setText(nama_barang);
        dialog.setTitle(nama_barang);
        dialog.show();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isChangeMenu = true;
                if (context instanceof MainActivity){
                    ((MainActivity) context).onPrepareOptionsMenu(MainActivity.menu);
                }
                dialog.cancel();
            }
        });

    }

}
