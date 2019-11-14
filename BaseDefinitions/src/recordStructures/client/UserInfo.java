/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordStructures.client;

import MessageBlocks.Message;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;

/**
 *
 * @author arevello
 */
//data for the controller containing their name, address, and if they are in the pool of data that can be shared
public class UserInfo implements RecordStructure, Message{
    public static final String tableName = "userdata";
    int idData = -1;
    String nameData = "";
    String addressData = "";
    Boolean processableData = false;
    
    boolean isEmpty = false;
    Map<String, PairGeneric> emptyList = new HashMap<>();
    public UserInfo(int id, String name, String address, Boolean processable) {
        this.idData = id;
        this.nameData = name;
        this.addressData = address;
        this.processableData = processable;
        
        emptyList.put("id", new PairGeneric(id));
        emptyList.put("name", new PairGeneric(name));
        emptyList.put("address", new PairGeneric(address));
        emptyList.put("processable", new PairGeneric(processable));
    }
    
    public UserInfo(){
        isEmpty = true;
        
        emptyList.put("id", new PairGeneric(0));
        emptyList.put("name", new PairGeneric(""));
        emptyList.put("address", new PairGeneric(""));
        emptyList.put("processable", new PairGeneric(false));
    }

    public Map<String, PairGeneric> getEmptyList() {
        return emptyList;
    }

    @Override
    public byte[] toByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(Message.messageSize);
        b.putInt(idData);
        b.putInt(nameData.length());
        b.put(nameData.getBytes());
        b.putInt(addressData.length());
        b.put(addressData.getBytes());
        if(processableData)
            b.putChar('t');
        else
            b.putChar('f');
        
        return b.array();
    }

    @Override
    public void fromByteBuffer(byte[] b) {
        ByteBuffer buf = ByteBuffer.wrap(b);
        idData = buf.getInt();
        emptyList.get("id").setData(idData);
        byte[] tempName = new byte[buf.getInt()];
        buf.get(tempName, 0, tempName.length);
        nameData = new String(tempName);
        emptyList.get("name").setData(nameData);
        byte[] tempAddress = new byte[buf.getInt()];
        buf.get(b, 0, tempAddress.length);
        addressData = new String(tempAddress);
        emptyList.get("address").setData(addressData);
        if(buf.getChar() == 't')
            processableData = true;
        else
            processableData = false;
        emptyList.get("processable").setData(processableData);
    }
}
