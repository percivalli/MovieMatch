package br.com.digitalhouse.moviematch.splash;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.moviematch.favoritos.MenuFavoritosActivity;
import br.com.digitalhouse.moviematch.login.LoginActivity;
import br.com.digitalhouse.moviematch.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageSplash = findViewById(R.id.imageSplash);

        imageSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump();
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump();
            }
        }, 3000);
    }

        private void jump () {
            timer.cancel();
            Intent intent = new Intent(SplashActivity.this, MenuFavoritosActivity.class); //LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

