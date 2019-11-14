/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.shared;

import java.util.HashMap;
import java.util.Map;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;

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
    
    boolean isEmpty = false;
    Map<String, PairGeneric> emptyList = new HashMap<>();
    public AccountType(int id, String username, int type, int password) {
        this.idData = id;
        this.usernameData = username;
        this.typeData = type;
        this.passwordHashData = password;
        
        emptyList.put("id", new PairGeneric(id));
        emptyList.put("username", new PairGeneric(username));
        emptyList.put("type", new PairGeneric(type));
        emptyList.put("passwordHash", new PairGeneric(password));
    }
    
    public AccountType(){
        isEmpty = true;
        
        emptyList.put("id", new PairGeneric(0));
        emptyList.put("username", new PairGeneric(""));
        emptyList.put("type", new PairGeneric(0));
        emptyList.put("passwordHash", new PairGeneric(0));
    }

    public Map<String, PairGeneric> getEmptyList() {
        return emptyList;
    }
}
