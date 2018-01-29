package com.example.estudiante.vigud;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Estudiante on 29/01/2018.
 */

public class SliderMenu extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderMenu(Context context){
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.btn_descubrir_minldpi,
            R.drawable.btn_puntos_vigud_minldpi,
            R.drawable.btn_mediateca_minldpi,
            R.drawable.btn_cartelera_minldpi

    };

    public String[] slide_heading = {
            "DESCUBRIR",
            "PUNTOS VIGUD",
            "MEDIATECA",
            "CARTELERA"
    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_menu, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.image_slide_menu);
        TextView slideHeading = (TextView) view.findViewById(R.id.heading_slide_menu);

        slideImageView.setBackgroundResource(slide_images[position]);
        slideHeading.setText(slide_heading[position]);

        slideImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, slide_heading[position ], Toast.LENGTH_LONG).show();
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
