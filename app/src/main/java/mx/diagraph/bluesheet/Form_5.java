package mx.diagraph.bluesheet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Form_5 extends AppCompatActivity {

    CheckBox oval, conica, irregular, cilindrica, plana, esfera, bobina,
             cera, agua, condensacion, grasa, laminado, tratamiento, polvo, desmoldante;
    Spinner tiempoInput, localizacionInput;
    TextView forma, recubrimiento, localizacion, tiemposec, seg;
    EditText segInput;

    ExcelFileGenerator excel = new ExcelFileGenerator();
    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_5);
        getSupportActionBar().setTitle(" ");
        generateText();
        generateSpinners();
    }

    private void generateSpinners() {

        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.localizacion, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter spinner_adapter2 = ArrayAdapter.createFromResource(this,R.array.booleans,android.R.layout.simple_spinner_item);
        spinner_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        localizacionInput.setAdapter(spinner_adapter);
        tiempoInput.setAdapter(spinner_adapter2);
    }

    private void generateText() {

        forma = findViewById(R.id.forma);
        segInput = findViewById(R.id.editText);
        recubrimiento = findViewById(R.id.recubrimiento);
        localizacion = findViewById(R.id.localizacion);
        tiemposec = findViewById(R.id.tiempoSecado);
        seg = findViewById(R.id.seg);
        desmoldante = findViewById(R.id.desmoldante);

        oval = findViewById(R.id.oval);
        conica = findViewById(R.id.conica);
        irregular = findViewById(R.id.irregular);
        cilindrica = findViewById(R.id.cilindrica);
        plana = findViewById(R.id.plana);
        esfera = findViewById(R.id.esfera);
        bobina = findViewById(R.id.bobina);
        cera = findViewById(R.id.cera);
        agua = findViewById(R.id.agua);
        polvo = findViewById(R.id.polvo);
        condensacion = findViewById(R.id.condensacion);
        grasa = findViewById(R.id.grasa);
        laminado = findViewById(R.id.laminado);
        tratamiento = findViewById(R.id.tratamiento);

        tiempoInput = findViewById(R.id.tiempoInput);
        localizacionInput = findViewById(R.id.localizacionInput);
    }

    public void sendMessage(View view){
        addToWorkBook();
        Intent intent = new Intent(this, Form_6.class);
        startActivity(intent);
    }

    private void addToWorkBook() {
        ArrayList<String> strArray;
        for (int i = 2; i < 10 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add(forma.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(oval.getText().toString());
                    strArray.add(oval.isChecked() ? "x" : " ");
                    strArray.add(conica.getText().toString());
                    strArray.add(conica.isChecked() ? "x" : " ");
                    strArray.add(irregular.getText().toString());
                    strArray.add(irregular.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(cilindrica.getText().toString());
                    strArray.add(cilindrica.isChecked() ? "x" : " ");
                    strArray.add(plana.getText().toString());
                    strArray.add(plana.isChecked() ? "x" : " ");
                    strArray.add(esfera.getText().toString());
                    strArray.add(esfera.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(" ");
                    arrayLists.add(strArray);
                    break;
                case 5:
                    strArray = new ArrayList<>();
                    strArray.add(cera.getText().toString());
                    strArray.add(cera.isChecked() ? "x" : " ");
                    strArray.add(condensacion.getText().toString());
                    strArray.add(condensacion.isChecked() ? "x" : " ");
                    strArray.add(grasa.getText().toString());
                    strArray.add(grasa.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 6:
                    strArray = new ArrayList<>();
                    strArray.add(agua.getText().toString());
                    strArray.add(agua.isChecked() ? "x" : " ");
                    strArray.add(polvo.getText().toString());
                    strArray.add(polvo.isChecked() ? "x" : " ");
                    strArray.add(desmoldante.getText().toString());
                    strArray.add(desmoldante.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 7:
                    strArray = new ArrayList<>();
                    strArray.add(laminado.getText().toString());
                    strArray.add(laminado.isChecked() ? "x" : " ");
                    strArray.add(tratamiento.getText().toString());
                    strArray.add(tratamiento.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 8:
                    strArray = new ArrayList<>();
                    strArray.add(localizacion.getText().toString());
                    strArray.add(localizacionInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 9:
                    strArray = new ArrayList<>();
                    strArray.add(tiemposec.getText().toString());
                    strArray.add(tiempoInput.getSelectedItem().toString());
                    strArray.add(seg.getText().toString());
                    strArray.add(segInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
            }
        }

    }
}
