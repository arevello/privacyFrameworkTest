/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.thirdParty;

import java.util.ArrayList;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;
import recordStructures.SqlDataPair;

/**
 *
 * @author arevello
 */
public class ThirdPartyFieldsUsed implements RecordStructure{
    int idData = -1;
    String fieldData = "";
    String usesData = "";
    String descriptionData = "";
    
    SqlDataPair id;
    SqlDataPair field;
    SqlDataPair uses;
    SqlDataPair description;
    
    boolean isEmpty = false;
    ArrayList<SqlDataPair> emptyList = new ArrayList<SqlDataPair>();
    public ThirdPartyFieldsUsed(int id, String field, String uses, String description) {
        this.idData = id;
        this.fieldData = field;
        this.usesData = uses;
        this.descriptionData = description;
        
        this.id = new SqlDataPair("id", new PairGeneric(id));
        this.field = new SqlDataPair("field", new PairGeneric(field));
        this.uses = new SqlDataPair("uses", new PairGeneric(uses));
        this.description = new SqlDataPair("description", new PairGeneric(description));
        
        emptyList.add(this.id);
        emptyList.add(this.field);
        emptyList.add(this.uses);
        emptyList.add(this.description);
    }
    
    public ThirdPartyFieldsUsed(){
        isEmpty = true;
        
        this.id = new SqlDataPair("id", new PairGeneric(0));
        this.field = new SqlDataPair("field", new PairGeneric(""));
        this.uses = new SqlDataPair("uses", new PairGeneric(""));
        this.description = new SqlDataPair("description", new PairGeneric(""));
        
        emptyList.add(id);
        emptyList.add(field);
        emptyList.add(uses);
        emptyList.add(description);
    }

    public ArrayList<SqlDataPair> getEmptyList() {
        return emptyList;
    }
}
