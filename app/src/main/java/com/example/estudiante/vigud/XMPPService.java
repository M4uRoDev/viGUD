package com.example.estudiante.vigud;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class XMPPService extends Service {

    ClientXMPP clientXMPP = new ClientXMPP();
    // Socket inutil
    static ServerSocket serverSocket;
    static Socket socket;
    static BufferedReader in;
    String solicitud = null;
    public XMPPService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clientXMPP.conectar();

        return START_STICKY;

    }

    public void listenerMessage(){
        Runnable listener = new Runnable() {
            @Override
            public void run() {
                while (true){

                }
            }
        };
        Thread listThread = new Thread(listener);
        listThread.start();
    }

    public void socket(){

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                while(true){

                    String cominginText = "";
                    try{
                        System.out.println("Inciando Socket");
                        serverSocket = new ServerSocket(6002);
                        socket = serverSocket.accept();
                        in = new BufferedReader (new InputStreamReader(socket.getInputStream ()));

                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("No se puede conectar con el socket");
                    }

                    try {

                        cominginText = in.readLine ();
                        if (cominginText != null && cominginText != "")
                            System.out.println ("Socket IN: " + cominginText);
                    }
                    catch (IOException e)
                    {
                        System.out.println("Socket: Se perdio la conexión con el servidor!");
                    }
                    try {
                        serverSocket.close();
                        socket.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    if (cominginText.equalsIgnoreCase("solicitudkinect"))
                        clientXMPP.sendMessage();
                }
            }
        };

        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
