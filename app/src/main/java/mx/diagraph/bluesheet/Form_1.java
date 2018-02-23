package mx.diagraph.bluesheet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class Form_1 extends AppCompatActivity {


    TextView representante, empresa, producto, montoVenta, aftermarket, fechaDeCierre;
    EditText representanteInput, empresaInput, productoInput, montoVentaInput, aftermarketInput, fechaDeCierreInput;
    ExcelFileGenerator excel = new ExcelFileGenerator();
    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_1);
        getSupportActionBar().setTitle("");
        generateText();
    }

    private void generateText() {
        representante = findViewById(R.id.representante);
        representanteInput = findViewById(R.id.representanteInput);
        empresa = findViewById(R.id.nombreEmpresa);
        empresaInput = findViewById(R.id.nombreEmpresaInput);
        producto = findViewById(R.id.producto);
        productoInput = findViewById(R.id.productoInput);
        montoVenta = findViewById(R.id.montoDeVenta2);
        montoVentaInput = findViewById(R.id.montoDeVentaInput);
        aftermarket = findViewById(R.id.aftermarket);
        aftermarketInput = findViewById(R.id.aftermarketInput2);
        fechaDeCierre = findViewById(R.id.fechaDeCierre);
        fechaDeCierreInput = findViewById(R.id.dateInput);
    }

    public void sendMessage(View view) throws IOException {
        addToWorkBook();
        Intent intent = new Intent(this, ARD.class);
        intent.putExtra("list", arrayLists);
        startActivity(intent);
    }

    private void addToWorkBook() {

        ArrayList<String> strArray;

        for (int i = 0; i < 6 ; i++) {

            switch (i){
                case 0:
                        strArray = new ArrayList<>();
                        strArray.add(representante.getText().toString());
                        strArray.add(representanteInput.getText().toString());
                        arrayLists.add(strArray);
                        break;
                case 1:
                        strArray = new ArrayList<>();
                        strArray.add(empresa.getText().toString());
                        strArray.add(empresaInput.getText().toString());
                        arrayLists.add(strArray);
                        break;
                case 2:
                        strArray = new ArrayList<>();
                        strArray.add(producto.getText().toString());
                        strArray.add(productoInput.getText().toString());
                        arrayLists.add(strArray);
                        break;
                case 3:
                        strArray = new ArrayList<>();
                        strArray.add(montoVenta.getText().toString());
                        strArray.add(montoVentaInput.getText().toString());
                        arrayLists.add(strArray);
                        break;
                case 4:
                        strArray = new ArrayList<>();
                        strArray.add(aftermarket.getText().toString());
                        strArray.add(aftermarketInput.getText().toString());
                        arrayLists.add(strArray);
                        break;

                case 5:
                        strArray = new ArrayList<>();
                        strArray.add(fechaDeCierre.getText().toString());
                        strArray.add(fechaDeCierreInput.getText().toString());
                        arrayLists.add(strArray);
                        break;

            }
        }


    }


    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("MyBoolean", true);
        savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
        double myDouble = savedInstanceState.getDouble("myDouble");
        int myInt = savedInstanceState.getInt("MyInt");
        String myString = savedInstanceState.getString("MyString");
    }*/

}
