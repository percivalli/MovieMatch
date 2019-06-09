package br.com.digitalhouse.moviematch.Perfil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.moviematch.R;

public class InformacoesPerfil extends AppCompatActivity {

    private Button prosseguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        prosseguir = findViewById(R.id.prosseguir);

        };
    }





