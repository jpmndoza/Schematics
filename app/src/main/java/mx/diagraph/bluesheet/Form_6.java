package mx.diagraph.bluesheet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Form_6 extends AppCompatActivity {

    Spinner llena_spinner, controlesInput, alimentacionInput, necesitaInput, transportadorInput, cotizacionInput, condensacionInput;
    TextView llena, condensacion, transportador, cotizacion, necesita, alimentacion, controles;
    EditText llenaInput;
    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_6);
        getSupportActionBar().setTitle(" ");
        generateText();
        generateSpinners();
    }

    private void generateSpinners() {
        ArrayAdapter controles_adapter = ArrayAdapter.createFromResource( this, R.array.controles, android.R.layout.simple_spinner_item);
        controles_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter boolean_adapter = ArrayAdapter.createFromResource(this,R.array.booleans,android.R.layout.simple_spinner_item);
        boolean_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter alimentacion_adapter = ArrayAdapter.createFromResource(this,R.array.alimentacion,android.R.layout.simple_spinner_item);
        alimentacion_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        llena_spinner.setAdapter(boolean_adapter);
        controlesInput.setAdapter(controles_adapter);
        necesitaInput.setAdapter(boolean_adapter);
        condensacionInput.setAdapter(boolean_adapter);
        transportadorInput.setAdapter(boolean_adapter);
        cotizacionInput.setAdapter(boolean_adapter);
        alimentacionInput.setAdapter(alimentacion_adapter);
    }

    private void generateText() {

        llena = findViewById(R.id.llena);
        llenaInput = findViewById(R.id.llenaInput);

        condensacion = findViewById(R.id.condensacion);
        transportador= findViewById(R.id.transportador);
        cotizacion = findViewById(R.id.requierecotizacion);
        necesita = findViewById(R.id.necesita);
        alimentacion = findViewById(R.id.alimentacion);
        controles = findViewById(R.id.controles);


        llena_spinner = findViewById(R.id.llena_spinner);
        controlesInput = findViewById(R.id.controlesInput);
        alimentacionInput = findViewById(R.id.alimentacionInput);
        necesitaInput = findViewById(R.id.necesitaInput);
        transportadorInput = findViewById(R.id.transportadorInput);
        cotizacionInput = findViewById(R.id.cotizacionInput);
        condensacionInput = findViewById(R.id.condensacionInput2);

    }

    public void sendMessage(View view){
        addToWorkBook();
        Intent intent = new Intent(this, Form_7.class);
        startActivity(intent);
    }
    private void addToWorkBook() {
        ArrayList<String> strArray;

        for (int i = 1; i < 7 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add("¿Se llena el producto o está lleno al momento de identificarlo?");
                    strArray.add(llena_spinner.getSelectedItem().toString());
                    strArray.add("Se llena con: " + llenaInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(condensacion.getText().toString());
                    strArray.add(condensacionInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(" ");
                    arrayLists.add(strArray);
                    break;
                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(transportador.getText().toString());
                    strArray.add(transportadorInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 5:
                    strArray = new ArrayList<>();
                    strArray.add(cotizacion.getText().toString());
                    strArray.add(cotizacionInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 6:
                    strArray = new ArrayList<>();
                    strArray.add(controles.getText().toString());
                    strArray.add(controlesInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
            }
        }
    }
}
