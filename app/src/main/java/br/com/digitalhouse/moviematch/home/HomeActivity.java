package br.com.digitalhouse.moviematch.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.login.LoginActivity;
import br.com.digitalhouse.moviematch.perfil.PerfilActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnHomeCriar;
    //private Button btnHomeFacebook;
    private Button btnHomeGoogle;
    private SwitchCompat swithHomeTermos;
    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Incialização das Views
        inicializaViews();

        onStart();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        //Botão Criar Conta
        btnHomeCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite criar a conta somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    Intent intent = new Intent(HomeActivity.this,
                            PerfilActivity.class);

                    startActivity(intent);
                }
            }
        });

        //Botão Usar Facebook
        // btnHomeFacebook.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {

        //Permite entrar pela conta Facebook somente se o termo de uso for aceito
        //        if (validaTermoUso()) {
        // --> Incluir chamada da Tela para Login pelo Facebook
//                }
//
        //          }
        //    });

        //Botão Usar Google
        btnHomeGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite entrar pela conta Google somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    // --> Incluir chamada da Tela para Login pelo Google
                    Intent signInIntent = googleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 101);
                }

            }
        });

    }

    //Incialização das Views
    public void inicializaViews() {

        btnHomeCriar = findViewById(R.id.btnHomeCriar);
        //  btnHomeFacebook = findViewById(R.id.btnHomeFacebook);
        btnHomeGoogle = findViewById(R.id.btnHomeGoogle);
        swithHomeTermos = findViewById(R.id.swithHomeTermos);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Toast.makeText(getApplicationContext(), "Problemas!", Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, PerfilActivity.class);
        intent.putExtra(LoginActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }


    public boolean validaTermoUso() {

        if (swithHomeTermos.isChecked()) { //True

            return true;

        } else { //False

            Toast.makeText(getApplicationContext(),
                    "Para prosseguir é necessário aceitar os Termos de Uso",
                    Toast.LENGTH_SHORT).show();

            return false;
        }
    }
}