package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewDetalheFavoritosAdapter;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewFavoritosAdapter;
import br.com.digitalhouse.moviematch.dar_match.DarMatchActivity;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewDetalheFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.Filme;
import br.com.digitalhouse.moviematch.model.GeneroFilme;

public class DetalheFavoritosActivity extends AppCompatActivity
        implements RecyclerViewDetalheFavoritosClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextView textViewSubtituloNomeGenero;
    private TextView textViewFavoritosLetraGenero;

    private List<Filme> listaFilmes = new ArrayList<>();
    private GeneroFilme generoFilme;
    private Button buttonProsseguir;

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerViewDetalheFavoritosAdapter adapter;

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("GENERO_FILME", generoFilme);
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_favoritos);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("SEUS FAVORITOS");

        // Inicializa as views
        recyclerView = findViewById(R.id.recyclerViewDetalheFavoritos);
        textViewSubtituloNomeGenero = findViewById(R.id.textViewFavoritosNomeGenero);
        textViewFavoritosLetraGenero = findViewById(R.id.textViewFavoritosLetraGenero);
        buttonProsseguir = findViewById(R.id.buttonDetalheFavoritosNext);

        //Valida se a Intent foi preenchida
        if (getIntent() != null && getIntent().getExtras() != null) {
            generoFilme = getIntent().getParcelableExtra("GENERO_FILME");

            if (generoFilme != null) {
                //Atualizar os dados na tela
                textViewSubtituloNomeGenero.setText(generoFilme.getNomeGenero());

                //Adiciona a lista de filmes
                listaFilmes = generoFilme.getListaFilmes();

                //Primeira letra do Nome do Genero
                textViewFavoritosLetraGenero.setText(generoFilme.getNomeGenero().substring(0, 1));

            }
        }

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewDetalheFavoritosAdapter(listaFilmes, this);
        recyclerView.setAdapter(adapter);


        buttonProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Somente permitir Dar March se houver pelo menos um filme selecionado
                if (generoFilme.retornaQuantidadeFilmesSelecionados() > 0) {
                    Intent intent = new Intent(
                            DetalheFavoritosActivity.this,
                            DarMatchActivity.class);

                    //Prepara dados para envio a tela DarMatch
                    intent.putExtra("FILMES_MARCADOS", generoFilme.retornaStringFilmesSelecionados());
                    intent.putExtra("GENEROS_MARCADOS", generoFilme.getNomeGenero());

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Selecionar pelo menos 1 filme",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(Filme filme, int position) {

        //Marca ou desmarca o filme após o clique
        if (filme.isFilmeSelecionado() == true) {
            filme.setFilmeSelecionado(false);
        } else {
            filme.setFilmeSelecionado(true);
        }

        //Atualiza a lista interna de filmes marcados/ não marcados
        listaFilmes.get(position).setFilmeSelecionado(filme.isFilmeSelecionado());
        generoFilme.setListaFilmes(listaFilmes);

    }
}