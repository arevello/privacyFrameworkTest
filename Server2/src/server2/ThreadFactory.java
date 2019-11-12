/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import server2.threads.client.ClientThread;

/**
 *
 * @author arevello
 */
public class ThreadFactory {
    public static final int clientThread = 1;
    public static final int processorThread = 2;
    public static final int thirdPartyThread = 3;
    public ThreadFactory() {
    }

    //start relevant thread that has connected with us
    public void startThread(int type, DataInputStream dis, DataOutputStream dos) {
            System.out.println("started thread " + type);
            if(type == clientThread){
                ClientThread ct = new ClientThread(dis, dos);
                ct.run();
            }
            else if(type == processorThread){
                
            }
            else if(type == thirdPartyThread){
                
            }
            else{
                System.out.println("recv thread type " + type);
            }
            System.out.println("going back to listen loop");
    }

}
