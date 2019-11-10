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
import recordStructures.client.UserInfo;
import server2.threads.ServerWorkerThread;
import sqlTools.TableReader;

/**
 *
 * @author arevello
 */
public class ClientThread extends ServerWorkerThread {
    int clientId = -1;

    public ClientThread(DataInputStream dis, DataOutputStream dos) {
        super(dis, dos);
    }

    @Override
    public void run() {
        try {
            TableReader tr = new TableReader(this.conn);
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
                    
                    if(tr.loginValid(lm)){
                        System.out.println("wow it worked");
                        outputStream.writeInt(ClientCodes.acceptMsg);
                    }
                    else{
                        System.out.println("nope");
                        outputStream.writeInt(ClientCodes.rejectMsg);
                    }
                    
                    clientId = tr.getUserId(lm);
                }
                else if(type == ClientCodes.editAccountInformation){
                    System.out.println("reading account info and sending");
                    UserInfo ui = tr.getClientInfo(clientId);
                    String msg = ui.toMessageString();
                    this.outputStream.writeInt(msg.length());
                    outputStream.write(msg.getBytes(), 0, msg.length());
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
