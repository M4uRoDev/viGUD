package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CarteleraActivity extends AppCompatActivity {

    ImageButton ButtonpalacioRioja;
    TextView TextpalacioRioja;

    Boolean ejecutado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        ButtonpalacioRioja = (ImageButton)findViewById(R.id.imageButton16);
        TextpalacioRioja = (TextView)findViewById(R.id.textView22);

        ButtonpalacioRioja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ejecutado) {
                    ejecutado = true;
                    Intent goToDetalleCartelera = new Intent(CarteleraActivity.this, CarteleraXPuntoActivity.class);
                    startActivity(goToDetalleCartelera);
                }
            }
        });

        TextpalacioRioja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ejecutado) {
                    ejecutado = true;
                    Intent goToDetalleCartelera = new Intent(CarteleraActivity.this, CarteleraXPuntoActivity.class);
                    startActivity(goToDetalleCartelera);
                }
            }
        });

    }


    public void goToBack(View view){
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
