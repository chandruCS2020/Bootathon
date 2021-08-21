/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Bean.JBean;
import static Customer.SendMail.otp;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.Box;
import static javax.swing.Box.createGlue;
import static javax.swing.Box.createHorizontalBox;
import static javax.swing.Box.createHorizontalStrut;
import static javax.swing.Box.createRigidArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import passwordHash.PasswordHash;
import passwordHash.Passwordhashing;

/**
 *
 * @author chandru
 */
public class Demo extends JFrame implements ActionListener,ItemListener,MouseListener,KeyListener{
    Container co;
    JBean ob;
    private static int otp;
    JPanel img_pane,login,signIn,nav,verify,cargo,availabilty,transaction,billing,settings,live_tracking;
    Border b = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
    JButton lsubmit,create,verifybtn,resendbtn;
    JLabel sCreate,Login_btn,Login_link,commentlabel,lTitle,lEmail_i,lemail,lvalid_email,lpass,lforget_password,address,pass_validate,pass,valid_email,email,user_validate,user,mobile_validate,Mobile,name,Title
            ,address_i,pass_i,Email_i,used_id,mobile_i,User_img,lpass_i,p4_title,p4_uname,p4_uname_il,p4_uname_id,p4_cargo,p4_cargo_il
            ,p4_avail,p4_avail_il,p4_trans_il,p4_trans,p4_bill,p4_bill_il,p4_live_il,p4_live,p4_set_il,p4_set
            ,p4_log_il,p4_log,v_title,v_head,otp_l,otp_validate;
    ImageIcon image,lemail_img,lpass_img,address_img,pass_img ,email_img,user_img,Mobile_img,uname_img,p4_uname_i,p4_cargo_i,p4_avail_i,p4_trans_i,p4_bill_i
            ,p4_live_i;
    JTextField l_email,temail,tuser,tmobile,tuname,votp;
    JPasswordField ltpass,tpass;
    JCheckBox lcheckpass ,checkpass ;
    JTextArea tarea;
    Box box1,box2,box3,box4,box5,box6,box7;
    Color bg=new Color(233,239,248);
    String str;
    
