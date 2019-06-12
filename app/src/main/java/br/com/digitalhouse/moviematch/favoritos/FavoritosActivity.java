package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewFavoritosAdapter;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;
import br.com.digitalhouse.moviematch.dar_match.DarMatchActivity;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.Filme;
import br.com.digitalhouse.moviematch.model.GeneroFilme;

public class FavoritosActivity extends AppCompatActivity
        implements RecyclerViewFavoritosClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerViewFavoritosAdapter adapter;

    private Button buttonFavoritosNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitle);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");

        // Inicializa as views
        recyclerView = findViewById(R.id.recyclerViewFavoritos);
        buttonFavoritosNext = findViewById(R.id.buttonFavoritosNext);

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewFavoritosAdapter(getGeneroFilme(), this);
        recyclerView.setAdapter(adapter);

        //Botão Prosseguir
        buttonFavoritosNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FavoritosActivity.this,
                        DarMatchActivity.class);

                startActivity(intent);


            }
        });

    }

    // Método para escutar evento de click em recyclerview
    @Override
    public void onClick(GeneroFilme generoFilme) {

        Intent intent = new Intent(this, DetalheFavoritosActivity.class);
        intent.putExtra("GENERO_FILME", generoFilme);
        startActivity(intent);

    }

    // Retorna lista de contatos para recycleriew
    private List<GeneroFilme> getGeneroFilme() {

        List<GeneroFilme> listaGenerosFilmes = new ArrayList<>();

        List<Filme> listaFilmes = new ArrayList<>();
        List<Filme> listaComedias = new ArrayList<>();

        //Inicializa lista de filmes
        //Comédia
        listaComedias.add(new Filme("Avengers: End Game"));
        listaComedias.add(new Filme("Jonh Wick 3"));
        listaComedias.add(new Filme("Aladdin"));
        listaComedias.add(new Filme("Hellboy"));
        listaComedias.add(new Filme("Rocketman"));

        listaGenerosFilmes.add(new GeneroFilme("Lançamentos", listaComedias));

        //Inicializa lista de filmes
        listaFilmes.clear();
        listaFilmes.add(new Filme("Filme 1"));
        listaFilmes.add(new Filme("Filme 2"));
        listaFilmes.add(new Filme("Filme 3"));
        listaFilmes.add(new Filme("Filme 4"));
        listaFilmes.add(new Filme("Filme 5"));
        listaFilmes.add(new Filme("Filme 6"));

        listaGenerosFilmes.add(new GeneroFilme("Comédia", listaFilmes));
        listaGenerosFilmes.add(new GeneroFilme("Terror", listaFilmes));
        listaGenerosFilmes.add(new GeneroFilme("Ação", listaFilmes));
        listaGenerosFilmes.add(new GeneroFilme("Romance", listaFilmes));
        listaGenerosFilmes.add(new GeneroFilme("Diversos1", listaFilmes));
        listaGenerosFilmes.add(new GeneroFilme("Diversos2", listaFilmes));

        return listaGenerosFilmes;
    }

}