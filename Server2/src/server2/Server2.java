/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2;

import java.io.DataInputStream;
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
            ServerSocket serverSocket = new ServerSocket(portNumber);
            int temp = 1;
            boolean first = true;
            while (true) {
                if(!first){
                    Socket clientSocket = serverSocket.accept();
                    DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
                    temp = dis.readInt();
                }
                //get info from client about type of session to start
                //
                first = false;
                new HelperThread(temp);
                System.out.println("started thread");
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
