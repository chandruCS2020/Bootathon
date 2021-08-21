/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import static Customer.SendMail.otp;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author chandru
 */
public class loginForm extends JFrame implements ActionListener,ItemListener{
    Container co;
    public loginForm(){
        co=getContentPane();
        co.setLayout(null);
        int width=1280;
        int height=768;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ima =new ImageIcon("C:\\Users\\chandru\\Documents\\NetBeansProjects\\Awt\\src\\logo.png");
        setIconImage(ima.getImage());
//        jpanel 1
        JPanel img_pane=new JPanel();
        img_pane.setSize(width/2,height);
        img_pane.setLayout(null);
//        jpanel 1 img
        ImageIcon image =new ImageIcon("src\\images\\Background.jpg");
        JLabel commentlabel = new JLabel(image);
        commentlabel.setSize(width/2, height);
        img_pane.add(commentlabel);
        co.add(img_pane);  
        
        //        jpanel 2
        JScrollPane login=new JScrollPane();
        login.setBounds(width/2,0,width/2,height);
        login.setBackground(new Color(233,239,248));
        login.setLayout(null);
//        jpanel 2 title
        JLabel Title=new JLabel("Cargo Logistics Management");
        Title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        Title.setBounds(20,70 , 600, 70);
        Title.setHorizontalAlignment(JLabel.CENTER);
        login.add(Title);
//        jpanel 2 name section
        ImageIcon email_img =new ImageIcon("src\\images\\email.png");
        JLabel Email_i=new JLabel(email_img);
        Email_i.setBounds(20, 200, 100, 50);
        Email_i.setHorizontalAlignment(JLabel.CENTER);
        Email_i.setVerticalAlignment(JLabel.CENTER);
        login.add(Email_i);
        
        JLabel email=new JLabel("Email");
        email.setBounds(100, 200, 150, 50);
        email.setFont(new java.awt.Font("Arial",25,25));
        login.add(email);
        
        JTextField temail=new JTextField("chandru6501@gmail.com");
        temail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder = temail.getBorder();
        Border redBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder = BorderFactory.createCompoundBorder(redBorder, oldBorder);
        temail.setBorder(newBorder);
        temail.setBackground(new Color(233,239,248));
        
        temail.setBounds(240, 205, 300, 35);
        temail.setFont(new java.awt.Font("Arial",25,20));
//        invalid email
        JLabel valid_email=new JLabel("* Invalid Email Address");
        valid_email.setBounds(240, 235, 300, 35);
        valid_email.setForeground(Color.red);
        valid_email.setVisible(false);
        login.add(valid_email);
        temail.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String emailregex =  "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!Pattern.matches(emailregex,text)) {
                   valid_email.setVisible(true);
                   temail.setForeground(Color.red);
                    return;
                } else {
                    valid_email.setVisible(false);
                    temail.setForeground(Color.black);
                } 
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
    
    });
        login.add(temail);
        
        
        
        
//        password section
        ImageIcon pass_img =new ImageIcon("src\\images\\password.png");
        JLabel pass_i=new JLabel(pass_img);
        pass_i.setBounds(20, 300, 100, 50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        login.add(pass_i);
       
        JLabel pass=new JLabel("Password");
        pass.setBounds(100, 300, 150, 50);
        pass.setFont(new java.awt.Font("Arial",25,25));
        login.add(pass);
        
        JPasswordField tpass=new JPasswordField();
        tpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder1 = tpass.getBorder();
        Border redBorder1 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder1 = BorderFactory.createCompoundBorder(redBorder1, oldBorder1);
        tpass.setBorder(newBorder);
        tpass.setBackground(new Color(233,239,248));
        tpass.setForeground(Color.black);
        tpass.setBounds(240, 305, 300, 35);
        tpass.setFont(new java.awt.Font("Arial",25,20));
        login.add(tpass);
        
        JCheckBox checkpass = new JCheckBox();
        checkpass.setIcon(new ImageIcon("src\\images\\eyeclose.png"));
        checkpass.setSelectedIcon(new ImageIcon("src\\images\\eyeopen.png"));
        checkpass.addActionListener(ae -> {
         JCheckBox c = (JCheckBox) ae.getSource();
         tpass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        checkpass.setBounds(550, 305, 50, 25);
        checkpass.setFont(new java.awt.Font("Arial",5,15));
        checkpass.setBackground(new Color(233,239,248));
        login.add(checkpass);
//        Submit button
        JButton submit =new JButton("LOGIN");
        submit.setBounds(302, 400, 175, 35);
        submit.setFont(new java.awt.Font("Arial",Font.BOLD,15));
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        submit.setHorizontalAlignment(JLabel.CENTER);
        submit.setVerticalAlignment(JLabel.CENTER);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setBackground(new Color(92, 112, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setBackground(Color.BLUE);
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               final String username = "chandru6501@gmail.com";
		final String password = "Chandru@123";

                final String from = username;
                final String to = temail.getText();
                Random rand=new Random();
                otp=rand.nextInt(9999);
                
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
			message.setText("Your otp is "+otp);

			Transport.send(message);

			System.out.println("Done");
                        System.err.println("Enter otp");
                        Scanner sc=new Scanner(System.in);
                        int n=sc.nextInt();
                        if(n==otp)
                        {
                            System.err.println("success");
                        }else{
                            System.err.println("not verified");
                        }

		} catch (MessagingException ae) {
			System.out.println(ae);
		}

            }
        });
        login.add(submit);
        //Forget password
        JLabel forget_password=new JLabel("Forgot Password ?");
        forget_password.setBounds(240, 450, 300, 35);
        forget_password.setForeground(Color.BLUE.darker());
        forget_password.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forget_password.setFont(new java.awt.Font("Arial",25,15));
        forget_password.setHorizontalAlignment(JLabel.CENTER);
        forget_password.setVerticalAlignment(JLabel.CENTER);
        login.add(forget_password);
        // create account
        JLabel Create=new JLabel("Create New Account");
        Create.setBounds(220, 570, 220, 45);
        Create.setForeground(Color.white);
        Create.setBackground(new Color(66,183,42));
        Create.setOpaque(true);
        Create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Create.setFont(new java.awt.Font("Arial",Font.BOLD,20));
//        Create.setBackground(Color.green);
//        Create.setOpaque(true);
        Create.setHorizontalAlignment(JLabel.CENTER);
        Create.setVerticalAlignment(JLabel.CENTER);
        Create.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    setVisible(false);
                    signInForm sc=new signInForm();
                    sc.setVisible(true);
                    dispose();
                    
                }

            });
        login.add(Create);
        
         co.add(login);
        
        setSize(width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new loginForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