    public Demo() throws NoSuchAlgorithmException, NoSuchProviderException{
        co=getContentPane();
        co.setLayout(null);
        int width=1280;
        int height=768;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ima =new ImageIcon("C:\\Users\\chandru\\Documents\\NetBeansProjects\\Awt\\src\\logo.png");
        setIconImage(ima.getImage());
//        <-------------jpanel 1--------------->
        img_pane=new JPanel();
        img_pane.setSize(width/2,height);
        img_pane.setLayout(null);
//        <------------jpanel 1 img------------>
        image =new ImageIcon("src\\images\\Background.jpg");
        commentlabel = new JLabel(image);
        commentlabel.setSize(width/2, height);
        img_pane.add(commentlabel);
        co.add(img_pane);  
        
//        <-------------jpanel 2---------------->
        login=new JPanel();
        login.setBounds(width/2,0,width/2,height);
        login.setBackground(bg);
        login.setLayout(null);
//        <-----------jpanel 2 title------------>
        lTitle=new JLabel("Cargo Logistics Management");
        lTitle.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        lTitle.setBounds(20,70 , 600, 70);
        lTitle.setHorizontalAlignment(JLabel.CENTER);
        login.add(lTitle);
//       <------- jpanel 2 name section ------>
        lemail_img =new ImageIcon("src\\images\\email.png");
        lEmail_i=new JLabel(lemail_img);
        lEmail_i.setBounds(20, 200, 100, 50);
        lEmail_i.setHorizontalAlignment(JLabel.CENTER);
        lEmail_i.setVerticalAlignment(JLabel.CENTER);
        login.add(lEmail_i);
//        <----------email label--------->
        lemail=new JLabel("Email");
        lemail.setBounds(100, 200, 150, 50);
        lemail.setFont(new java.awt.Font("Arial",25,25));
        login.add(lemail);
//        <---------email textfield-------->
        l_email=new JTextField();
        l_email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        l_email.setBorder(b);
        l_email.setBackground(bg);
        l_email.setBounds(240, 205, 300, 35);
        l_email.setFont(new java.awt.Font("Arial",25,20));
        l_email.addKeyListener(this);
        login.add(l_email);
//       <------- invalid email--------->
        lvalid_email=new JLabel("* Invalid Email Address");
        lvalid_email.setBounds(240, 235, 300, 35);
        lvalid_email.setForeground(Color.red);
        lvalid_email.setVisible(false);
        login.add(lvalid_email);
        
//       <--------- password section--------->
        lpass_img =new ImageIcon("src\\images\\password.png");
        lpass_i=new JLabel(lpass_img);
        lpass_i.setBounds(20, 300, 100, 50);
        lpass_i.setHorizontalAlignment(JLabel.CENTER);
        lpass_i.setVerticalAlignment(JLabel.CENTER);
        login.add(lpass_i);
//       <--------password lable---------->
        lpass=new JLabel("Password");
        lpass.setBounds(100, 300, 150, 50);
        lpass.setFont(new java.awt.Font("Arial",25,25));
        login.add(lpass);
//        <---------password field------>
        ltpass=new JPasswordField("chandru");
        ltpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ltpass.setBorder(b);
        ltpass.setBackground(bg);
        ltpass.setForeground(Color.black);
        ltpass.setBounds(240, 305, 300, 35);
        ltpass.setFont(new java.awt.Font("Arial",25,20));
        
        login.add(ltpass);
//        <---------chack password---------->
        lcheckpass = new JCheckBox();
        lcheckpass.setIcon(new ImageIcon("src\\images\\eyeclose.png"));
        lcheckpass.setSelectedIcon(new ImageIcon("src\\images\\eyeopen.png"));
        lcheckpass.addActionListener(this);
        lcheckpass.setBounds(550, 305, 50, 25);
        lcheckpass.setFont(new java.awt.Font("Arial",5,15));
        lcheckpass.setBackground(bg);
        login.add(lcheckpass);
//        <-------------Submit button--------->
        lsubmit =new JButton("LOGIN");
        lsubmit.setBounds(302, 400, 175, 35);
        lsubmit.setFont(new java.awt.Font("Arial",Font.BOLD,15));
        lsubmit.setForeground(Color.white);
        lsubmit.setBackground(Color.blue);
        lsubmit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        lsubmit.setHorizontalAlignment(JLabel.CENTER);
        lsubmit.setVerticalAlignment(JLabel.CENTER);
        lsubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lsubmit.addMouseListener(this);
        lsubmit.addActionListener(this);
        login.add(lsubmit);
//      <---------------Forget password-------------->
        lforget_password=new JLabel("Forgot Password ?");
        lforget_password.setBounds(240, 450, 300, 35);
        lforget_password.setForeground(Color.BLUE.darker());
        lforget_password.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lforget_password.setFont(new java.awt.Font("Arial",25,15));
        lforget_password.setHorizontalAlignment(JLabel.CENTER);
        lforget_password.setVerticalAlignment(JLabel.CENTER);
        login.add(lforget_password);
//      <-----------create account---------->
        sCreate=new JLabel("Create New Account");
        sCreate.setBounds(220, 570, 220, 45);
        sCreate.setForeground(Color.white);
        sCreate.setBackground(new Color(66,183,42));
        sCreate.setOpaque(true);
        sCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sCreate.setFont(new java.awt.Font("Arial",Font.BOLD,20));
        sCreate.setHorizontalAlignment(JLabel.CENTER);
        sCreate.setVerticalAlignment(JLabel.CENTER);
        sCreate.addMouseListener(this);
        login.add(sCreate);
        co.add(login);
        
         
         
//<------------------------------------------------------jpanel 3--------------------------------------------------------->



        signIn=new JPanel();
        signIn.setBounds(width/2,0,width/2,height);
        signIn.setBackground(new Color(233,239,248));
        signIn.setLayout(null);
//        jpanel 2 title
        Title=new JLabel("Cargo Logistics Management");
        Title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        Title.setBounds(20,20 , 600, 70);
        Title.setHorizontalAlignment(JLabel.CENTER);
        signIn.add(Title);
//        jpanel 2 name section
        uname_img=new ImageIcon("src\\images\\user.png");
        User_img=new JLabel(uname_img);
        User_img.setBounds(50,120,50,50);
        User_img.setHorizontalAlignment(JLabel.CENTER);
        User_img.setVerticalAlignment(JLabel.CENTER);
        signIn.add(User_img);
        
        name=new JLabel("Name");
        name.setBounds(120, 120, 150, 50);
        name.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(name);
        
        
        tuname=new JTextField();
        tuname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tuname.setBorder(b);
        tuname.setBackground(new Color(233,239,248));
        tuname.setBounds(290, 125, 280, 35);
        tuname.setFont(new java.awt.Font("Arial",25,18));
        signIn.add(tuname);
        
        Mobile_img=new ImageIcon("src\\images\\phone.png");
        mobile_i=new JLabel(Mobile_img);
        mobile_i.setBounds(50,180,50,50);
        mobile_i.setHorizontalAlignment(JLabel.CENTER);
        mobile_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(mobile_i);
        
        Mobile=new JLabel("Mobile Number");
        Mobile.setBounds(120, 180, 150, 50);
        Mobile.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(Mobile);
        
        tmobile=new JTextField();
        tmobile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tmobile.setBorder(b);
        tmobile.setBackground(new Color(233,239,248));
        tmobile.setBounds(290, 185, 280, 35);
        tmobile.setFont(new java.awt.Font("Arial",25,18));
        
        mobile_validate=new JLabel("* invalid phone number");
        mobile_validate.setBounds(290, 220,280, 20);
        mobile_validate.setForeground(Color.red);
        mobile_validate.setVisible(false);
        signIn.add(mobile_validate);
        tmobile.addKeyListener(this);
        signIn.add(tmobile);
        
        user_img=new ImageIcon("src\\images\\user_id.png");
        used_id=new JLabel(user_img);
        used_id.setBounds(50,250,50,50);
        used_id.setHorizontalAlignment(JLabel.CENTER);
        used_id.setVerticalAlignment(JLabel.CENTER);
        signIn.add(used_id);
        
        user=new JLabel("User Name");
        user.setBounds(120, 250, 150, 50);
        user.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(user);
        
        tuser=new JTextField();
        tuser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tuser.setBorder(b);
        tuser.setBackground(new Color(233,239,248));
        tuser.setBounds(290, 250, 280, 35);
        tuser.setFont(new java.awt.Font("Arial",25,18));
        
        user_validate=new JLabel("* User name already exist");
        user_validate.setBounds(290, 290,280, 20);
        user_validate.setForeground(Color.red);
        user_validate.setVisible(false);
        signIn.add(user_validate);
        signIn.add(tuser);
        
        
        email_img =new ImageIcon("src\\images\\email.png");
        Email_i=new JLabel(email_img);
        Email_i.setBounds(50,320,50,50);
        Email_i.setHorizontalAlignment(JLabel.CENTER);
        Email_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(Email_i);
        
        email=new JLabel("Email");
        email.setBounds(120, 320, 150, 50);
        email.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(email);
        
        temail=new JTextField();
        temail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        temail.setBorder(b);
        temail.setBackground(new Color(233,239,248));
        
        temail.setBounds(290, 320, 280, 35);
        temail.setFont(new java.awt.Font("Arial",25,18));
//        invalid email
        valid_email=new JLabel("* Invalid Email Address");
        valid_email.setBounds(290, 355, 280, 35);
        valid_email.setForeground(Color.red);
        valid_email.setVisible(false);
        signIn.add(valid_email);
        temail.addKeyListener(this);
        signIn.add(temail);
        
//        password section
        pass_img =new ImageIcon("src\\images\\password.png");
        pass_i=new JLabel(pass_img);
        pass_i.setBounds(50,380,50,50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(pass_i);
       
        pass=new JLabel("Password");
        pass.setBounds(120, 380, 150, 50);
        pass.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(pass);
        
        tpass=new JPasswordField();
        tpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tpass.setBorder(b);
        tpass.setBackground(new Color(233,239,248));
        tpass.setForeground(Color.black);
        tpass.setBounds(290, 395, 280, 20);
        tpass.setFont(new java.awt.Font("Arial",25,18));
        tpass.addKeyListener(this);
        signIn.add(tpass);
        pass_validate=new JLabel("Enter a weak Password");
        pass_validate.setBounds(290, 420, 280, 20);
        pass_validate.setForeground(Color.red);
        pass_validate.setVisible(false);
        signIn.add(pass_validate);
        
        checkpass = new JCheckBox();
        checkpass.setIcon(new ImageIcon("src\\images\\eyeclose.png"));
        checkpass.setSelectedIcon(new ImageIcon("src\\images\\eyeopen.png"));
        checkpass.addActionListener(this);
        checkpass.setBounds(575, 385, 50, 25);
        checkpass.setFont(new java.awt.Font("Arial",5,15));
        checkpass.setBackground(new Color(233,239,248));
        signIn.add(checkpass);
        
//        address
        address_img =new ImageIcon("src\\images\\address.png");
        address_i=new JLabel(address_img);
        address_i.setBounds(50,450,50,50);
        address_i.setHorizontalAlignment(JLabel.CENTER);
        address_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(address_i);
        
         address=new JLabel("Address");
        address.setBounds(120, 450, 150, 50);
        address.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(address);
        
        tarea=new JTextArea();  
        JScrollPane tareas=new JScrollPane(tarea);
        tarea.setLineWrap(true);
        tareas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tareas.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b4 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
        tareas.setBorder(b4);
        tarea.setBackground(new Color(233,239,248));
        tarea.setForeground(Color.black);
        tareas.setBounds(290, 465, 280, 130);
        tareas.setFont(new java.awt.Font("Arial",25,18));
        tareas.setVisible(true);
        signIn.add(tareas);
        
//        create button

        create=new JButton("Create");
        create.setBounds(290, 620, 175, 35);
        create.setFont(new java.awt.Font("Arial",Font.BOLD,15));
        create.setForeground(Color.white);
        create.setBackground(Color.blue);
        create.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        create.setHorizontalAlignment(JLabel.CENTER);
        create.setVerticalAlignment(JLabel.CENTER);
        create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        create.addMouseListener(this);
        create.addActionListener(this);
        signIn.add(create);
        
        
        Login_link=new JLabel("Already have an account?");
        Login_link.setBounds(290, 670, 150, 30);
        Login_link.setForeground(Color.BLUE.darker());
        signIn.add(Login_link);
        
        Login_btn=new JLabel("Log in");
        Login_btn.setBounds(440, 670, 150, 30);
        Login_btn.setForeground(Color.red);
        Login_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Login_btn.addMouseListener(this);
        signIn.add(Login_btn);
        
        co.add(signIn);
        
        
//        <----------------------panel 4 for navbar------------------>

        nav=new JPanel();
        nav.setSize(width/3, height);
        nav.setBackground(Color.YELLOW);
        nav.setLayout(null);
        co.add(nav);
        System.err.println(width/3);
        
//        panel 4 title
        p4_title=new JLabel("Cargo Logistics Management");
        p4_title.setBounds(10, 20, 406, 90);
        p4_title.setHorizontalAlignment(JLabel.CENTER);
        p4_title.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 32));
        p4_title.setBackground(Color.yellow);
        p4_title.setOpaque(true);
        nav.add(p4_title);
        
        
//        panel 4 customer name
        p4_uname_i=new ImageIcon("src\\images\\user.png");
        p4_uname_il=new JLabel(p4_uname_i);
        p4_uname_il.setBounds(10,130,60,70);
        p4_uname_il.setBackground(Color.orange);
        p4_uname_il.setOpaque(true);
        p4_uname_il.setHorizontalAlignment(JLabel.CENTER);
        nav.add(p4_uname_il);
        
       
        p4_uname=new JLabel("Chandru");
        p4_uname.setBounds(70, 130, 206, 70);
//        p4_uname.setHorizontalAlignment(JLabel.CENTER);
        p4_uname.setFont(new Font("Arial", Font.BOLD, 25));
        p4_uname.setBackground(Color.orange);
        p4_uname.setOpaque(true);
        nav.add(p4_uname);
        
