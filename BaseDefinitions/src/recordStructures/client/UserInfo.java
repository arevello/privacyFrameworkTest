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
//data for the controller containing their name, address, and if they are in the pool of data that can be shared
public class UserInfo implements RecordStructure{
    public static final String tableName = "userdata";
    int idData = -1;
    String nameData = "";
    String addressData = "";
    Boolean processableData = false;
    
    SqlDataPair idPair;
    SqlDataPair namePair;
    SqlDataPair addressPair;
    SqlDataPair processablePair;
    
    boolean isEmpty = false;
    ArrayList<SqlDataPair> emptyList = new ArrayList<SqlDataPair>();
    public UserInfo(int id, String name, String address, Boolean processable) {
        this.idData = id;
        this.nameData = name;
        this.addressData = address;
        this.processableData = processable;
        
        this.idPair = new SqlDataPair("id", new PairGeneric(id));
        this.namePair = new SqlDataPair("name", new PairGeneric(name));
        this.addressPair = new SqlDataPair("address", new PairGeneric(address));
        this.processablePair = new SqlDataPair("processable", new PairGeneric(processable));
        
        emptyList.add(this.idPair);
        emptyList.add(this.namePair);
        emptyList.add(this.addressPair);
        emptyList.add(this.processablePair);
    }
    
    public UserInfo(String message) {
        String[] tokens = message.split(",");
        System.out.println(message);
        for(int i = 0; i < tokens.length; i++)
            System.out.println("AAAAA " + tokens[i]);
        this.idData = Integer.parseInt(tokens[0]);
        this.nameData = tokens[1];
        this.addressData = tokens[2];
        this.processableData = Boolean.valueOf(tokens[3]);
        
        this.idPair = new SqlDataPair("id", new PairGeneric(idData));
        this.namePair = new SqlDataPair("name", new PairGeneric(nameData));
        this.addressPair = new SqlDataPair("address", new PairGeneric(addressData));
        this.processablePair = new SqlDataPair("processable", new PairGeneric(processableData));
        
        emptyList.add(this.idPair);
        emptyList.add(this.namePair);
        emptyList.add(this.addressPair);
        emptyList.add(this.processablePair);
    }
    
    public UserInfo(){
        isEmpty = true;
        
        this.idPair = new SqlDataPair("id", new PairGeneric(0));
        this.namePair = new SqlDataPair("name", new PairGeneric(""));
        this.addressPair = new SqlDataPair("address", new PairGeneric(""));
        this.processablePair = new SqlDataPair("processable", new PairGeneric(false));
        
        emptyList.add(idPair);
        emptyList.add(namePair);
        emptyList.add(addressPair);
        emptyList.add(processablePair);
    }

    public ArrayList<SqlDataPair> getEmptyList() {
        return emptyList;
    }
    
    public String toMessageString(){
        return "" + idData + "," + nameData + "," + addressData + "," + processableData;
    }
}
