/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Bean.JBean;
import OTP.SendOtp;
import billing.Pdf;
import com.toedter.calendar.JDateChooser;
import database.DBOperations;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboPopup;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import passwordHash.Passwordhashing;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author chandru
 */
public class Demo extends JFrame implements ActionListener, ItemListener, MouseListener, KeyListener {

    Container co;
    JBean ob = new JBean();
    DBOperations dbo;
    Pdf bill;
    Border c = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    private static int otp;
    JPanel img_pane, login, signIn, nav, verify, availabilty, transaction, billing, settings, live_tracking, forget_pass;
    Border b = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
    JButton lsubmit, create, verifybtn, resendbtn;
    JLabel sCreate, Login_btn, Login_link, commentlabel, lTitle, lEmail_i, lemail, lvalid_email, lpass, lforget_password, address, pass_validate, pass, valid_email, email, user_validate, user, mobile_validate, Mobile, name, Title, address_i, pass_i, Email_i, used_id, mobile_i, User_img, lpass_i, p4_title, p4_uname, p4_uname_il, p4_uname_id, p4_cargo, p4_cargo_il, p4_avail, p4_avail_il, p4_trans_il, p4_trans, p4_bill, p4_bill_il, p4_live_il, p4_live, p4_set_il, p4_set, p4_log_il, p4_log, v_title, v_head, otp_l, otp_validate, check_email;
    ImageIcon image, lemail_img, lpass_img, address_img, pass_img, email_img, user_img, Mobile_img, uname_img, p4_uname_i, p4_cargo_i, p4_avail_i, p4_trans_i, p4_bill_i, p4_live_i;
    JTextField l_email, temail, tuser, tmobile, tuname, votp;
    JPasswordField ltpass, tpass;
    JCheckBox lcheckpass, checkpass;
    JTextArea tarea;
    Box box1, box2, box3, box4, box5, box6, box7;
    Color bg = new Color(233, 239, 248);
    Color nav_bg = new Color(207, 228, 255);
    Color nav_r = new Color(13, 61, 110);
    Color li = new Color(51, 153, 238);
    String str;

    //settings declaration
    JLabel settings_title, title_img;
    JLabel update, delete;
    JLabel uname_validate, spass_validate, smobile_validate;
    JLabel s_uname, s_pass, s_email, s_address, s_name, s_mobile, si_uname, si_pass, si_email, si_address, si_name, si_mobile;
    JTextField ts_uname, ts_email, ts_name, ts_mobile;
    JPasswordField ts_pass;
    JTextArea ts_address;
    JCheckBox eye;

//    availabilty declaration
    JLabel avail_title, avail_check, avail_btn;
    LocalDate u_date;
    JComboBox<String> from;
    JComboBox<String> to;
    JComboBox<String> mode;
    List<String> list1 = null;
    List<String> list2 = null;
    List<String> list3 = null;
    int days;

    JDateChooser dateChooser;
    JTextField WEIGHT;
    String SFrom, STo, SMode;

//    forget password declaration
    JLabel forget_title, f_email, f_otp, f_pass, f_otp_btn, f_change, f_email_valid, f_pass_valid;
    JTextField forget_email, forget_otp;
    JCheckBox feye;
    JPasswordField forget_password;
    JLabel forget_back;

//    transaction panel declaration
    JLabel t_label;
    DefaultTableModel model;
    JTable table;
    String[] columnNames = {"TRANSACTION ID", "TRANSPORT ID", "BILLING ID", "AMOUNT", "DATE", "PAYMENT"};

//    billing panel declaration
    DefaultTableModel b_model;
    JTable b_table;
    String[] b_columnNames = {"BILLING ID", "TRANSACTION ID", "NAME", "EMAIL"};

//    cargo panel decalartion
    JPanel cargo;
    JLabel cargo_title, cargo_p1, cargo_p2, cargo_p3, cargo_p4, cargo_flow, cargo_next;

//    cargo_information
    JPanel cargo_information;
    JLabel cargo_info_title, cargo_ship_d, cargo_d, cargo_gross, cargo_back, cargo_finish;
    JTextField cargo_quantity, cargo_gross_wt;
    JDateChooser cargo_date;
    JComboBox<String> commodity;
    JComboBox<String> container_type;
    JComboBox<String> cargo_gross_mt;
    JComboBox<String> cargo_from;
    JComboBox<String> cargo_to;
    JComboBox<String> cargo_mode;
    List<String> list4 = null;
    List<String> list5 = null;
    List<String> list6 = null;

//    payment panel
    JPanel payment;
    JLabel payment_title, pay_cus_id, pay_cus_l, transport_l, transport_id, pay_amt, pay_amt_l, pay_date_l, pay_date, paymethod_l, payment_box;
    JLabel card_no_valid, card_conform_btn;
    JComboBox<String> PAYMETHOD;
    JTextField card_no, card_month, card_holder, card_email;
    JPasswordField card_ccv;

//    payment verify panel
    JPanel payment_verify;
    JLabel payment_verify_box, payment_verify_label, payment_verify_btn;
    JTextField payment_verify_otp;

//    random id generation
    Random customer_rand = new Random();
    int transport_id_rand, billing_id_rand, transaction_id_rand;
    double pay_amounts;
    int number;

    Connection con = Dbconnection.Db_connection.getConnection();
    Passwordhashing sc = new Passwordhashing();

