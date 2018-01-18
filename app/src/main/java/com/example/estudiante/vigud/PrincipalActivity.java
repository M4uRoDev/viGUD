package com.example.estudiante.vigud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.graphics.Color.GREEN;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(Config.FLAG, Context.MODE_PRIVATE);

                if(sharedPreferences.getBoolean(Config.FLAG,true)){


                    startActivity(new Intent(PrincipalActivity.this,DefaultIntro.class));
                    SharedPreferences.Editor e=sharedPreferences.edit();

                    e.putBoolean(Config.FLAG,false);

                    e.apply();

                }
            }
        });
        t.start();

    }

    public void login(View view){
        Intent login = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(login);
        overridePendingTransition(R.anim.slide_in_buttom, R.anim.slide_out_top);
        finish();
    }

    public void register(View view){
        Intent register = new Intent(PrincipalActivity.this, RegisterActivity.class);
        startActivity(register);
        overridePendingTransition(R.anim.slide_in_buttom, R.anim.slide_out_top);
        finish();
    }
}
