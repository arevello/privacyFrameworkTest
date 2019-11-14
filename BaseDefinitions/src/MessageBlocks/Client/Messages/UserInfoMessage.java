/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageBlocks.Client.Messages;

import MessageBlocks.Message;
import java.nio.ByteBuffer;

/**
 *
 * @author arevello
 */
public class UserInfoMessage implements Message{
    
    public int id;
    public String name;
    public String address;
    public boolean processable;
    
    public UserInfoMessage(int id, String name, String address, boolean p){
        this.id = id;
        this.name = name;
        this.address = address;
        this.processable = p;
    }
    
    public UserInfoMessage(){
        this.id = 0;
        this.name = "";
        this.address = "";
        this.processable = false;
    }
    @Override
    public byte[] toByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(Message.messageSize);
        b.putInt(id);
        b.putInt(name.length());
        b.put(name.getBytes());
        b.putInt(address.length());
        b.put(address.getBytes());
        if(processable)
            b.putChar('t');
        else
            b.putChar('f');
        
        return b.array();
        
    }

    @Override
    public void fromByteBuffer(byte[] b) {
        ByteBuffer buf = ByteBuffer.wrap(b);
        id = buf.getInt();
        byte[] tempName = new byte[buf.getInt()];
        buf.get(tempName, 0, tempName.length);
        name = new String(tempName);
        byte[] tempAddress = new byte[buf.getInt()];
        buf.get(b, 0, tempAddress.length);
        address = new String(tempAddress);
        if(buf.getChar() == 't')
            processable = true;
        else
            processable = false;
    }
    
}