    public Demo() throws NoSuchAlgorithmException, NoSuchProviderException, ParseException {
        co = getContentPane();
        co.setLayout(null);
        int width = 1280;
        int height = 768;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CARGO LOGISTICS MANAGEMENT SYSTEM");
        ImageIcon ima = new ImageIcon("C:\\Users\\chandru\\Documents\\NetBeansProjects\\Awt\\src\\logo.png");
        setIconImage(ima.getImage());
//        <-------------jpanel 1--------------->
        img_pane = new JPanel();
        img_pane.setSize(width / 2, height);
        img_pane.setLayout(null);
//        <------------jpanel 1 img------------>
        image = new ImageIcon(getClass().getClassLoader().getResource("BOOTCARGO.png"));
        commentlabel = new JLabel(image);
        commentlabel.setSize(width / 2, height);
        img_pane.add(commentlabel);
        co.add(img_pane);

//        <-------------jpanel 2---------------->
        login = new JPanel();
        login.setBounds(width / 2, 0, width / 2, height);
        login.setBackground(bg);
        login.setLayout(null);
//        <-----------jpanel 2 title------------>
        lTitle = new JLabel("Cargo Logistics Management");
        lTitle.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        lTitle.setBounds(20, 70, 600, 70);
        lTitle.setHorizontalAlignment(JLabel.CENTER);
        login.add(lTitle);
//       <------- jpanel 2 name section ------>
        lemail_img = new ImageIcon(getClass().getClassLoader().getResource("email.png"));
        lEmail_i = new JLabel(lemail_img);
        lEmail_i.setBounds(20, 200, 100, 50);
        lEmail_i.setHorizontalAlignment(JLabel.CENTER);
        lEmail_i.setVerticalAlignment(JLabel.CENTER);
        login.add(lEmail_i);
//        <----------email label--------->
        lemail = new JLabel("Email");
        lemail.setBounds(100, 200, 150, 50);
        lemail.setFont(new java.awt.Font("Arial", 25, 25));
        login.add(lemail);
//        <---------email textfield-------->
        l_email = new JTextField();
        l_email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        l_email.setBorder(b);
        l_email.setBackground(bg);
        l_email.setBounds(240, 205, 300, 35);
        l_email.setFont(new java.awt.Font("Arial", 25, 20));
        l_email.addKeyListener(this);
        login.add(l_email);
//       <------- invalid email--------->
        lvalid_email = new JLabel("* Invalid Email Address");
        lvalid_email.setBounds(240, 235, 300, 35);
        lvalid_email.setForeground(Color.red);
        lvalid_email.setVisible(false);
        login.add(lvalid_email);

//       <--------- password section--------->
        lpass_img = new ImageIcon(getClass().getClassLoader().getResource("password.png"));
        lpass_i = new JLabel(lpass_img);
        lpass_i.setBounds(20, 300, 100, 50);
        lpass_i.setHorizontalAlignment(JLabel.CENTER);
        lpass_i.setVerticalAlignment(JLabel.CENTER);
        login.add(lpass_i);
//       <--------password lable---------->
        lpass = new JLabel("Password");
        lpass.setBounds(100, 300, 150, 50);
        lpass.setFont(new java.awt.Font("Arial", 25, 25));
        login.add(lpass);
//        <---------password field------>
        ltpass = new JPasswordField();
        ltpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ltpass.setBorder(b);
        ltpass.setBackground(bg);
        ltpass.setForeground(Color.black);
        ltpass.setBounds(240, 305, 300, 35);
        ltpass.setFont(new java.awt.Font("Arial", 25, 20));

        login.add(ltpass);
//        <---------chack password---------->
        lcheckpass = new JCheckBox();
        lcheckpass.setIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeclose.png")));
        lcheckpass.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeopen.png")));
        lcheckpass.addActionListener(this);
        lcheckpass.setBounds(550, 305, 50, 25);
        lcheckpass.setFont(new java.awt.Font("Arial", 5, 15));
        lcheckpass.setBackground(bg);
        login.add(lcheckpass);
//        <-------------Submit button--------->
        lsubmit = new JButton("LOGIN");
        lsubmit.setBounds(302, 400, 175, 35);
        lsubmit.setFont(new java.awt.Font("Arial", Font.BOLD, 15));
        lsubmit.setForeground(Color.white);
        lsubmit.setBackground(Color.blue);
        lsubmit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        lsubmit.setHorizontalAlignment(JLabel.CENTER);
        lsubmit.setVerticalAlignment(JLabel.CENTER);
        lsubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lsubmit.addActionListener(this);
        lsubmit.addMouseListener(this);

        login.add(lsubmit);
//      <---------------Forget password-------------->
        lforget_password = new JLabel("Forgot Password ?");
        lforget_password.setBounds(240, 450, 300, 35);
        lforget_password.setForeground(Color.BLUE.darker());
        lforget_password.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lforget_password.setFont(new java.awt.Font("Arial", 25, 15));
        lforget_password.setHorizontalAlignment(JLabel.CENTER);
        lforget_password.setVerticalAlignment(JLabel.CENTER);
        lforget_password.addMouseListener(this);
        login.add(lforget_password);
//      <-----------create account---------->
        sCreate = new JLabel("Create New Account");
        sCreate.setBounds(220, 570, 220, 45);
        sCreate.setForeground(Color.white);
        sCreate.setBackground(new Color(66, 183, 42));
        sCreate.setOpaque(true);
        sCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sCreate.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        sCreate.setHorizontalAlignment(JLabel.CENTER);
        sCreate.setVerticalAlignment(JLabel.CENTER);
        sCreate.addMouseListener(this);
        login.add(sCreate);
        co.add(login);

//<------------------------------------------------------jpanel 3--------------------------------------------------------->
        signIn = new JPanel();
        signIn.setBounds(width / 2, 0, width / 2, height);
        signIn.setBackground(new Color(233, 239, 248));
        signIn.setLayout(null);
//        jpanel 2 title
        Title = new JLabel("Cargo Logistics Management");
        Title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        Title.setBounds(20, 20, 600, 70);
        Title.setHorizontalAlignment(JLabel.CENTER);
        signIn.add(Title);
//        jpanel 2 name section
        uname_img = new ImageIcon(getClass().getClassLoader().getResource("user.png"));
        User_img = new JLabel(uname_img);
        User_img.setBounds(50, 120, 50, 50);
        User_img.setHorizontalAlignment(JLabel.CENTER);
        User_img.setVerticalAlignment(JLabel.CENTER);
        signIn.add(User_img);

        name = new JLabel("Name");
        name.setBounds(120, 120, 150, 50);
        name.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(name);

        tuname = new JTextField();
        tuname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tuname.setBorder(b);
        tuname.setBackground(new Color(233, 239, 248));
        tuname.setBounds(290, 125, 280, 35);
        tuname.setFont(new java.awt.Font("Arial", 25, 18));
        signIn.add(tuname);

        Mobile_img = new ImageIcon(getClass().getClassLoader().getResource("phone.png"));
        mobile_i = new JLabel(Mobile_img);
        mobile_i.setBounds(50, 180, 50, 50);
        mobile_i.setHorizontalAlignment(JLabel.CENTER);
        mobile_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(mobile_i);

        Mobile = new JLabel("Mobile Number");
        Mobile.setBounds(120, 180, 150, 50);
        Mobile.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(Mobile);

        tmobile = new JTextField();
        tmobile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tmobile.setBorder(b);
        tmobile.setBackground(new Color(233, 239, 248));
        tmobile.setBounds(290, 185, 280, 35);
        tmobile.setFont(new java.awt.Font("Arial", 25, 18));

        mobile_validate = new JLabel("* invalid phone number");
        mobile_validate.setBounds(290, 220, 280, 20);
        mobile_validate.setForeground(Color.red);
        mobile_validate.setVisible(false);
        signIn.add(mobile_validate);
        tmobile.addKeyListener(this);
        signIn.add(tmobile);

        user_img = new ImageIcon(getClass().getClassLoader().getResource("user_id.png"));
        used_id = new JLabel(user_img);
        used_id.setBounds(50, 250, 50, 50);
        used_id.setHorizontalAlignment(JLabel.CENTER);
        used_id.setVerticalAlignment(JLabel.CENTER);
        signIn.add(used_id);

        user = new JLabel("User Name");
        user.setBounds(120, 250, 150, 50);
        user.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(user);

        tuser = new JTextField();
        tuser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tuser.setBorder(b);
        tuser.setBackground(new Color(233, 239, 248));
        tuser.setBounds(290, 250, 280, 35);
        tuser.addKeyListener(this);
        tuser.setFont(new java.awt.Font("Arial", 25, 18));

        user_validate = new JLabel("* User name already exist");
        user_validate.setBounds(290, 290, 280, 20);
        user_validate.setForeground(Color.red);
        user_validate.setVisible(false);
        signIn.add(user_validate);
        signIn.add(tuser);

        email_img = new ImageIcon(getClass().getClassLoader().getResource("email.png"));
        Email_i = new JLabel(email_img);
        Email_i.setBounds(50, 320, 50, 50);
        Email_i.setHorizontalAlignment(JLabel.CENTER);
        Email_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(Email_i);

        email = new JLabel("Email");
        email.setBounds(120, 320, 150, 50);
        email.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(email);

        temail = new JTextField();
        temail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        temail.setBorder(b);
        temail.setBackground(new Color(233, 239, 248));
        temail.addKeyListener(this);
        temail.setBounds(290, 320, 280, 35);
        temail.setFont(new java.awt.Font("Arial", 25, 18));
//        invalid email
        valid_email = new JLabel("* Invalid Email Address");
        valid_email.setBounds(290, 355, 280, 35);
        valid_email.setForeground(Color.red);
        valid_email.setVisible(false);
        signIn.add(valid_email);
        signIn.add(temail);

        check_email = new JLabel("* Invalid Email Address");
        check_email.setBounds(290, 355, 280, 35);
        check_email.setForeground(Color.red);
        check_email.setVisible(false);
        signIn.add(check_email);
        signIn.add(temail);

//        password section
        pass_img = new ImageIcon(getClass().getClassLoader().getResource("password.png"));
        pass_i = new JLabel(pass_img);
        pass_i.setBounds(50, 380, 50, 50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(pass_i);

        pass = new JLabel("Password");
        pass.setBounds(120, 380, 150, 50);
        pass.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(pass);

        tpass = new JPasswordField();
        tpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tpass.setBorder(b);
        tpass.setBackground(new Color(233, 239, 248));
        tpass.setForeground(Color.black);
        tpass.setBounds(290, 395, 280, 20);
        tpass.setFont(new java.awt.Font("Arial", 25, 18));
        tpass.addKeyListener(this);
        signIn.add(tpass);
        pass_validate = new JLabel("Enter a weak Password");
        pass_validate.setBounds(290, 420, 280, 20);
        pass_validate.setForeground(Color.red);
        pass_validate.setVisible(false);
        signIn.add(pass_validate);

        checkpass = new JCheckBox();
        checkpass.setIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeclose.png")));
        checkpass.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeopen.png")));
        checkpass.addActionListener(this);
        checkpass.setBounds(575, 385, 50, 25);
        checkpass.setFont(new java.awt.Font("Arial", 5, 15));
        checkpass.setBackground(new Color(233, 239, 248));
        signIn.add(checkpass);

//        address
        address_img = new ImageIcon(getClass().getClassLoader().getResource("address.png"));
        address_i = new JLabel(address_img);
        address_i.setBounds(50, 450, 50, 50);
        address_i.setHorizontalAlignment(JLabel.CENTER);
        address_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(address_i);

        address = new JLabel("Address");
        address.setBounds(120, 450, 150, 50);
        address.setFont(new java.awt.Font("Arial", 25, 20));
        signIn.add(address);

        tarea = new JTextArea();
        JScrollPane tareas = new JScrollPane(tarea);
        tarea.setLineWrap(true);
        tareas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tareas.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b4 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
        tareas.setBorder(b4);
        tarea.setBackground(new Color(233, 239, 248));
        tarea.setForeground(Color.black);
        tareas.setBounds(290, 465, 280, 130);
        tareas.setFont(new java.awt.Font("Arial", 25, 18));
        tareas.setVisible(true);
        signIn.add(tareas);

//        create button
        create = new JButton("Create");
        create.setBounds(290, 620, 175, 35);
        create.setFont(new java.awt.Font("Arial", Font.BOLD, 15));
        create.setForeground(Color.white);
        create.setBackground(Color.blue);
        create.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        create.setHorizontalAlignment(JLabel.CENTER);
        create.setVerticalAlignment(JLabel.CENTER);
        create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        create.addMouseListener(this);
        create.addActionListener(this);
        signIn.add(create);

        Login_link = new JLabel("Already have an account?");
        Login_link.setBounds(290, 670, 150, 30);
        Login_link.setForeground(Color.BLUE.darker());
        signIn.add(Login_link);

        Login_btn = new JLabel("Log in");
        Login_btn.setBounds(440, 670, 150, 30);
        Login_btn.setForeground(Color.red);
        Login_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Login_btn.addMouseListener(this);
        signIn.add(Login_btn);

        co.add(signIn);

//        <----------------------panel 4 for navbar------------------>
        nav = new JPanel();
        nav.setSize(width / 3, height);
        nav.setBackground(nav_bg);
        nav.setLayout(null);
        co.add(nav);
        System.err.println(width / 3);

//        panel 4 title
        p4_title = new JLabel("Cargo Logistics Management");
        p4_title.setBounds(10, 20, 406, 90);
        p4_title.setHorizontalAlignment(JLabel.CENTER);
        p4_title.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 32));
        p4_title.setBackground(nav_bg);
        p4_title.setForeground(new Color(13, 61, 110));
        p4_title.setOpaque(true);
        nav.add(p4_title);

//        panel 4 customer name
        p4_uname_i = new ImageIcon(getClass().getClassLoader().getResource("user.png"));
        p4_uname_il = new JLabel(p4_uname_i);
        p4_uname_il.setBounds(10, 130, 60, 70);
        p4_uname_il.setBackground(new Color(51, 153, 238));
        p4_uname_il.setOpaque(true);
        p4_uname_il.setHorizontalAlignment(JLabel.CENTER);
        nav.add(p4_uname_il);

        p4_uname = new JLabel(ob.getCustomer_uname());
        p4_uname.setBounds(70, 130, 206, 70);
//        p4_uname.setHorizontalAlignment(JLabel.CENTER);
        p4_uname.setFont(new Font("Arial", Font.BOLD, 25));
        p4_uname.setBackground(li);
        p4_uname.setOpaque(true);
        nav.add(p4_uname);

        p4_uname_id = new JLabel("#" + Integer.toString(ob.getCustomer_id()));
        p4_uname_id.setBounds(276, 130, 140, 70);
        p4_uname_id.setHorizontalAlignment(JLabel.CENTER);
        p4_uname_id.setFont(new Font("Arial", Font.BOLD, 20));
        p4_uname_id.setBackground(li);
        p4_uname_id.setOpaque(true);
        nav.add(p4_uname_id);
//        cargo

        p4_cargo_i = new ImageIcon(getClass().getClassLoader().getResource("cargo.png"));
        p4_cargo_il = new JLabel(p4_cargo_i);
//        p4_cargo_il.setBounds(10,240,60,70);
        p4_cargo_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_cargo_il);

        p4_cargo = new JLabel("Cargo");
//        p4_cargo.setBounds(70, 240, 346, 70);
        p4_cargo.setFont(new Font("Arial", Font.BOLD, 25));
        p4_cargo.setForeground(nav_r);
//        nav.add(p4_cargo);

        box1 = createHorizontalBox();
        box1.add(createGlue());
        box1.add(p4_cargo_il);
        box1.add(createHorizontalStrut(10));
        box1.add(p4_cargo);
        box1.add(createGlue());
        box1.setBounds(10, 225, 406, 70);
        box1.setBackground(li);
        box1.setOpaque(true);
        box1.add(createRigidArea(new Dimension(260, 10)));
        box1.setBorder(b);
        box1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box1.addMouseListener(this);
        nav.add(box1);

//        avalaibilty
        p4_avail_i = new ImageIcon(getClass().getClassLoader().getResource("availability.png"));
        p4_avail_il = new JLabel(p4_avail_i);
//        p4_avail_il.setBounds(10,310,60,70);
        p4_avail_il.setBackground(Color.orange);
        p4_avail_il.setOpaque(true);
        p4_avail_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_avail_il);

        p4_avail = new JLabel("Availability");
//        p4_avail.setBounds(70, 310, 346, 70);
        p4_avail.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_avail);

        box2 = createHorizontalBox();
        box2.add(createGlue());
        box2.add(p4_avail_il);
        box2.add(createHorizontalStrut(10));
        box2.add(p4_avail);
        box2.add(createGlue());
        box2.setBounds(10, 295, 406, 70);
        box2.setBackground(li);
        box2.setOpaque(true);
        box2.add(createRigidArea(new Dimension(205, 10)));
        box2.setBorder(b);
        box2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box2.addMouseListener(this);
        nav.add(box2);
