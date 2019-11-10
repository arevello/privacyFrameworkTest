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
            Connection conn = DriverManager.getConnection("jdbc:sqlite:../Server2/temp.db");
            String sql = "CREATE TABLE IF NOT EXISTS userdata (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    address text,\n"
                + "    processable bool\n"
                + ");";
            
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            
            stmt.execute("INSERT INTO userdata VALUES (1,\'bob\',\'1 temp lane\',0)");
            stmt.execute("INSERT INTO userdata VALUES (2,\'bobby\',\'2 temp lane\',0)");
            stmt.execute("INSERT INTO userdata VALUES (3,\'bobarino\',\'3 temp lane\',0)");
            stmt.execute("INSERT INTO userdata VALUES (4,\'bobette\',\'4 temp lane\',0)");
            stmt.execute("INSERT INTO userdata VALUES (5,\'bobert\',\'Norway\',0)");
            
            sql = "CREATE TABLE IF NOT EXISTS Accounts (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    username text NOT NULL,\n"
                + "    type integer,\n"
                + "    passwordHash integer\n"
                + ");";
            stmt.execute(sql);
            
            int hash1 = ("temp").hashCode();
            System.out.println(hash1);
            stmt.execute("INSERT INTO Accounts VALUES (1,\'bob\',1,"+hash1+")");
            stmt.execute("INSERT INTO Accounts VALUES (2,\'bobby\',1,"+hash1+")");
            stmt.execute("INSERT INTO Accounts VALUES (3,\'bobarino\',1,"+hash1+")");
            stmt.execute("INSERT INTO Accounts VALUES (4,\'bobette\',1,"+hash1+")");
            stmt.execute("INSERT INTO Accounts VALUES (5,\'bobert\',1,"+hash1+")");
        } catch (SQLException ex) {
            Logger.getLogger(BuildDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
