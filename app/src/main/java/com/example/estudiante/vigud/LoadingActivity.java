package com.example.estudiante.vigud;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoadingActivity extends AppCompatActivity{
    final Intent i = new Intent(this, PrincipalActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    if(!solicitar_permisos()){
                        goToMenu();
                    }
                }
            }
        };
        timer.start();



    }

    private void goToMenu(){

        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        finish();
    }

    private boolean solicitar_permisos(){
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToMenu();

                } else {
                    dialog_info_gps();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void dialog_info_gps(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage(R.string.dialog_gps_message);
        myBuild.setTitle(R.string.dialog_gps_title);
        myBuild.setIcon(R.drawable.ic_warning_black_24dp);
        myBuild.setPositiveButton(R.string.dialog_gps_accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                solicitar_permisos();
            }
        });

        myBuild.setNegativeButton(R.string.dialog_gps_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

}
