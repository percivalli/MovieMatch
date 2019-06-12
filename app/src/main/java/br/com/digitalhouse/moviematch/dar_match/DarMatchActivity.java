package br.com.digitalhouse.moviematch.dar_match;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_match);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("VAI DAR MATCH?");

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
                AlertDialog.Builder alerta = new AlertDialog.Builder(DarMatchActivity.this);
                alerta.setTitle("Que pena não foi desta Vez");
            }
        });
    }}