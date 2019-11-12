/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageBlocks;

/**
 *
 * @author arevello
 */
//interface that all messages will have, will be sent over tcp as bytebuffers
public interface Message {
    public byte[] toByteBuffer();
    //public Class <?> fromByteBuffer(byte[] b);
}