//        Tranansaction

        p4_trans_i = new ImageIcon(getClass().getClassLoader().getResource("transaction.png"));
        p4_trans_il = new JLabel(p4_trans_i);
//        p4_trans_il.setBounds(10,380,60,70);

//        nav.add(p4_trans_il);
        p4_trans = new JLabel("Transaction");
//        p4_trans.setBounds(70, 380, 346, 70);
        p4_trans.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_trans);

        box3 = createHorizontalBox();
        box3.add(createGlue());
        box3.add(p4_trans_il);
        box3.add(createHorizontalStrut(10));
        box3.add(p4_trans);
        box3.add(createGlue());
        box3.setBounds(10, 365, 406, 70);
        box3.setBackground(li);
        box3.setOpaque(true);
        box3.add(createRigidArea(new Dimension(195, 10)));
        box3.setBorder(b);
        box3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box3.addMouseListener(this);
        nav.add(box3);

//        billing
        p4_bill_i = new ImageIcon(getClass().getClassLoader().getResource("billing.png"));
        p4_bill_il = new JLabel(p4_bill_i);
//        p4_bill_il.setBounds(10,450,60,70);
        p4_bill_il.setHorizontalAlignment(JLabel.CENTER);
//        nav.add(p4_bill_il);

        p4_bill = new JLabel("Billing");
//        p4_bill.setBounds(70, 450, 346, 70);
        p4_bill.setFont(new Font("Arial", Font.BOLD, 25));
//        nav.add(p4_bill);

        box4 = createHorizontalBox();
        box4.add(createGlue());
        box4.add(p4_bill_il);
        box4.add(createHorizontalStrut(10));
        box4.add(p4_bill);
        box4.add(createGlue());
        box4.setBounds(10, 435, 406, 70);
        box4.setBackground(li);
        box4.setOpaque(true);
        box4.add(createRigidArea(new Dimension(255, 10)));
        box4.setBorder(b);
        box4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box4.addMouseListener(this);
        nav.add(box4);

//        live tracking
        p4_live_i = new ImageIcon(getClass().getClassLoader().getResource("tracking.png"));
        p4_live_il = new JLabel(p4_live_i);
        p4_live = new JLabel("Live Tracking");
        p4_live.setFont(new Font("Arial", Font.BOLD, 25));
        box5 = createHorizontalBox();
        box5.add(createGlue());
        box5.add(p4_live_il);
        box5.add(createHorizontalStrut(10));
        box5.add(p4_live);
        box5.add(createGlue());
        box5.setBounds(10, 505, 406, 70);
        box5.setBackground(li);
        box5.setOpaque(true);
        box5.add(createRigidArea(new Dimension(170, 10)));
        box5.setBorder(b);
        box5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box5.addMouseListener(this);
        nav.add(box5);

//        Settings
        p4_set_il = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("settings.png")));
        p4_set_il.setHorizontalAlignment(JLabel.CENTER);
        p4_set = new JLabel("Settings");
        p4_set.setFont(new Font("Arial", Font.BOLD, 25));
        box6 = createHorizontalBox();
        box6.add(createGlue());
        box6.add(p4_set_il);
        box6.add(createHorizontalStrut(10));
        box6.add(p4_set);
        box6.add(createGlue());
        box6.setBounds(10, 575, 406, 70);
        box6.setBackground(li);
        box6.setOpaque(true);
        box6.add(createRigidArea(new Dimension(235, 10)));
        box6.setBorder(b);
        box6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box6.addMouseListener(this);
        nav.add(box6);

//        logout
        p4_log_il = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logout.png")));
        p4_log_il.setHorizontalAlignment(JLabel.CENTER);
        p4_log = new JLabel("Logout");
        p4_log.setFont(new Font("Arial", Font.BOLD, 25));
        box7 = createHorizontalBox();
        box7.add(createGlue());
        box7.add(p4_log_il);
        box7.add(createHorizontalStrut(10));
        box7.add(p4_log);
        box7.add(createGlue());
        box7.setBounds(10, 645, 406, 70);
        box7.setBackground(li);
        box7.setOpaque(true);
        box7.add(createRigidArea(new Dimension(250, 10)));
        box7.setBorder(b);
        box7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box7.addMouseListener(this);

        nav.add(box7);

//        <--------------------------panel 5 verify email---------------------------------->
        verify = new JPanel();
        verify.setBounds(width / 2, 0, width / 2, height);
        verify.setLayout(null);
        verify.setBackground(new Color(233, 239, 248));
        co.add(verify);

//            title
        v_title = new JLabel("Cargo Logistics Management");
        v_title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        v_title.setBounds(15, 70, 600, 70);
        v_title.setHorizontalAlignment(JLabel.CENTER);
        verify.add(v_title);

//        verification title
        v_head = new JLabel("Email Verification");
        v_head.setFont(new java.awt.Font("Arial", Font.BOLD, 35));
        v_head.setBounds(15, 160, 600, 70);
        v_head.setHorizontalAlignment(JLabel.CENTER);
        verify.add(v_head);

//        otp label
        otp_l = new JLabel("OTP(One Time Password) *");
        otp_l.setFont(new java.awt.Font("Arial", 25, 20));
        otp_l.setBounds(55, 290, 300, 50);
        otp_l.setHorizontalAlignment(JLabel.CENTER);
        verify.add(otp_l);

//        otp textfiled
        votp = new JTextField();
        votp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        votp.setBorder(b);
        votp.setBackground(new Color(233, 239, 248));
        votp.setBounds(355, 290, 200, 35);
        votp.setFont(new java.awt.Font("Arial", 25, 20));
        votp.addKeyListener(this);
        verify.add(votp);

//        otp validate
//        verify button
        verifybtn = new JButton("VERIFY");
        verifybtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        verifybtn.setBackground(Color.GREEN);
        verifybtn.setForeground(Color.white);
        verifybtn.setBounds(80, 420, 200, 50);
        verifybtn.setFont(new java.awt.Font("Arial", Font.BOLD, 25));
        verifybtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verifybtn.addActionListener(this);
        verify.add(verifybtn);

//        resend otp
        resendbtn = new JButton("RESEND OTP");
        resendbtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        resendbtn.setBackground(Color.RED);
        resendbtn.setForeground(Color.white);
        resendbtn.setBounds(360, 420, 200, 50);
        resendbtn.setFont(new java.awt.Font("Arial", Font.BOLD, 25));
        resendbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resendbtn.addActionListener(this);
        verify.add(resendbtn);

//        <-----------------panel7 customer update,delete ---------------->
        settings = new JPanel();
        settings.setBounds(width / 3, 0, 854, height);
        settings.setLayout(null);
        settings.setBackground(bg);

        title_img = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("settings.png")));
        title_img.setBounds(10, 20, 50, 50);
        settings.add(title_img);

        settings_title = new JLabel("SETTINGS");
        settings_title.setBounds(70, 20, 200, 50);
        settings_title.setForeground(nav_r);
        settings_title.setFont(new Font("Arial", Font.BOLD, 35));
        settings.add(settings_title);

        si_uname = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("user_id.png")));
        si_uname.setBounds(10, 100, 50, 50);
        settings.add(si_uname);

        s_uname = new JLabel("USERNAME");
        s_uname.setBounds(70, 100, 200, 50);
        s_uname.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_uname);

        ts_uname = new JTextField(ob.getCustomer_uname());
        ts_uname.setBounds(280, 100, 300, 45);
        ts_uname.setBorder(BorderFactory.createEmptyBorder());
        ts_uname.setFont(new Font("arial", Font.BOLD, 20));
        ts_uname.setBackground(new Color(233, 239, 248));
        ts_uname.setBorder(b);
        ts_uname.setEnabled(true);
        settings.add(ts_uname);

        uname_validate = new JLabel("* user name already exist");
        uname_validate.setBounds(280, 145, 300, 20);
        uname_validate.setForeground(Color.red);
        uname_validate.setVisible(false);
        settings.add(uname_validate);

        si_name = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("user.png")));
        si_name.setBounds(10, 170, 50, 50);
        settings.add(si_name);

        s_name = new JLabel("NAME");
        s_name.setBounds(70, 170, 200, 50);
        s_name.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_name);

        ts_name = new JTextField();
        ts_name.setBounds(280, 170, 300, 45);
        ts_name.setBorder(BorderFactory.createEmptyBorder());
        ts_name.setFont(new Font("arial", Font.BOLD, 20));
        ts_name.setBackground(new Color(233, 239, 248));
        ts_name.setBorder(b);
        ts_name.setEnabled(true);
        settings.add(ts_name);

        si_email = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("email.png")));
        si_email.setBounds(10, 230, 50, 50);
        settings.add(si_email);

        s_email = new JLabel("EMAIL");
        s_email.setBounds(70, 240, 200, 50);
        s_email.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_email);

        ts_email = new JTextField(ob.getCustomer_email());
        ts_email.setBounds(280, 230, 300, 45);
        ts_email.setBorder(BorderFactory.createEmptyBorder());
        ts_email.setFont(new Font("arial", Font.BOLD, 20));
        ts_email.setBackground(new Color(233, 239, 248));
        ts_email.setBorder(b);
        ts_email.setEnabled(true);
        settings.add(ts_email);

        si_pass = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("password.png")));
        si_pass.setBounds(10, 300, 50, 50);
        settings.add(si_pass);

        s_pass = new JLabel("PASSWORD");
        s_pass.setBounds(70, 300, 200, 50);
        s_pass.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_pass);

        ts_pass = new JPasswordField();
        ts_pass.setBounds(280, 300, 300, 45);
        ts_pass.setBorder(BorderFactory.createEmptyBorder());
        ts_pass.setFont(new Font("arial", Font.BOLD, 20));
        ts_pass.setBackground(new Color(233, 239, 248));
        ts_pass.setBorder(b);
        ts_pass.setEnabled(true);
        ts_pass.addKeyListener(this);
        settings.add(ts_pass);

        eye = new JCheckBox();
        eye.setIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeclose.png")));
        eye.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeopen.png")));
        eye.setBounds(590, 300, 50, 45);
        eye.setBackground(bg);
        eye.addActionListener(this);
        settings.add(eye);

        spass_validate = new JLabel();
        spass_validate.setBounds(280, 350, 300, 25);
        settings.add(spass_validate);

        si_mobile = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("phone.png")));
        si_mobile.setBounds(10, 370, 50, 50);
        settings.add(si_mobile);

        s_mobile = new JLabel("PHONE");
        s_mobile.setBounds(70, 370, 200, 50);
        s_mobile.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_mobile);

        ts_mobile = new JTextField(ob.getCustomer_phone());
        ts_mobile.setBounds(280, 370, 300, 45);
        ts_mobile.setBorder(BorderFactory.createEmptyBorder());
        ts_mobile.setFont(new Font("arial", Font.BOLD, 20));
        ts_mobile.setBackground(new Color(233, 239, 248));
        ts_mobile.setBorder(b);
        ts_mobile.setEnabled(true);
        ts_mobile.addKeyListener(this);
        settings.add(ts_mobile);

        smobile_validate = new JLabel("* enter invalid number");
        smobile_validate.setBounds(280, 415, 300, 25);
        smobile_validate.setVisible(false);
        settings.add(smobile_validate);

        si_address = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("address.png")));
        si_address.setBounds(10, 455, 50, 50);
        settings.add(si_address);

        s_address = new JLabel("ADDRESS");
        s_address.setBounds(70, 455, 200, 50);
        s_address.setFont(new Font("Arial", Font.BOLD, 20));
        settings.add(s_address);

        ts_address = new JTextArea(ob.getCustomer_address());
        JScrollPane tareas1 = new JScrollPane(ts_address);
        ts_address.setLineWrap(true);
        tareas1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tareas1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // Border b4 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
        tareas1.setBorder(b4);
        ts_address.setBackground(new Color(233, 239, 248));
        ts_address.setForeground(Color.black);
        tareas1.setBounds(280, 455, 300, 130);
        tareas1.setFont(new java.awt.Font("Arial", 25, 18));
        tareas1.setVisible(true);
        settings.add(tareas1);

        update = new JLabel("UPDATE");
        update.setBounds(280, 600, 300, 55);
        update.setBackground(Color.GREEN);
        update.setOpaque(true);
        update.setFont(new Font("arial", Font.BOLD, 20));
        update.setHorizontalAlignment(JLabel.CENTER);
        update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        update.addMouseListener(this);
        update.setForeground(Color.WHITE);
        settings.add(update);

        delete = new JLabel("DELETE ACCOUNT");
        delete.setBounds(620, 20, 200, 55);
        delete.setBackground(Color.red);
        delete.setOpaque(true);
        delete.setFont(new Font("arial", Font.BOLD, 15));
        delete.setHorizontalAlignment(JLabel.CENTER);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addMouseListener(this);
        delete.setForeground(Color.WHITE);
        settings.add(delete);
        co.add(settings);

