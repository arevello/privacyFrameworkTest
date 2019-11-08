/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builddatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arevello
 */
public class BuildDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Connection conn = DriverManager.getConnection("jdbc:sqlite:temp.db");
            String sql = "CREATE TABLE IF NOT EXISTS userdata (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    address text\n"
                + ");";
            
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BuildDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
