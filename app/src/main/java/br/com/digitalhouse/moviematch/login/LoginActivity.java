package br.com.digitalhouse.moviematch.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;
import br.com.digitalhouse.moviematch.home.HomeActivity;
import br.com.digitalhouse.moviematch.perfil.PerfilActivity;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextInputLayout textInputLayoutLoginEmail;
    private TextInputLayout textInputLayoutLoginPassword;
    private Button btnLoginLogin;
    private Button btnLoginCriarConta;
    private Button btnLoginFacebook;
    private Button btnLoginGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("CRIAR SUA CONTA");

        //Incialização das Views
        inicializaDadosLogin();

        //Botão Login
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaDadosLogin()) {
                    Intent intent = new Intent(LoginActivity.this,
                            PerfilActivity.class);

                    startActivity(intent);
                }

            }
        });

        //Botão Criar Conta
        btnLoginCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,
                        CadastroUsuarioActivity.class);

                startActivity(intent);
            }
        });

        //Botão User Facebook
        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // --> Incluir chamada da Tela para Login pelo Facebook

            }
        });

        //Botão Usar Google
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // --> Incluir chamada da Tela para Login pelo Google
            }
        });
    }

    public void inicializaDadosLogin() {

        //Incialização das Views
        textInputLayoutLoginEmail = findViewById(R.id.textInputLayoutLoginEmail);
        textInputLayoutLoginPassword = findViewById(R.id.textInputLayoutLoginPassword);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);
        btnLoginCriarConta = findViewById(R.id.btnLoginCriarConta);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
    }

    public boolean validaDadosLogin(){

        String textEmail = textInputLayoutLoginEmail.getEditText().getText().toString();
        String textPassword = textInputLayoutLoginPassword.getEditText().getText().toString();

        //Email Obrigatório
        if (textEmail.isEmpty()) {
            textInputLayoutLoginEmail.setError("Favor preencher o Email");
            return false;
        }

        //Senha Obrigatória
        if (textPassword.isEmpty()) {
            textInputLayoutLoginPassword.setError("Favor preencher a Senha");
            return false;
        }

        return true;

    }
}