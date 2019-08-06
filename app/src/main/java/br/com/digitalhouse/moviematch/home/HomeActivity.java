package br.com.digitalhouse.moviematch.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.login.LoginActivity;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button btnHomeCriar;
    //private Button btnHomeFacebook;
    private Button btnHomeGoogle;
    private SwitchCompat swithHomeTermos;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    private static final int RESULT_GOOGLE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        //Incialização das Views
        inicializaViews();

        //Botão Criar Conta
        btnHomeCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite criar a conta somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    Intent intent = new Intent(HomeActivity.this,
                            LoginActivity.class);

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
                    GoogleSignInOptions options = new GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build();

                    googleSignInClient = GoogleSignIn.getClient(HomeActivity.this, options);
                    signIn();
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

    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RESULT_GOOGLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_GOOGLE) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                authWithGoogle(account);

            } catch (ApiException e) {

                e.printStackTrace();

            }
        }
    }

    private void authWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        goToHome();
                    } else {
                        Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToHome() {
        // Sign in success, update UI with the signed-in user's information
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Authentication Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
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