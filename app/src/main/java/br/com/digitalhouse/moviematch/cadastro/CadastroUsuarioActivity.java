package br.com.digitalhouse.moviematch.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.moviematch.home.HomeActivity;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText editTextName;
    private TextInputEditText editTextIdade;
    private TextInputEditText editTextSexo;
    private TextInputEditText editTextCidade;

    private Button btnFinalizar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.digitalhouse.moviematch.R.layout.activity_cadastro_usuario);

        editTextName = (TextInputEditText) findViewById(br.com.digitalhouse.moviematch.R.id.editTextNome);
        editTextIdade = (TextInputEditText) findViewById(br.com.digitalhouse.moviematch.R.id.editTextIdade);
        editTextSexo = (TextInputEditText) findViewById(br.com.digitalhouse.moviematch.R.id.editTextSexo);
        editTextCidade = (TextInputEditText) findViewById(br.com.digitalhouse.moviematch.R.id.editTextCidade);

        btnFinalizar = (Button) findViewById(br.com.digitalhouse.moviematch.R.id.btnLogin);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editTextName.getText().toString();
                String idade = editTextIdade.getText().toString();
                String sexo = editTextSexo.getText().toString();
                String cidade = editTextCidade.getText().toString();

                if(nome.isEmpty()){
                    editTextName.setError("Nome não pode ser vazio!");
                    return;
                }if(idade.isEmpty()){
                    editTextIdade.setError("Idade não pode ser vazio!");
                    return;
                }if(sexo.isEmpty()){
                    editTextSexo.setError("Sexo não pode ser vazio!");
                    return;
                }if(cidade.isEmpty()){
                    editTextCidade.setError("Cidade não pode ser vazio!");
                    return;
                }if (nome  != null && idade!= null && sexo != null && cidade != null){
                    Intent intent = new Intent(CadastroUsuarioActivity.this, HomeActivity.class);

                    startActivity(intent);
                }else {
                    Snackbar.make(editTextName, "Nome, idade, sexo ou cidade inválidos!", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

}
