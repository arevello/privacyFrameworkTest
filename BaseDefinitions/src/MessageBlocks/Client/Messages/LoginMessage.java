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
public class LoginMessage implements Message{
    public String username;
    public int passwordHash;
    
    public LoginMessage(String u, int p){
        username = u;
        passwordHash = p;
    }
    
    public LoginMessage(byte[] b){
        ByteBuffer buf = ByteBuffer.wrap(b);
        int unameLen = buf.getInt();
        byte[] tempUname = new byte[unameLen];
        buf.get(tempUname, 0, unameLen);
        username = new String(tempUname);
        passwordHash =buf.getInt();
    }

    @Override
    public byte[] toByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(1000);
        b.putInt(username.length());
        b.put(username.getBytes());
        b.putInt(passwordHash);
        
        return b.array();
    }
}
