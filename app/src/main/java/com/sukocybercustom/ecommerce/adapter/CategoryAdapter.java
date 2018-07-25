package com.sukocybercustom.ecommerce.adapter;

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
import com.sukocybercustom.ecommerce.model.Category;
import com.sukocybercustom.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> listcategory;
    Context context;

    public CategoryAdapter(Context context, List<Category> listcategory) {
        this.listcategory = listcategory;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      holder.tvCategory.setText(listcategory.get(position).getName());
      holder.tvCategory.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(context, MainActivity.class);
              intent.putExtra("category_id",String.valueOf(listcategory.get(position).getCategory_id()));
              intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return listcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvCategoryAdapter) TextView tvCategory;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
