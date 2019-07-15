package br.com.digitalhouse.moviematch.meus_matchs;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("MEUS MATCHS");

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
        enviaEmail();

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

    public void enviaEmail() {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"aquieucolocomeuemaildecontato"});
        email.putExtra(Intent.EXTRA_SUBJECT,
                "Sugestão: ");
        email.putExtra(Intent.EXTRA_TEXT, "Olá " + "");
        startActivity(Intent.createChooser(email, "ENVIAR E-MAIL"));

    }
}
