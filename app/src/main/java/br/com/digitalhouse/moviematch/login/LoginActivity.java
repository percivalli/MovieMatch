package br.com.digitalhouse.moviematch.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;
import br.com.digitalhouse.moviematch.perfil.PerfilActivity;

//import android.support.v7.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextInputLayout textInputLayoutLoginEmail;
    private TextInputLayout textInputLayoutLoginPassword;
    private Button btnLoginLogin;
    private Button btnLoginCriarConta;
//  private Button btnLoginFacebook;
    private Button btnLoginGoogle;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("CRIAR SUA CONTA");
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this, (GoogleApiClient.OnConnectionFailedListener) this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Incialização das Views
        inicializaDadosLogin();

        //Botão Login
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaDadosLogin()) {

                    //Chama a tela de Perfil
                    Intent intent = new Intent(LoginActivity.this,
                            PerfilActivity.class);

                    /*Intent intent = new Intent(LoginActivity.this,
                            FavoritosActivity.class);*/

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
            public void onClick(View v)  {
                signIn();
            }
        });

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
        }
    }

    private void signIn() {
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                authWithGoogle(account);
            }
        }
    }

    private void authWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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