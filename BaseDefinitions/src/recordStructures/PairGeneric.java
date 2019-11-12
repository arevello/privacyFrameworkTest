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
//allows storing of any class into a sturcture that can be read from the table
public class PairGeneric<T> {
    T data;
    
    public PairGeneric(T data){
        this.data = data;
    }
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return data;
    }
    
    public Class<? extends Object> getGenericClass(){
        return data.getClass();
    }
}
