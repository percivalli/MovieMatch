package br.com.digitalhouse.moviematch.meus_matchs;

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
import br.com.digitalhouse.moviematch.adapters.RecyclerViewMeusMatchsAdapter;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewMeusMatchsClickListener;
import br.com.digitalhouse.moviematch.model.MeusMatchs;

public class MeusMatchsActivity extends AppCompatActivity
        implements RecyclerViewMeusMatchsClickListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerViewMeusMatchsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_matchs);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitle);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");

        // Inicializamos as views
        recyclerView = findViewById(R.id.recyclerViewMeusMatchs);

        // Adiciona o layout manager ao recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adiciona o adapter ao recyclerview
        adapter = new RecyclerViewMeusMatchsAdapter(getMeusMatchs(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(MeusMatchs meusMatchs) {

        //Enviar email

    }

    // Retorna lista dos Meus Matchs para recycleriew
    private List<MeusMatchs> getMeusMatchs() {

        List<MeusMatchs> listaMeusMatchs = new ArrayList<>();

        //Inicializa lista de pessoas
        listaMeusMatchs.add(new MeusMatchs("Erika"));
        listaMeusMatchs.add(new MeusMatchs("Flavia"));
        listaMeusMatchs.add(new MeusMatchs("Alberto"));
        listaMeusMatchs.add(new MeusMatchs("João"));
        listaMeusMatchs.add(new MeusMatchs("Fernanda"));

        return listaMeusMatchs;
    }

}