package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity {

    ImageButton vigudOn;

    private  ViewPager mSlideViewPager;
    private LinearLayout  mDotLayout;

    private TextView[] mDots;

    private SliderMenu sliderMenu;

    public static proximityListener booleanVariable = new proximityListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        vigudOn = (ImageButton)findViewById(R.id.imageButton7);
        vigudOn.setAlpha(0.2f);
        mSlideViewPager = (ViewPager)findViewById(R.id.menuCarousel);
        mDotLayout = (LinearLayout)findViewById(R.id.dotsLayout);

        sliderMenu = new SliderMenu(this);
        mSlideViewPager.setAdapter(sliderMenu);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        booleanVariable.setValueChangeListener(new proximityListener.onValueChangeListener() {
            @Override
            public void onChange() {

                if(booleanVariable.getVariable()){
                    vigudOn.setAlpha(1f);
                    vigudOn.setVisibility(View.VISIBLE);

                    vigudOn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent goPuntoVigud = new Intent(MenuActivity.this, PuntoVigudActivity.class);
                            startActivity(goPuntoVigud);
                            overridePendingTransition(R.anim.slide_in_buttom, R.anim.slide_out_top);
                        }
                    });

                }else{
                    vigudOn.setAlpha(0.2f);
                    vigudOn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }


            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            mDotLayout.addView(mDots[i]);

        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.blank));
        }


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public void goToConfig(View view){
        Intent goConfig = new Intent(MenuActivity.this, ConfigActivity.class);
        startActivity(goConfig);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void goToPuntoVigud(View view){
        Intent goPuntoVigud = new Intent(MenuActivity.this, PuntoVigudActivity.class);
        startActivity(goPuntoVigud);
        overridePendingTransition(R.anim.slide_in_buttom, R.anim.slide_out_top);
    }
}
