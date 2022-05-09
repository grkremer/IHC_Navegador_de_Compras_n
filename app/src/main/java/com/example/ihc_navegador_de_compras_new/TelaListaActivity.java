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
    private Button retorno;
    private Button finalizar;

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
        retorno = findViewById(R.id.editar_lista);
        retorno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openTelaListaActivity();
                    }
                }
        );
        finalizar = findViewById(R.id.finalizar);
        finalizar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openLoginActivity();
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
    private void openTelaListaActivity() {
        Intent intent = new Intent(this, TelaPesquisaActivity.class);
        startActivity(intent);
    }
    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}