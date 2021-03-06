package br.com.digitalhouse.moviematch.perfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.favoritos.FavoritosActivity;
import br.com.digitalhouse.moviematch.meus_matchs.MeusMatchsActivity;

public class PerfilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private TextView toobarTitle;

    private EditText editTextPerfilNome;
    private EditText editTextPerfilIdade;
    //private EditText editTextPerfilSexo;
    private Spinner spinnerPerfilCidade;
    private Spinner spinnerPerfilSexo;
    private Spinner spinnerPerfilInteresse;
    private TextView textViewPerfilMeusMatchs;
    private TextView textViewPerfilFaleConosco;
    private Button buttonPerfilNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
       //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimples);
        toobarTitle.setText("PERFIL");


        //Inicialização das Views
        inicializaViews();

        //Prepara Spinner de Sexo
        String[] listaSexo = getResources().getStringArray(R.array.arraySexo);

        ArrayAdapter<String> arrayAdapterSexo = new ArrayAdapter<String>(
                this, R.layout.spinner_item, listaSexo);

        spinnerPerfilSexo.setAdapter(arrayAdapterSexo);

        //Prepara Spinner de Cidades
        String[] listaCidades = getResources().getStringArray(R.array.arrayCidades);

        ArrayAdapter<String> arrayAdapterCidade = new ArrayAdapter<String>(
                this, R.layout.spinner_item, listaCidades);

        spinnerPerfilCidade.setAdapter(arrayAdapterCidade);

        //Prepara Spinner de Interesses
        String[] listaInteresses = getResources().getStringArray(R.array.arrayInteresses);

        ArrayAdapter<String> arrayAdapterInteresses = new ArrayAdapter<String>(
                this, R.layout.spinner_item, listaInteresses);

        spinnerPerfilInteresse.setAdapter(arrayAdapterInteresses);

        //Opção Meus Matchs
        textViewPerfilMeusMatchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Valida o perfil foi preenchido corretamente
                if (validaDadosPerfil()) {

                    //Chama a tela MeusMatchs
                    Intent intent = new Intent(PerfilActivity.this,
                            MeusMatchsActivity.class);

                    startActivity(intent);
                }

            }
        });

        //Opção Fale Conosco
        textViewPerfilFaleConosco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Envia e-mail
                enviaEmail();

            }
        });

        //Botão Prosseguir
        buttonPerfilNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela Favoritos
                Intent intent = new Intent(PerfilActivity.this,
                        FavoritosActivity.class);

                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Inicialização das Views
    public void inicializaViews() {

        editTextPerfilNome = findViewById(R.id.editTextPerfilNome);
        editTextPerfilIdade = findViewById(R.id.editTextPerfilIdade);
        //editTextPerfilSexo = findViewById(R.id.editTextPerfilSexo);
        spinnerPerfilSexo = findViewById(R.id.spinnerPerfilSexo);
        spinnerPerfilCidade = findViewById(R.id.spinnerPerfilCidade);
        spinnerPerfilInteresse = findViewById(R.id.spinnerPerfilInteresse);
        textViewPerfilMeusMatchs = findViewById(R.id.textViewPerfilMeusMatchs);
        textViewPerfilFaleConosco = findViewById(R.id.textViewPerfilFaleConosco);
        buttonPerfilNext = findViewById(R.id.buttonPerfilNext);

    }

    public boolean validaDadosPerfil() {

        String textNome = editTextPerfilNome.getText().toString();
        String textIdade = editTextPerfilIdade.getText().toString();
        //String textSexo = editTextPerfilSexo.getText().toString();
        String textSexo = spinnerPerfilSexo.getSelectedItem().toString();
        String textCidade = spinnerPerfilCidade.getSelectedItem().toString();
        String textInteresse = spinnerPerfilInteresse.getSelectedItem().toString();

        //Recuperar as preferencias do usuário
        SharedPreferences preferences = getSharedPreferences(
                "APP", MODE_PRIVATE);

        editTextPerfilNome.setText(preferences.getString("NOME", ""));
        editTextPerfilIdade.setText(preferences.getString("IDADE", ""));

        //Nome obrigatório
        if (textNome.isEmpty()) {
            editTextPerfilNome.setError("Favor preencher o Nome");
            return false;
        }

        //Idade obrigatório
        if (textIdade.isEmpty()) {
            editTextPerfilIdade.setError("Favor preencher a Idade");
            return false;
        }

        /*
        //Sexo obrigatório
        if (textSexo.isEmpty()) {
            editTextPerfilSexo.setError("Favor preencher o Sexo");
            return false;
        }

        //Cidade obrigatório
        if (textCidade.isEmpty()) {
           spinnerPerfilCidade.setError("Favor preencher a Cidade");
           return false;
        }

        //Interesse obrigatório
        if (textInteresse.isEmpty()) {
           spinnerPerfilInteresse.setError("Favor preencher o Interesse");
            return false;
        }
        */

        return true;
    }

    public void enviaEmail() {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"aquieucolocomeuemaildecontato"});
        email.putExtra(Intent.EXTRA_SUBJECT,
                "Sugestão: ");
        email.putExtra(Intent.EXTRA_TEXT, "Olá " + "");
        startActivity(Intent.createChooser(email, "ENVIAR E-MAIL"));
    }
}
