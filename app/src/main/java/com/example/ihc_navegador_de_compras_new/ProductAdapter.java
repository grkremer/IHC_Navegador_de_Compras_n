package com.example.ihc_navegador_de_compras_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> products;
    private TelaListaActivity activity;

    public ProductAdapter(TelaListaActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    private ProductModel findProductByName(List<ProductModel> products, String name) {
        for(ProductModel product : products) {
            if(product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductModel item = products.get(position);
        holder.prodCheck.setText(item.getName());
        holder.prodCheck.setChecked(item.getSelected());
        holder.prodCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                findProductByName(products, item.getName()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox prodCheck;

        ViewHolder(View view) {
            super(view);
            prodCheck = view.findViewById(R.id.check_box_item);
        }
    }
}
