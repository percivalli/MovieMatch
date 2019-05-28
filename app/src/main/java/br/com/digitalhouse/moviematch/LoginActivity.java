package br.com.digitalhouse.moviematch;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private Button btnCriarConta;
    private Button btnLogin;
    private Button btnLoginFacebook;
    private Button btnLoginGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        initViews();

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndGoToCadastroInicial();
            }
        });

        /*
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndGoToProfile();
            }
        });
        */
    }

    private void initViews(){
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
    }

    private void validateAndGoToCadastroInicial(){
        String email = textInputLayoutEmail.getEditText().getText().toString();
        String password = textInputLayoutPassword.getEditText().getText().toString();

        if(email.isEmpty()){
            textInputLayoutEmail.setError("Email não pode ser vazio");
            return;
        }

        if(password.isEmpty()){
            textInputLayoutPassword.setError("Senha não pode ser vazia");
            return;
        }

        /*
        if(email != null && password != null){
            Intent intent = new Intent(LoginActivity.this,Cadastro.class);
            Bundle bundle = new Bundle();
            bundle.putString("EMAIL",email);
            intent.putExtras(bundle);

            startActivity(intent);
        }else {
            Snackbar.make(textInputLayoutEmail, "Email ou Senha inválidos", Snackbar.LENGTH_SHORT);
        }
        */
    }

    /* Método para validar os campos de e-mail e senha e seguir para a tela de perfil
    private void validateAndGoToProfile(){
        String email = textInputLayoutEmail.getEditText().getText().toString();
        String password = textInputLayoutPassword.getEditText().getText().toString();

        if(email.isEmpty()){
            textInputLayoutEmail.setError("Email não pode ser vazio");
            return;
        }

        if(password.isEmpty()){
            textInputLayoutPassword.setError("Senha não pode ser vazia");
            return;
        }

        if(email != null && password != null){
            Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("EMAIL",email);
            intent.putExtras(bundle);

            startActivity(intent);
        }else {
            Snackbar.make(textInputLayoutEmail, "Email ou Senha inválidos", Snackbar.LENGTH_SHORT);
        }

    }*/

}
