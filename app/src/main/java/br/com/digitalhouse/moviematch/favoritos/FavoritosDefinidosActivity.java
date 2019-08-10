package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.com.digitalhouse.moviematch.R;
import android.content.Intent;


public class FavoritosDefinidosActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private TextView textViewDarMatchTituloFilmesComum;
    private TextView textViewDarMatchFilmesComum;
    private TextView textViewDarMatchTituloGenerosComum;
    private TextView textViewDarMatchGenerosComum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_definidos);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("SEUS FAVORITOS");

        //Inicializa Views
        inicializaViews();

        }
    //Inicializa Views
    public void inicializaViews() {

        textViewDarMatchTituloFilmesComum = findViewById(R.id.textViewDarMatchTituloFilmesComum);
        textViewDarMatchFilmesComum = findViewById(R.id.textViewDarMatchFilmesComum);
        textViewDarMatchTituloGenerosComum = findViewById(R.id.textViewDarMatchTituloGenerosComum);
        textViewDarMatchGenerosComum = findViewById(R.id.textViewDarMatchGenerosComum);


    }

        //Valida se a Intent foi preenchida
         //if (getIntent() != null && getIntent().getExtras() != null) {
          // String textFilmesMarcados = getIntent().getStringExtra("FILMES_MARCADOS");
          // String textGenerosMarcados = getIntent().getStringExtra("GENEROS_MARCADOS");

            //Atualizar os dados na tela
         //  if (textFilmesMarcados != null) { //Filmes em comum
         //      textViewDarMatchFilmesComum.setText(textFilmesMarcados);
          // }

         //  if (textGenerosMarcados != null) { //Generos em comum
         //      textViewDarMatchGenerosComum.setText(textGenerosMarcados);
         //  }
     //  }


        }