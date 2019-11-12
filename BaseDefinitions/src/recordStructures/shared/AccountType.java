/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.shared;

import java.util.ArrayList;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;
import recordStructures.SqlDataPair;

/**
 *
 * @author arevello
 */
//account data table used for logging in and displaying the correct interface
public class AccountType implements RecordStructure{
    public final static String tableName = "Accounts";
    int idData = -1;
    String usernameData = "";
    int typeData = -1;
    int passwordHashData = -1;
    
    SqlDataPair id;
    SqlDataPair username;
    SqlDataPair type;
    SqlDataPair passwordHash;
    
    boolean isEmpty = false;
    ArrayList<SqlDataPair> emptyList = new ArrayList<>();
    public AccountType(int id, String username, int type, int password) {
        this.idData = id;
        this.usernameData = username;
        this.typeData = type;
        this.passwordHashData = password;
        
        this.id = new SqlDataPair("id", new PairGeneric(id));
        this.username = new SqlDataPair("username", new PairGeneric(username));
        this.type = new SqlDataPair("type", new PairGeneric(type));
        this.passwordHash = new SqlDataPair("passwordHash", new PairGeneric(password));
        
        emptyList.add(this.id);
        emptyList.add(this.username);
        emptyList.add(this.type);
        emptyList.add(this.passwordHash);
    }
    
    public AccountType(){
        isEmpty = true;
        
        this.id = new SqlDataPair("id", new PairGeneric(0));
        this.username = new SqlDataPair("username", new PairGeneric(""));
        this.type = new SqlDataPair("type", new PairGeneric(0));
        this.passwordHash = new SqlDataPair("passwordHash", new PairGeneric(0));
        
        emptyList.add(id);
        emptyList.add(username);
        emptyList.add(type);
        emptyList.add(passwordHash);
    }

    public ArrayList<SqlDataPair> getEmptyList() {
        return emptyList;
    }
}
