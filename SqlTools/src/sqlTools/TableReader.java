/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlTools;

import MessageBlocks.Client.Messages.LoginMessage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import recordStructures.client.UserInfo;
import recordStructures.shared.AccountType;

/**
 *
 * @author Alex
 */
public class TableReader {
    Connection conn;
    public TableReader(Connection conn){
        this.conn = conn;
    }
    
    public boolean userExists(String table, String username){
        try {
            boolean ret = false;
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + table + " WHERE username=" + username);
            if(rs.next()){
                ret = true;
            }
            rs.close();
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean loginValid(LoginMessage lm){
        try {
            boolean ret = false;
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + AccountType.tableName + " WHERE username=\'" + 
                    lm.username + "\' AND passwordHash=\'" + lm.passwordHash + "\'");
            if(rs.next()){
                ret = true;
            }
            rs.close();
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int getUserId(LoginMessage lm){
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT id FROM " + AccountType.tableName + " WHERE username=\'" + 
                    lm.username + "\' AND passwordHash=\'" + lm.passwordHash + "\'");
            int id = rs.getInt("id");
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
    
    public UserInfo getClientInfo(int clientId){
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + UserInfo.tableName + " WHERE id=\'" + 
                    clientId + "\'");
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            Boolean proc = rs.getBoolean("processable");
            return new UserInfo(id, name, address, proc);
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return new UserInfo();
        }
    }
}
