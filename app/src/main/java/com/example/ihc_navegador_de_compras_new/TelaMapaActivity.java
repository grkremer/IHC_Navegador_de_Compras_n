package com.example.ihc_navegador_de_compras_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TelaMapaActivity extends AppCompatActivity {

    private MapaView mapaView;

    private Button peguei;
    private Button retorno;
    private Button lista;
    private Button finalizar;

    private TextView produto;

    private List<ProductModel> cart;
    private int prodCount;

    private List<ProductModel> ordenaProdutos(List<ProductModel> productModelList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return productModelList.stream().sorted(Comparator.comparing(ProductModel::getLocalizacao)).collect(Collectors.toList());
        }
        return productModelList;
    }

    void apagaCaminho() {
        for(Corredor corredor : LoginActivity.corredores) {
            corredor.setCaminho(false);
        }
    }

    void encontraCaminho(int local1, int local2) {
        apagaCaminho();
        LoginActivity.corredores.get(local1 - 1).setCaminho(true);
        LoginActivity.corredores.get(local1 - 1).getVizinhos().get(0).setCaminho(true);

        LoginActivity.corredores.get(local2 - 1).setCaminho(true);
        LoginActivity.corredores.get(local2 - 1).getVizinhos().get(0).setCaminho(true);

        int menorId = Math.min(LoginActivity.corredores.get(local1 - 1).getVizinhos().get(0).getNumero(), LoginActivity.corredores.get(local2 - 1).getVizinhos().get(0).getNumero());
        int maiorId = Math.max(LoginActivity.corredores.get(local1 - 1).getVizinhos().get(0).getNumero(), LoginActivity.corredores.get(local2 - 1).getVizinhos().get(0).getNumero());
        for(int id = menorId; id < maiorId; id++) {
            LoginActivity.corredores.get(id).setCaminho(true);
        }
    }

    void updateTextoProduto() {
        if(prodCount < cart.size()) {
            produto.setText(cart.get(prodCount).getName() + " - Corredor " + cart.get(prodCount).getLocalizacao());
            peguei.setVisibility(View.VISIBLE);
        }
        else {
            produto.setText("Agora é só se dirigir ao caixa!");
            peguei.setVisibility(View.INVISIBLE);
        }
    }

    void incrementaProdCount() {
        boolean incrementa;
        do{
            prodCount++;
            if(prodCount < cart.size()) {
                incrementa = cart.get(prodCount).getSelected();
            }
            else {
                incrementa = false;
            }
        }while (incrementa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mapa);
        Objects.requireNonNull(getSupportActionBar()).hide();
        cart = getProductsByName(getIntent().getStringArrayExtra("products"));
        cart = ordenaProdutos(cart);

        mapaView = findViewById(R.id.mapaView);

        prodCount = -1;
        incrementaProdCount();
        if(!cart.isEmpty() && prodCount == 0) {
            LoginActivity.inicio = 1;
            LoginActivity.fim = cart.get(prodCount).getLocalizacao();
            encontraCaminho(LoginActivity.inicio, LoginActivity.fim);
        }
        peguei = findViewById(R.id.peguei);
        peguei.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(prodCount < cart.size()) {
                            cart.get(prodCount).setSelected(true);
                            incrementaProdCount();
                        }
                        if(prodCount < cart.size()) {
                            LoginActivity.inicio = cart.get(prodCount-1).getLocalizacao();
                            LoginActivity.fim = cart.get(prodCount).getLocalizacao();
                            if(LoginActivity.inicio == LoginActivity.fim) {
                                apagaCaminho();
                                LoginActivity.fim = -1;
                            }
                            else {
                                encontraCaminho(LoginActivity.inicio, LoginActivity.fim);
                            }
                        }
                        else {
                            LoginActivity.inicio = -1;
                            LoginActivity.fim = -1;
                            apagaCaminho();
                        }
                        updateTextoProduto();
                        mapaView.refresh();
                    }
                }
        );


        produto = findViewById(R.id.produto);
        updateTextoProduto();

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
        for(ProductModel productModel : cart) {
            productModel.setSelected(true);
        }
        intent.putExtra("products", getIntent().getStringArrayExtra("products"));
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