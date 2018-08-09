package mx.diagraph.bluesheet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Form_4 extends AppCompatActivity {

    TextView modulo;

    CheckBox hoja, caja, madera, plastico, papel, pet, cartoncillo, sacosp, sacosrafia, micro, metal, vidrio;
    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_4);
        getSupportActionBar().setTitle("");
        generateText();
    }

    private void generateText() {
        hoja = findViewById(R.id.hoja);
        caja = findViewById(R.id.madera);
        madera = findViewById(R.id.madera);
        plastico = findViewById(R.id.plastico);
        papel = findViewById(R.id.papel);
        pet = findViewById(R.id.pet);
        cartoncillo = findViewById(R.id.cartoncillo);
        sacosp = findViewById(R.id.sacos);
        sacosrafia = findViewById(R.id.rafia);
        micro = findViewById(R.id.micro);
        metal = findViewById(R.id.metal);
        vidrio = findViewById(R.id.vidrio);

        modulo = findViewById(R.id.headerModulo);
    }

    public void sendMessage(View view){
        addToWorkBook();
        Intent intent = new Intent(this, Form_5.class);
        startActivity(intent);
    }


    private void addToWorkBook() {
        ArrayList<String> strArray;

        for (int i = 2; i < 5 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add(modulo.getText().toString());
                    arrayLists.add(strArray);
                    break;

                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(hoja.getText().toString());
                    strArray.add(hoja.isChecked() ? "x" : " ");
                    strArray.add(madera.getText().toString());
                    strArray.add(hoja.isChecked() ? "x" : " ");
                    strArray.add(plastico.getText().toString());
                    strArray.add(plastico.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(caja.getText().toString());
                    strArray.add(caja.isChecked() ? "x" : " ");
                    strArray.add(papel.getText().toString());
                    strArray.add(papel.isChecked() ? "x" : " ");
                    strArray.add(pet.getText().toString());
                    strArray.add(pet.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(micro.getText().toString());
                    strArray.add(micro.isChecked() ? "x" : " ");
                    strArray.add(metal.getText().toString());
                    strArray.add(metal.isChecked() ? "x" : " ");
                    strArray.add(vidrio.getText().toString());
                    strArray.add(vidrio.isChecked() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
            }
        }

    }
}
