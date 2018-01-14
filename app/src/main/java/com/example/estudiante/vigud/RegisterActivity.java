package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    void exit_button(View view){
        Intent exit = new Intent(RegisterActivity.this, PrincipalActivity.class);
        startActivity(exit);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_buttom);
        finish();
    }
}
