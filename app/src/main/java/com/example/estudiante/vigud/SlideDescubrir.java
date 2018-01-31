package com.example.estudiante.vigud;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Estudiante on 31/01/2018.
 */

public class SlideDescubrir extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideDescubrir(Context context){
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.bg_descubrir_cast_brunet,
            R.drawable.bg_descubrir_borde_costero
    };

    public String[] slide_heading = {
            "CASTILLO BRUNET",
            "BORDE COSTERO"
    };

    public String[] slide_desc = {
            "En un borde del Cerro Castillo, con vista al centro de la ciudad y a la orilla norte del Estero Marga-Marga, se encuentra el Castillo Brunet, llamado así por la familia que encargó su construcción en 1923 al arquitecto francés Alfredo Azancot.",
            "Acorde a su carácter costeor, Viña del Mar ofrece al visitante un hermoso y extenso borde costero que invita a contemplar desde sus variados puntos la inmensidad oceánica y el apacible paisaje marino."
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
        View view = layoutInflater.inflate(R.layout.slide_descubrir, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.image_slide_descubrir);
        TextView slideHeading = (TextView) view.findViewById(R.id.title_slide_descubrir);
        TextView slideText = (TextView) view.findViewById(R.id.desc_slide_descubrir);

        slideImageView.setBackgroundResource(slide_images[position]);
        slideHeading.setText(slide_heading[position]);
        slideText.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
