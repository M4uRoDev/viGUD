package com.example.estudiante.vigud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PrincipalActivity extends AppCompatActivity {

    TextView debug;
    Boolean socket = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        debug = (TextView)findViewById(R.id.debug);
        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!socket) {
                    //LEVANTAMOS SERVICIO XMPP
                    socket = true;
                    Intent xmpp = new Intent(PrincipalActivity.this, XMPPService.class);
                    startService(xmpp);

                }
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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
