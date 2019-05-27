package br.com.digitalhouse.moviematch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView editTextName;
    private TextView editTextIdade;
    private TextView editTextSexo;
    private TextView editTextCidade;
    private TextView editTextEmail;
    private TextView editTextSenha;
    private Button btnFinalizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (TextView) findViewById(R.id.editTextNome);
        editTextIdade = (TextView) findViewById(R.id.editTextIdade);
        editTextSexo = (TextView) findViewById(R.id.editTextSexo);
        editTextCidade = (TextView) findViewById(R.id.editTextCidade);
        editTextEmail = (TextView) findViewById(R.id.editTextEmail);
        editTextSenha = (TextView) findViewById(R.id.editTextSenha);
        btnFinalizar =(Button) findViewById(R.id.btnLogin);



    }
}
