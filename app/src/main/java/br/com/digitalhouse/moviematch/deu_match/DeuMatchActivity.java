package br.com.digitalhouse.moviematch.deu_match;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.digitalhouse.moviematch.R;

public class DeuMatchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toobarTitle;
    private ImageView imageViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deu_match);

        // Toolbar
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toobarTitle = findViewById(R.id.toolbarTitleSimplesSemImagem);
        toobarTitle.setText("DEU MATCH!");

        //Inicialização das Views
        imageViewEmail = findViewById(R.id.imageViewEmail);

        imageViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviaEmail();
            }
        });
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
