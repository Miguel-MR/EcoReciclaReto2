package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecorecicla.modelo.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonIngreso = findViewById(R.id.buttonIngresar);
        Button botonRegistro = findViewById(R.id.buttonRegistroNew);
        EditText usuarioEdit = findViewById(R.id.editUsuario);
        EditText passwordEdit = findViewById(R.id.editContraseña);

        Intent registroIntent = new Intent(this, resgistro.class);
        Intent principalIntent = new Intent(this, categorias.class);

        // Lector de los datos registrados
        File file = new File(getFilesDir(), "registroDeUsuarios.txt");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en los datos del usuario separados por comas
                String[] userData = line.split(",");
                String nombre = userData[0];
                String correo = userData[1];
                String celular = userData[2];
                String password = userData[3];
                // Crear un objeto Usuario y añadirlo a la lista de usuarios
                Usuario nuevoUsuario = new Usuario(nombre, correo, celular, password);
                usuarios.add(nuevoUsuario);
            }
            reader.close();

            // Imprimir información de los usuarios leídos en el archivo
            for (Usuario usuario : usuarios) {
                Log.d("Usuarios", "Nombre: " + usuario.getNombre() + ", Correo: " + usuario.getCorreo() +
                                ", Celular: " + usuario.getCelular() + ", Contraseña: " + usuario.getContraseña());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurar el evento de clic para el botón de ingreso
        botonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si se han ingresado datos de usuario y contraseña
                if (!usuarioEdit.getText().toString().isEmpty() && !passwordEdit.getText().toString().isEmpty()) {
                    String inputUsuario = usuarioEdit.getText().toString();
                    String inputPassword = passwordEdit.getText().toString();

                    // Buscar el usuario coincidente en la lista de usuarios
                    for (Usuario usuario : usuarios) {
                        if (usuario.getContraseña().equals(inputPassword)) {
                            if (usuario.getContraseña().equals(inputUsuario) || usuario.getCorreo().equals(inputUsuario) || usuario.getNombre().equals(inputUsuario)) {
                                // Si se encuentra una coincidencia, iniciar la actividad principal y salir del bucle
                                startActivity(principalIntent);
                                return;
                            }
                        }
                    }
                    // Si no se encontró una coincidencia, mostrar un mensaje de error utilizando Toast
                    Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el evento de clic para el enlace de registro
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de registro de usuario
                startActivity(registroIntent);
            }
        });
    }
}
