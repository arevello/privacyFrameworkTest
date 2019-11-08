/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server2;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author arevello
 */
public class HelperThread implements Runnable {
    int type;
    public HelperThread(int type) {
        this.type = type;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("started thread " + type);
            while (true) {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:temp.db");
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(HelperThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HelperThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
