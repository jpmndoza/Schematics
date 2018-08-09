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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Form_7 extends AppCompatActivity {

    RadioButton bandaPlana, servomotor, bandaRodillos, tablillas, canjillones, transporteAereo;
    Spinner spinner_velocidad, spinner_banda, spinner_union;
    TextView tipoTransportador, tipoBanda, velocidadLinea, velMax, velMin;
    EditText velMaxInput, velMinInput;
    ExcelFileGenerator excel = new ExcelFileGenerator();
    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_7);
        getSupportActionBar().setTitle("");
        generateText();
        generateSpinners();
    }

    private void generateSpinners() {
        ArrayAdapter boolean_adapter = ArrayAdapter.createFromResource(this,R.array.booleans,android.R.layout.simple_spinner_item);
        boolean_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter velocidad_adapter = ArrayAdapter.createFromResource(this, R.array.velocidades,android.R.layout.simple_spinner_item);
        velocidad_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter banda_adapter = ArrayAdapter.createFromResource(this, R.array.bandas,android.R.layout.simple_spinner_item);
        banda_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_union.setAdapter(boolean_adapter);
        spinner_velocidad.setAdapter(velocidad_adapter);
        spinner_banda.setAdapter(banda_adapter);
    }

    private void generateText() {
        spinner_velocidad = findViewById(R.id.spinnerVelocidad);
        spinner_banda = findViewById(R.id.spinnerBanda);
        spinner_union = findViewById(R.id.spinnerUnion);

        bandaPlana = findViewById(R.id.bandaPlana);
        servomotor = findViewById(R.id.servomotor);
        bandaRodillos = findViewById(R.id.bandaRodillos);
        tablillas = findViewById(R.id.tablillas);
        canjillones = findViewById(R.id.canjillones);
        transporteAereo = findViewById(R.id.transporteAereo);

        tipoBanda = findViewById(R.id.tipoBanda);
        tipoTransportador = findViewById(R.id.tipoTransportador);
        velocidadLinea = findViewById(R.id.velocidadLinea);

        velMax = findViewById(R.id.velMax);
        velMaxInput = findViewById(R.id.velMaxInput);
        velMin = findViewById(R.id.velMin);
        velMinInput = findViewById(R.id.velMinInput);
    }

    public void sendMessage2(View view) throws IOException {
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

        Intent intent = new Intent(this,MainScreen.class);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent .setType("vnd.android.cursor.dir/email");

        emailIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
        startActivity(Intent.createChooser(emailIntent , "Enviar Hoja Azul por..."));
    }
    private void addToWorkBook() {
        ArrayList<String> strArray;

        for (int i = 1; i < 8 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add(" ");
                    arrayLists.add(strArray);
                    break;
                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(bandaPlana.getText().toString());
                    strArray.add(bandaPlana.isSelected() ? "x" : " ");
                    strArray.add(bandaRodillos.getText().toString());
                    strArray.add(bandaRodillos.isSelected() ? "x" : " ");
                    strArray.add(tablillas.getText().toString());
                    strArray.add(tablillas.isSelected() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(servomotor.getText().toString());
                    strArray.add(servomotor.isSelected() ? "x" : " ");
                    strArray.add(canjillones.getText().toString());
                    strArray.add(canjillones.isSelected() ? "x" : " ");
                    strArray.add(transporteAereo.getText().toString());
                    strArray.add(transporteAereo.isSelected() ? "x" : " ");
                    arrayLists.add(strArray);
                    break;
                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(" ");
                    arrayLists.add(strArray);
                    break;
                case 5:
                    strArray = new ArrayList<>();
                    strArray.add(tipoBanda.getText().toString());
                    strArray.add(spinner_banda.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 6:
                    strArray = new ArrayList<>();
                    strArray.add(" ");
                    arrayLists.add(strArray);
                    break;
                case 7:
                    strArray = new ArrayList<>();
                    strArray.add(velocidadLinea.getText().toString());
                    strArray.add(spinner_velocidad.getSelectedItem().toString());
                    strArray.add(velMin.getText().toString() + " ");
                    strArray.add(velMinInput.getText().toString());
                    strArray.add(velMax.getText().toString() + " ");
                    strArray.add(velMaxInput.getText().toString());
                    arrayLists.add(strArray);
                    break;
            }
        }

        excel.setArrayList(Form_2.arrayLists);
        excel.setArrayList2(Form_1.arrayLists);
        excel.setArrayList3(Form_Three.arrayLists);
        excel.setArrayList4(Form_4.arrayLists);
        excel.setArrayList5(Form_5.arrayLists);
        excel.setArrayList6(Form_6.arrayLists);
        excel.setArrayList7(arrayLists);

    }
}
