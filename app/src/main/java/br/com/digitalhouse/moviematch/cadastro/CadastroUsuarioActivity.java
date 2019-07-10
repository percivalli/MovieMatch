package br.com.digitalhouse.moviematch.cadastro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;
import br.com.digitalhouse.moviematch.perfil.PerfilActivity;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextInputEditText editTextCadastroNome;
    private TextInputEditText editTextCadastroIdade;
    private TextInputEditText editTextCadastroSexo;
    private Spinner spinnerCadastroCidade;
    private Button btnCadastroFinalizar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("SEU CADASTRO");

        //Inicialização das Views
        inicializaViews();

        //Prepara Spinner de Cidades
        String[] listaCidades = getResources().getStringArray(R.array.arrayCidades);

        ArrayAdapter<String> arrayAdapterCidade = new ArrayAdapter<String>(
                this, R.layout.spinner_item, listaCidades);

        spinnerCadastroCidade.setAdapter(arrayAdapterCidade);


        btnCadastroFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Efetua a validação dos dados de cadastro
                if (validadaCadastro()) {

                    Intent intent = new Intent(CadastroUsuarioActivity.this,
                            FavoritosActivity.class);

                    startActivity(intent);
                }
                if (validadaCadastro()) {

                    //Grava as Preferências do Usuário
                    SharedPreferences preferences = getSharedPreferences("APP", MODE_PRIVATE);

//                    preferences.edit().putString("NOME", textNome).commit();
//                    preferences.edit().putString("IDADE", textIdade).commit();
//                    preferences.edit().putString("SEXO", textSexo).commit();
//                    preferences.edit().putString("CIDADE", textCidade).commit();

                    Intent intent = new Intent(CadastroUsuarioActivity.this, FavoritosActivity.class);

                    startActivity(intent);
                }

            }
        });

    }

    //Inicialização das Views
    public void inicializaViews() {

        editTextCadastroNome = findViewById(R.id.editTextCadastroNome);
        editTextCadastroIdade = findViewById(R.id.editTextCadastroIdade);
        editTextCadastroSexo = findViewById(R.id.editTextCadastroSexo);
        spinnerCadastroCidade = findViewById(R.id.spinnerCadastroCidade);
        btnCadastroFinalizar = findViewById(R.id.btnCadastroFinalizar);

    }

    //Validação dos dados de Cadastro
    public boolean validadaCadastro() {

        String textNome = editTextCadastroNome.getText().toString();
        String textIdade = editTextCadastroIdade.getText().toString();
        String textSexo = editTextCadastroSexo.getText().toString();
        String textCidade = spinnerCadastroCidade.getSelectedItem().toString();

        //Nome obrigatório
        if (textNome.isEmpty()) {
            editTextCadastroNome.setError("Favor preencher o nome");
            return false;
        }

        //Idade obrigatória
        if (textIdade.isEmpty()) {
            editTextCadastroIdade.setError("Favor preencher a idade");
            return false;
        }

        //Sexo obrigatório
        if (textSexo.isEmpty()) {
            editTextCadastroSexo.setError("Favor preencher o sexo");
            return false;
        }

//        //Cidade obrigatório
//        if (textCidade.isEmpty()) {
//            spinnerCadastroCidade.setError("Favor preencher a Cidade");
//            return false;
//        }

        return true;
    }

}