        p4_uname_id=new JLabel("#1234");
        p4_uname_id.setBounds(276, 130,140 ,70);
        p4_uname_id.setHorizontalAlignment(JLabel.CENTER);
        p4_uname_id.setFont(new Font("Arial", Font.BOLD, 20));
        p4_uname_id.setBackground(Color.orange);
        p4_uname_id.setOpaque(true);
        nav.add(p4_uname_id);
//        cargo

        p4_cargo_i =new ImageIcon("src\\images\\cargo.png");
        p4_cargo_il=new JLabel(p4_cargo_i);
//        p4_cargo_il.setBounds(10,240,60,70);
        p4_cargo_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_cargo_il);
        
        p4_cargo=new JLabel("Cargo");
//        p4_cargo.setBounds(70, 240, 346, 70);
        p4_cargo.setFont(new Font("Arial", Font.BOLD, 25));
        p4_cargo.setForeground(Color.RED);
//        nav.add(p4_cargo);
        
        box1 = createHorizontalBox( );
        box1.add( createGlue( ) ); 
        box1.add(p4_cargo_il); 
        box1.add( createHorizontalStrut( 10 ) ); 
        box1.add( p4_cargo);
        box1.add( createGlue( ) ); 
        box1.setBounds(10, 225, 406, 70);
        box1.setBackground(Color.orange);
        box1.setOpaque(true);
        box1.add( createRigidArea( new Dimension( 260, 10 ) ) );
        box1.setBorder(b);
        box1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box1.addMouseListener(this);
        nav.add(box1);
        
