/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
//interface for all records
public interface RecordStructure {
    
    public ArrayList<SqlDataPair> getEmptyList(); //list of all sql column names and their value
}
