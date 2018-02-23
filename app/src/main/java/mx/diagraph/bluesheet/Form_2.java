package mx.diagraph.bluesheet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.StrictMode;
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

public class Form_2 extends AppCompatActivity {

    ExcelFileGenerator excel = new ExcelFileGenerator();
    Form_1 form_1;

    TextView calle, colonia, cp, ciudad,estado;
    EditText calleInput, coloniaInput, cpInput, ciudadInput;
    Spinner estadoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_2);
        getSupportActionBar().setTitle("Info. del cliente");
        generateText();
        generateSpinner();
    }

    private void generateSpinner() {
        Spinner spinner_animales = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.estados, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_animales.setAdapter(spinner_adapter);
    }

    private void generateText() {
        calle = findViewById(R.id.calle);
        calleInput = findViewById(R.id.calleInput);
        colonia = findViewById(R.id.colonia);
        coloniaInput = findViewById(R.id.coloniaInput);
        cp = findViewById(R.id.cp);
        cpInput = findViewById(R.id.cpInput);
        ciudad = findViewById(R.id.ciudad);
        ciudadInput = findViewById(R.id.ciudadInput);
        estadoInput = findViewById(R.id.spinner);
        estado = findViewById(R.id.estado);
    }

    public void sendMessage(View view) throws IOException {

        Intent intent = new Intent(this, MainScreen.class);

        startActivity(intent);
    }

    public void sendMessage2(View view) throws IOException{
        addToWorkBook();
        Workbook wb = excel.printExcel();
        OutputStream stream = openFileOutput("HojaAzul.xlsx", Context.MODE_PRIVATE);

        try {
            wb.write(stream);
        } finally {
            stream.close();
        }

        File filelocation = new File(getFilesDir().getAbsolutePath(), "HojaAzul.xlsx");
        Uri photoURI = FileProvider.getUriForFile(getBaseContext(),getApplicationContext().getPackageName()+".my.package.name.provider", filelocation);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent .setType("vnd.android.cursor.dir/email");

        emailIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(emailIntent , "Send email..."));

    }

    private void addToWorkBook() {
        ArrayList<String> strArray;
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        for (int i = 1; i < 6 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add(calle.getText().toString());
                    strArray.add(calleInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(colonia.getText().toString());
                    strArray.add(coloniaInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(cp.getText().toString());
                    strArray.add(cpInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(estado.getText().toString());
                    strArray.add(estadoInput.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 5:
                    strArray = new ArrayList<>();
                    strArray.add(ciudad.getText().toString());
                    strArray.add(ciudadInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
            }
        }

        excel.setArrayList(arrayLists);
        excel.setArrayList2(Form_1.arrayLists);

    }
}
