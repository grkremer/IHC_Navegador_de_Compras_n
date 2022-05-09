package com.example.ihc_navegador_de_compras_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    private Button mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista);
        Objects.requireNonNull(getSupportActionBar()).hide();
        List<ProductModel> cart = getProductsByName(getIntent().getStringArrayExtra("products"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(TelaListaActivity.this,true);
        recyclerView.setAdapter(adapter);
        adapter.setProducts(cart);

        mapa = findViewById(R.id.mapa);
        mapa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openTelaMapaActivity();
                    }
                }
        );
    }
    private List<ProductModel> getProductsByName(String[] names) {
        List<ProductModel> selected = new ArrayList<ProductModel>();
        for(ProductModel productModel : LoginActivity.products) {
            for(String name : names) {
                if(productModel.getName().equals(name)) {
                    selected.add(productModel);
                }
            }
        }
        return selected;
    }
    private void openTelaMapaActivity() {
        Intent intent = new Intent(this, TelaMapaActivity.class);
        intent.putExtra("products", getIntent().getStringArrayExtra("products"));
        startActivity(intent);
    }
}