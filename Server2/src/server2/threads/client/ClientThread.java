/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.threads.client;

import MessageBlocks.Client.ClientCodes;
import MessageBlocks.Client.Messages.LoginMessage;
import MessageBlocks.Message;
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
//thread to handle client requests
public class ClientThread extends ServerWorkerThread {
    int clientId = -1;

    public ClientThread(DataInputStream dis, DataOutputStream dos) {
        super(dis, dos);
    }

    //main loop
    @Override
    public void run() {
        try {
            TableReader tr = new TableReader(this.conn);
            while (true) {
                System.out.println("client in loop");
                //read int for type to read next
                int type = inputStream.readInt();
                System.out.println(type);
                
                //process a login and 
                if(type == ClientCodes.login){
                    System.out.println("reading login message");
                    //TODO change to send size before read OR define message size
                    //  defined message size can't be hijacked by baddies
                    //  if all look same won't know how to interpret
                    byte[] b = new byte[Message.messageSize];
                    inputStream.read(b, 0, Message.messageSize);
                    LoginMessage lm = new LoginMessage();
                    lm.fromByteBuffer(b);
                    System.out.println(lm.username + " " + lm.passwordHash);
                    
                    if(tr.loginValid(lm)){
                        System.out.println("wow it worked");
                        outputStream.writeInt(ClientCodes.acceptMsg);
                        
                        clientId = tr.getUserId(lm);
                    }
                    else{
                        System.out.println("nope");
                        outputStream.writeInt(ClientCodes.rejectMsg);
                    }
                    
                }
                //allow user to view and change their account information
                else if(type == ClientCodes.editAccountInformation){
                    System.out.println("reading account info and sending");
                    UserInfo ui = tr.getClientInfo(clientId);
                    outputStream.write(ui.toByteBuffer(), 0, Message.messageSize);
                }
                else if(type == ClientCodes.createNewAccount){
                    LoginMessage lm = new LoginMessage();
                    byte[] b = new byte[Message.messageSize];
                    inputStream.read(b, 0, Message.messageSize);
                    String table = "User";
                    if(tr.userExists(table, lm.username)){
                        outputStream.writeInt(ClientCodes.rejectMsg);
                    }
                    else{
                        tr.addUser(lm);
                        outputStream.writeInt(ClientCodes.acceptMsg);
                    }
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
