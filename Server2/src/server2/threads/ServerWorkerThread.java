/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arevello
 */
public class ServerWorkerThread implements Runnable{
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    public Connection conn;
    public ServerWorkerThread(DataInputStream dis, DataOutputStream dos){
        inputStream = dis;
        outputStream = dos;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:../Server2/temp.db");
        } catch (SQLException ex) {
            Logger.getLogger(ServerWorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
    }
}
