/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  

public class sendAttachment{  

    public sendAttachment(String email,String name) {
        String to=email;
        final String user="cargologisticsmb@gmail.com"; 
        final String password="Cargo@123";

        Properties properties = System.getProperties();  
        properties.put("mail.smtp.auth", "true"); 
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties,  
         new javax.mail.Authenticator() {  
         protected PasswordAuthentication getPasswordAuthentication() {  
         return new PasswordAuthentication(user,password);  
         }  
        });  

        //2) compose message     
        try{  
          MimeMessage message = new MimeMessage(session);  
          message.setFrom(new InternetAddress(user));  
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
          message.setSubject("Cargo Logistic Management");     
          BodyPart messageBodyPart1 = new MimeBodyPart();  
          messageBodyPart1.setText("Cargo Logistics Management bill");      
          MimeBodyPart messageBodyPart2 = new MimeBodyPart();  

          String filename = "D:\\New folder\\"+name+".pdf";
          String letter="cargo_billing.pdf";
          DataSource source = new FileDataSource(filename);  
          messageBodyPart2.setDataHandler(new DataHandler(source));  
          messageBodyPart2.setFileName(letter);  
     
          Multipart multipart = new MimeMultipart();  
          multipart.addBodyPart(messageBodyPart1);  
          multipart.addBodyPart(messageBodyPart2);  

          message.setContent(multipart );  

          Transport.send(message);  

         System.out.println("message sent....");  
         }catch (MessagingException ex) {ex.printStackTrace();}  
          }
    
        public static void main(String [] args){  

         new sendAttachment("","");
        }  
} 
