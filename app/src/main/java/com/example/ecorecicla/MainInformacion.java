package com.example.ecorecicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainInformacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_informacion);

        // Crear archivo para usuarios y escribir los datos
        File file1 = new File(getFilesDir(), "registroDeUsuarios.txt");
        try {
            FileWriter writer = new FileWriter(file1);
            writer.append("root,root@root.com,toor,TOOR\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear archivo para registro de agua
        File file2 = new File(getFilesDir(), "agua.txt");
        try {
            FileWriter writer = new FileWriter(file2);
            writer.append("15,150000,enero\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear archivo para registro de electricidad
        File file3 = new File(getFilesDir(), "electricidad.txt");
        try {
            FileWriter writer = new FileWriter(file3);
            writer.append("15,150000,enero\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear archivo para registro de consejos
        File file4 = new File(getFilesDir(), "consejos.txt");
        try {
            FileWriter writer = new FileWriter(file4);

            // Lista de consejos para ahorrar agua y electricidad en un array
            String[] bancodeConsejos = {
                    "Apaga los electrodomésticos y luces cuando no los estés utilizando.",
                    "Utiliza bombillas LED de bajo consumo energético.",
                    "Aprovecha la luz natural abriendo cortinas y persianas durante el día.",
                    "No dejes los grifos abiertos innecesariamente.",
                    "Instala aireadores en los grifos.",
                    "Repara las fugas de agua tan pronto como las detectes.",
                    "Utiliza lavadoras y lavavajillas con carga completa.",
                    "Recoge agua de lluvia para regar las plantas.",
                    "Utiliza programas de lavado en frío en la lavadora.",
                    "Invierte en electrodomésticos de alta eficiencia energética."
            };

            writer.append("Lista de consejos:\n");
            for (String consejo : bancodeConsejos) {
                writer.append(consejo).append("\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, activity.class);
        startActivity(intent);
    }
}