//        avalaibilty

        p4_avail_i =new ImageIcon("src\\images\\availability.png");
        p4_avail_il=new JLabel(p4_avail_i);
//        p4_avail_il.setBounds(10,310,60,70);
        p4_avail_il.setBackground(Color.orange);
        p4_avail_il.setOpaque(true);
        p4_avail_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_avail_il);
        
        p4_avail=new JLabel("Availability");
//        p4_avail.setBounds(70, 310, 346, 70);
        p4_avail.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_avail);
        
        box2 = createHorizontalBox( );
        box2.add( createGlue() ); 
        box2.add( p4_avail_il ); 
        box2.add( createHorizontalStrut( 10 ) ); 
        box2.add( p4_avail );
        box2.add( createGlue( ) ); 
        box2.setBounds(10, 295, 406, 70);
        box2.setBackground(Color.orange);
        box2.setOpaque(true);
        box2.add( createRigidArea( new Dimension( 205, 10 ) ) );
        box2.setBorder(b);
        box2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box2.addMouseListener(this);
        nav.add(box2);
//        Tranansaction

        p4_trans_i =new ImageIcon("src\\images\\transaction.png");
        p4_trans_il=new JLabel(p4_trans_i);
//        p4_trans_il.setBounds(10,380,60,70);
        
