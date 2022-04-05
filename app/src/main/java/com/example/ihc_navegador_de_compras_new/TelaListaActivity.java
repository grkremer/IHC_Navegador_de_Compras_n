package com.example.ihc_navegador_de_compras_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    public static List<ProductModel> products = new ArrayList<>();
    int counter = 0;
    private SearchView searchView;
    private Button comecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista);
        Objects.requireNonNull(getSupportActionBar()).hide();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(TelaListaActivity.this,false);
        recyclerView.setAdapter(adapter);

        products.add(new ProductModel("Uva", 9));
        products.add(new ProductModel("Queijo Prato", 12));
        products.add(new ProductModel("Arroz Branco", 4));

        List<ProductModel> products1 = new ArrayList<ProductModel>();
        products1.add(new ProductModel("Uva"));
        products1.add(new ProductModel("Suco de Uva"));

        List<ProductModel> products2 = new ArrayList<ProductModel>();
        products2.add(new ProductModel("Queijo Parmesão"));
        products2.add(new ProductModel("Queijo Mussarela"));
        products2.add(new ProductModel("Queijo Cheddar"));
        products2.add(new ProductModel("Queijo Prato"));
        products2.add(new ProductModel("Uva", true));

        List<ProductModel> products3 = new ArrayList<ProductModel>();
        products3.add(new ProductModel("Arroz Branco"));
        products3.add(new ProductModel("Arroz Integral"));
        products3.add(new ProductModel("Arroz Parabolizado"));
        products3.add(new ProductModel("Uva", true));
        products3.add(new ProductModel("Queijo Prato",true));

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

        comecar = findViewById(R.id.comecar);
        comecar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openTelaRotaActivity();
                    }
                }
        );
    }
    public void openTelaRotaActivity() {
        Intent intent = new Intent(this, TelaRotaActivity.class);
        String[] p = {"Uva", "Queijo Prato", "Arroz Branco"};
        intent.putExtra("products", p);
        startActivity(intent);
    }
}