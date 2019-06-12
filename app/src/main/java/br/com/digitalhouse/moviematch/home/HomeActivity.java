package br.com.digitalhouse.moviematch.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.cadastro.CadastroUsuarioActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnHomeCriar;
    private Button btnHomeFacebook;
    private Button btnHomeGoogle;
    private SwitchCompat swithHomeTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Incialização das Views
        inicializaViews();

        //Botão Criar Conta
        btnHomeCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite criar a conta somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    Intent intent = new Intent(HomeActivity.this,
                            CadastroUsuarioActivity.class);

                    startActivity(intent);
                }
            }
        });

        //Botão Usar Facebook
        btnHomeFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite entrar pela conta Facebook somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    // --> Incluir chamada da Tela para Login pelo Facebook
                }

            }
        });

        //Botão Usar Google
        btnHomeGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Permite entrar pela conta Google somente se o termo de uso for aceito
                if (validaTermoUso()) {
                    // --> Incluir chamada da Tela para Login pelo Google
                }

            }
        });
    }

    public boolean validaTermoUso() {

        if (swithHomeTermos.isChecked()) { //True

            return true;

        } else { //False

            Toast.makeText(getApplicationContext(),
                    "Para prosseguir é necessário aceitar os Termos de Uso",
                    Toast.LENGTH_LONG).show();

            return false;
        }
    }

    //Incialização das Views
    public void inicializaViews(){

        btnHomeCriar = findViewById(R.id.btnHomeCriar);
        btnHomeFacebook = findViewById(R.id.btnHomeFacebook);
        btnHomeGoogle = findViewById(R.id.btnHomeGoogle);
        swithHomeTermos = findViewById(R.id.swithHomeTermos);

    }
}