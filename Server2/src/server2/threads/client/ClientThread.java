/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.threads.client;

import MessageBlocks.Client.ClientCodes;
import MessageBlocks.Client.Messages.LoginMessage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server2.threads.ServerWorkerThread;

/**
 *
 * @author arevello
 */
public class ClientThread extends ServerWorkerThread {

    public ClientThread(DataInputStream dis, DataOutputStream dos) {
        super(dis, dos);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("client in loop");
                //read int for type to read next
                int type = inputStream.readInt();
                System.out.println(type);
                if(type == ClientCodes.login){
                    System.out.println("reading login message");
                    byte[] b = new byte[1000];
                    inputStream.read(b, 0, 1000);
                    LoginMessage lm = new LoginMessage(b);
                    System.out.println(lm.username + " " + lm.passwordHash);
                    if(lm.username.equals("butt"))
                        outputStream.writeInt(ClientCodes.acceptMsg);
                    else
                        outputStream.writeInt(ClientCodes.rejectMsg);
                }
                else{
                    System.out.println("WRONG");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
