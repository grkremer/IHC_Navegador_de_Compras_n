package com.example.ihc_navegador_de_compras_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> products;
    private AppCompatActivity activity;
    boolean mostraLocalizacao;

    public ProductAdapter(AppCompatActivity activity, boolean mostraLocalizacao) {
        this.activity = activity;
        this.products = new ArrayList<ProductModel>();
        this.mostraLocalizacao = mostraLocalizacao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductModel item = products.get(position);
        if(mostraLocalizacao)
            holder.locationView.setText("Corredor "+item.getLocalizacao());
        holder.prodCheck.setText(item.getName());
        holder.prodCheck.setChecked(item.getSelected());
        holder.prodCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() { return products.size(); }

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
        TextView locationView;

        ViewHolder(View view) {
            super(view);
            prodCheck = view.findViewById(R.id.check_box_item);
            locationView = view.findViewById(R.id.text_item);
        }
    }
}
