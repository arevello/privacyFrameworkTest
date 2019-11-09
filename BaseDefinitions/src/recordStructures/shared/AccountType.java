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
public class AccountType implements RecordStructure{
    int idData = -1;
    int typeData = -1;
    int passwordHashData = -1;
    
    SqlDataPair id;
    SqlDataPair type;
    SqlDataPair passwordHash;
    
    boolean isEmpty = false;
    ArrayList<SqlDataPair> emptyList = new ArrayList<SqlDataPair>();
    public AccountType(int id, int type, int password) {
        this.idData = id;
        this.typeData = type;
        this.passwordHashData = password;
        
        this.id = new SqlDataPair("id", new PairGeneric(id));
        this.type = new SqlDataPair("type", new PairGeneric(type));
        this.passwordHash = new SqlDataPair("address", new PairGeneric(password));
        
        emptyList.add(this.id);
        emptyList.add(this.type);
        emptyList.add(this.passwordHash);
    }
    
    public AccountType(){
        isEmpty = true;
        
        this.id = new SqlDataPair("id", new PairGeneric(0));
        this.type = new SqlDataPair("name", new PairGeneric(0));
        this.passwordHash = new SqlDataPair("address", new PairGeneric(0));
        
        emptyList.add(id);
        emptyList.add(type);
        emptyList.add(passwordHash);
    }

    public ArrayList<SqlDataPair> getEmptyList() {
        return emptyList;
    }
}
