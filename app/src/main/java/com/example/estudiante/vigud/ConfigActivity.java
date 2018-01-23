package com.example.estudiante.vigud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    Switch alerta_georeferenciada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        alerta_georeferenciada = (Switch)findViewById(R.id.switch_alertas_georeferenciadas);

        boolean switch_alerta_georeferenciada = sharedPref.getBoolean(getString(R.string.switch_notification), false);

        if(switch_alerta_georeferenciada){
            alerta_georeferenciada.setChecked(true);
        }else{
            alerta_georeferenciada.setChecked(false);
        }

        alerta_georeferenciada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Intent alert_proximidad = new Intent(ConfigActivity.this, ProximityAlerts.class);
                    startService(alert_proximidad);
                    editor.putBoolean(getString(R.string.switch_notification), true);
                    editor.commit();
                }else if(!b){
                    Intent alert_proximidad = new Intent(ConfigActivity.this, ProximityAlerts.class);
                    stopService(alert_proximidad);
                    editor.putBoolean(getString(R.string.switch_notification), false);
                    editor.commit();
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