//        nav.add(p4_trans_il);
        
        p4_trans=new JLabel("Transaction");
//        p4_trans.setBounds(70, 380, 346, 70);
        p4_trans.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_trans);
        
        box3 = createHorizontalBox( );
        box3.add( createGlue( ) ); 
        box3.add(p4_trans_il); 
        box3.add( createHorizontalStrut( 10 ) ); 
        box3.add(p4_trans );
        box3.add( createGlue( ) ); 
        box3.setBounds(10, 365, 406, 70);
        box3.setBackground(Color.orange);
        box3.setOpaque(true);
        box3.add( createRigidArea( new Dimension( 195, 10 ) ) );
        box3.setBorder(b);
        box3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box3.addMouseListener(this);
        nav.add(box3);
        
//        billing
        p4_bill_i =new ImageIcon("src\\images\\billing.png");
        p4_bill_il=new JLabel(p4_bill_i);
//        p4_bill_il.setBounds(10,450,60,70);
        p4_bill_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_bill_il);
        
        p4_bill=new JLabel("Billing");
//        p4_bill.setBounds(70, 450, 346, 70);
        p4_bill.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_bill);
        
        box4 = createHorizontalBox( );
        box4.add( createGlue( ) ); 
        box4.add(p4_bill_il); 
        box4.add( createHorizontalStrut( 10 ) ); 
        box4.add(p4_bill );
        box4.add( createGlue( ) ); 
        box4.setBounds(10, 435, 406, 70);
        box4.setBackground(Color.orange);
        box4.setOpaque(true);
        box4.add( createRigidArea( new Dimension( 255, 10 ) ) );
        box4.setBorder(b);
        box4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box4.addMouseListener(this);
        nav.add(box4);
        
        
        