//        availabilty panel
        availabilty = new JPanel();
        availabilty.setBounds(width / 3, 0, 854, height);
        availabilty.setLayout(null);
        availabilty.setBackground(bg);
        co.add(availabilty);

        avail_title = new JLabel("AVAILABILITY");
        avail_title.setBounds(277, 70, 300, 50);
        avail_title.setHorizontalAlignment(JLabel.CENTER);
        avail_title.setForeground(nav_r);
        avail_title.setFont(new Font("arial", Font.BOLD, 40));
        availabilty.add(avail_title);

        dbo = new DBOperations();
        from = new JComboBox<>();
        to = new JComboBox<>();
        mode = new JComboBox<>();

        from.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "FROM", TitledBorder.LEFT, TitledBorder.TOP));
        to.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TO", TitledBorder.LEFT, TitledBorder.TOP));
        mode.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TYPE OF SHIPMENT", TitledBorder.LEFT, TitledBorder.TOP));
        from.setFont(new Font("arial", 25, 15));
        to.setFont(new Font("arial", 25, 15));
        mode.setFont(new Font("arial", 25, 15));
        from.setBackground(Color.WHITE);
        to.setBackground(Color.WHITE);
        mode.setBackground(Color.WHITE);
        from.setForeground(Color.BLACK);
        to.setForeground(Color.BLACK);
        mode.setForeground(Color.BLACK);
        from.setBounds(30, 200, 150, 50);
        to.setBounds(335, 200, 150, 50);
        mode.setBounds(620, 200, 150, 50);
        list1 = dbo.getFroms();
        for (int i = 0; i < list1.size(); i++) {
            from.addItem(list1.get(i));
        }
        System.err.println("bb");
        from.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    if (e.getStateChange() == 2) {
                        ItemSelectable itemselected = e.getItemSelectable();
                        if (itemselected == from) {
                            if (!from.getSelectedItem().toString().equals("")) {
                                to.removeAllItems();
                                try {
                                    String city = from.getSelectedItem().toString();

                                    list2 = dbo.getTos();
                                    list2.remove(city);

                                    for (int i = 0; i < list2.size(); i++) {
                                        to.addItem(list2.get(i));
                                    }

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "@@@@@@@@@@@@@@@" + ex.toString());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("1 ---------------->   " + ex);
                }

            }
        });

        to.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {

                    System.out.println("  Second Box is Working");
                    if (e.getStateChange() == 1) {

                        System.out.println("  Welcome Welcome Welcome  ");
                        ItemSelectable itemselected = e.getItemSelectable();
                        if (itemselected == to) {
                            if (!to.getSelectedItem().toString().equals("")) {

                                mode.removeAllItems();
                                String froms = from.getSelectedItem().toString();
                                String tos = to.getSelectedItem().toString();

                                System.out.println(" " + froms + "  " + tos);
                                list3 = dbo.getMode(froms, tos);
                                for (int i = 0; i < list3.size(); i++) {
                                    mode.addItem(list3.get(i));
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("2 ----------------------->   " + ex);
                }

            }
        });
        mode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {

                    System.out.println("  third Box is Working");
                    if (e.getStateChange() == 1) {

                        System.out.println("  Welc  ");
                        ItemSelectable itemselected = e.getItemSelectable();
                        if (itemselected == mode) {
                            if (!mode.getSelectedItem().toString().equals("")) {

                                String froms = from.getSelectedItem().toString();
                                String tos = to.getSelectedItem().toString();
                                String modes = mode.getSelectedItem().toString();

                                System.out.println(" " + froms + "  " + tos + " " + modes);
                                days = dbo.days(froms, tos, modes);
                                System.err.println(days);

                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("2 ----------------------->   " + ex);
                }
            }
        });

        availabilty.add(from);
        availabilty.add(to);
        availabilty.add(mode);
        dateChooser = new JDateChooser();
        Date date = new Date();
        dateChooser.setDate(date);
        dateChooser.setBounds(302, 300, 250, 60);
        dateChooser.setFont(new Font("arial", Font.BOLD, 20));
        dateChooser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "AVAILABILITY", TitledBorder.LEFT, TitledBorder.TOP));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        u_date = LocalDate.parse(d.format(dateChooser.getDate()));
        availabilty.add(dateChooser);

        System.err.println(d.format(dateChooser.getDate()));

//        avail_check=new JLabel("AVAILABLE");
//        avail_check.setBounds(302, 400, 250, 60);
//        avail_check.setHorizontalAlignment(JLabel.CENTER);
//        avail_check.setForeground(Color.GRAY);
//        avail_check.setFont(new Font("arial", Font.BOLD, 35));
//        availabilty.add(avail_check);
        avail_btn = new JLabel("CHECK AVAILABILITY");
        avail_btn.setBounds(302, 400, 250, 60);
        avail_btn.setHorizontalAlignment(JLabel.CENTER);
        avail_btn.setForeground(Color.white);
        avail_btn.setBackground(new Color(172, 250, 5));
        avail_btn.setOpaque(true);
        avail_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        avail_btn.setFont(new Font("arial", Font.BOLD, 20));
        avail_btn.addMouseListener(this);
        avail_btn.setEnabled(true);
        availabilty.add(avail_btn);

//        forget password panel
        forget_pass = new JPanel();
        forget_pass.setBounds(width / 2, 0, width / 2, height);
        forget_pass.setBackground(bg);
        forget_pass.setLayout(null);
        forget_pass.setVisible(true);
        co.add(forget_pass);

        forget_title = new JLabel("FORGOT PASSWORD");
        forget_title.setBounds(150, 20, 350, 50);
        forget_title.setHorizontalAlignment(JLabel.CENTER);
        forget_title.setFont(new Font("arial", Font.BOLD, 25));
        forget_title.setForeground(new Color(250, 5, 91));
        forget_pass.add(forget_title);

        forget_back = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("back.png")));
        forget_back.setBounds(60, 20, 50, 50);
        forget_back.setHorizontalAlignment(JLabel.CENTER);
        forget_back.setFont(new Font("arial", Font.BOLD, 25));
        forget_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forget_back.addMouseListener(this);
        forget_back.setForeground(new Color(250, 5, 91));
        forget_pass.add(forget_back);

        f_email = new JLabel("EMAIL");
        f_email.setBounds(70, 100, 200, 50);
        f_email.setFont(new Font("Arial", Font.BOLD, 20));
        forget_pass.add(f_email);

        forget_email = new JTextField();
        forget_email.setBounds(280, 100, 300, 45);
        forget_email.setBorder(BorderFactory.createEmptyBorder());
        forget_email.setFont(new Font("arial", Font.BOLD, 20));
        forget_email.setBackground(new Color(233, 239, 248));
        forget_email.setBorder(b);
        forget_email.addKeyListener(this);
        forget_pass.add(forget_email);

        f_email_valid = new JLabel("* email doesn't exist");
        f_email_valid.setBounds(280, 150, 300, 20);
        f_email_valid.setVisible(false);
        forget_pass.add(f_email_valid);

        f_otp_btn = new JLabel("SEND OTP");
        f_otp_btn.setBounds(280, 180, 300, 45);
        f_otp_btn.setHorizontalAlignment(JLabel.CENTER);
        f_otp_btn.setForeground(Color.WHITE);
        f_otp_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        f_otp_btn.setFont(new Font("arial", Font.BOLD, 20));
        f_otp_btn.setBackground(new Color(172, 250, 5));
        f_otp_btn.setOpaque(true);
        f_otp_btn.addMouseListener(this);
        f_otp_btn.setEnabled(false);
        forget_pass.add(f_otp_btn);

        f_otp = new JLabel("OTP");
        f_otp.setBounds(70, 250, 200, 50);
        f_otp.setFont(new Font("Arial", Font.BOLD, 20));
        forget_pass.add(f_otp);

        forget_otp = new JTextField();
        forget_otp.setBounds(280, 250, 300, 45);
        forget_otp.setBorder(BorderFactory.createEmptyBorder());
        forget_otp.setFont(new Font("arial", Font.BOLD, 20));
        forget_otp.setBackground(new Color(233, 239, 248));
        forget_otp.setBorder(b);
        forget_otp.setEnabled(false);
        forget_otp.addKeyListener(this);
        forget_pass.add(forget_otp);

        otp_validate = new JLabel("* Incorrect OTP");
        otp_validate.setFont(new java.awt.Font("Arial", 25, 15));
        otp_validate.setBounds(280, 290, 300, 45);
        otp_validate.setVisible(false);
        forget_pass.add(otp_validate);

        f_pass = new JLabel("PASSWORD");
        f_pass.setBounds(70, 360, 200, 50);
        f_pass.setFont(new Font("Arial", Font.BOLD, 20));
        forget_pass.add(f_pass);

        forget_password = new JPasswordField();
        forget_password.setBounds(280, 360, 300, 45);
        forget_password.setBorder(BorderFactory.createEmptyBorder());
        forget_password.setFont(new Font("arial", Font.BOLD, 20));
        forget_password.setBackground(new Color(233, 239, 248));
        forget_password.setBorder(b);
        forget_password.setEnabled(false);
        forget_password.addKeyListener(this);
        forget_pass.add(forget_password);

        feye = new JCheckBox();
        feye.setIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeclose.png")));
        feye.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("eyeopen.png")));
        feye.setBounds(590, 360, 50, 45);
        feye.setBackground(bg);
        feye.addActionListener(this);
        forget_pass.add(feye);

        f_pass_valid = new JLabel("* OLD PASSWORD CAN'T BE NEW PASSWORD");
        f_pass_valid.setBounds(280, 420, 300, 20);
        f_pass_valid.setVisible(false);
        forget_pass.add(f_pass_valid);

        f_change = new JLabel("CHANGE PASSWORD");
        f_change.setBounds(280, 450, 300, 45);
        f_change.setEnabled(false);
        f_change.setHorizontalAlignment(JLabel.CENTER);
        f_change.setForeground(Color.WHITE);
        f_change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        f_change.setFont(new Font("arial", Font.BOLD, 20));
        f_change.setBackground(new Color(172, 250, 5));
        f_change.setOpaque(true);
        f_change.setEnabled(false);
        f_change.addMouseListener(this);
        forget_pass.add(f_change);

