package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecorecicla.modelo.Liquidos;
import com.example.ecorecicla.modelo.Papel;
import com.example.ecorecicla.modelo.Plastico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class estadisticas extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);

        Button botonRegresar = findViewById(R.id.buttonRegreso);
        tableLayout = findViewById(R.id.myTableLayout);
        Intent intent = new Intent(this, tipo.class);

        File liquidosFile = new File(getFilesDir(), "liquidos.txt");
        File plasticosFile = new File(getFilesDir(), "plastico.txt");

        List<Liquidos> listaLiquidos = leerArchivoliquidos(liquidosFile);
        List<Plastico> listaPlastico = leerArchivoPlastico(plasticosFile);

        addElementLiquidos(listaLiquidos);
        addElementPlastico(listaPlastico);
        addPromedioLiquidos(listaLiquidos);
        addPromedioPlastico(listaPlastico);

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
    private void addPromedioLiquidos(List<Liquidos> liquidosList){

        float promedioConsumoLiquidos = calcularPromedioVolumenLiquidos(liquidosList);
        float promedioPrecioLiquidos = calcularPromedioPrecioLiquidos(liquidosList);

        TableRow row = new TableRow(this);

        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white);

        TextView cell2 = new TextView(this);
        cell2.setText("Liquidos");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white);

        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoLiquidos));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        tableLayout.addView(row);
    }
    private void addPromedioPlastico(List<Plastico> plasticoList){

        float promedioConsumoPlastico = calcularPromedioKilovatios(plasticoList);
        float promedioPrecioPlastico = calcularPromedioPrecioPlastico(plasticoList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white);

        TextView cell2 = new TextView(this);
        cell2.setText("plastico");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white);

        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoPlastico));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        tableLayout.addView(row);
    }
    private void addElementLiquidos(List<Liquidos> liquidosList){

        for (Liquidos item: liquidosList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText("Liquidos");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getMedida())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            tableLayout.addView(row);
        }
    }
    private void addElementPlastico(List<Plastico> plasticoList){

        for (Plastico item: plasticoList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText("Elastico");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getMedida())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            tableLayout.addView(row);
        }
    }
    private static List<Liquidos> leerArchivoliquidos(File archivo) {
        List<Liquidos> listaLiquidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float volumen = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Liquidos liquidos = new Liquidos(volumen, precio, mes);
                listaLiquidos.add(liquidos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaLiquidos;
    }

    private static List<Plastico> leerArchivoPlastico(File archivo) {
        List<Plastico> listaPlastico = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float kilovatios = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Plastico plastico = new Plastico(kilovatios, precio, mes);
                listaPlastico.add(plastico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPlastico;
    }

    private float calcularPromedioVolumenLiquidos(List<Liquidos> liquidosList) {
        float sum = 0;
        for (Liquidos item : liquidosList) {
            sum += item.getMedida();
        }
        return sum / liquidosList.size();
    }
    private float calcularPromedioPrecioLiquidos(List<Liquidos> liquidosList) {
        float sum = 0;
        for (Liquidos item : liquidosList) {
            sum += item.getCantidad();
        }
        return sum / liquidosList.size();
    }

    private float calcularPromedioKilovatios(List<Plastico> plasticoList) {
        float sum = 0;
        for (Plastico item : plasticoList) {
            sum += item.getMedida();
        }
        return sum / plasticoList.size();
    }

    private float calcularPromedioPrecioPlastico(List<Plastico> plasticoList) {
        float sum = 0;
        for (Plastico item : plasticoList) {
            sum += item.getCantidad();
        }
        return sum / plasticoList.size();
    }
}
