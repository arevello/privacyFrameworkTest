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
//pair of column name and value from sql table
public class SqlDataPair {
    public String key = "";
    public PairGeneric value = null;
    public SqlDataPair(String key, PairGeneric value){
        this.key = key;
        this.value = value;
    }
}
