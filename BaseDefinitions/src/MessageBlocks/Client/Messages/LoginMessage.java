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
//message for logging in to any account. Password must be hashed
public class LoginMessage implements Message{
    public String username;
    public int passwordHash;
    public int type;
    
    public LoginMessage(String u, int p, int t){
        username = u;
        passwordHash = p;
        type = t;
    }
    
    public LoginMessage(){
        username = "";
        passwordHash = 0;
        type = 0;
    }
    
    @Override
    public void fromByteBuffer(byte[] b){
        ByteBuffer buf = ByteBuffer.wrap(b);
        int unameLen = buf.getInt();
        byte[] tempUname = new byte[unameLen];
        buf.get(tempUname, 0, unameLen);
        username = new String(tempUname);
        passwordHash =buf.getInt();
        type = buf.getInt();
    }

    @Override
    public byte[] toByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(Message.messageSize);
        b.putInt(username.length());
        b.put(username.getBytes());
        b.putInt(passwordHash);
        b.putInt(type);
        
        return b.array();
    }
}
