package mx.diagraph.bluesheet;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().setTitle("");
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, Form_1.class);
        startActivity(intent);
    }

    public void testing(View view){
        Intent intent = new Intent(this, Form_2.class);
        startActivity(intent);
    }


}
