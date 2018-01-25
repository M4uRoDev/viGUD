package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.ItemPicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class MenuActivity extends AppCompatActivity {


    TextView menuText;
    ImageButton vigudOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.menuCarousel);

        vigudOn = (ImageButton)findViewById(R.id.imageButton7);

        menuText = (TextView)findViewById(R.id.textMenu);

        //Creating a list of itens
        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.btn_puntos_vigud_minldpi, "Puntos Vigud"));
        itens.add(new ItemPicker(R.drawable.btn_cartelera_minldpi,"Cartelera"));
        itens.add(new ItemPicker(R.drawable.btn_descubrir_minldpi, "Descubrir"));
        itens.add(new ItemPicker(R.drawable.btn_mediateca_minldpi,"Mediateca"));

        //adding a list and building the carousel
        carouselPicker.addList(itens).build(this);

        carouselPicker.addListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "HOLA", Toast.LENGTH_LONG).show();
            }
        });

        proximityListener bv = new proximityListener();
        bv.setListener(new proximityListener.ChangeListener() {
            @Override
            public void onChange() {
                vigudOn.setVisibility(View.VISIBLE);
            }
        });


    }



    public void goToConfig(View view){
        Intent goConfig = new Intent(MenuActivity.this, ConfigActivity.class);
        startActivity(goConfig);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
