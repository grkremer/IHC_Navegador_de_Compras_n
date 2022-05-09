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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if(products.isEmpty()) {
            products.add(new ProductModel("Uva", 9));
            products.add(new ProductModel("Queijo Prato", 12));
            products.add(new ProductModel("Arroz Branco", 4));

            products.add(new ProductModel("Suco de Uva", 0));

            products.add(new ProductModel("Queijo Parmesão", 0));
            products.add(new ProductModel("Queijo Mussarela", 0));
            products.add(new ProductModel("Queijo Cheddar", 0));

            products.add(new ProductModel("Arroz Integral", 0));
            products.add(new ProductModel("Arroz Parabolizado", 0));
        }
        for(ProductModel p: products) {
            p.setSelected(false);
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