//        transaction panel
        transaction = new JPanel();
        transaction.setLayout(null);
        transaction.setBackground(bg);
        Font a = new Font("Helvetica", Font.BOLD, 28);
        Font b = new Font("Helvetica", Font.PLAIN, 22);
        Font bs = new Font("Helvetica", Font.PLAIN, 12);

        transaction.setBounds(width / 3, 0, 854, 768);
        t_label = new JLabel("TRANSACTION");
        t_label.setFont(a);
        t_label.setForeground(nav_r);
        t_label.setBounds(30, 20, 300, 35);
        transaction.add(t_label);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        table.setRowHeight(23);
        table.setFont(bs);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setFont(b);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);
        table.getTableHeader().setBackground(nav_bg);
        table.setRowHeight(35);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0, 80, 848, 650);
        scroll.setVisible(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        TableColumn column = null;
        for (int i = 0; i < 6; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }

        transaction.add(scroll);
        co.add(transaction);

//    billing panel
        billing = new JPanel();
        billing.setLayout(null);
        billing.setBackground(bg);
        billing.setBounds(width / 3, 0, 854, 768);

        t_label = new JLabel("BILLING");
        t_label.setFont(a);
        t_label.setForeground(nav_r);
        t_label.setBounds(30, 20, 300, 35);
        billing.add(t_label);
        b_model = new DefaultTableModel();
        b_model.setColumnIdentifiers(b_columnNames);
        b_table = new JTable(b_model);
        b_table.setRowHeight(23);
        b_table.setFont(bs);
        b_table.setBackground(Color.WHITE);
        b_table.getTableHeader().setFont(b);
        b_table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        b_table.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);
        b_table.getTableHeader().setBackground(nav_bg);
        b_table.setRowHeight(35);
        JScrollPane lscroll = new JScrollPane(b_table);
        lscroll.setBounds(0, 80, 848, 650);
        lscroll.setVisible(true);
        lscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        b_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        b_table.setFillsViewportHeight(true);
        TableColumn bcolumn = null;
        for (int i = 0; i < 4; i++) {
            bcolumn = b_table.getColumnModel().getColumn(i);
            bcolumn.setPreferredWidth(211);
        }
        billing.add(lscroll);
        co.add(billing);

//        cargo panel
        cargo = new JPanel();
        cargo.setBounds(width / 3, 0, 854, height);
        cargo.setLayout(null);
        cargo.setVisible(true);
        cargo.setBackground(bg);

        cargo_title = new JLabel("CARGO LOGISTICS");
        cargo_title.setBounds(177, 20, 500, 50);
        cargo_title.setForeground(nav_r);
        cargo_title.setHorizontalAlignment(JLabel.CENTER);
        cargo_title.setFont(new Font("Arial", Font.BOLD, 35));
        cargo.add(cargo_title);

        cargo_p1 = new JLabel("Cargo includes any item of bulk goods or produce conveyed by water, air or land");
        cargo_p2 = new JLabel("Freight is a cargo that is transported at a freight rate for commercial gain. It was originally a");
        cargo_p3 = new JLabel("shipload but now covers all types of freight, including transport by rail , van , truck , ship, or");
        cargo_p4 = new JLabel("intermodal container.");
        cargo_p1.setBounds(120, 100, 830, 35);
        cargo_p2.setBounds(30, 130, 830, 35);
        cargo_p3.setBounds(30, 160, 830, 35);
        cargo_p4.setBounds(30, 190, 830, 35);
        //select1.setHorizontalAlignment(JLabel.CENTER);
        cargo_p1.setFont(new java.awt.Font("Segoe UI", 25, 20));
        cargo_p2.setFont(new java.awt.Font("Segoe UI", 25, 20));
        cargo_p3.setFont(new java.awt.Font("Segoe UI", 25, 20));
        cargo_p4.setFont(new java.awt.Font("Segoe UI", 25, 20));
        cargo.add(cargo_p1);
        cargo.add(cargo_p2);
        cargo.add(cargo_p3);
        cargo.add(cargo_p4);

        cargo_flow = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("flowchart1.png")));
        cargo_flow.setBounds(10, 250, 830, 280);
        cargo.add(cargo_flow);

        cargo_next = new JLabel("NEXT");
        cargo_next.setBounds(700, 658, 120, 40);
        cargo_next.setForeground(Color.white);
        cargo_next.setBackground(Color.GREEN);
        cargo_next.setOpaque(true);
        cargo_next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cargo_next.setHorizontalAlignment(JLabel.CENTER);
        cargo_next.setFont(new Font("Arial", Font.BOLD, 20));
        cargo_next.addMouseListener(this);
        cargo.add(cargo_next);
        cargo.setVisible(false);
        co.add(cargo);

//        cargo information
        cargo_information = new JPanel();
        cargo_information.setBounds(width / 3, 0, 854, height);
        cargo_information.setLayout(null);
        cargo_information.setVisible(true);
        cargo_information.setBackground(bg);

        cargo_info_title = new JLabel("CARGO INFORMATION");
        cargo_info_title.setBounds(177, 20, 500, 50);
        cargo_info_title.setForeground(nav_r);
        cargo_info_title.setHorizontalAlignment(JLabel.CENTER);
        cargo_info_title.setFont(new Font("Arial", Font.BOLD, 35));
        cargo_information.add(cargo_info_title);

        co.add(cargo_information);

        cargo_ship_d = new JLabel("SHIPMENT DETAILS");
        cargo_ship_d.setBounds(30, 90, 300, 50);
        cargo_ship_d.setForeground(Color.black);
        cargo_ship_d.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cargo_information.add(cargo_ship_d);

        dbo = new DBOperations();
        cargo_from = new JComboBox<>();
        cargo_to = new JComboBox<>();
        cargo_mode = new JComboBox<>();

        cargo_from.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "FROM", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_to.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TO", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_mode.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TYPE OF SHIPMENT", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_from.setFont(new Font("arial", 25, 15));
        cargo_to.setFont(new Font("arial", 25, 15));
        cargo_mode.setFont(new Font("arial", 25, 15));
        cargo_from.setBackground(Color.WHITE);
        cargo_to.setBackground(Color.WHITE);
        cargo_mode.setBackground(Color.WHITE);
        cargo_from.setForeground(Color.BLACK);
        cargo_to.setForeground(Color.BLACK);
        cargo_mode.setForeground(Color.BLACK);
        cargo_from.setBounds(30, 180, 200, 50);
        cargo_to.setBounds(335, 180, 200, 50);
        cargo_mode.setBounds(620, 180, 200, 50);

        list4 = dbo.getFroms();

        for (int i = 0; i < list4.size(); i++) {
            cargo_from.addItem(list4.get(i));
        }
        System.err.println("bb");
        cargo_from.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {
                    if (e.getStateChange() == 2) {

                        ItemSelectable itemselected = e.getItemSelectable();

                        if (itemselected == cargo_from) {
                            if (!cargo_from.getSelectedItem().toString().equals("")) {

                                cargo_to.removeAllItems();

                                try {
                                    String city = cargo_from.getSelectedItem().toString();

                                    list5 = dbo.getTos();
                                    list5.remove(city);

                                    for (int i = 0; i < list5.size(); i++) {
                                        cargo_to.addItem(list5.get(i));
                                    }

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "@@@@@@@@@@@@@@@" + ex.toString());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("1 ---------------->   " + ex);
                }

            }
        });

        cargo_to.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {

                    System.out.println("  Second Box is Working");
                    if (e.getStateChange() == 1) {

                        System.out.println("  Welcome Welcome Welcome  ");
                        ItemSelectable itemselected = e.getItemSelectable();
                        if (itemselected == cargo_to) {
                            if (!cargo_to.getSelectedItem().toString().equals("")) {

                                cargo_mode.removeAllItems();
                                String from = cargo_from.getSelectedItem().toString();
                                String to = cargo_to.getSelectedItem().toString();

                                System.out.println(" " + from + "  " + to);
                                list6 = dbo.getMode(from, to);
                                for (int i = 0; i < list6.size(); i++) {
                                    cargo_mode.addItem(list6.get(i));
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("2 ----------------------->   " + ex);
                }

            }
        });

        cargo_date = new JDateChooser();
        cargo_date.setDate(date);
        cargo_date.setBounds(30, 255, 200, 50);
        cargo_date.setFont(new Font("arial", 25, 15));
        cargo_date.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DATE", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_date.setDateFormatString("yyyy-MM-dd");
        cargo_information.add(cargo_date);
        System.err.println(d.format(cargo_date.getDate()));

        String commoditys[] = {"Food Stuff", "Livestock and Animals", "LNG, CNG, and Other Gas-Based Fuels", "Shipping Cars and Other Vehicles", "Machinery, Equipment, and Factory Parts", "Shipping Dry Bulk Cargo", "Shipping Liquid Bulk Cargo", "Chemical, Hazardous, and Toxic Products", "Others"};
        commodity = new JComboBox<>(commoditys);
        commodity.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "COMMODITY", TitledBorder.LEFT, TitledBorder.TOP));
        commodity.setBounds(335, 255, 200, 50);
        commodity.setForeground(Color.BLACK);
        commodity.setBackground(Color.WHITE);
        cargo_information.add(commodity);

        cargo_d = new JLabel("CARGO DETAILS");
        cargo_d.setBounds(30, 350, 300, 50);
        cargo_d.setForeground(Color.black);
        cargo_d.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cargo_information.add(cargo_d);

        cargo_quantity = new JTextField();
        cargo_quantity.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "QUANTITY", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_quantity.setBounds(30, 420, 200, 50);
        cargo_quantity.setForeground(Color.BLACK);
        cargo_quantity.setBackground(Color.WHITE);
        cargo_information.add(cargo_quantity);

        String contype[] = {"20' Standard Container", "40' Standard Container", "40 high cube Standard Container", "Others"};
        container_type = new JComboBox<>(contype);
        container_type.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CONTAINER TYPE", TitledBorder.LEFT, TitledBorder.TOP));
        container_type.setBounds(335, 420, 200, 50);
        container_type.setForeground(Color.BLACK);
        container_type.setBackground(Color.WHITE);
        cargo_information.add(container_type);

        cargo_gross_wt = new JTextField();
        cargo_gross_wt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "WEIGHT", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_gross_wt.setBounds(620, 420, 100, 50);
        cargo_gross_wt.setForeground(Color.BLACK);
        cargo_gross_wt.setBackground(Color.WHITE);
        cargo_information.add(cargo_gross_wt);

        String metric[] = {"KG", "MT"};
        cargo_gross_mt = new JComboBox<>(metric);
        cargo_gross_mt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "METRIC UNIT", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_gross_mt.setBounds(720, 420, 100, 50);
        cargo_gross_mt.setForeground(Color.BLACK);
        cargo_gross_mt.setBackground(Color.WHITE);
        cargo_information.add(cargo_gross_mt);

        cargo_finish = new JLabel("FINISH");
        cargo_finish.setBounds(700, 658, 120, 40);
        cargo_finish.setForeground(Color.white);
        cargo_finish.setBackground(Color.GREEN);
        cargo_finish.setOpaque(true);
        cargo_finish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cargo_finish.setHorizontalAlignment(JLabel.CENTER);
        cargo_finish.setFont(new Font("Arial", Font.BOLD, 20));
        cargo_finish.addMouseListener(this);
        cargo_information.add(cargo_finish);

        cargo_back = new JLabel("BACK");
        cargo_back.setBounds(30, 658, 120, 40);
        cargo_back.setForeground(Color.white);
        cargo_back.setBackground(Color.GRAY);
        cargo_back.setOpaque(true);
        cargo_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cargo_back.setHorizontalAlignment(JLabel.CENTER);
        cargo_back.setFont(new Font("Arial", Font.BOLD, 20));
        cargo_back.addMouseListener(this);
        cargo_information.add(cargo_back);
        cargo_information.setVisible(false);

