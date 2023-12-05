package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class liquidos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liquidos);

        Button buttonRegreso = findViewById(R.id.buttonRegreso);

        Intent regresarInten = new Intent(this,tipo.class);
        buttonRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(regresarInten);
            }
        });

        Spinner spn1 = findViewById(R.id.spinner4);
        ArrayList Lista = new ArrayList();
        Lista.add("Ml");
        Lista.add("Lt");

        ArrayAdapter adap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Lista);
        spn1.setAdapter(adap);

        Button puntos = findViewById(R.id.buttonGuar2);
        puntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent punt = new Intent(liquidos.this, splashpuntos.class);
                startActivity(punt);

            }

        });
    }
}
