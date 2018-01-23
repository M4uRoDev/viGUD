package com.example.estudiante.vigud;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.graphics.Color.GREEN;

public class PrincipalActivity extends AppCompatActivity {

    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final int[] pressConfig = {5};
        debug = (TextView)findViewById(R.id.debug);

        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pressConfig[0] == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);

                    final EditText textoBusqueda = new EditText(PrincipalActivity.this);
                    builder.setTitle("Config Server");   // Título
                    builder.setView(textoBusqueda);
                    builder.setPositiveButton(R.string.dialog_gps_accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getApplicationContext(), textoBusqueda.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.create();
                    pressConfig[0]=5;
                }else if(pressConfig[0] <= 5){
                    Toast.makeText(getApplicationContext(), "Presione " + pressConfig[0] + " veces para configurar el hostname",Toast.LENGTH_LONG).show();

                }
                pressConfig[0] = pressConfig[0] - 1;
            }
        });

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
