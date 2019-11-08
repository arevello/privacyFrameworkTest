/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures;

/**
 *
 * @author arevello
 */
public class SqlDataPair {
    String key = "";
    PairGeneric value = null;
    public SqlDataPair(String key, PairGeneric value){
        this.key = key;
        this.value = value;
    }
}
