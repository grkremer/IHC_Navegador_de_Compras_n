package com.example.ihc_navegador_de_compras_new;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    private List<ProductModel> products;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista);
        //Objects.requireNonNull(getSupportActionBar()).hide();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new ProductAdapter(TelaListaActivity.this);
        //recyclerView.setAdapter(adapter);

        products = new ArrayList<ProductModel>();
        products.add(new ProductModel("Uva"));
        products.add(new ProductModel("Suco de Uva"));

        /*searchView = findViewById(R.id.searchView);
        searchView.setOnSearchClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       //adapter.setProducts(products);
                    }
                }
        );*/
    }
}