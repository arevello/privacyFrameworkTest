/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author arevello
 */
public class Server2 {

    public static void main(String args[]) {
        try {
            int portNumber = 420;
            int temp = 1;
            ThreadFactory tf = new ThreadFactory();
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                temp = dis.readInt();
                //get info from client about type of session to start
                //
                tf.startThread(temp, dis, dos);
                System.out.println("started thread");
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
