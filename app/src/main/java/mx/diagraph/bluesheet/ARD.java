package mx.diagraph.bluesheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ARD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ard);
        getSupportActionBar().setTitle("");
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, Form_2.class);
        startActivity(intent);
    }
}
