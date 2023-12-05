package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class desechost extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desechost);

        Button buttonRegreso = findViewById(R.id.buttonRegreso);

        Intent regresarInten = new Intent(this,tipo.class);
        buttonRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(regresarInten);
            }
        });

        Spinner spn1 = findViewById(R.id.spinner5);
        ArrayList Lista = new ArrayList();
        Lista.add("Gr");
        Lista.add("Lb");
        Lista.add("Kg");

        ArrayAdapter adap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Lista);
        spn1.setAdapter(adap);

        Button puntos = findViewById(R.id.buttonGuar1);
        puntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent punt = new Intent(desechost.this, splashpuntos.class);
                startActivity(punt);

            }

        });
    }
}
