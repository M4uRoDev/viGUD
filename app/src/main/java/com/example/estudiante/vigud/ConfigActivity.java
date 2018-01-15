package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    Switch alerta_georefenciada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        alerta_georefenciada = (Switch)findViewById(R.id.switch_alertas_georeferenciadas);

        alerta_georefenciada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplicationContext(), "Servicio Activado", Toast.LENGTH_LONG).show();
                    Intent alert_proximidad = new Intent(ConfigActivity.this, ProximityAlerts.class);
                    startService(alert_proximidad);
                }else if(!b){
                    Toast.makeText(getApplicationContext(), "Servicio desactivado",Toast.LENGTH_LONG).show();
                    Intent alert_proximidad = new Intent(ConfigActivity.this, ProximityAlerts.class);
                    stopService(alert_proximidad);
                }
            }
        });

    }

    public void goToBack(View view){
        Intent goBack = new Intent(ConfigActivity.this, MenuActivity.class);
        startActivity(goBack);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

}
