/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.thirdParty;

import java.util.HashMap;
import java.util.Map;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;

/**
 *
 * @author arevello
 */
//table that displays what fields third parties use and how they use them
public class ThirdPartyFieldsUsed implements RecordStructure{
    int idData = -1;
    String fieldData = "";
    String usesData = "";
    String descriptionData = "";
    
    boolean isEmpty = false;
    Map<String, PairGeneric> emptyList = new HashMap<>();
    public ThirdPartyFieldsUsed(int id, String field, String uses, String description) {
        this.idData = id;
        this.fieldData = field;
        this.usesData = uses;
        this.descriptionData = description;
        
        emptyList.put("id", new PairGeneric(id));
        emptyList.put("field", new PairGeneric(field));
        emptyList.put("uses", new PairGeneric(uses));
        emptyList.put("description", new PairGeneric(description));
    }
    
    public ThirdPartyFieldsUsed(){
        isEmpty = true;
        
        emptyList.put("id", new PairGeneric(0));
        emptyList.put("field", new PairGeneric(""));
        emptyList.put("uses", new PairGeneric(""));
        emptyList.put("description", new PairGeneric(""));
    }

    public Map<String, PairGeneric> getEmptyList() {
        return emptyList;
    }
}
