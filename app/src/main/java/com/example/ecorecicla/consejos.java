package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class consejos extends AppCompatActivity {
    private List<String> consejosList;
    private TextView consejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);

        Button buttonRegreso = findViewById(R.id.buttonRegreso);

        Intent regresarInten = new Intent(this,categorias.class);
        buttonRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(regresarInten);
            }
        });

        consejos = findViewById(R.id.textConsejosVr);
        consejosList = new ArrayList<>();

        leerConsejos();
        mostrarConsejosAleatorios();
    }

    private void leerConsejos() {
        File file = new File(getFilesDir(),"consejos.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                consejosList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void mostrarConsejosAleatorios() {
        if (consejosList.isEmpty()){
            consejos.setText("El banco de consejos está cargando");
            return;
        }
        Random random = new Random();
        int deft = random.nextInt(consejosList.size());
        String consejo = consejosList.get(deft);
        consejos.setText(consejo);
    }
}