//        live tracking

        p4_live_i=new ImageIcon("src\\images\\tracking.png");
        p4_live_il=new JLabel(p4_live_i);
        p4_live=new JLabel("Live Tracking");
        p4_live.setFont(new Font("Arial", Font.BOLD, 25));
        box5 = createHorizontalBox( );
        box5.add( createGlue( ) ); 
        box5.add(p4_live_il); 
        box5.add( createHorizontalStrut( 10 ) ); 
        box5.add(p4_live );
        box5.add( createGlue( ) ); 
        box5.setBounds(10, 505, 406, 70);
        box5.setBackground(Color.orange);
        box5.setOpaque(true);
        box5.add( createRigidArea( new Dimension( 170, 10 ) ) );
        box5.setBorder(b);
        box5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box5.addMouseListener(this);
        nav.add(box5);
        
        
//        Settings

        p4_set_il=new JLabel(new ImageIcon("src\\images\\settings.png"));
        p4_set_il.setHorizontalAlignment(JLabel.CENTER);
        p4_set=new JLabel("Settings");
        p4_set.setFont(new Font("Arial", Font.BOLD, 25));
        box6 = createHorizontalBox( );
        box6.add( createGlue( ) ); 
        box6.add(p4_set_il); 
        box6.add( createHorizontalStrut( 10 ) ); 
        box6.add(p4_set );
        box6.add( createGlue( ) ); 
        box6.setBounds(10, 575, 406, 70);
        box6.setBackground(Color.orange);
        box6.setOpaque(true);
        box6.add( createRigidArea( new Dimension( 235, 10 ) ) );
        box6.setBorder(b);
        box6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box6.addMouseListener(this);
        nav.add(box6);
        
        
//        logout
        p4_log_il=new JLabel(new ImageIcon("src\\images\\logout.png"));
        p4_log_il.setHorizontalAlignment(JLabel.CENTER);
        p4_log=new JLabel("Logout");
        p4_log.setFont(new Font("Arial", Font.BOLD, 25));
        box7 = createHorizontalBox( );
        box7.add( createGlue( ) ); 
        box7.add(p4_log_il); 
        box7.add( createHorizontalStrut( 10 ) ); 
        box7.add(p4_log );
        box7.add( createGlue( ) ); 
        box7.setBounds(10, 645, 406, 70);
        box7.setBackground(Color.orange);
        box7.setOpaque(true);
        box7.add( createRigidArea( new Dimension( 250, 10 ) ) );
        box7.setBorder(b);
        box7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box7.addMouseListener(this);
        
        nav.add(box7);

        
//        <--------------------------panel 5 verify email---------------------------------->

        verify=new JPanel();
        verify.setBounds(width/2, 0, width/2, height);
        verify.setLayout(null);
        verify.setBackground(new Color(233,239,248));
        co.add(verify);
            
            
//            title

        v_title=new JLabel("Cargo Logistics Management");
        v_title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        v_title.setBounds(15,70 , 600, 70);
        v_title.setHorizontalAlignment(JLabel.CENTER);
        verify.add(v_title);
        
        

        
        
//        verification title
        v_head=new JLabel("Email Verification");
        v_head.setFont(new java.awt.Font("Arial",Font.BOLD, 35));
        v_head.setBounds(15,160 , 600, 70);
        v_head.setHorizontalAlignment(JLabel.CENTER);
        verify.add(v_head);
        
                
//        otp label

        otp_l=new JLabel("OTP(One Time Password) *");
        otp_l.setFont(new java.awt.Font("Arial", 25, 20));
        otp_l.setBounds(55,290 , 300, 50);
        otp_l.setHorizontalAlignment(JLabel.CENTER);
        verify.add(otp_l);
        
        
//        otp textfiled

        votp=new JTextField();
        votp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        votp.setBorder(b);
        votp.setBackground(new Color(233,239,248));
        votp.setBounds(355, 290, 200, 35);
        votp.setFont(new java.awt.Font("Arial", 25, 20));
        votp.addKeyListener(this);
        verify.add(votp);
        
        
//        otp validate
        otp_validate=new JLabel("* Incorrect ,Please entered correct OTP");
        otp_validate.setFont(new java.awt.Font("Arial", 25, 25));
        otp_validate.setBounds(15,240 , 600, 25);
        otp_validate.setForeground(Color.red);
        otp_validate.setHorizontalAlignment(JLabel.CENTER);
        otp_validate.setVisible(false);
        verify.add(otp_validate);
        
        
        
