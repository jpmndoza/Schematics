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

public class Form_Three extends AppCompatActivity {

    EditText tempmaxi, tempmini, tempmaxi2, tempmini2, humedadin;
    TextView tempmin, tempmax, tempmin2, tempmax2, muestras, temps, temparea, humedad,
             tempsup, polvo, area;
    Spinner muestrasin, polvoin, areain;
    ExcelFileGenerator excel = new ExcelFileGenerator();

    public static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form__three);
        generateText();
        generateSpinner();
    }

    private void generateText() {
        tempmaxi = findViewById(R.id.tempMaxInput);
        tempmini = findViewById(R.id.tempMinInput);
        tempmaxi2 = findViewById(R.id.tempMaxInput2);
        tempmini2 = findViewById(R.id.tempMinInput2);
        humedadin = findViewById(R.id.humedadInput);

        tempmin = findViewById(R.id.tempMin);
        tempmax = findViewById(R.id.tempMax);
        tempmin2 = findViewById(R.id.tempMin2);
        tempmax2 = findViewById(R.id.tempMax2);
        muestras = findViewById(R.id.muestras);
        temps = findViewById(R.id.temperaturas);
        temparea = findViewById(R.id.tempArea);
        humedad = findViewById(R.id.humedad);
        tempsup = findViewById(R.id.tempSuperficie2);
        polvo = findViewById(R.id.polvo);
        area = findViewById(R.id.area);

        muestrasin = findViewById(R.id.spinnerMuestras);
        polvoin = findViewById(R.id.spinnerPolvo);
        areain = findViewById(R.id.spinnerArea);
    }

    private void generateSpinner() {
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.booleans, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        muestrasin.setAdapter(spinner_adapter);
        polvoin.setAdapter(spinner_adapter);
        areain.setAdapter(spinner_adapter);
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

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent .setType("vnd.android.cursor.dir/email");

        emailIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(emailIntent , "Enviar Hoja Azul por..."));

    }

    private void addToWorkBook() {
        ArrayList<String> strArray;

        for (int i = 1; i < 7 ; i++) {

            switch (i){
                case 1:
                    strArray = new ArrayList<>();
                    strArray.add(muestras.getText().toString());
                    strArray.add(muestrasin.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 2:
                    strArray = new ArrayList<>();
                    strArray.add(temparea.getText().toString());
                    strArray.add(tempmin.getText().toString() + " " + tempmini.getText().toString());
                    strArray.add(tempmax.getText().toString() + " " + tempmaxi.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 3:
                    strArray = new ArrayList<>();
                    strArray.add(tempsup.getText().toString());
                    strArray.add(tempmin2.getText().toString() + " " + tempmini2.getText().toString());
                    strArray.add(tempmax2.getText().toString() + " " + tempmaxi2.getText().toString());
                    arrayLists.add(strArray);
                    break;

                case 4:
                    strArray = new ArrayList<>();
                    strArray.add(humedad.getText().toString());
                    strArray.add(humedadin.getText().toString());
                    arrayLists.add(strArray);
                    break;
                case 5:
                    strArray = new ArrayList<>();
                    strArray.add(area.getText().toString());
                    strArray.add(areain.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;
                case 6:
                    strArray = new ArrayList<>();
                    strArray.add(polvo.getText().toString());
                    strArray.add(polvoin.getSelectedItem().toString());
                    arrayLists.add(strArray);
                    break;

            }
        }

        excel.setArrayList(Form_2.arrayLists);
        excel.setArrayList2(Form_1.arrayLists);
        excel.setArrayList3(arrayLists);

    }
}
