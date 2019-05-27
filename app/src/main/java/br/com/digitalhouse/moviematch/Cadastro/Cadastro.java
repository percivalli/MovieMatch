package br.com.digitalhouse.moviematch.Cadastro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import br.com.digitalhouse.moviematch.R;

public class Cadastro extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextIdade;
    private EditText editTextSexo;
    private EditText editTextCidade;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button btnFinalizar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_inicial);

        editTextName = (EditText) findViewById(R.id.editTextNome);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextSexo = (EditText) findViewById(R.id.editTextSexo);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        btnFinalizar = (Button) findViewById(R.id.btnLogin);



    }
}
