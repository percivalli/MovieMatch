package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewFavoritosAdapter;
import br.com.digitalhouse.moviematch.dar_match.DarMatchActivity;
import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.GeneroDAO;
import br.com.digitalhouse.moviematch.data.database.dao.UsuarioDAO;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.genero.Genero;
import br.com.digitalhouse.moviematch.viewmodel.GeneroViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoritosActivity extends AppCompatActivity
        implements RecyclerViewFavoritosClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerViewFavoritosAdapter adapter;

    private Button buttonFavoritosNext;
    private TextView textViewFavoritosContador;

    private List<Genero> listaGeneros = new ArrayList<>();

    //Declaração da interface com a tabela Genero
    private GeneroDAO generoDAO;
    private UsuarioDAO usuarioDAO;

    private int quantidadeFilmesSelecionados;

    //Declaração do ViewModel
    private GeneroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("SEUS FAVORITOS");

        // Inicializa as views
        inicializaViews();

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewFavoritosAdapter(listaGeneros, this);
        recyclerView.setAdapter(adapter);

        //****************************** View Model ******************************************
        // Inicializa ViewModel
        viewModel = ViewModelProviders.of(this).get(GeneroViewModel.class);
        viewModel.searchGenero();

        // Adicionar os observables
        viewModel.getGeneroLiveData().observe(this, generos -> adapter.update(listaGeneros));

        /*
        //Observable Loading
        viewModel.getLoadingLiveData().observe(this, isLoading -> {

            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
        */

        //Observable Error
        viewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerView, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();

        });
        //****************************** View Model ******************************************

        //Permite comunicação/acesso aos registros da tabela Genero
        generoDAO = Database.getDatabase(this).generoDAO();

        //Permite comunicação/acesso aos registros da tabela Usuario
        usuarioDAO = Database.getDatabase(this).usuarioDAO();

        //Busca Lista de Gêneros
        recuperaListaGeneros();

        //Atualiza a quantidade de Filmes selecionados na tela
        preparaQuantidadeFilmesSelecionados();

        //Botão Prosseguir
        buttonFavoritosNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Somente permitir Dar March se houver pelo menos 10 filmes selecionados e no máximo
                //20 filmes
                if ((quantidadeFilmesSelecionados > 9) && (quantidadeFilmesSelecionados < 21)) {

                    Intent intent = new Intent(FavoritosActivity.this,
                            DarMatchActivity.class);

                    //Prepara dados para envio a tela DarMatch
                    //intent.putExtra("GENEROS_MARCADOS", preparaStringGenerosSelecionados());

                    startActivity(intent);

                } else if (quantidadeFilmesSelecionados > 20) {
                    Toast.makeText(getApplicationContext(),
                            "Selecione no máximo 20 filmes",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Selecione pelo menos 10 filmes",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void recuperaListaGeneros() {

        //new Thread(() -> {

            /*
            //Inicializa lista de generos:
            List<Genero> listaGeneros = new ArrayList<>();

            listaGeneros.add(new Genero(10L, "Lançamentos"));
            listaGeneros.add(new Genero(20L, "Comédia"));
            listaGeneros.add(new Genero(30L, "Terror"));
            listaGeneros.add(new Genero(40L, "Ação"));
            listaGeneros.add(new Genero(100L, "Romance"));

            //Deleta e Grava na tabela de genero a lista de generos
            generoDAO.deleteAll();
            generoDAO.insertAll(listaGeneros);
            */

        //Recupera todos os generos do banco de dados
        buscarTodosOsGeneros();

        //}).start();

    }

    private void buscarTodosOsGeneros() {
        generoDAO.getAllRxJava()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(generos -> {
                    adapter.update(generos);
                }, throwable -> {
                    Log.i("TAG", "buscarTodosOsContatos: " + throwable.getMessage());
                });
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        //Este método é chamado após sair da tela de Filmes
        //Atualiza a quantidade de Filmes selecionados na tela
        preparaQuantidadeFilmesSelecionados();
    }

    // Método para escutar evento de click em recyclerview
    @Override
    public void onClick(Genero genero) {
        Intent intent = new Intent(
                FavoritosActivity.this, DetalheFavoritosActivity.class);
        intent.putExtra("GENERO", genero);
        startActivity(intent);

    }

    // Inicializa as views
    public void inicializaViews() {

        recyclerView = findViewById(R.id.recyclerViewFavoritos);
        buttonFavoritosNext = findViewById(R.id.buttonFavoritosNext);
        textViewFavoritosContador = findViewById(R.id.textViewFavoritosContador);

    }

    public void preparaQuantidadeFilmesSelecionados() {

        new Thread(() -> {

            quantidadeFilmesSelecionados = usuarioDAO.getByQtdeFilme();
            textViewFavoritosContador.setText(quantidadeFilmesSelecionados + "/20");

        }).start();
    }
}