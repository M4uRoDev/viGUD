package com.example.estudiante.vigud;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView welcome, city;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        welcome = (TextView)findViewById(R.id.textView5);
        city = (TextView)findViewById(R.id.textView6);
        logo = (ImageView)findViewById(R.id.imglogo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        welcome.startAnimation(animation);
        city.startAnimation(animation);
        logo.startAnimation(animation);

        final Intent i = new Intent(this, PrincipalActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }
}
