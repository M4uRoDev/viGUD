package com.example.estudiante.vigud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        forgotPassword = (TextView)findViewById(R.id.forgot_password);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"@FORGOT_PASSWORD",Toast.LENGTH_LONG).show();
            }
        });

    }

    void login_button(View view){
        Intent menu = new Intent(LoginActivity.this, LoadingActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.slide_in_buttom, R.anim.slide_out_top);
        finish();
    }
    void exit_button(View view){
        Intent exit = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivity(exit);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_buttom);
        finish();
    }
}
