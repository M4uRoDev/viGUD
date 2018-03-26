package com.example.estudiante.vigud;

/**
 * Created by Estudiante on 30/01/2018.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityFullJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientXMPP {

    static XMPPTCPConnection conexion = null;
    XMPPTCPConnectionConfiguration.Builder configBuilder = null;
    static String usuario = null;
    static String contrasena = null;
    static String dominio = null;
    ChatManager chatManager = null;

    Context context = null;


    public ClientXMPP(){
        dominio = "mail.awaresystems.cl";
        usuario = "vigud";
        contrasena = "vigud";

        try {
            //CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.configBuilder = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("mail.awaresystems.cl")
                    .setUsernameAndPassword(usuario,contrasena)
                    .setDebuggerEnabled(true)
                    .setHost("awaresystems.cl")
                    .setResource("vigud")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            //InputStream canInput = new BufferedInputStream()


        } catch (XmppStringprepException e) {
            e.printStackTrace();
            return;
        /*} catch (CertificateException e) {
            e.printStackTrace();*/
        }


    }

    public void listenerMessage(boolean status){

        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = null;
        try {
            clientSocket = new Socket("127.0.0.1", 6001);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //modifiedSentence = inFromServer.readLine();
            outToServer.writeBytes(status + "");
            //System.out.println("Estmos enviando " + status);

            Log.e("SocketClient: ", ""+status);

            /*Toast.makeText(this.context,
                    "Enviando por socket a Unity",
                    Toast.LENGTH_SHORT).show();*/
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean conectar(Context context) {
        this.context = context;
        conexion = new XMPPTCPConnection(configBuilder.build());
        boolean exito = false;
        try {
            conexion.connect().login();
            chatManager = ChatManager.getInstanceFor(conexion);
            Log.e("XMPP","CONNECT");
            exito = true;
        }catch (SmackException | IOException | XMPPException | InterruptedException ex){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        /*
        if(exito){
            chatManager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally) {
                    chat.addMessageListener(new ChatMessageListener() {
                        @Override
                        public void processMessage(Chat chat, Message message) {
                            if (message.getBody().toString().trim().contains("kinect:true")){
                                Log.e("KINECT: ", "TRUE");
                                listenerMessage(true);
                            }
                            if (message.getBody().toString().trim().contains("kinect:false")){
                                Log.e("KINECT: ", "FALSE");
                                listenerMessage(false);
                            }

                        }
                    });
                }
            });
        }*/
        return exito;
    }

    public void sendMessage(){
        EntityFullJid jid = null;
        try {
            jid   = JidCreate.entityFullFrom("totem1@mail.awaresystems.cl/test");
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }

        Chat chat = chatManager.createChat(jid);
        try {
            chat.sendMessage("kinect");
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
