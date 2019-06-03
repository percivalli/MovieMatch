package br.com.digitalhouse.moviematch.favoritos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.digitalhouse.moviematch.R;

public class MenuFavoritosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_favoritos);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitle);
        toobarTitle.setText("ESCOLHA SEUS FAVORITOS");


    }
}
