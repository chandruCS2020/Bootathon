/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTP;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author chandru
 */
public class SendOtp {
    public int otp_generate(String email){
        final String username = "cargologisticsmb@gmail.com";
        final String password = "Cargo@123";

        final String from = username;
        final String to = email;
        Random rand=new Random();
        int otp=rand.nextInt(999999)+100000;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
        });

        try {

                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
                message.setSubject("Cargo Logistics Management");
                message.setText("Verification code for you payment is "+otp);

                Transport.send(message);

                System.out.println("Done");
                


        } catch (MessagingException ae) {
                System.out.println(ae);
        }
        return otp;
    }
    
}
