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

public class TelaRotaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Button retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_rota);
        List<ProductModel> cart = getProductsByName(getIntent().getStringArrayExtra("products"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(TelaRotaActivity.this,true);
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
    }

    private List<ProductModel> getProductsByName(String[] names) {
        List<ProductModel> selected = new ArrayList<ProductModel>();
        for(ProductModel productModel : TelaListaActivity.products) {
            for(String name : names) {
                if(productModel.getName().equals(name)) {
                    selected.add(productModel);
                }
            }
        }
        return selected;
    }
    private void openTelaListaActivity() {
        Intent intent = new Intent(this, TelaListaActivity.class);
        startActivity(intent);
    }
}