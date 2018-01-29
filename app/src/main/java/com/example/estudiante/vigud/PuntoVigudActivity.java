package com.example.estudiante.vigud;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PuntoVigudActivity extends AppCompatActivity {

    ImageButton btnTemp;
    TextView textTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_vigud);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        btnTemp = (ImageButton)findViewById(R.id.btn_temperatura);
        textTemp = (TextView)findViewById(R.id.temperatura);
        update_temperatura();
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    JSONObject movieObject = new JSONObject(get_temperatura());
                    String temp = movieObject.getString("temperatura");
                    textTemp.setText(temp+"°");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void update_temperatura(){
        try {

            JSONObject movieObject = new JSONObject(get_temperatura());
            String temp = movieObject.getString("temperatura");
            textTemp.setText(temp+"°");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String get_temperatura(){
        String temperatura = null;
        try {
            Request request = new Request.Builder()
                    .url("http://www.awaresystems.cl/Antiguo/workshop/temperatura")
                    .get()
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Response response = okHttpClient.newCall(request).execute();
            String responseStr = response.body().string();
            temperatura = responseStr;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  temperatura;
    }

    public void goToBack(View view){
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

}
