package br.com.digitalhouse.moviematch.DarMatch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.home.MainActivity;


public class DarMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_match);


        ImageButton btnDeuMatch = (ImageButton) findViewById(R.id.btnDarMatch);

        btnDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.Activity_deu_match);
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