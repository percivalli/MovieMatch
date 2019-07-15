package br.com.digitalhouse.moviematch.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.home.HomeActivity;

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

    private void jump() {
        timer.cancel();

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);

        finish();
    }
}