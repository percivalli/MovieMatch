package br.com.digitalhouse.moviematch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Button btnCriar;
    //Button btnFacebook;
    //Button btnGoogle;
    //Switch btnTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //btnCriar = findViewById(R.id.btnCriar);
        //btnFacebook = findViewById(R.id.btnFacebook);
        //btnGoogle = findViewById(R.id.btnGoogle);
        //btnTermos = findViewById(R.id.btnTermos);

        //btnCriar.setOnClickListener(this);
        //btnFacebook.setOnClickListener(this);
        //btnGoogle.setOnClickListener(this);
        //btnTermos.setOnClickListener(this);
    }

    //public void telaCriar(View v) {}
}
