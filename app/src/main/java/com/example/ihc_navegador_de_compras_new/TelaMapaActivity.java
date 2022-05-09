package com.example.ihc_navegador_de_compras_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaMapaActivity extends AppCompatActivity {

    private MapaView mapaView;

    private Button peguei;
    private Button retorno;
    private Button lista;
    private Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mapa);
        Objects.requireNonNull(getSupportActionBar()).hide();
        List<ProductModel> cart = getProductsByName(getIntent().getStringArrayExtra("products"));

        mapaView = findViewById(R.id.mapaView);

        peguei = findViewById(R.id.peguei);
        peguei.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        mapaView.refresh();
                    }
                }
        );
        retorno = findViewById(R.id.editar_lista);
        retorno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openTelaPesquisaActivity();
                    }
                }
        );
        lista = findViewById(R.id.lista);
        lista.setOnClickListener(
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
    private void openTelaPesquisaActivity() {
        Intent intent = new Intent(this, TelaPesquisaActivity.class);
        startActivity(intent);
    }
    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void openTelaListaActivity() {
        Intent intent = new Intent(this, TelaListaActivity.class);
        intent.putExtra("products", getIntent().getStringArrayExtra("products"));
        startActivity(intent);
    }
}