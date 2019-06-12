package br.com.digitalhouse.moviematch.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;
import br.com.digitalhouse.moviematch.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

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

        //Incialização das Views
        inicializaDadosLogin();

        //Botão Login
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaDadosLogin()) {
                    Intent intent = new Intent(LoginActivity.this,
                            FavoritosActivity.class);

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