package com.example.ihc_navegador_de_compras_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button login;

    public static List<ProductModel> products = new ArrayList<>();
    public static List<Corredor> corredores = new ArrayList<>();
    public static int nPrateleirasX = 5;
    public static int nPrateleirasY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if(products.isEmpty()) {
            products.add(new ProductModel("Uva", 9));
            products.add(new ProductModel("Maçã", 9));
            products.add(new ProductModel("Banana", 9));
            products.add(new ProductModel("Laranja", 9));

            products.add(new ProductModel("Queijo Prato", 2));
            products.add(new ProductModel("Queijo Parmesão", 2));
            products.add(new ProductModel("Queijo Mussarela", 2));
            products.add(new ProductModel("Queijo Cheddar", 2));
            products.add(new ProductModel("Presunto", 2));

            products.add(new ProductModel("Suco de Uva", 8));
            products.add(new ProductModel("Suco de Laranja", 8));
            products.add(new ProductModel("Refrigerante", 8));

            products.add(new ProductModel("Arroz Branco", 4));
            products.add(new ProductModel("Arroz Integral", 4));
            products.add(new ProductModel("Arroz Parabolizado", 4));
            products.add(new ProductModel("Feijão", 4));

            products.add(new ProductModel("Ração para Cachorros", 5));
            products.add(new ProductModel("Ração para Gatos", 5));

            products.add(new ProductModel("Detergente", 11));
            products.add(new ProductModel("Veneno para Baratas", 11));

            products.add(new ProductModel("Sabonete", 12));
            products.add(new ProductModel("Creme Dental", 12));
        }
        for(ProductModel p: products) {
            p.setSelected(false);
        }

        if(corredores.isEmpty()) {
            corredores.add(new Corredor(true, 1, 0, 0, new ArrayList<>()));
            corredores.add(new Corredor(true, 2, 2, 0, new ArrayList<>()));
            corredores.add(new Corredor(true, 3, 4, 0, new ArrayList<>()));
            corredores.add(new Corredor(true, 4, 6, 0, new ArrayList<>()));
            corredores.add(new Corredor(true, 5, 8, 0, new ArrayList<>()));
            corredores.add(new Corredor(true, 6, 10, 0, new ArrayList<>()));

            corredores.add(new Corredor(true, 7, 0, 2, new ArrayList<>()));
            corredores.add(new Corredor(true, 8, 2, 2, new ArrayList<>()));
            corredores.add(new Corredor(true, 9, 4, 2, new ArrayList<>()));
            corredores.add(new Corredor(true, 10, 6, 2, new ArrayList<>()));
            corredores.add(new Corredor(true, 11, 8, 2, new ArrayList<>()));
            corredores.add(new Corredor(true, 12, 10, 2, new ArrayList<>()));


            corredores.add(new Corredor(false, 13, 0, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 14, 1, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 15, 2, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 16, 3, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 17, 4, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 18, 5, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 19, 6, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 20, 7, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 21, 8, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 22, 9, 1, new ArrayList<>()));
            corredores.add(new Corredor(false, 23, 10, 1, new ArrayList<>()));


            corredores.get(12).getVizinhos().add(corredores.get(0));
            corredores.get(12).getVizinhos().add(corredores.get(6));
            corredores.get(12).getVizinhos().add(corredores.get(13));
            corredores.get(0).getVizinhos().add(corredores.get(12));
            corredores.get(6).getVizinhos().add(corredores.get(12));

            corredores.get(13).getVizinhos().add(corredores.get(12));
            corredores.get(13).getVizinhos().add(corredores.get(14));

            corredores.get(14).getVizinhos().add(corredores.get(13));
            corredores.get(14).getVizinhos().add(corredores.get(15));
            corredores.get(14).getVizinhos().add(corredores.get(1));
            corredores.get(14).getVizinhos().add(corredores.get(7));
            corredores.get(1).getVizinhos().add(corredores.get(14));
            corredores.get(7).getVizinhos().add(corredores.get(14));

            corredores.get(15).getVizinhos().add(corredores.get(14));
            corredores.get(15).getVizinhos().add(corredores.get(16));

            corredores.get(16).getVizinhos().add(corredores.get(15));
            corredores.get(16).getVizinhos().add(corredores.get(17));
            corredores.get(16).getVizinhos().add(corredores.get(2));
            corredores.get(16).getVizinhos().add(corredores.get(8));
            corredores.get(2).getVizinhos().add(corredores.get(16));
            corredores.get(8).getVizinhos().add(corredores.get(16));

            corredores.get(17).getVizinhos().add(corredores.get(16));
            corredores.get(17).getVizinhos().add(corredores.get(18));

            corredores.get(18).getVizinhos().add(corredores.get(17));
            corredores.get(18).getVizinhos().add(corredores.get(19));
            corredores.get(18).getVizinhos().add(corredores.get(3));
            corredores.get(18).getVizinhos().add(corredores.get(9));
            corredores.get(3).getVizinhos().add(corredores.get(18));
            corredores.get(9).getVizinhos().add(corredores.get(18));

            corredores.get(19).getVizinhos().add(corredores.get(18));
            corredores.get(19).getVizinhos().add(corredores.get(20));

            corredores.get(20).getVizinhos().add(corredores.get(19));
            corredores.get(20).getVizinhos().add(corredores.get(21));
            corredores.get(20).getVizinhos().add(corredores.get(4));
            corredores.get(20).getVizinhos().add(corredores.get(10));
            corredores.get(4).getVizinhos().add(corredores.get(20));
            corredores.get(10).getVizinhos().add(corredores.get(20));

            corredores.get(21).getVizinhos().add(corredores.get(20));
            corredores.get(21).getVizinhos().add(corredores.get(22));

            corredores.get(22).getVizinhos().add(corredores.get(21));
            corredores.get(22).getVizinhos().add(corredores.get(5));
            corredores.get(22).getVizinhos().add(corredores.get(11));
            corredores.get(5).getVizinhos().add(corredores.get(22));
            corredores.get(11).getVizinhos().add(corredores.get(22));

            corredores.get(1).setCaminho(true);
            corredores.get(14).setCaminho(true);
            corredores.get(15).setCaminho(true);
            corredores.get(16).setCaminho(true);
            corredores.get(8).setCaminho(true);
        }

        login = findViewById(R.id.login_botao);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openTelaListaActivity();
                    }
                }
        );

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mercados_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void openTelaListaActivity() {
        Intent intent = new Intent(this, TelaPesquisaActivity.class);
        startActivity(intent);
    }
}