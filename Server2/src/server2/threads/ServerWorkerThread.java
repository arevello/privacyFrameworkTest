/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author arevello
 */
public class ServerWorkerThread implements Runnable{
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    public ServerWorkerThread(DataInputStream dis, DataOutputStream dos){
        inputStream = dis;
        outputStream = dos;
    }

    @Override
    public void run() {
    }
}
