package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewDetalheFavoritosAdapter;
import br.com.digitalhouse.moviematch.dar_match.DarMatchActivity;
import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.FilmeDAO;
import br.com.digitalhouse.moviematch.data.database.dao.UsuarioDAO;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewDetalheFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.filme.Filme;
import br.com.digitalhouse.moviematch.model.genero.Genero;
import br.com.digitalhouse.moviematch.model.usuario.Usuario;
import br.com.digitalhouse.moviematch.viewmodel.FilmeViewModel;


public class FavoritosDefinidosActivity extends AppCompatActivity
        implements RecyclerViewDetalheFavoritosClickListener {

        private Toolbar toolbar;
        private TextView toobarTitle;

       // private TextView textViewSubtituloNomeGenero;
       // private TextView textViewFavoritosLetraGenero;

        private List<Filme> listaFilmes = new ArrayList<>();

        //lista com os filmes escolhidos pelo usuario
        private List<Usuario> listaUsuarios = new ArrayList<>();

        private Genero genero;

       // private Button buttonProsseguir;

        //Atributos
        private RecyclerView recyclerView;
        private RecyclerViewDetalheFavoritosAdapter adapter;

        //Declaração da interface com a tabela Filme
        private FilmeDAO filmeDAO;

        //Declaração da interface com a tabela Usuario para controlar os Generos e Filmes que o usuario
        //escolheu
        private UsuarioDAO usuarioDAO;

        //****************************** View Model ******************************************
        //Declaração do ViewModel
        private FilmeViewModel viewModel;
        //****************************** View Model ******************************************

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_favoritos_definidos);

            // Toolbar
            //toolbar = findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            toobarTitle = findViewById(R.id.toolbarTitleSimples);
            toobarTitle.setText("SEUS FAVORITOS");

            // Inicializa as views
            recyclerView = findViewById(R.id.recyclerViewDetalheFavoritos);
           // textViewSubtituloNomeGenero = findViewById(R.id.textViewFavoritosNomeGenero);
           // textViewFavoritosLetraGenero = findViewById(R.id.textViewFavoritosLetraGenero);
           // buttonProsseguir = findViewById(R.id.buttonDetalheFavoritosNext);

            //Valida se a Intent foi preenchida
           // if (getIntent() != null && getIntent().getExtras() != null) {
           //     genero = getIntent().getParcelableExtra("GENERO");

           //     if (genero != null) {
                    //Atualizar os dados na tela
            //        textViewSubtituloNomeGenero.setText(genero.getName());

                    //Primeira letra do Nome do Genero
           //         textViewFavoritosLetraGenero.setText(genero.getName().substring(0, 1));
           //     }
          //  }

            // Adiciona o layout manager ao recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Adiciona o adapter ao recyclerview
            adapter = new RecyclerViewDetalheFavoritosAdapter(listaFilmes, this);
            recyclerView.setAdapter(adapter);

            //****************************** View Model ******************************************
            // Inicializa ViewModel
            viewModel = ViewModelProviders.of(this).get(FilmeViewModel.class);
            viewModel.searchFilme(genero.getId());

            // Adicionar os observables
            viewModel.getFilmeLiveData().observe(this, filmes -> adapter.update(filmes));

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

            //Permite comunicação/acesso aos registros da tabela Filme
            filmeDAO = Database.getDatabase(this).filmeDAO();

            //Permite comunicação/acesso aos registros da tabela UsuarioFilme
            usuarioDAO = Database.getDatabase(this).usuarioDAO();

            //Busca Lista de Filmes por Id do Gênero
            //buscarTodosOsFilmesPorGenero(genero.getId());

          //  buttonProsseguir.setOnClickListener(new View.OnClickListener() {
               // @Override
               // public void onClick(View v) {

               //     new Thread(() -> {

               //         usuarioDAO.getByQtdeFilme();

                        //Verifica se o usuário escolheu no minimo 10 filmes e no máximo 20 filmes para
                        //ir para a Tela de Match
                        //if (usuarioDAO.getByQtdeFilme() > 9 && usuarioDAO.getByQtdeFilme() < 21) {
                       // if (usuarioDAO.getByQtdeFilme() > 0) {

                            //Recupera todos filmes por generos
                            //buscarTodosOsFilmesPorGenero(genero.getId());

                          //  Intent intent = new Intent(
                          //          br.com.digitalhouse.moviematch.favoritos.DetalheFavoritosActivity.this,
                           //         DarMatchActivity.class);

                            //Prepara dados para envio a tela DarMatch
                            //intent.putExtra("GENEROS_MARCADOS", genero.getName());

                          //  startActivity(intent);
                     //   } else {
                      //      Toast.makeText(getApplicationContext(),
                      //              "Selecionar pelo menos 1 filme",
                      //              Toast.LENGTH_SHORT).show();
                   //     }
                  //  }).start();
             //   }
          //  });
    //    }



         private void buscarTodosOsFilmesPorGenero(long generoId) {

                new Thread(() -> {

                    List<Filme> filmes = filmeDAO.getByGenreId(generoId);

                    //Atualiza a lista de filmes selecionados anteriormente pelo usuário
                    for (Filme linhaFilme : filmes) {

                        if (validaFilmeSelecionadoUsuario(linhaFilme.getGenreId(), linhaFilme.getId())) {
                            linhaFilme.setFilmeSelecionado(true);
                        }
                    }

                    runOnUiThread(() -> {
                        adapter.update(filmes);
                    });

                }).start();
            }



            private void buscarTodosOsGenerosFilmes() {

                new Thread(() -> {

                    List<Usuario> listaUsuarios = new ArrayList<>();

                    usuarioDAO.getAll();

                }).start();
            }
        }

    private boolean validaFilmeSelecionadoUsuario(long idGenero, Long idFilme) {

        if (usuarioDAO.getByIdGeneroFilme(idGenero, idFilme) > 0) {
            return true;
        }

        return false;
    }


    @Override
    public void onClick(Filme filme, int position) {

        if (filme.isFilmeSelecionado() == true) {
            filme.setFilmeSelecionado(false);

            //Deleta os filmes que o usuario desmarcou da lista de filmes
            deletaFilmesUsuario(filme);

        } else {
            filme.setFilmeSelecionado(true);

            //Grava os filmes que o usuario selecionou na lista de filmes
       //     gravaFilmesUsuario(filme.getGenreId(), filme.getId());

        }

    }

    private void deletaFilmesUsuario(Filme filme) {

        //ao clicar no item, deleta o genero e o filme na tabela de usuário
        new Thread(() -> {

            usuarioDAO.deleteByIdGeneroFilme(filme.getGenreId(), filme.getId());

        }).start();
    }

    }
