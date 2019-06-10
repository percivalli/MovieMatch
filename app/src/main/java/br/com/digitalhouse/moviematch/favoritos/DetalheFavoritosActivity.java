package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.ListViewDetalheFavoritosAdapter;
import br.com.digitalhouse.moviematch.dar_match.DarMatchActivity;
import br.com.digitalhouse.moviematch.model.Filme;
import br.com.digitalhouse.moviematch.model.GeneroFilme;

public class DetalheFavoritosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private List<Filme> rowItemsFilmes;
    private ListView listViewFilmes;

    private TextView textViewSubtituloNomeGenero;
    private TextView textViewFavoritosLetraGenero;

    private List<Filme> listaFilmes = new ArrayList<>();
    private Button buttonProsseguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_favoritos);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitle);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");

        //Inicialização dos objetos da tela
        textViewSubtituloNomeGenero = findViewById(R.id.textViewFavoritosNomeGenero);
        textViewFavoritosLetraGenero = findViewById(R.id.textViewFavoritosLetraGenero);
        buttonProsseguir = findViewById(R.id.buttonDetalheFavoritosNext);

        //Valida a Intent foi preenchida
        if (getIntent() != null && getIntent().getExtras() != null) {
            GeneroFilme generoFilme = getIntent().getParcelableExtra("GENERO_FILME");

            if (generoFilme != null) {
                //Atualizar os dados na tela
                textViewSubtituloNomeGenero.setText(generoFilme.getNomeGenero());

                //Adiciona a lista de filmes
                listaFilmes = generoFilme.getListaFilmes();

                //Primeira letra do Nome do Genero
                textViewFavoritosLetraGenero.setText(generoFilme.getNomeGenero().substring(0, 1));

            }
        }

        rowItemsFilmes = new ArrayList<Filme>();

        for (int pos = 0; pos < listaFilmes.size(); pos++) {
            Filme itemFilme = new Filme(listaFilmes.get(pos).getNomeFilme());

            rowItemsFilmes.add(itemFilme);
        }

        listViewFilmes = findViewById(R.id.listViewFilmes);

        //Sem linha de divisão entre os itens na tela
        listViewFilmes.setDividerHeight(0);

        ListViewDetalheFavoritosAdapter adapter = new ListViewDetalheFavoritosAdapter(
                this,
                rowItemsFilmes);

        if (adapter != null) {
            listViewFilmes.setAdapter(adapter);
        }


        buttonProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetalheFavoritosActivity.this, DarMatchActivity.class);
                //intent.putExtra("GENERO_FILME", generoFilme);
                startActivity(intent);

            }
        });


    }
}
