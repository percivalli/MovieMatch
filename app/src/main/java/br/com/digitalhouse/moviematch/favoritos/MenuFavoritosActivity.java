package br.com.digitalhouse.moviematch.favoritos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.adapters.RecyclerViewFavoritosAdapter;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.GeneroFilme;

public class MenuFavoritosActivity extends AppCompatActivity
        implements RecyclerViewFavoritosClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerViewFavoritosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_favoritos);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitle);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");

        // Inicializamos as views
        recyclerView = findViewById(R.id.recyclerViewFavoritos);

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewFavoritosAdapter(getGeneroFilme(), this);
        recyclerView.setAdapter(adapter);

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

        listaGenerosFilmes.add(new GeneroFilme("Lançamentos"));
        listaGenerosFilmes.add(new GeneroFilme("Comédia"));
        listaGenerosFilmes.add(new GeneroFilme("Terror"));
        listaGenerosFilmes.add(new GeneroFilme("Ação"));
        listaGenerosFilmes.add(new GeneroFilme("Romance"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos1"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos2"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos3"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos4"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos5"));
        listaGenerosFilmes.add(new GeneroFilme("Diversos6"));

        return listaGenerosFilmes;
    }

}
