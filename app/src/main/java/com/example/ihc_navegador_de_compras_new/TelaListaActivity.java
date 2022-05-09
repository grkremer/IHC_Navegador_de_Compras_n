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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TelaListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    private List<ProductModel> productList;
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

        productList = new ArrayList<ProductModel>();

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateProductList(pesquisaProdutos(query));
                adapter.setProducts(productList);
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

    void updateProductList(List<ProductModel> results) {
        for(ProductModel productModel : productList) {
            if(productModel.getSelected()) {
                results.add(productModel);
            }
        }
        productList = results;
    }

    List<ProductModel> pesquisaProdutos(String termo) {
        termo = termo.toUpperCase();
        List<ProductModel> results = new ArrayList<ProductModel>();
        for(ProductModel productModel : LoginActivity.products) {
            if(productModel.getName().toUpperCase().contains(termo)) {
                results.add(productModel);
            }
        }
        return results;
    }

    String[] getSelectedNames(List<ProductModel> productModelList) {
        List<String> names = new ArrayList<String>();
        for(ProductModel productModel : productModelList) {
            if(productModel.getSelected())
                names.add(productModel.getName());
        }
        String[] namesArray = new String[names.size()];
        for(int i=0; i<namesArray.length; i++)
            namesArray[i] = names.get(i);
        return namesArray;
    }

    public void openTelaRotaActivity() {
        Intent intent = new Intent(this, TelaRotaActivity.class);
        intent.putExtra("products", getSelectedNames(productList));
        startActivity(intent);
    }
}