//        payment panel
        payment = new JPanel();
        payment.setBounds(width / 3, 0, 854, height);
        payment.setLayout(null);
        payment.setVisible(true);
        payment.setBackground(bg);
        co.add(payment);

        payment_title = new JLabel("PAYMENT");
        payment_title.setBounds(177, 20, 500, 50);
        payment_title.setForeground(nav_r);
        payment_title.setHorizontalAlignment(JLabel.CENTER);
        payment_title.setFont(new Font("Arial", Font.BOLD, 35));
        payment.add(payment_title);

        pay_cus_l = new JLabel("CUSTOMER ID   :");
        pay_cus_l.setFont(new Font("Arial", 25, 20));
        pay_cus_l.setBounds(30, 90, 200, 50);
        pay_cus_l.setForeground(Color.BLACK);
        pay_cus_l.setBackground(Color.WHITE);
        payment.add(pay_cus_l);

        pay_cus_id = new JLabel();
        pay_cus_id.setFont(new Font("Arial", 25, 20));
        pay_cus_id.setBounds(250, 90, 200, 50);
        pay_cus_id.setForeground(Color.BLACK);
        pay_cus_id.setBackground(Color.WHITE);
        payment.add(pay_cus_id);

        pay_amt_l = new JLabel("AMOUNT   :");
        pay_amt_l.setBounds(550, 150, 200, 50);
        pay_amt_l.setFont(new Font("Arial", 25, 20));
        payment.add(pay_amt_l);

        pay_amt = new JLabel("₹4500");
        pay_amt.setFont(new Font("Arial", 25, 20));
        pay_amt.setBounds(680, 150, 200, 50);
        pay_amt.setForeground(Color.BLACK);
        pay_amt.setBackground(Color.WHITE);
        payment.add(pay_amt);

        pay_date_l = new JLabel("DATE        :");
        pay_date_l.setBounds(550, 90, 200, 50);
        pay_date_l.setFont(new Font("Arial", 25, 20));
        payment.add(pay_date_l);

        pay_date = new JLabel(d.format(date));
        pay_date.setFont(new Font("Arial", 25, 20));
        pay_date.setBounds(680, 90, 200, 50);
        pay_date.setForeground(Color.BLACK);
        pay_date.setBackground(Color.WHITE);
        payment.add(pay_date);

        transport_l = new JLabel("TRANSPORT ID  :");
        transport_l.setFont(new Font("Arial", 25, 20));
        transport_l.setBounds(30, 150, 200, 50);
        transport_l.setForeground(Color.BLACK);
        transport_l.setBackground(Color.WHITE);
        payment.add(transport_l);

        transport_id = new JLabel("#1234");
        transport_id.setFont(new Font("Arial", 25, 20));
        transport_id.setBounds(250, 150, 200, 50);
        transport_id.setForeground(Color.BLACK);
        transport_id.setBackground(Color.WHITE);
        payment.add(transport_id);

        payment_box = new JLabel("");
        payment_box.setFont(new Font("Arial", 25, 20));
        payment_box.setBounds(272, 250, 310, 440);
        payment_box.setBorder(c);
        payment_box.setForeground(Color.BLACK);
        payment_box.setBackground(Color.WHITE);
        payment.add(payment_box);

        paymethod_l = new JLabel("PAYMENT DETAILS");
        paymethod_l.setFont(new Font("Arial", Font.BOLD, 20));
        paymethod_l.setBounds(30, 10, 250, 50);
        paymethod_l.setHorizontalAlignment(JLabel.CENTER);
        paymethod_l.setForeground(nav_r);
        paymethod_l.setBackground(Color.WHITE);
        payment_box.add(paymethod_l);

        String paymethod[] = {"DEBIT CARD", "CREDIT CARD"};
        PAYMETHOD = new JComboBox<>(paymethod);
        PAYMETHOD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "PAYMENT METHOD", TitledBorder.LEFT, TitledBorder.TOP));
        PAYMETHOD.setBounds(30, 60, 250, 50);
        PAYMETHOD.setForeground(Color.BLACK);
        PAYMETHOD.setBackground(Color.WHITE);
        payment_box.add(PAYMETHOD);

        card_no_valid = new JLabel("");
        card_no_valid.setForeground(Color.RED);
        card_no_valid.setBounds(30, 165, 250, 25);
        payment_box.add(card_no_valid);
        card_no = new JTextField(16);
        card_no.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CARD NUMBER", TitledBorder.LEFT, TitledBorder.TOP));
        card_no.setBounds(30, 120, 250, 50);
        card_no.setForeground(Color.BLACK);
        card_no.setBackground(Color.WHITE);
        card_no.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = card_no.getText();
                int l = value.length();
                System.err.println(l);
                if (l == 4 || l == 9 || l == 14) {
                    card_no.setText(value + "-");
                } else {
                    card_no.setText(value);
                }

                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 19 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    card_no.setEditable(true);
                    card_no_valid.setText("");
                } else {
                    card_no.setEditable(false);
                    if (l == 19) {
                        card_no_valid.setText("");
                    } else {
                        card_no_valid.setText("* Enter only numeric digits(0-9)");
                    }
                }

            }
        });

        card_month = new JTextField(16);
        card_month.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "MM/YY", TitledBorder.LEFT, TitledBorder.TOP));
        card_month.setBounds(30, 190, 100, 50);
        card_month.setForeground(Color.BLACK);
        card_month.setBackground(Color.WHITE);
        card_month.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = card_month.getText();
                int l = value.length();
                System.err.println(l);
                if (l == 2) {
                    card_month.setText(value + "/");
                } else {
                    card_month.setText(value);
                }
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 5 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    card_month.setEditable(true);
                } else {
                    card_month.setEditable(false);
                }
            }
        });

        card_ccv = new JPasswordField();
        card_ccv.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CCV", TitledBorder.LEFT, TitledBorder.TOP));
        card_ccv.setBounds(180, 190, 100, 50);
        card_ccv.setForeground(Color.BLACK);
        card_ccv.setBackground(Color.WHITE);
        card_ccv.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = card_ccv.getText();
                int l = value.length();
                System.err.println(l);
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    card_ccv.setEditable(true);
                } else {
                    card_ccv.setEditable(false);
                }
            }
        });

        card_holder = new JTextField(16);
        card_holder.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CARD HOLDER", TitledBorder.LEFT, TitledBorder.TOP));
        card_holder.setBounds(30, 250, 250, 50);
        card_holder.setForeground(Color.BLACK);
        card_holder.setBackground(Color.WHITE);

        card_email = new JTextField(16);
        card_email.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CARD EMAIL", TitledBorder.LEFT, TitledBorder.TOP));
        card_email.setBounds(30, 310, 250, 50);
        card_email.setForeground(Color.BLACK);
        card_email.setBackground(Color.WHITE);

        card_conform_btn = new JLabel("CONFIRM PAYMENT");
        card_conform_btn.setForeground(Color.WHITE);
        card_conform_btn.setBounds(30, 370, 250, 50);
        card_conform_btn.setHorizontalAlignment(JLabel.CENTER);
        card_conform_btn.setFont((new Font("Arial", Font.BOLD, 17)));
        card_conform_btn.setBackground(Color.GREEN);
        card_conform_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card_conform_btn.setOpaque(true);
        card_conform_btn.addMouseListener(this);
        payment_box.add(card_conform_btn);

        payment_box.add(card_email);
        payment_box.add(card_holder);
        payment_box.add(card_ccv);
        payment_box.add(card_month);
        payment_box.add(card_no);
        payment.setVisible(false);

        cargo.setVisible(true);
        cargo_information.add(cargo_from);
        cargo_information.add(cargo_to);
        cargo_information.add(cargo_mode);

