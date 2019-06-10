package br.com.digitalhouse.moviematch.dar_match;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import br.com.digitalhouse.moviematch.R;

public class DarMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_match);


        ImageButton btnDeuMatch = (ImageButton) findViewById(R.id.btnDarMatch);

        btnDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_deu_match);
            }
        });
        ImageButton btnNaoDeuMatch = (ImageButton) findViewById(R.id.btnNaoDeuMatch);

        btnNaoDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(DarMatchActivity.this);
                alerta.setTitle("Que pena não foi desta Vez");
            }
        });
    }}