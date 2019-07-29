package br.com.digitalhouse.moviematch.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextInputLayout textInputLayoutLoginEmail;
    private TextInputLayout textInputLayoutLoginPassword;
    private Button btnLoginLogin;
    private Button btnLoginCriarConta;
//  private Button btnLoginFacebook;
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

                    //Chama a tela de Perfil
                    //Intent intent = new Intent(LoginActivity.this,
                    //        PerfilActivity.class);

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
 //       btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
  //          @Override
    //        public void onClick(View v) {

                // --> Incluir chamada da Tela para Login pelo Facebook

      //      }
      //  });

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
  //    btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
    }

    public boolean validaDadosLogin() {

        String textEmail = textInputLayoutLoginEmail.getEditText().getText().toString();
        String textPassword = textInputLayoutLoginPassword.getEditText().getText().toString();

        //minimo de caracteres permitidos para o cadastro de senha
        int minimalPassLen = 6;

        //Inicializa o set Error
        textInputLayoutLoginEmail.setError("");
        textInputLayoutLoginPassword.setError("");

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

        if (textPassword.length() < minimalPassLen) {
            textInputLayoutLoginPassword.setError("Digite uma senha com 6 ou mais caracteres");
            return false;
        }

        if (!(textEmail.isEmpty()) && !(textPassword.isEmpty())) {

            //Grava as Preferencias do Usuario
            SharedPreferences preferences = getSharedPreferences("APP", MODE_PRIVATE);

            preferences.edit().putString("EMAIL", textEmail).commit();
            preferences.edit().putString("SENHA", textPassword).commit();

            return true;
        }
        return true;
    }
}