//        verify button

        verifybtn=new JButton("VERIFY");
        verifybtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        verifybtn.setBackground(Color.GREEN);
        verifybtn.setForeground(Color.white);
        verifybtn.setBounds(80, 420, 200,50);
        verifybtn.setFont(new java.awt.Font("Arial", Font.BOLD, 25));
        verifybtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verifybtn.addActionListener(this);
        verify.add(verifybtn);
        
//        resend otp

        resendbtn=new JButton("RESEND OTP");
        resendbtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        resendbtn.setBackground(Color.RED);
        resendbtn.setForeground(Color.white);
        resendbtn.setBounds(360, 420, 200,50);
        resendbtn.setFont(new java.awt.Font("Arial", Font.BOLD, 25));
        resendbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resendbtn.addActionListener(this);
        verify.add(resendbtn);
        
        
//        <-------------------panel 6 cargo module------------------------->
        cargo=new JPanel();
        cargo.setBounds(width/3, 0, 854, height);
        cargo.setBackground(Color.BLUE);
        co.add(cargo);
        
      
        
    
            
//        main container
        signIn.setVisible(true);
        img_pane.setVisible(true);
        cargo.setVisible(false);
        login.setVisible(false);
        nav.setVisible(false);
        verify.setVisible(false);
        setSize(width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        passwordHash.Passwordhashing sc=new passwordHash.Passwordhashing();
        str=sc.decrypt("5FMaMNF3PuI=Y2hhbmRydQ==");
        
        System.err.println(str);
        if(ltpass.getText().equals(str)){
            System.err.println("correct");
        }
        
        
    }
   
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        new Demo();
         
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj_source=e.getSource();
        if(obj_source == lsubmit){
            img_pane.setVisible(false);
            signIn.setVisible(false);
            login.setVisible(false);
            nav.setVisible(true);
            
        }else if(obj_source==checkpass){
            JCheckBox c = (JCheckBox) e.getSource();
            tpass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        }else if(obj_source==lcheckpass){
            JCheckBox c = (JCheckBox) e.getSource();
            ltpass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        }else if(obj_source==verifybtn){
            String otp_e=votp.getText();
            int n=Integer.parseInt(otp_e);
            if(n==otp){
                
                nav.setVisible(true);
                
                img_pane.setVisible(false);
                verify.setVisible(false);
                login.setVisible(false);
                signIn.setVisible(false);
            }else{
                otp_validate.setVisible(true);
            }
        }else if(obj_source==create){
            
//                String uname= tuname.getText();
//                String phone=tmobile.getText();
//                String user=tuser.getText();
//                String email=temail.getText();
//                String pass=tpass.getText();
//                String address=tarea.getText();
                Random customer_rand=new Random();
                int c_id=customer_rand.nextInt(9999);
                passwordHash.Passwordhashing sc=new Passwordhashing();
                ob=new JBean();
                ob.setCustomer_name(tuname.getText());
                ob.setCustomer_phone(tmobile.getText());
                ob.setCustomer_uname(tuser.getText());
                ob.setCustomer_email(temail.getText());
                ob.setCustomer_password(sc.encrypt(tpass.getText()));
                ob.setCustomer_address(tarea.getText());
                ob.setCustomer_id(c_id);
                verify.setVisible(true);
                signIn.setVisible(false);
                p4_uname.setText(tuname.getText());
                final String username = "chandru6501@gmail.com";
		final String password = "Chandru@123";

                final String from = username;
                final String to = ob.getCustomer_email();
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
			message.setText("Your OTP for your Account Creation in CARGO LOGISTICS MANAGEMENT is "+otp);

			Transport.send(message);

			System.out.println("Done");
                        

		} catch (MessagingException ae) {
			System.out.println(ae);
		}
                System.out.println(ob.getCustomer_email());

        }
    }
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj_source=e.getSource();
        if(obj_source==sCreate){
                    login.setVisible(false);
                    signIn.setVisible(true);
                   
        }else if(obj_source==Login_btn){
            signIn.setVisible(false);
            login.setVisible(true);
        }else if(obj_source==box1 ){
            p4_cargo.setForeground(Color.red);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
        }else if(obj_source==box2 ){
            p4_cargo.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_avail.setForeground(Color.red);
        }else if(obj_source==box3 ){
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_trans.setForeground(Color.red);
        }else if(obj_source==box4 ){
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_bill.setForeground(Color.red);
        }else if(obj_source==box5 ){
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_live.setForeground(Color.red);
        }else if(obj_source==box6 ){
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_set.setForeground(Color.red);
        }else if(obj_source==box7 ){
            
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.red);
//            UIManager.put("OptionPane.minimumSize", new Dimension(300,300));
            UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,25));
            int LOG = JOptionPane.showConfirmDialog(this, "do you like to logout?","LOGOUT",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("src\\images\\logout.png"));
            System.err.println(LOG);
            if(LOG == JOptionPane.YES_OPTION){
                signIn.setVisible(false);
                img_pane.setVisible(true);
                cargo.setVisible(false);
                login.setVisible(true);
                nav.setVisible(false);
                verify.setVisible(false);
            }else if(LOG==JOptionPane.NO_OPTION){
                signIn.setVisible(false);
                img_pane.setVisible(false);
                cargo.setVisible(true);
                login.setVisible(false);
                nav.setVisible(true);
                verify.setVisible(false);
            }
            
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
            }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object obj_source=e.getSource();
        if(obj_source==create){
            create.setBackground(new Color(92, 112, 237));
        }else if(obj_source==lsubmit){
            lsubmit.setBackground(new Color(92, 112, 237));
        }else if(obj_source==box1 ){
            box1.setBackground(Color.yellow);
        }else if(obj_source==box2 ){
            box2.setBackground(Color.yellow);
        }else if(obj_source==box3 ){
            box3.setBackground(Color.yellow);
        }else if(obj_source==box4 ){
            box4.setBackground(Color.yellow);
        }else if(obj_source==box5 ){
            box5.setBackground(Color.yellow);
        }else if(obj_source==box6 ){
            box6.setBackground(Color.yellow);
        }else if(obj_source==box7 ){
            box7.setBackground(Color.yellow);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj_source=e.getSource();
        if(obj_source==create){
            create.setBackground(Color.BLUE);
        }else if(obj_source==lsubmit){
            lsubmit.setBackground(Color.BLUE);
        }else if(obj_source==box1 ){
            box1.setBackground(Color.orange);
        }else if(obj_source==box2 ){
            box2.setBackground(Color.orange);
        }else if(obj_source==box3 ){
            box3.setBackground(Color.orange);
        }else if(obj_source==box4 ){
            box4.setBackground(Color.orange);
        }else if(obj_source==box5 ){
            box5.setBackground(Color.orange);
        }else if(obj_source==box6 ){
            box6.setBackground(Color.orange);
        }else if(obj_source==box7 ){
            box7.setBackground(Color.orange);
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        Object obj_source=e.getSource();
        if(obj_source==tpass){
            String password_regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!Pattern.matches(password_regex,text)) {
                    pass_validate.setText("Requires 1 Uppercase,1 Symbol and 1 Number");
                    pass_validate.setForeground(Color.red);
                   pass_validate.setVisible(true);
                   tpass.setForeground(Color.red);
                    return;
                } else {
                    
                    pass_validate.setText("Entered strong password");
                    pass_validate.setForeground(Color.green);
                    tpass.setForeground(Color.black);
                } 
        }else if(obj_source==temail){
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
        }else if(obj_source==tmobile){
            JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!Pattern.matches("^[0-9]{10}", text)) {
                    mobile_validate.setVisible(true);
                   tmobile.setForeground(Color.red);
                    return;
                } else {
                    mobile_validate.setVisible(false);
                    tmobile.setForeground(Color.black);
                }
        }else if(obj_source==l_email){
            String emailregex =  "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!Pattern.matches(emailregex,text)) {
                   lvalid_email.setVisible(true);
                   l_email.setForeground(Color.red);
                    return;
                } else {
                    lvalid_email.setVisible(false);
                    l_email.setForeground(Color.black);
                } 
        }else if(obj_source==votp){
            JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                String otp1=Integer.toString(otp);
                System.out.println(text);
                if(text.length()==0){
                    otp_validate.setVisible(false);
                }
                else if (!Pattern.matches(otp1,text)) {
                   otp_validate.setVisible(true);
                   
                    return;
                } else {
                    otp_validate.setVisible(false);
                    
                } 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
}
