package com.example.estudiante.vigud;

import android.util.Log;

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
import org.jxmpp.stringprep.XmppStringprepException;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Estudiante on 24/01/2018.
 */

public class ClienteXMPP {

    static XMPPTCPConnection conexion = null;
    XMPPTCPConnectionConfiguration.Builder configBuilder = null;
    static String usuario = null;
    static String contrasena = null;
    static String dominio = null;
    ChatManager chatManager = null;


    public ClienteXMPP(){
        dominio = "mail.awaresystems.cl";
        usuario = "vigud";
        contrasena = "vigud";

        try {
            //CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.configBuilder = XMPPTCPConnectionConfiguration.builder()
                .setEnabledSSLProtocols(new String[]{"TLS"})
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

    public boolean conectar(){
        conexion = new XMPPTCPConnection(configBuilder.build());
        boolean exito = false;
        try {
            conexion.connect().login();
            chatManager = ChatManager.getInstanceFor(conexion);
            exito = true;
        }catch (SmackException | IOException | XMPPException | InterruptedException ex){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public String temperatura(){
        final String[] mensaje = new String[1];
        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {
                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        mensaje[0] = message.getBody();
                        Log.e("Temperatura", mensaje[0]);
                    }
                });
            }
        });
        return mensaje[0];
    }

}
