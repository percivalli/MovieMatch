package br.com.digitalhouse.moviematch.dar_match;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.deu_match.DeuMatchActivity;
import br.com.digitalhouse.moviematch.favoritos.DetalheFavoritosActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class DarMatchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private TextView textViewDarMatchTituloFilmesComum;
    private TextView textViewDarMatchFilmesComum;
    private TextView textViewDarMatchTituloGenerosComum;
    private TextView textViewDarMatchGenerosComum;
    private CircleImageView  btnNaoDeuMatch;

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

        toobarTitle.setText("VAI DAR MATCH?");

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
                Intent intent = new Intent(DarMatchActivity.this, DeuMatchActivity.class);
                //intent.putExtra("GENERO_FILME", generoFilme);
                startActivity(intent);

            }
        });

         CircleImageView btnNaoDeuMatch = findViewById(R.id.circleImageViewNaoDarMatch);

        btnNaoDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Não foi dessa vez",Snackbar.LENGTH_SHORT).show();
            }
        });

        /*btnNaoDeuMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alerta = new AlertDialog.Builder(DarMatchActivity.this);
                alerta.setTitle("Que pena não foi desta Vez");
                alerta.setMessage("");
                alerta.setCancelable(false);
                alerta.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog1 = alerta.create();
                alertDialog1.show();
            }
        });*/
    }


    //Inicializa Views
    public void inicializaViews() {

        textViewDarMatchTituloFilmesComum = findViewById(R.id.textViewDarMatchTituloFilmesComum);
        textViewDarMatchFilmesComum = findViewById(R.id.textViewDarMatchFilmesComum);
        textViewDarMatchTituloGenerosComum = findViewById(R.id.textViewDarMatchTituloGenerosComum);
        textViewDarMatchGenerosComum = findViewById(R.id.textViewDarMatchGenerosComum);
        textViewDarMatchMensagemRodape = findViewById(R.id.textViewDarMatchMensagemRodape);
        toobarTitle = findViewById(R.id.toolbarTitleSimples);
       btnNaoDeuMatch = findViewById(R.id.circleImageViewNaoDarMatch);


    }

}