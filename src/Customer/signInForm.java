package customer;
import java.awt.Color;
import java.awt.Container;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
public class signInForm extends JFrame implements ActionListener,ItemListener{
    Container co;

    public signInForm(){

        co=getContentPane();
        co.setLayout(null);
        int width=1280;
        int height=768;
        ImageIcon ima =new ImageIcon("C:\\Users\\chandru\\Documents\\NetBeansProjects\\Awt\\src\\logo.png");
        setIconImage(ima.getImage());
//        jpanel 1
        JPanel img_pane=new JPanel();
        img_pane.setSize(width/2,height);
        img_pane.setLayout(null);
//        jpanel 1 img
        ImageIcon image =new ImageIcon("C:\\Users\\chandru\\Pictures\\Background.jpg");
        JLabel commentlabel = new JLabel(image);
        commentlabel.setSize(width/2, height);
        img_pane.add(commentlabel);
        co.add(img_pane);    
//        jpanel 2
        JPanel login=new JPanel();
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
        ImageIcon email_img =new ImageIcon("C:\\Users\\chandru\\Downloads\\icons8-email-50.png");
        JLabel Email_i=new JLabel(email_img);
        Email_i.setBounds(70, 200, 100, 50);
        Email_i.setHorizontalAlignment(JLabel.CENTER);
        Email_i.setVerticalAlignment(JLabel.CENTER);
        login.add(Email_i);
       
        JLabel email=new JLabel("Email");
        email.setBounds(130, 200, 100, 50);
        email.setFont(new java.awt.Font("Arial",25,25));
        email.setHorizontalAlignment(JLabel.CENTER);
        email.setVerticalAlignment(JLabel.CENTER);
        login.add(email);
        
        JTextField temail=new JTextField();
        temail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder = temail.getBorder();
        Border redBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder = BorderFactory.createCompoundBorder(redBorder, oldBorder);
        temail.setBorder(newBorder);
        temail.setBackground(new Color(233,239,248));
        temail.setForeground(Color.red);
        temail.setBounds(280, 205, 300, 35);
        temail.setFont(new java.awt.Font("Arial",25,25));
        login.add(temail);
        
//        password section
        ImageIcon pass_img =new ImageIcon("C:\\Users\\chandru\\Downloads\\icons8-sign-in-form-password-48.png");
        JLabel pass_i=new JLabel(pass_img);
        pass_i.setBounds(70, 300, 100, 50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        login.add(pass_i);
       
        JLabel pass=new JLabel("Password");
        pass.setBounds(130, 310, 150, 35);
        pass.setFont(new java.awt.Font("Arial",25,25));
        pass.setHorizontalAlignment(JLabel.CENTER);
        pass.setVerticalAlignment(JLabel.CENTER);
        login.add(pass);
        
        JPasswordField tpass=new JPasswordField();
        tpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder1 = tpass.getBorder();
        Border redBorder1 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder1 = BorderFactory.createCompoundBorder(redBorder1, oldBorder1);
        tpass.setBorder(newBorder);
        tpass.setBackground(new Color(233,239,248));
        tpass.setForeground(Color.red);
        tpass.setBounds(280, 305, 300, 35);
        tpass.setFont(new java.awt.Font("Arial",25,25));
        login.add(tpass);
        
        JCheckBox checkpass = new JCheckBox("Show Password");
        checkpass.addActionListener(ae -> {
         JCheckBox c = (JCheckBox) ae.getSource();
         tpass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        checkpass.setBounds(280, 350, 300, 35);
        checkpass.setFont(new java.awt.Font("Arial",5,15));
        checkpass.setBackground(new Color(233,239,248));
        login.add(checkpass);
//        Submit button
        JButton submit =new JButton("SUBMIT");
        submit.setBounds(280, 400, 300, 35);
        submit.setBackground(Color.white);
        submit.setFont(new java.awt.Font("Arial",25,15));
        submit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setBackground(new Color(0XFFFFFFFF,true));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setBackground(UIManager.getColor("control"));
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               Customer.loginForm sc=new Customer.loginForm();
            }
        });
        login.add(submit);
         co.add(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        setSize(width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String args[]){
        new signInForm();
    }

    
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}