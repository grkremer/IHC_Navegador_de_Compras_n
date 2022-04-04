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

    //private List<ProductModel> products;
    int counter = 0;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista);
        Objects.requireNonNull(getSupportActionBar()).hide();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(TelaListaActivity.this);
        recyclerView.setAdapter(adapter);

        List<ProductModel> products1 = new ArrayList<ProductModel>();
        products1.add(new ProductModel("Uva"));
        products1.add(new ProductModel("Suco de Uva"));

        List<ProductModel> products2 = new ArrayList<ProductModel>();
        products2.add(new ProductModel("Queijo Parmesão"));
        products2.add(new ProductModel("Queijo Mussarela"));
        products2.add(new ProductModel("Queijo Cheddar"));
        products2.add(new ProductModel("Queijo Prato"));

        List<ProductModel> products3 = new ArrayList<ProductModel>();
        products3.add(new ProductModel("Arroz Branco"));
        products3.add(new ProductModel("Arroz Integral"));
        products3.add(new ProductModel("Arroz Parabolizado"));



        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                switch (counter) {
                    case 0:
                        adapter.setProducts(products1);
                        break;
                    case 1:
                        adapter.setProducts(products2);
                        break;
                    default:
                        adapter.setProducts(products3);
                        break;
                }
                counter++;
                //adapter.setProducts(products);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}