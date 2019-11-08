/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.client;

import recordStructures.PairGeneric;
import recordStructures.SqlDataPair;

/**
 *
 * @author arevello
 */
public class UserInfo {
    int idData = -1;
    String nameData = "";
    String addressData = "";
    boolean processableData = false;
    
    SqlDataPair id;
    SqlDataPair name;
    SqlDataPair address;
    SqlDataPair processable;
    
    public UserInfo(int id, String name, String address, boolean processable) {
        this.idData = id;
        this.nameData = name;
        this.addressData = address;
        this.processableData = processable;
        
        this.id = new SqlDataPair("id", new PairGeneric(id));
        this.name = new SqlDataPair("name", new PairGeneric(name));
        this.address = new SqlDataPair("address", new PairGeneric(address));
        this.processable = new SqlDataPair("processable", new PairGeneric(processable));
    }
    
    public UserInfo(){
        
    }
}
