package com.example.estudiante.vigud;

/**
 * Created by Estudiante on 10/01/2018.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

public final class DefaultIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Down here, we add the xml layouts into sample slide inflater.
        addSlide(ShowSlide.newInstance(R.layout.intro));
        addSlide(ShowSlide.newInstance(R.layout.intro2));
        addSlide(ShowSlide.newInstance(R.layout.intro3));
        addSlide(ShowSlide.newInstance(R.layout.intro4));
        addSlide(ShowSlide.newInstance(R.layout.intro5));


        showStatusBar(false);

        setDepthAnimation();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadMainActivity();
        Toast.makeText(getApplicationContext(), "Saltar", Toast.LENGTH_SHORT).show();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
