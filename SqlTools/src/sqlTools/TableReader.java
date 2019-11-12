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
//interact with database
public class TableReader {
    Connection conn;
    public TableReader(Connection conn){
        this.conn = conn;
    }
    
    //see if user exists and return boolean
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
    
    //see if username and password match records
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
    
    //get a user id from a login
    //should only be used after valid login
    public int getUserId(LoginMessage lm){
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT id FROM " + AccountType.tableName + " WHERE username=\'" + 
                    lm.username + "\' AND passwordHash=\'" + lm.passwordHash + "\'");
            int id = rs.getInt("id");
            rs.close();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
    
    //get info of user to be processed and displayed
    public UserInfo getClientInfo(int clientId){
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + UserInfo.tableName + " WHERE id=\'" + 
                    clientId + "\'");
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            Boolean proc = rs.getBoolean("processable");
            rs.close();
            return new UserInfo(id, name, address, proc);
        } catch (SQLException ex) {
            Logger.getLogger(TableReader.class.getName()).log(Level.SEVERE, null, ex);
            return new UserInfo();
        }
    }
}
