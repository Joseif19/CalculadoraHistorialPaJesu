package com.example.calculadorahistorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private String input = "";
    private double n1 = 0, n2 = 0;
    private String operacion = "";
    private Button botonHistorial;
    private ArrayList<String> listaHistorial = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        botonHistorial = (Button) findViewById(R.id.botonHistorial);

        configBotones();

        botonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historial = new Intent(MainActivity.this, MainActivity2.class);
                historial.putStringArrayListExtra("historial", listaHistorial);
                startActivity(historial);
            }
        });
    }



    private void configBotones() {
        findViewById(R.id.boton1).setOnClickListener(v -> añadirTexto("1"));
        findViewById(R.id.boton2).setOnClickListener(v -> añadirTexto("2"));
        findViewById(R.id.boton3).setOnClickListener(v -> añadirTexto("3"));
        findViewById(R.id.boton4).setOnClickListener(v -> añadirTexto("4"));
        findViewById(R.id.boton5).setOnClickListener(v -> añadirTexto("5"));
        findViewById(R.id.boton6).setOnClickListener(v -> añadirTexto("6"));
        findViewById(R.id.boton7).setOnClickListener(v -> añadirTexto("7"));
        findViewById(R.id.boton8).setOnClickListener(v -> añadirTexto("8"));
        findViewById(R.id.boton9).setOnClickListener(v -> añadirTexto("9"));
        findViewById(R.id.boton0).setOnClickListener(v -> añadirTexto("0"));

        findViewById(R.id.sumar).setOnClickListener(v -> operacion("+"));
        findViewById(R.id.restar).setOnClickListener(v -> operacion("-"));
        findViewById(R.id.multiplicar).setOnClickListener(v -> operacion("*"));
        findViewById(R.id.dividir).setOnClickListener(v -> operacion("/"));
        findViewById(R.id.igual).setOnClickListener(v -> calcular());
    }

    private void añadirTexto(String valor) {
        input = input + valor;
        texto.setText(input);
    }

    private void operacion(String operacion) {
        n1 = Double.parseDouble(input);
        this.operacion = operacion;
        input = "";
    }

    private void calcular() {
        n2 = Double.parseDouble(input);
        double resultado = 0;
        String operacion = n1 + " " + this.operacion + " " + n2 + " = ";

        switch (this.operacion) {
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;
            case "*":
                resultado = n1 * n2;
                break;
            case "/":
                if (n2 != 0) {
                    resultado = n1 / n2;
                } else {
                    texto.setText("ERROR");
                }
                break;
        }

        texto.setText(String.valueOf(resultado));

        operacion += resultado;

        listaHistorial.add(operacion);

        input = "";
        n1 = resultado;
    }
}