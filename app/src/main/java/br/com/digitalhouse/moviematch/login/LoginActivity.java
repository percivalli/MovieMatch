package br.com.digitalhouse.moviematch.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.view.View;

import br.com.digitalhouse.moviematch.Cadastro.Cadastro;
import br.com.digitalhouse.moviematch.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnCriar;
    //Button btnFacebook;
    //Button btnGoogle;
    //Switch btnTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriar = findViewById(R.id.btnCriar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Cadastro.class);
                startActivity(intent);

            }
        });

        //btnFacebook = findViewById(R.id.btnFacebook);
        //btnGoogle = findViewById(R.id.btnGoogle);
        //btnTermos = findViewById(R.id.btnTermos);

        //btnCriar.setOnClickListener(this);
        //btnFacebook.setOnClickListener(this);
        //btnGoogle.setOnClickListener(this);
        //btnTermos.setOnClickListener(this);
    }


}
