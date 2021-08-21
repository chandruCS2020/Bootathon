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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Scanner;
public class PasswordHash
{

    public PasswordHash (String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        String passwordToHash = "chandru";
        byte[] salt = getSalt();
         
        String securePassword = "73563ba51d8c19b21807592e0df9de84";
        System.out.println(securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66
        
//        Scanner sc=new Scanner(System.in);
//        System.err.println("enter");
//        String str=sc.nextLine();
         
        String regeneratedPassowrdToVerify = getSecurePassword(str, salt);
        System.out.println(regeneratedPassowrdToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
        if(securePassword.equals(regeneratedPassowrdToVerify)){
            System.err.println("correct");
        }else{
            System.err.println("incorrect");
        }
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException  
    {
        PasswordHash sc=new PasswordHash("chandru");
        
    }
     
    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}

