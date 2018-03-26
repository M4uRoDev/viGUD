package com.example.estudiante.vigud;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class XMPPService extends Service {

    ClientXMPP clientXMPP = new ClientXMPP();
    static boolean isConected = false;
    // Socket inutil
    static ServerSocket serverSocket;
    static Socket socket;
    static BufferedReader in;
    String solicitud = null;
    public XMPPService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isConected = clientXMPP.conectar(getApplicationContext());
        if (isConected){
            Toast.makeText(getApplicationContext(),
                    "Conectado al servidor XMPP",
                    Toast.LENGTH_SHORT).show();

            clientXMPP.chatManager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally) {
                    chat.addMessageListener(new ChatMessageListener() {
                        @Override
                        public void processMessage(Chat chat, Message message) {
                            if(message != null){
                                if (message.getBody().toString().trim().contains("kinect:true")){
                                    Log.e("KINECT: ", "TRUE");
                                    clientXMPP.listenerMessage(true);
                                    //Toast.makeText(getApplicationContext(),
                                           //"Enviando por sockets: TRUE",
                                            //Toast.LENGTH_SHORT).show();

                                }
                                if (message.getBody().toString().trim().contains("kinect:false")){
                                    Log.e("KINECT: ", "FALSE");
                                    clientXMPP.listenerMessage(false);
                                    //Toast.makeText(getApplicationContext(),
                                         //   "Enviando por sockets: FALSE",
                                         //   Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    });
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "ERROR en la coexión al servidor XMPP",
                    Toast.LENGTH_SHORT).show();
        }
        socket();
        return START_STICKY;

    }

    public void socket(){

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                while(true){

                    String cominginText = "";
                    try{
                        System.out.println("Inciando Socket");
                        serverSocket = new ServerSocket(6000);
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
