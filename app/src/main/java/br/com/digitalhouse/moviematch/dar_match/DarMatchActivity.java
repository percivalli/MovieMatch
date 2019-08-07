package br.com.digitalhouse.moviematch.dar_match;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.deu_match.DeuMatchActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class DarMatchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private TextView textViewDarMatchTituloFilmesComum;
    private TextView textViewDarMatchFilmesComum;
    private TextView textViewDarMatchTituloGenerosComum;
    private TextView textViewDarMatchGenerosComum;

    private CircleImageView btnNaoDeuMatch;

    private TextView textViewDarMatchMensagemRodape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_match);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Inicializa Views
        inicializaViews();

        Toolbar toolbar = findViewById(R.id.toolbarTitleSimplesSemImagem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Valida se a Intent foi preenchida
        if (getIntent() != null && getIntent().getExtras() != null) {
            String textFilmesMarcados = getIntent().getStringExtra("FILMES_MARCADOS");
            String textGenerosMarcados = getIntent().getStringExtra("GENEROS_MARCADOS");

            //Atualizar os dados na tela
            if (textFilmesMarcados != null) { //Filmes em comum
                textViewDarMatchFilmesComum.setText(textFilmesMarcados);
            }

            if (textGenerosMarcados != null) { //Generos em comum
                textViewDarMatchGenerosComum.setText(textGenerosMarcados);
            }
        }

        CircleImageView btnDeuMatch = findViewById(R.id.circleImageViewDarMatch);

        btnDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CircleImageView btnNaoDeuMatch = findViewById(R.id.circleImageViewNaoDarMatch);

        btnNaoDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DarMatchActivity.this, DarMatchActivity1.class);
                //intent.putExtra("GENERO_FILME", generoFilme);
                startActivity(intent);

                /*
                Toast.makeText(getApplicationContext(),
                        "Que pena não foi desta vez!",
                        Toast.LENGTH_SHORT).show();

                Snackbar.make(v, "Que pena, não foi dessa vez!", Snackbar.LENGTH_SHORT).show();*/
            }
        });
    }

    //Inicializa Views
    public void inicializaViews() {

        textViewDarMatchTituloFilmesComum = findViewById(R.id.textViewDarMatchTituloFilmesComum);
        textViewDarMatchFilmesComum = findViewById(R.id.textViewDarMatchFilmesComum);
        textViewDarMatchTituloGenerosComum = findViewById(R.id.textViewDarMatchTituloGenerosComum);
        textViewDarMatchGenerosComum = findViewById(R.id.textViewDarMatchGenerosComum);
        textViewDarMatchMensagemRodape = findViewById(R.id.textViewDarMatchMensagemRodape);
        toobarTitle = findViewById(R.id.toolbarTitleSimples);

    }
}