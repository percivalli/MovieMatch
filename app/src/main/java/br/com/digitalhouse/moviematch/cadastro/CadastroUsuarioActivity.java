package br.com.digitalhouse.moviematch.cadastro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.perfil.PerfilActivity;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private TextInputEditText editTextCadastroNome;
    private TextInputEditText editTextCadastroIdade;
    //private TextInputEditText editTextCadastroSexo;
    //private TextInputEditText editTextCadastroCidade;
    private Spinner spinnerCadastroCidade;
    private Spinner spinnerCadastroSexo;
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

        //Prepara Spinner de Sexo
        String[] listaSexo = getResources().getStringArray(R.array.arraySexo);

        ArrayAdapter<String> arrayAdapterSexo = new ArrayAdapter<>(
                this, R.layout.spinner_item_cadastro, listaSexo);

        spinnerCadastroSexo.setAdapter(arrayAdapterSexo);

        //Prepara Spinner de Cidades
        String[] listaCidades = getResources().getStringArray(R.array.arrayCidades);

        ArrayAdapter<String> arrayAdapterCidade = new ArrayAdapter<>(
                this, R.layout.spinner_item_cadastro, listaCidades);

        spinnerCadastroCidade.setAdapter(arrayAdapterCidade);

        btnCadastroFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Efetua a validação dos dados de cadastro
                if (validadaCadastro()) {

                    //Intent intent = new Intent(CadastroUsuarioActivity.this,
                    //        FavoritosActivity.class);

                    Intent intent = new Intent(CadastroUsuarioActivity.this,
                            PerfilActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    //Inicialização das Views
    public void inicializaViews() {

        editTextCadastroNome = findViewById(R.id.editTextCadastroNome);
        editTextCadastroIdade = findViewById(R.id.editTextCadastroIdade);
        //editTextCadastroSexo = findViewById(R.id.editTextCadastroSexo);
        //editTextCadastroCidade = findViewById(R.id.editTextCadastroCidade);
        spinnerCadastroSexo = findViewById(R.id.spinnerCadastroSexo);
        spinnerCadastroCidade = findViewById(R.id.spinnerCadastroCidade);
        btnCadastroFinalizar = findViewById(R.id.btnCadastroFinalizar);

    }

    //Validação dos dados de Cadastro
    public boolean validadaCadastro() {

        String textNome = editTextCadastroNome.getText().toString();
        String textIdade = editTextCadastroIdade.getText().toString();
        String textSexo = spinnerCadastroSexo.getSelectedItem().toString();
        String textCidade = spinnerCadastroCidade.getSelectedItem().toString();
        //String textSexo = editTextCadastroSexo.getText().toString();
        //String textCidade= editTextCadastroCidade.getText().toString();

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

        /*
        //Sexo obrigatório
        if (textSexo.isEmpty()) {
            editTextCadastroSexo.setError("Favor preencher o sexo");
            return false;
        }
        */

        if (!(textNome.isEmpty()) && !(textIdade.isEmpty()) && !(textSexo.isEmpty())
                && !(textCidade.isEmpty())) {

            //Grava as Preferências do Usuário
            SharedPreferences preferences = getSharedPreferences("APP", MODE_PRIVATE);

            preferences.edit().putString("NOME", textNome).commit();
            preferences.edit().putString("IDADE", textIdade).commit();
            preferences.edit().putString("SEXO", textSexo).commit();
            preferences.edit().putString("CIDADE", textCidade).commit();

        }

        /*
        //Cidade obrigatório
        if (textCidade.isEmpty()) {
            editTextCadastroCidade.setError("Favor preencher a Cidade");
            return false;
        }
        */

        return true;
    }
}
