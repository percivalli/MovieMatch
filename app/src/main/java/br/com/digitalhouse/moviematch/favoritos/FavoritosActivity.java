package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textViewFavoritosContador;

    private List<GeneroFilme> listaGenerosFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");

        // Inicializa as views
        inicializaViews();

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewFavoritosAdapter(listaGenerosFilmes, this);
        recyclerView.setAdapter(adapter);

        //Botão Prosseguir
        buttonFavoritosNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Somente permitir Dar March se houver pelo menos um filme selecionado
                if (preparaQuantidadeFilmesSelecionados() > 0) {
                    Intent intent = new Intent(FavoritosActivity.this,
                            DarMatchActivity.class);

                    //Prepara dados para envio a tela DarMatch
                    intent.putExtra("FILMES_MARCADOS", preparaStringFilmesSelecionados());
                    intent.putExtra("GENEROS_MARCADOS", preparaStringGenerosSelecionados());

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
    protected void onRestart() {
        super.onRestart();

        //Atualiza a quantidade de Filmes selecionados na tela
        textViewFavoritosContador.setText(preparaQuantidadeFilmesSelecionados() + "/20");

    }

    // Método para escutar evento de click em recyclerview
    @Override
    public void onClick(GeneroFilme generoFilme) {

        Intent intent = new Intent(FavoritosActivity.this, DetalheFavoritosActivity.class);
        intent.putExtra("GENERO_FILME", generoFilme);
        startActivityForResult(intent, 1);

    }

    //Incializa Lista de Filmes e Generos ---> Substituir este método pelo resultado da API
    private List<GeneroFilme> inicializaGeneroFilme() {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verfica se o requestCode é o mesmo que foi passado
        if (requestCode == 1 && resultCode == RESULT_OK) {
            GeneroFilme generoFilmeNew = data.getParcelableExtra("GENERO_FILME");

            //Atualiza a seleção dos filmes do usuário
            if (generoFilmeNew != null) {
                breakloop:
                for (GeneroFilme itemGenero : listaGenerosFilmes) {
                    if (itemGenero.getNomeGenero().equals(generoFilmeNew.getNomeGenero())) {
                        itemGenero.setListaFilmes(generoFilmeNew.getListaFilmes());

                        break breakloop; //Encerra a busca, pois o genero já foi atualizado
                    }
                }
            }
        }
    }

    //Concatena lista de filmes selecionados para String
    public String preparaStringFilmesSelecionados() {

        String retornoListaFilmes = "";

        for (GeneroFilme itemGenero : listaGenerosFilmes) {

            String retornoFilmes = "";

            retornoFilmes = itemGenero.retornaStringFilmesSelecionados();

            if (!retornoFilmes.equals("")) {
                if (retornoListaFilmes.equals("")) {
                    retornoListaFilmes = retornoFilmes;
                } else {
                    retornoListaFilmes = retornoListaFilmes + ", " + retornoFilmes;
                }
            }
        }

        return retornoListaFilmes;
    }

    //Concatena lista de Generos selecionados para String
    public String preparaStringGenerosSelecionados() {

        String retornoListaGeneros = "";

        for (GeneroFilme itemGenero : listaGenerosFilmes) {

            if (itemGenero.retornaQuantidadeFilmesSelecionados() != 0) {
                if (retornoListaGeneros.equals("")) {
                    retornoListaGeneros = itemGenero.getNomeGenero();
                } else {
                    retornoListaGeneros = retornoListaGeneros + ", " + itemGenero.getNomeGenero();
                }
            }
        }

        return retornoListaGeneros;
    }

    //Contagem da quantidade de filmes selecionados
    public int preparaQuantidadeFilmesSelecionados() {

        int qtdFilmesMarcados = 0;

        for (GeneroFilme itemGenero : listaGenerosFilmes) {
            qtdFilmesMarcados = qtdFilmesMarcados
                    + itemGenero.retornaQuantidadeFilmesSelecionados();
        }

        return qtdFilmesMarcados;
    }

    // Inicializa as views
    public void inicializaViews() {

        recyclerView = findViewById(R.id.recyclerViewFavoritos);
        buttonFavoritosNext = findViewById(R.id.buttonFavoritosNext);
        textViewFavoritosContador = findViewById(R.id.textViewFavoritosContador);

        listaGenerosFilmes = inicializaGeneroFilme();

    }

}
