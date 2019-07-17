package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.os.Bundle;
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

public class DetalheFavoritosActivity extends AppCompatActivity
        implements RecyclerViewDetalheFavoritosClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextView textViewSubtituloNomeGenero;
    private TextView textViewFavoritosLetraGenero;

    private List<Filme> listaFilmes = new ArrayList<>();

    //lista com os filmes escolhidos pelo usuario
    private List<Usuario> listaUsuarios = new ArrayList<>();

    private Genero genero;

    private Button buttonProsseguir;

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
            genero = getIntent().getParcelableExtra("GENERO");

            if (genero != null) {
                //Atualizar os dados na tela
                textViewSubtituloNomeGenero.setText(genero.getName());

                //Primeira letra do Nome do Genero
                textViewFavoritosLetraGenero.setText(genero.getName().substring(0, 1));
            }
        }

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewDetalheFavoritosAdapter(listaFilmes, this);
        recyclerView.setAdapter(adapter);

        //****************************** View Model ******************************************
        // Inicializa ViewModel
        viewModel = ViewModelProviders.of(this).get(FilmeViewModel.class);
        viewModel.searchFilme();

        // Adicionar os observables
        viewModel.getFilmeLiveData().observe(this, generos -> adapter.update(listaFilmes));

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

        //Busca Lista de Filmes
        recuperaListaFilmes();

        buttonProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(() -> {

                    usuarioDAO.getByQtdeFilme();

                    //Verifica se o usuário escolheu no minimo 10 filmes e no máximo 20 filmes para
                    //ir para a Tela de Match
                    //if (usuarioDAO.getByQtdeFilme() > 9 && usuarioDAO.getByQtdeFilme() < 21) {
                    if (usuarioDAO.getByQtdeFilme() > 0) {

                        //Recupera todos filmes por generos
                        buscarTodosOsFilmesPorGenero(genero.getId());

                        Intent intent = new Intent(
                                DetalheFavoritosActivity.this,
                                DarMatchActivity.class);

                        //Prepara dados para envio a tela DarMatch
                        //intent.putExtra("GENEROS_MARCADOS", genero.getName());

                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Selecionar pelo menos 1 filme",
                                Toast.LENGTH_SHORT).show();
                    }
                }).start();
            }
        });
    }


    private void recuperaListaFilmes() {

        // new Thread(() -> {

            /*
            //Inicializa lista de generos:
            List<Long> generoLancamento = new ArrayList<>();
            List<Long> generoComedia = new ArrayList<>();
            List<Long> generoTerror = new ArrayList<>();
            List<Long> generoAcao = new ArrayList<>();
            List<Long> generoRomance = new ArrayList<>();

            List<Long> generoMisto = new ArrayList<>();

            generoLancamento.add(new Long(10L));
            generoComedia.add(new Long(20L));
            generoTerror.add(new Long(30L));
            generoAcao.add(new Long(40L));
            generoRomance.add(new Long(100L));

            generoMisto.add(new Long(10L));
            generoMisto.add(new Long(100L));

            //Inicializa lista de filmes:
            List<Filme> listaFilmes = new ArrayList<>();

            //Inicializa lista de filmes: Lançamentos
            listaFilmes.add(new Filme(generoMisto, 1L, "Nasce Uma Estrela"));
            listaFilmes.add(new Filme(generoLancamento, 2L, "Liga Da Justiça"));
            listaFilmes.add(new Filme(generoLancamento, 3L, "Jonh Wick 3"));
            listaFilmes.add(new Filme(generoLancamento, 4L, "Aladdin"));
            listaFilmes.add(new Filme(generoLancamento, 5L, "Hellboy"));
            listaFilmes.add(new Filme(generoLancamento, 6L, "Rocketman"));

            //Inicializa lista de filmes: Comédia
            listaFilmes.add(new Filme(generoComedia, 7L, "Todo Mundo em Pânico"));
            listaFilmes.add(new Filme(generoComedia, 8L, "Corra Que A Polícia Vem Aí"));
            listaFilmes.add(new Filme(generoComedia, 9L, "O Virgem de 40 Anos "));
            listaFilmes.add(new Filme(generoComedia, 10L, "As Branquelas"));
            listaFilmes.add(new Filme(generoComedia, 11L, "Borat"));

            //Inicializa lista de filmes: Terror
            listaFilmes.add(new Filme(generoTerror, 12L, "A Invocação Do Mal 2"));
            listaFilmes.add(new Filme(generoTerror, 13L, "Exorcista"));
            listaFilmes.add(new Filme(generoTerror, 14L, "Anabelle"));
            listaFilmes.add(new Filme(generoTerror, 15L, "A Morte do Diabo"));
            listaFilmes.add(new Filme(generoTerror, 16L, "Madrugada dos Mortos"));

            //Inicializa lista de filmes: Ação
            listaFilmes.add(new Filme(generoAcao, 17L, "Duro de Matar 4.0"));
            listaFilmes.add(new Filme(generoAcao, 18L, "Velozes e Furiosos 9"));
            listaFilmes.add(new Filme(generoAcao, 19L, "Rambo"));
            listaFilmes.add(new Filme(generoAcao, 20L, "Avengers: Guerra Infinita"));
            listaFilmes.add(new Filme(generoAcao, 21L, "Jason Bourne"));

            //Inicializa lista de filmes: Romance
            listaFilmes.add(new Filme(generoRomance, 22L, "A Culpa é Das Estrelas"));
            listaFilmes.add(new Filme(generoRomance, 23L, "Titanic"));
            listaFilmes.add(new Filme(generoRomance, 24L, "Amor Além Da Vida"));
            listaFilmes.add(new Filme(generoRomance, 25L, "A Escolha"));
            listaFilmes.add(new Filme(generoRomance, 26L, "Questão de Tempo"));

            //Deleta a lista de filmes
            filmeDAO.deleteAll();

            //Grava na tabela de genero a lista de filmes
            filmeDAO.insertAll(listaFilmes);
            */

        //Recupera todos filmes por generos
        buscarTodosOsFilmesPorGenero(genero.getId());

        //  }).start();

    }

    private boolean validaFilmeSelecionadoUsuario(long idGenero, long idFilme) {

        if (usuarioDAO.getByIdGeneroFilme(idGenero, idFilme) > 0) {
            return true;
        }

        return false;
    }

    private void buscarTodosOsFilmesPorGenero(long generoId) {

        new Thread(() -> {

            List<Filme> filmes = (List<Filme>) filmeDAO.getByGenreId(generoId);

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


    @Override
    public void onClick(Filme filme, int position) {

        //Marca ou desmarca o filme após o clique
        if (filme.isFilmeSelecionado() == true) {
            filme.setFilmeSelecionado(false);

            //Deleta os filmes que o usuario desmarcou da lista de filmes
            deletaFilmesUsuario(filme);

        } else {
            filme.setFilmeSelecionado(true);

            //Grava os filmes que o usuario selecionou na lista de filmes
            gravaFilmesUsuario(filme.getGenreId(), filme.getId());

        }
    }

    private void gravaFilmesUsuario(long generoId, long filmeId) {

        //ao clicar no item, incluir o genero e o filme na tabela de usuário
        new Thread(() -> {

            Usuario usuario = new Usuario("TESTE", generoId, filmeId);

            usuarioDAO.insert(usuario);

        }).start();
    }

    private void deletaFilmesUsuario(Filme filme) {

        //ao clicar no item, deleta o genero e o filme na tabela de usuário
        new Thread(() -> {

            usuarioDAO.deleteByIdGeneroFilme(filme.getGenreId(), filme.getId());

        }).start();
    }

    private void buscarTodosOsGenerosFilmes() {

        new Thread(() -> {

            List<Usuario> listaUsuarios = new ArrayList<>();

            usuarioDAO.getAll();

        }).start();
    }
}