//        main container
        settings.setVisible(false);
        signIn.setVisible(false);
        img_pane.setVisible(true);
        cargo.setVisible(false);
        login.setVisible(true);
        nav.setVisible(false);
        verify.setVisible(false);
        availabilty.setVisible(false);
        forget_pass.setVisible(false);
        transaction.setVisible(false);
        billing.setVisible(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
//        dbo.cargo_db(123, 452, "air", "chennai", "mumbai", "fish","123", "45f", "21", "2021-02-25", "2021-12-50");
//        dbo.transaction(123,452,562,251,2500.00,"2021-25-22","debit card");
//        o=new Pdf();
//        o.writeUsingIText("chandru", "chandru6501@gmail.com", 123, "20' c conatainer", "chennai", "mumbai", 1200.00);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, ParseException {
        new Demo();

    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj_source = e.getSource();

        if (obj_source == lsubmit) {
//            ob.setCustomer_email(l_email.getText());
//            ob.setCustomer_password(lpass.getText());
//            System.err.println(ob.getCustomer_email());
            try {
                PreparedStatement query = con.prepareStatement("select * from cargo_admin where customer_email = ?");
                query.setString(1, l_email.getText());
                ResultSet q = query.executeQuery();
                if (q.next()) {

                    if (sc.decrypt(q.getString(5)).equals(ltpass.getText())) {

                        ob.setCustomer_id(q.getInt(1));
                        ob.setCustomer_name(q.getString(2));
                        ob.setCustomer_phone(q.getString(3));
                        ob.setCustomer_email(q.getString(4));
                        ob.setCustomer_password(q.getString(5));
                        ob.setCustomer_address(q.getString(6));
                        ob.setCustomer_Uid(q.getString(7));
                        ob.setCustomer_uname(q.getString(8));
                        p4_uname.setText(ob.getCustomer_uname());
                        p4_uname_id.setText("#" + Integer.toString(ob.getCustomer_id()));
                        ts_uname.setText(ob.getCustomer_uname());
                        ts_name.setText(ob.getCustomer_name());
                        ts_mobile.setText(ob.getCustomer_phone());
                        ts_address.setText(ob.getCustomer_address());
                        ts_email.setText(ob.getCustomer_email());
                        ts_pass.setText(sc.decrypt(ob.getCustomer_password()));

                        JOptionPane.showMessageDialog(null, "Login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        //set.Setting setting=new Setting(ob);
//                        setting.setVisible(true);
//                        co.add(setting);
                        img_pane.setVisible(false);
                        settings.setVisible(false);
                        cargo.setVisible(true);
                        signIn.setVisible(false);
                        login.setVisible(false);
                        nav.setVisible(true);
                        loginfield();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed", "Failed", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account doesn't exist", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (obj_source == checkpass) {
            JCheckBox c = (JCheckBox) e.getSource();
            tpass.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        } else if (obj_source == lcheckpass) {
            JCheckBox c = (JCheckBox) e.getSource();
            ltpass.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        } else if (obj_source == verifybtn) {
            String otp_e = votp.getText();
            int n = Integer.parseInt(otp_e);
            if (n == otp) {
                try {
                    PreparedStatement st = con.prepareStatement("insert into cargo_admin values(?,?,?,?,?,?,?,?)");
                    st.setInt(1, ob.getCustomer_id());
                    st.setString(2, ob.getCustomer_name());
                    st.setLong(3, Long.parseLong(ob.getCustomer_phone()));
                    st.setString(4, ob.getCustomer_email());
                    st.setString(5, ob.getCustomer_password());
                    st.setString(6, ob.getCustomer_address());
                    st.setString(7, ob.getCustomer_Uid());
                    st.setString(8, ob.getCustomer_uname());
                    st.executeUpdate();
                    System.err.println("done");

                } catch (SQLException ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "ACCOUNT CREATED SUCCESSFULLY", "SIGN IN", JOptionPane.INFORMATION_MESSAGE);
                nav.setVisible(false);
                img_pane.setVisible(true);
                verify.setVisible(false);
                login.setVisible(true);
                signIn.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "INVALID OTP", "SIGN IN", JOptionPane.INFORMATION_MESSAGE);

            }
        } else if (obj_source == create) {
            Random customer_rand = new Random();
            int c_id = customer_rand.nextInt(9999);
            UUID uuid = UUID.randomUUID();
            String uuid_g = uuid.toString();
            ob.setCustomer_name(tuname.getText());
            ob.setCustomer_phone(tmobile.getText());
            ob.setCustomer_uname(tuser.getText());
            ob.setCustomer_email(temail.getText());
            ob.setCustomer_password(sc.encrypt(tpass.getText()));
            ob.setCustomer_address(tarea.getText());
            ob.setCustomer_id(c_id);
            ob.setCustomer_Uid(uuid_g);
            verify.setVisible(true);
            signIn.setVisible(false);
            otp_generate();
        } else if (obj_source == eye) {
            JCheckBox c = (JCheckBox) e.getSource();
            ts_pass.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));

        } else if (obj_source == resendbtn) {
            otp_generate();
        } else if (obj_source == feye) {
            JCheckBox c = (JCheckBox) e.getSource();
            forget_password.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj_source = e.getSource();
        if (obj_source == sCreate) {
            login.setVisible(false);
            signIn.setVisible(true);
            loginfield();
        } else if (obj_source == forget_back) {
            login.setVisible(true);
            forget_pass.setVisible(false);
            loginfield();
        } else if (obj_source == Login_btn) {
            signIn.setVisible(false);
            login.setVisible(true);
            signinfield();
        } else if (obj_source == box1) {
            p4_cargo.setForeground(nav_r);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            signIn.setVisible(false);
            img_pane.setVisible(false);
            cargo.setVisible(true);
            login.setVisible(false);
            nav.setVisible(true);
            verify.setVisible(false);
            settings.setVisible(false);
            availabilty.setVisible(false);
            transaction.setVisible(false);
            billing.setVisible(false);

        } else if (obj_source == box2) {
            p4_cargo.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_avail.setForeground(nav_r);
            signIn.setVisible(false);
            img_pane.setVisible(false);
            cargo.setVisible(false);
            login.setVisible(false);
            nav.setVisible(true);
            verify.setVisible(false);
            settings.setVisible(false);
            availabilty.setVisible(true);
            transaction.setVisible(false);
            billing.setVisible(false);
            cargo_information.setVisible(false);
            payment.setVisible(false);
        } else if (obj_source == box3) {
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_trans.setForeground(nav_r);
            signIn.setVisible(false);
            img_pane.setVisible(false);
            cargo.setVisible(false);
            login.setVisible(false);
            nav.setVisible(true);
            verify.setVisible(false);
            settings.setVisible(false);
            availabilty.setVisible(false);
            transaction.setVisible(true);
            billing.setVisible(false);
            cargo_information.setVisible(false);
            payment.setVisible(false);
            String cus_id = p4_uname_id.getText();
            String id_cus = cus_id.replace("#", "");
            number = Integer.parseInt(id_cus);
            model.setRowCount(0);
            try {
                PreparedStatement ps = con.prepareStatement("select * from transaction where customer_id=?");
                ps.setInt(1, number);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else if (obj_source == box4) {
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_bill.setForeground(nav_r);
            signIn.setVisible(false);
            img_pane.setVisible(false);
            cargo.setVisible(false);
            login.setVisible(false);
            nav.setVisible(true);
            verify.setVisible(false);
            settings.setVisible(false);
            availabilty.setVisible(false);
            transaction.setVisible(false);
            billing.setVisible(true);
            cargo_information.setVisible(false);
            payment.setVisible(false);
            String cus_id = p4_uname_id.getText();
            String id_cus = cus_id.replace("#", "");
            number = Integer.parseInt(id_cus);
            b_model.setRowCount(0);
            try {
                PreparedStatement ps = con.prepareStatement("select * from billing where customer_id=?");
                ps.setInt(1, number);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    b_model.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else if (obj_source == box5) {
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_live.setForeground(nav_r);
            String cus_id = p4_uname_id.getText();
            String id_cus = cus_id.replace("#", "");
            number = Integer.parseInt(id_cus);
            try {
                PreparedStatement ps = con.prepareStatement("select * from cargo where customer_id=?");
                ps.setInt(1, number);
                ResultSet rs = ps.executeQuery();
                int i = 0;
                String to = null, from = null;
                while (rs.next()) {
                    i++;
                    from = rs.getString(4);
                    to = rs.getString(5);

                }
                if (i > 0) {
                    String url = "https://www.google.com/maps/dir/?api=1&origin=" + from + "&destination=" + to; // capture the URL when the user presses the button.
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(url);
                    desktop.browse(oURL);
                }

            } catch (Exception ke) {
                ke.printStackTrace();
            }
        } else if (obj_source == box6) {
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_log.setForeground(Color.black);
            p4_set.setForeground(nav_r);
            signIn.setVisible(false);
            img_pane.setVisible(false);
            cargo.setVisible(false);
            login.setVisible(false);
            nav.setVisible(true);
            verify.setVisible(false);
            settings.setVisible(true);
            availabilty.setVisible(false);
            transaction.setVisible(false);
            cargo_information.setVisible(false);
            payment.setVisible(false);
        } else if (obj_source == box7) {
            p4_cargo.setForeground(Color.black);
            p4_avail.setForeground(Color.black);
            p4_trans.setForeground(Color.black);
            p4_bill.setForeground(Color.black);
            p4_live.setForeground(Color.black);
            p4_set.setForeground(Color.black);
            p4_log.setForeground(nav_r);

            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 25));
            int LOG = JOptionPane.showConfirmDialog(this, "do you like to logout?", "LOGOUT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getClassLoader().getResource("logout.png")));
            System.err.println(LOG);
            if (LOG == JOptionPane.YES_OPTION) {
                signIn.setVisible(false);
                img_pane.setVisible(true);
                cargo.setVisible(false);
                login.setVisible(true);
                nav.setVisible(false);
                verify.setVisible(false);
                settings.setVisible(false);
                availabilty.setVisible(false);
                transaction.setVisible(false);
                billing.setVisible(false);
                cargo_information.setVisible(false);
                payment.setVisible(false);
            } else if (LOG == JOptionPane.NO_OPTION) {
                signIn.setVisible(false);
                img_pane.setVisible(false);
                cargo.setVisible(true);
                login.setVisible(false);
                nav.setVisible(true);
                verify.setVisible(false);
            }

        } else if (obj_source == delete) {
            String cus_id = p4_uname_id.getText();
            String id_cus = cus_id.replace("#", "");
            number = Integer.parseInt(id_cus);
            settings.setVisible(false);
            signIn.setVisible(false);
            img_pane.setVisible(true);
            cargo.setVisible(false);
            login.setVisible(true);
            nav.setVisible(false);
            verify.setVisible(false);
            availabilty.setVisible(false);
            forget_pass.setVisible(false);
            transaction.setVisible(false);
            billing.setVisible(false);
            dbo.delete(number);
            delete.setForeground(Color.green);
        } else if (obj_source == update) {
            String cus_id = p4_uname_id.getText();
            String id_cus = cus_id.replace("#", "");
            number = Integer.parseInt(id_cus);
            dbo.update(ts_uname.getText(), ts_name.getText(), ts_email.getText(), ts_pass.getText(), ts_mobile.getText(), ts_address.getText(), number);
        } else if (obj_source == f_otp_btn) {
            Socket sock = new Socket();
            InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
            try {
                sock.connect(addr, 3000);
                JOptionPane.showMessageDialog(null, "true");
                ob.setCustomer_email(forget_email.getText());
                otp_generate();
            } catch (Exception ae) {
                JOptionPane.showMessageDialog(null, "false");
            } finally {
                try {
                    sock.close();
                } catch (Exception ae) {
                }
            }

        } else if (obj_source == f_change) {
            int x = dbo.update_pass(sc.encrypt(forget_password.getText()), forget_email.getText());
            if (x == 1) {
                JOptionPane.showMessageDialog(null, "Password changed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                login.setVisible(true);
                forget_pass.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Login Successfully", "not changed", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (obj_source == lforget_password) {
            settings.setVisible(false);
            signIn.setVisible(false);
            img_pane.setVisible(true);
            cargo.setVisible(false);
            login.setVisible(false);
            nav.setVisible(false);
            verify.setVisible(false);
            availabilty.setVisible(false);
            forget_pass.setVisible(true);
        } else if (obj_source == avail_btn) {
            Date d1 = new Date();
            LocalDate date1 = LocalDate.parse(d.format(d1));
            LocalDate date2 = date1.plusDays(days);
            String d3 = d.format(dateChooser.getDate());
            String d4 = date2.toString();
            if (d3.compareTo(d4) == 0) {
                JOptionPane.showMessageDialog(null, "AVAILABLE", "AVAILABILITY", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "NOT AVAILABLE", "AVAILABILITY", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (obj_source == cargo_next) {
            cargo.setVisible(false);
            cargo_information.setVisible(true);
        } else if (obj_source == cargo_back) {
            cargo.setVisible(true);
            cargo_information.setVisible(false);
        } else if (obj_source == card_conform_btn) {
            System.err.println(PAYMETHOD.getSelectedItem());
            if (!card_no.getText().equals("")) {
                if (!card_month.getText().equals("")) {
                    if (!card_ccv.getText().equals("")) {
                        if (!card_holder.getText().equals("")) {
                            if (!card_email.getText().equals("")) {
                                String c_from = cargo_from.getSelectedItem().toString();
                                String c_to = cargo_to.getSelectedItem().toString();
                                String c_mode = cargo_mode.getSelectedItem().toString();
                                String c_date = d.format(cargo_date.getDate());
                                String c_com = commodity.getSelectedItem().toString();
                                String c_quan = cargo_quantity.getText();
                                String c_container = container_type.getSelectedItem().toString();
                                String c_gross_weight = cargo_gross_wt.getText();
                                String c_gross_mt = cargo_gross_mt.getSelectedItem().toString();
                                System.err.println("fn");
                                String g_weight = c_gross_weight + c_gross_mt;
                                Date d1 = new Date();
                                LocalDate date1 = LocalDate.parse(d.format(d1));
                                LocalDate date2 = date1.plusDays(dbo.days(c_from, c_to, c_mode));
                                System.err.println(date2);
                                String d3 = c_date;
                                String d4 = date2.toString();
                                Date pay_date = new Date();
                                String cus_id = p4_uname_id.getText();
                                String id_cus = cus_id.replace("#", "");
                                number = Integer.parseInt(id_cus);

                                SendOtp otp = new SendOtp();
                                int n = otp.otp_generate(card_email.getText());
                                String name = JOptionPane.showInputDialog(null, "Enter OTP");
                                if (Integer.parseInt(name) == n) {
                                    JOptionPane.showMessageDialog(null, "PAYMENT SUCCESSFULL", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                                    billing_id_rand = customer_rand.nextInt(999999) + 100000;
                                    transaction_id_rand = customer_rand.nextInt(999999) + 100000;
                                    dbo.cargo_db(number, Integer.parseInt(transport_id.getText()), c_mode, c_from, c_to, c_com, c_quan, c_container, g_weight, c_date, d4);
                                    bill = new Pdf();
                                    bill.writeUsingIText(card_holder.getText(), card_email.getText(), billing_id_rand, c_container, c_from, c_to, pay_amounts);
                                    dbo.transaction(number, transaction_id_rand, Integer.parseInt(transport_id.getText()), billing_id_rand, pay_amounts, d.format(pay_date), PAYMETHOD.getSelectedItem().toString());
                                    dbo.billing(number, billing_id_rand, transaction_id_rand, card_holder.getText(), card_email.getText());
                                    System.err.println(billing_id_rand);
                                    cargo.setVisible(true);
                                    payment.setVisible(false);
                                    clearpaymentField();
                                    cargo_details();
                                } else {
                                    clearpaymentField();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ENTER ALL DETIALS", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "ENTER ALL DETIALS", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ENTER ALL DETIALS", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER ALL DETIALS", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "ENTER ALL DETIALS", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (obj_source == cargo_finish) {
            if (!cargo_from.getSelectedItem().equals("")) {
                if (!cargo_to.getSelectedItem().equals("")) {
                    if (!cargo_mode.getSelectedItem().equals("")) {
                        if (!cargo_date.equals("")) {
                            if (!commodity.getSelectedItem().equals("")) {
                                if (!cargo_quantity.getText().equals("")) {
                                    if (!container_type.getSelectedItem().equals("")) {
                                        if (!cargo_gross_wt.getText().equals("")) {
                                            if (!cargo_gross_mt.getSelectedItem().equals("")) {
                                                transport_id_rand = customer_rand.nextInt(999999) + 100000;
                                                transport_id.setText(Integer.toString(transport_id_rand));
                                                String cus_id = p4_uname_id.getText();
                                                pay_cus_id.setText(cus_id);
                                                payment.setVisible(true);
                                                double amount = dbo.price(cargo_from.getSelectedItem().toString(), cargo_to.getSelectedItem().toString(), cargo_mode.getSelectedItem().toString());
                                                pay_amounts = amount * Double.parseDouble(cargo_quantity.getText());
                                                pay_amt.setText(Double.toString(pay_amounts));
                                                cargo_information.setVisible(false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void clearpaymentField() {
        card_no.setText("");
        card_no.setEditable(true);
        card_month.setText("");
        card_month.setEditable(true);
        card_ccv.setText("");
        card_ccv.setEditable(true);
        card_holder.setText("");
        card_email.setText("");
    }

    public void loginfield() {
        ltpass.setText("");
        l_email.setText("");
    }

    public void signinfield() {
        tuname.setText("");
        tmobile.setText("");
        tuser.setText("");
        temail.setText("");
        tpass.setText("");
        tarea.setText("");
        valid_email.setVisible(false);
        user_validate.setVisible(false);
        mobile_validate.setVisible(false);
        check_email.setVisible(false);
        pass_validate.setVisible(false);
    }

    public void cargo_details() {
        cargo_gross_wt.setText("");
        cargo_quantity.setText("");

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
        Object obj_source = e.getSource();
        if (obj_source == create) {
            create.setBackground(new Color(92, 112, 237));
        } else if (obj_source == lsubmit) {
            lsubmit.setBackground(new Color(92, 112, 237));
        } else if (obj_source == box1) {
            box1.setBackground(nav_bg);
        } else if (obj_source == box2) {
            box2.setBackground(nav_bg);
        } else if (obj_source == box3) {
            box3.setBackground(nav_bg);
        } else if (obj_source == box4) {
            box4.setBackground(nav_bg);
        } else if (obj_source == box5) {
            box5.setBackground(nav_bg);
        } else if (obj_source == box6) {
            box6.setBackground(nav_bg);
        } else if (obj_source == box7) {
            box7.setBackground(nav_bg);
        } else if (obj_source == delete) {
            delete.setBackground(new Color(242, 119, 119));
        } else if (obj_source == update) {
            update.setBackground(new Color(138, 209, 136));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj_source = e.getSource();
        if (obj_source == create) {
            create.setBackground(Color.BLUE);
        } else if (obj_source == lsubmit) {
            lsubmit.setBackground(Color.BLUE);
        } else if (obj_source == box1) {
            box1.setBackground(li);
        } else if (obj_source == box2) {
            box2.setBackground(li);
        } else if (obj_source == box3) {
            box3.setBackground(li);
        } else if (obj_source == box4) {
            box4.setBackground(li);
        } else if (obj_source == box5) {
            box5.setBackground(li);
        } else if (obj_source == box6) {
            box6.setBackground(li);
        } else if (obj_source == box7) {
            box7.setBackground(li);
        } else if (obj_source == delete) {
            delete.setBackground(Color.RED);
        } else if (obj_source == update) {
            update.setBackground(Color.GREEN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object obj_source = e.getSource();
        if (obj_source == tpass) {
            String password_regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            System.err.println(text);
            if (!Pattern.matches(password_regex, text)) {
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
        } else if (obj_source == temail) {
            String emailregex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                    + "[a-zA-Z0-9_+&*-]+)*@"
                    + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                    + "A-Z]{2,7}$";
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            if (!Pattern.matches(emailregex, text)) {
                valid_email.setVisible(true);
                temail.setForeground(Color.red);
                return;
            } else {
                valid_email.setVisible(false);
                temail.setForeground(Color.black);
            }
        } else if (obj_source == tmobile) {
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
        } else if (obj_source == l_email) {
            String emailregex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                    + "[a-zA-Z0-9_+&*-]+)*@"
                    + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                    + "A-Z]{2,7}$";
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            if (!Pattern.matches(emailregex, text)) {
                lvalid_email.setVisible(true);
                l_email.setForeground(Color.red);
                return;
            } else {
                lvalid_email.setVisible(false);
                l_email.setForeground(Color.black);
            }
        } else if (obj_source == tuser) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            System.out.println(text);
            try {
                PreparedStatement query = con.prepareStatement("select * from cargo_admin where customer_uname = ?");
                query.setString(1, text);
                ResultSet q = query.executeQuery();
//                System.err.println(!(q.next()));
                if (q.next()) {
                    System.out.println("having");
                    create.setEnabled(false);
                    user_validate.setVisible(true);
                    return;
                } else {
                    System.out.println("not");
                    create.setEnabled(true);
                    user_validate.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (obj_source == ts_pass) {
            String password_regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            System.err.println(text);
            if (!Pattern.matches(password_regex, text)) {
                spass_validate.setText("Requires 1 Uppercase,1 Symbol and 1 Number");
                spass_validate.setForeground(Color.red);
                spass_validate.setVisible(true);
                ts_pass.setForeground(Color.red);
                return;
            } else {

                spass_validate.setText("Entered strong password");
                spass_validate.setForeground(Color.green);
                ts_pass.setForeground(Color.black);
            }
        } else if (obj_source == ts_mobile) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            if (!Pattern.matches("^[0-9]{10}", text)) {
                mobile_validate.setVisible(true);
                ts_mobile.setForeground(Color.red);
                mobile_validate.setForeground(Color.red);
                return;
            } else {
                mobile_validate.setVisible(false);
                ts_mobile.setForeground(Color.black);
            }
        } else if (obj_source == forget_email) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            System.out.println(text);
            try {
                PreparedStatement query = con.prepareStatement("select * from cargo_admin where customer_email = ?");
                query.setString(1, text);
                ResultSet q = query.executeQuery();
//                System.err.println(!(q.next()));
                if (q.next()) {
                    System.out.println("having");
                    f_email_valid.setVisible(false);
                    f_otp_btn.setEnabled(true);
                    forget_otp.setEnabled(true);
                    return;
                } else {
                    System.out.println("not");
                    f_email_valid.setVisible(true);
                    f_otp_btn.setEnabled(false);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (obj_source == forget_otp) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();;
            int n = Integer.parseInt(text);
            if (n == otp) {
                forget_password.setEnabled(true);
                otp_validate.setVisible(false);

            } else {
                forget_password.setEnabled(false);
                otp_validate.setVisible(true);
            }
        } else if (obj_source == forget_password) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            System.out.println(text);
            try {
                PreparedStatement query = con.prepareStatement("select * from cargo_admin where customer_email = ?");
                query.setString(1, forget_email.getText());
                ResultSet q = query.executeQuery();
                if (q.next()) {
                    if (sc.decrypt(q.getString(5)).equals(text)) {
                        f_change.setEnabled(false);
                        f_pass_valid.setVisible(true);
                    } else {
                        f_change.setEnabled(true);
                        f_pass_valid.setVisible(false);
                    }
                } else {
//                    f_change.setEnabled(true);
//                    f_pass_valid.setVisible(false);   
                }
            } catch (SQLException ex) {
                Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void otp_generate() {
        final String username = "cargologisticsmb@gmail.com";
        final String password = "Cargo@123";

        final String from = username;
        final String to = ob.getCustomer_email();
        Random rand = new Random();
        otp = rand.nextInt(999999);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Cargo Logistics Management");
            message.setText("Your OTP for your Account Creation in CARGO LOGISTICS MANAGEMENT is " + otp);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException ae) {
            System.out.println(ae);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
