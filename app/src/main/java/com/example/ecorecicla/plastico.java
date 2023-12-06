package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class plastico extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plastico);

        Button buttonRegreso = findViewById(R.id.buttonRegreso);

        Intent regresarInten = new Intent(this,tipo.class);
        buttonRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(regresarInten);
            }
        });

        Button puntos = findViewById(R.id.buttonGuar5);
        puntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent punt = new Intent(plastico.this, splashpuntos.class);
                startActivity(punt);

            }
        });
    }
}
