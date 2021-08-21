/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordHash;

/**
 *
 * @author chandru
 */
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class Passwordhashing {
 
    private static Random rand = new Random((new Date()).getTime());
    public  String encrypt(String str) {
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] salt = new byte[8];
            rand.nextBytes(salt);
            return encoder.encode(salt) + encoder.encode(str.getBytes());
    }
    public static String decrypt(String encstr) {
          if (encstr.length() > 12) {
            String cipher = encstr.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try { 
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {
                
            } 
        }
        return null;
    }
 
    
}
