package com.example.estudiante.vigud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PuntoVigudActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_vigud);
    }

    public void goToBack(View view){
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

}
