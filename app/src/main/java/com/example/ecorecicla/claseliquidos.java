package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecorecicla.modelo.Liquidos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class claseliquidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claseliquidos);

        Button botonRegresar = findViewById(R.id.buttonRegreso);
        Button botonGuardar = findViewById(R.id.buttonGuar2);
        EditText medida = findViewById(R.id.editTextMedida);
        EditText cantidad = findViewById(R.id.editTextNumber);
        EditText mes = findViewById(R.id.editTextMes);

        Intent intent1 = new Intent(this, tipo.class);
        Intent intent2 = new Intent(this, categorias.class);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!medida.getText().toString().isEmpty() && !cantidad.getText().toString().isEmpty() && !mes.getText().toString().isEmpty()) {
                    String mesBuscado = mes.getText().toString();
                    boolean mesExiste = verificarMes(mesBuscado);
                    if (mesExiste) {
                        Toast.makeText(claseliquidos.this, "El mes ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(medida.getText().toString(), cantidad.getText().toString(), mes.getText().toString());
                        if (datosGuardados) {
                            startActivity(intent2);
                        } else {
                            Toast.makeText(claseliquidos.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(claseliquidos.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean verificarMes(String mesBuscado) {
        File file = new File(getFilesDir(), "liquidos.txt");
        mesBuscado = mesBuscado.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[2];
                if (mes.equalsIgnoreCase(mesBuscado)) {
                    bufferedReader.close();
                    return true;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean guardarDatos(String medida, String cantidad, String mes) {
        File file = new File(getFilesDir(), "liquidos.txt");
        mes = mes.toLowerCase();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Liquidos liquidos = new Liquidos(Float.parseFloat(medida), Float.parseFloat(cantidad), mes);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%s", liquidos.getMedida(), liquidos.getCantidad(),liquidos.getMes());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
