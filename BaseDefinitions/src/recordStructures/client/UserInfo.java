/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.client;

import java.util.ArrayList;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;
import recordStructures.SqlDataPair;

/**
 *
 * @author arevello
 */
public class UserInfo implements RecordStructure{
    int idData = -1;
    String nameData = "";
    String addressData = "";
    boolean processableData = false;
    
    SqlDataPair id;
    SqlDataPair name;
    SqlDataPair address;
    SqlDataPair processable;
    
    boolean isEmpty = false;
    ArrayList<SqlDataPair> emptyList = new ArrayList<SqlDataPair>();
    public UserInfo(int id, String name, String address, boolean processable) {
        this.idData = id;
        this.nameData = name;
        this.addressData = address;
        this.processableData = processable;
        
        this.id = new SqlDataPair("id", new PairGeneric(id));
        this.name = new SqlDataPair("name", new PairGeneric(name));
        this.address = new SqlDataPair("address", new PairGeneric(address));
        this.processable = new SqlDataPair("processable", new PairGeneric(processable));
        
        emptyList.add(this.id);
        emptyList.add(this.name);
        emptyList.add(this.address);
        emptyList.add(this.processable);
    }
    
    public UserInfo(){
        isEmpty = true;
        
        this.id = new SqlDataPair("id", new PairGeneric(0));
        this.name = new SqlDataPair("name", new PairGeneric(""));
        this.address = new SqlDataPair("address", new PairGeneric(""));
        this.processable = new SqlDataPair("processable", new PairGeneric(false));
        
        emptyList.add(id);
        emptyList.add(name);
        emptyList.add(address);
        emptyList.add(processable);
    }

    public ArrayList<SqlDataPair> getEmptyList() {
        return emptyList;
    }
}
