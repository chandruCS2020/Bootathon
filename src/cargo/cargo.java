/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargo;

import OTP.SendOtp;
import billing.Pdf;
import com.toedter.calendar.JDateChooser;
import database.DBOperations;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.smartcardio.Card;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author chandru
 */
public class cargo extends JFrame implements ActionListener,KeyListener,MouseListener{
    Container co;
    Connection con = Dbconnection.Db_connection.getConnection();
    Color bg=new Color(233,239,248);
    DBOperations dbo;
    Border b = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
    Pdf bill;
        
//    cargo panel decalartion
    JPanel cargo;
    JLabel cargo_title,cargo_p1,cargo_p2,cargo_p3,cargo_p4,cargo_flow,cargo_next;

//    cargo_information
    JPanel cargo_information;
    JLabel cargo_info_title,cargo_ship_d,cargo_d,cargo_gross,cargo_back,cargo_finish;
    JTextField cargo_quantity,cargo_gross_wt;
    JDateChooser cargo_date;
    JComboBox<String> commodity;
    JComboBox<String> container_type;
    JComboBox<String> cargo_gross_mt;
    JComboBox<String> cargo_from ;
    JComboBox<String> cargo_to ;
    JComboBox<String> cargo_mode;
    List<String> list1 = null;
    List<String> list2 = null;
    List<String> list3 = null;
    
//    payment panel
    JPanel payment;
    JLabel payment_title,pay_cus_id,pay_cus_l,transport_l,transport_id,pay_amt,pay_amt_l,pay_date_l,pay_date,paymethod_l,payment_box;
    JLabel card_no_valid,card_conform_btn;
    JComboBox<String> PAYMETHOD;
    JTextField card_no,card_month,card_holder,card_email;
    JPasswordField card_ccv;
    
//    payment verify panel
    JPanel payment_verify;
    JLabel payment_verify_box,payment_verify_label,payment_verify_btn;
    JTextField payment_verify_otp;
    
    
//    random id generation
    Random customer_rand=new Random();
    int transport_id_rand,billing_id_rand,transaction_id_rand;
    double pay_amounts;
    
    
    public cargo(){
        co=getContentPane();
        co.setLayout(null);
        int width=1280;
        int height=768;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ima =new ImageIcon("C:\\Users\\chandru\\Documents\\NetBeansProjects\\Awt\\src\\logo.png");
        setIconImage(ima.getImage());
        
        
        
        
//        cargo panel
        cargo=new JPanel();
        cargo.setBounds(width/3, 0, 854, height);
        cargo.setLayout(null);
        cargo.setVisible(true);
        cargo.setBackground(bg);
        
        
        cargo_title=new JLabel("CARGO LOGISTICS");
        cargo_title.setBounds(177, 20, 500, 50);
        cargo_title.setForeground(Color.RED);
        cargo_title.setHorizontalAlignment(JLabel.CENTER);
        cargo_title.setFont(new Font("Arial", Font.BOLD, 35));
        cargo.add(cargo_title);
        
        
        cargo_p1=new JLabel("Cargo includes any item of bulk goods or produce conveyed by water, air or land");
        cargo_p2=new JLabel("Freight is a cargo that is transported at a freight rate for commercial gain. It was originally a");
        cargo_p3=new JLabel("shipload but now covers all types of freight, including transport by rail , van , truck , ship, or"); 
        cargo_p4=new JLabel("intermodal container.");
        cargo_p1.setBounds(120,100,830,35);
        cargo_p2.setBounds(30,130,830,35);
        cargo_p3.setBounds(30,160,830,35);
        cargo_p4.setBounds(30,190,830,35);
        //select1.setHorizontalAlignment(JLabel.CENTER);
        cargo_p1.setFont(new java.awt.Font("Segoe UI",25,20));
        cargo_p2.setFont(new java.awt.Font("Segoe UI",25,20));
        cargo_p3.setFont(new java.awt.Font("Segoe UI",25,20));
        cargo_p4.setFont(new java.awt.Font("Segoe UI",25,20));
        cargo.add(cargo_p1);
        cargo.add(cargo_p2);
        cargo.add(cargo_p3);
        cargo.add(cargo_p4);
        
        
        cargo_flow=new JLabel(new ImageIcon("src\\images\\flowchart1.png"));
        cargo_flow.setBounds(10,250,830,280);
        cargo.add(cargo_flow);
        
        cargo_next=new JLabel("NEXT");
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
        cargo_information=new JPanel();
        cargo_information.setBounds(width/3, 0, 854, height);
        cargo_information.setLayout(null);
        cargo_information.setVisible(true);
        cargo_information.setBackground(bg);
        
        
        cargo_info_title=new JLabel("CARGO INFORMATION");
        cargo_info_title.setBounds(177, 20, 500, 50);
        cargo_info_title.setForeground(Color.RED);
        cargo_info_title.setHorizontalAlignment(JLabel.CENTER);
        cargo_info_title.setFont(new Font("Arial", Font.BOLD, 35));
        cargo_information.add(cargo_info_title);
        
        co.add(cargo_information);
        

        cargo_ship_d=new JLabel("SHIPMENT DETAILS");
        cargo_ship_d.setBounds(30, 90, 300, 50);
        cargo_ship_d.setForeground(Color.black);
        cargo_ship_d.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cargo_information.add(cargo_ship_d);
        
        dbo = new DBOperations();
        cargo_from = new JComboBox<>();        
        cargo_to = new JComboBox<>();
        cargo_mode = new JComboBox<>();
        
        
        cargo_from.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "FROM", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_to.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "TO", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_mode.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "TYPE OF SHIPMENT", TitledBorder.LEFT, TitledBorder.TOP));
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
        
        
        list1 = dbo.getFroms();
        
        
        
        for (int i = 0; i < list1.size(); i++) {
            cargo_from.addItem(list1.get(i));
        }
        System.err.println("bb");
        cargo_from.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                try{
                if(e.getStateChange()==2){

                    ItemSelectable itemselected=e.getItemSelectable();
                    
                    if(itemselected==cargo_from){
                        if(!cargo_from.getSelectedItem().toString().equals("")){

                            cargo_to.removeAllItems();
                            

                            
                            try{
                                String city = cargo_from.getSelectedItem().toString();                                
                                
                                list2 = dbo.getTos();
                                list2.remove(city);
                                
                                for (int i = 0; i < list2.size(); i++) {            
                                   cargo_to.addItem(list2.get(i));            
                                }
                                
                                
                            }
                            catch(Exception ex){
                                JOptionPane.showMessageDialog(null,"@@@@@@@@@@@@@@@"+ ex.toString());
                            }
                        }
                    }
                }                    
                }
                catch(Exception ex){
                    System.out.println("1 ---------------->   "+ex);
                }


            }
        });
        

        
        
        cargo_to.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                try{
                    
                System.out.println("  Second Box is Working");
                if(e.getStateChange()==1){

                    System.out.println("  Welcome Welcome Welcome  ");
                    ItemSelectable itemselected=e.getItemSelectable();
                    if(itemselected==cargo_to){
                        if(!cargo_to.getSelectedItem().toString().equals("")){

                                cargo_mode.removeAllItems();
                                String from = cargo_from.getSelectedItem().toString();
                                String to = cargo_to.getSelectedItem().toString();
                                
                                System.out.println(" "+from+"  "+to);
                                list3 = dbo.getMode(from, to);
                                for (int i = 0; i < list3.size(); i++) {            
                                    cargo_mode.addItem(list3.get(i));            
                                }
                        }
                    }
                }                    
                }
                catch(Exception ex){
                    System.out.println("2 ----------------------->   "+ex);
                }                


            }
        });
        
        cargo_date = new JDateChooser();
        Date date = new Date();
        cargo_date.setDate(date);
        cargo_date.setBounds(30, 255, 200, 50);
        cargo_date.setFont(new Font("arial", 25, 15));
        cargo_date.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "DATE", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_date.setDateFormatString("yyyy-MM-dd");
        cargo_information.add(cargo_date);
        System.err.println(d.format(cargo_date.getDate()));
        
        String commoditys[]={"Food Stuff","Livestock and Animals","LNG, CNG, and Other Gas-Based Fuels","Shipping Cars and Other Vehicles","Machinery, Equipment, and Factory Parts","Shipping Dry Bulk Cargo","Shipping Liquid Bulk Cargo","Chemical, Hazardous, and Toxic Products","Others"}; 
        commodity=new JComboBox<>(commoditys);
        commodity.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "COMMODITY", TitledBorder.LEFT, TitledBorder.TOP));
        commodity.setBounds(335, 255, 200, 50);
        commodity.setForeground(Color.BLACK);
        commodity.setBackground(Color.WHITE);
        cargo_information.add(commodity);
        
        cargo_d=new JLabel("CARGO DETAILS");
        cargo_d.setBounds(30, 350, 300, 50);
        cargo_d.setForeground(Color.black);
        cargo_d.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cargo_information.add(cargo_d);
        
        cargo_quantity=new JTextField();
        cargo_quantity.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "QUANTITY", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_quantity.setBounds(30,420,200,50);
        cargo_quantity.setForeground(Color.BLACK);
        cargo_quantity.setBackground(Color.WHITE);
        cargo_information.add(cargo_quantity);
        
        String contype[]={"20' Standard Container","40' Standard Container","40 high cube Standard Container","Others"};  
        container_type=new JComboBox<>(contype);
        container_type.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "CONTAINER TYPE", TitledBorder.LEFT, TitledBorder.TOP));
        container_type.setBounds(335,420,200,50);
        container_type.setForeground(Color.BLACK);
        container_type.setBackground(Color.WHITE);
        cargo_information.add(container_type);
        
        
        
        cargo_gross_wt=new JTextField();
        cargo_gross_wt.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "WEIGHT", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_gross_wt.setBounds(620,420,100,50);
        cargo_gross_wt.setForeground(Color.BLACK);
        cargo_gross_wt.setBackground(Color.WHITE);
        cargo_information.add(cargo_gross_wt);
        
        String metric[]={"KG","MT"}; 
        cargo_gross_mt=new JComboBox<>(metric);
        cargo_gross_mt.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "METRIC UNIT", TitledBorder.LEFT, TitledBorder.TOP));
        cargo_gross_mt.setBounds(720,420,100,50);
        cargo_gross_mt.setForeground(Color.BLACK);
        cargo_gross_mt.setBackground(Color.WHITE);
        cargo_information.add(cargo_gross_mt);
        
        cargo_finish=new JLabel("FINISH");
        cargo_finish.setBounds(700, 658, 120, 40);
        cargo_finish.setForeground(Color.white);
        cargo_finish.setBackground(Color.GREEN);
        cargo_finish.setOpaque(true);
        cargo_finish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cargo_finish.setHorizontalAlignment(JLabel.CENTER);
        cargo_finish.setFont(new Font("Arial", Font.BOLD, 20));
        cargo_finish.addMouseListener(this);
        cargo_information.add(cargo_finish);
        
        cargo_back=new JLabel("BACK");
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
        
        payment=new JPanel();
        payment.setBounds(width/3, 0, 854, height);
        payment.setLayout(null);
        payment.setVisible(true);
        payment.setBackground(bg);
        co.add(payment);
        
        payment_title=new JLabel("PAYMENT");
        payment_title.setBounds(177, 20, 500, 50);
        payment_title.setForeground(Color.RED);
        payment_title.setHorizontalAlignment(JLabel.CENTER);
        payment_title.setFont(new Font("Arial", Font.BOLD, 35));
        payment.add(payment_title);

        pay_cus_l=new JLabel("CUSTOMER ID   :");
        pay_cus_l.setFont(new Font("Arial", 25, 20));
        pay_cus_l.setBounds(30,90,200,50);
        pay_cus_l.setForeground(Color.BLACK);
        pay_cus_l.setBackground(Color.WHITE);
        payment.add(pay_cus_l);
        
        pay_cus_id=new JLabel("#1234");
        pay_cus_id.setFont(new Font("Arial", 25, 20));
        pay_cus_id.setBounds(250,90,200,50);
        pay_cus_id.setForeground(Color.BLACK);
        pay_cus_id.setBackground(Color.WHITE);
        payment.add(pay_cus_id);
        
        pay_amt_l=new JLabel("AMOUNT   :");
        pay_amt_l.setBounds(550,150,200,50);
        pay_amt_l.setFont(new Font("Arial", 25, 20));
        payment.add(pay_amt_l);

        pay_amt=new JLabel("₹4500");
        pay_amt.setFont(new Font("Arial", 25, 20));
        pay_amt.setBounds(680,150,200,50);
        pay_amt.setForeground(Color.BLACK);
        pay_amt.setBackground(Color.WHITE);
        payment.add(pay_amt);
        
        pay_date_l=new JLabel("DATE        :");
        pay_date_l.setBounds(550,90,200,50);
        pay_date_l.setFont(new Font("Arial", 25, 20));
        payment.add(pay_date_l);
        
        pay_date=new JLabel(d.format(date));
        pay_date.setFont(new Font("Arial", 25, 20));
        pay_date.setBounds(680,90,200,50);
        pay_date.setForeground(Color.BLACK);
        pay_date.setBackground(Color.WHITE);
        payment.add(pay_date);
        
        transport_l=new JLabel("TRANSPORT ID  :");
        transport_l.setFont(new Font("Arial", 25, 20));
        transport_l.setBounds(30,150,200,50);
        transport_l.setForeground(Color.BLACK);
        transport_l.setBackground(Color.WHITE);
        payment.add(transport_l);
        
        transport_id=new JLabel("#1234");
        transport_id.setFont(new Font("Arial", 25, 20));
        transport_id.setBounds(250,150,200,50);
        transport_id.setForeground(Color.BLACK);
        transport_id.setBackground(Color.WHITE);
        payment.add(transport_id);
        
        
        
        
        
        
        
        payment_box=new JLabel("");
        payment_box.setFont(new Font("Arial", 25, 20));
        payment_box.setBounds(272,250,310,440);
        payment_box.setBorder(b);
        payment_box.setForeground(Color.BLACK);
        payment_box.setBackground(Color.WHITE);
        payment.add(payment_box);
        
        paymethod_l=new JLabel("PAYMENT DETAILS");
        paymethod_l.setFont(new Font("Arial", Font.BOLD, 20));
        paymethod_l.setBounds(30,10,250,50);
        paymethod_l.setHorizontalAlignment(JLabel.CENTER);
        paymethod_l.setForeground(Color.RED);
        paymethod_l.setBackground(Color.WHITE);
        payment_box.add(paymethod_l);
        
        String paymethod[]={"DEBIT CARD","CREDIT CARD"}; 
        PAYMETHOD=new JComboBox<>(paymethod);
        PAYMETHOD.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "PAYMENT METHOD", TitledBorder.LEFT, TitledBorder.TOP));
        PAYMETHOD.setBounds(30,60,250,50);
        PAYMETHOD.setForeground(Color.BLACK);
        PAYMETHOD.setBackground(Color.WHITE);
        payment_box.add(PAYMETHOD);
        
        card_no_valid =new JLabel("");
        card_no_valid.setForeground(Color.RED);
        card_no_valid.setBounds(30, 165, 250, 25);
        payment_box.add(card_no_valid);
        card_no=new JTextField(16);
        card_no.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "CARD NUMBER", TitledBorder.LEFT, TitledBorder.TOP));
        card_no.setBounds(30,120,250,50);
        card_no.setForeground(Color.BLACK);
        card_no.setBackground(Color.WHITE);
        card_no.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = card_no.getText();
            int l = value.length();
            System.err.println(l);
            if(l==4 || l==9 || l==14 ){
               card_no.setText(value+"-");
            }else{
               card_no.setText(value);
            }
            
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l<19  || ke.getKeyCode()  == KeyEvent.VK_BACK_SPACE ) {
               card_no.setEditable(true);
               card_no_valid.setText("");
            } else {
               card_no.setEditable(false);
               if(l==19){
                   card_no_valid.setText("");
               }else{
                   card_no_valid.setText("* Enter only numeric digits(0-9)");
               }
            }
            
         }
      });
        
        card_month=new JTextField(16);
        card_month.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "MM/YY", TitledBorder.LEFT, TitledBorder.TOP));
        card_month.setBounds(30,190,100,50);
        card_month.setForeground(Color.BLACK);
        card_month.setBackground(Color.WHITE);
        card_month.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = card_month.getText();
            int l = value.length();
             System.err.println(l);
            if(l==2){
                card_month.setText(value+"/");
            }else{
                card_month.setText(value);
            }
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l<5 || ke.getKeyCode()  == KeyEvent.VK_BACK_SPACE  ||ke.getKeyCode()==KeyEvent.VK_BACK_SLASH ) {
               card_month.setEditable(true);
            } else {
               card_month.setEditable(false);
            }
         }
      });
        
        card_ccv=new JPasswordField();
        card_ccv.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "CCV", TitledBorder.LEFT, TitledBorder.TOP));
        card_ccv.setBounds(180,190,100,50);
        card_ccv.setForeground(Color.BLACK);
        card_ccv.setBackground(Color.WHITE);
        card_ccv.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = card_ccv.getText();
            int l = value.length();
             System.err.println(l);
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l<3 || ke.getKeyCode()  == KeyEvent.VK_BACK_SPACE  ||ke.getKeyCode()==KeyEvent.VK_BACK_SLASH ) {
               card_ccv.setEditable(true);
            } else {
               card_ccv.setEditable(false);
            }
         }
      });
        
        card_holder=new JTextField(16);
        card_holder.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "CARD HOLDER", TitledBorder.LEFT, TitledBorder.TOP));
        card_holder.setBounds(30,250,250,50);
        card_holder.setForeground(Color.BLACK);
        card_holder.setBackground(Color.WHITE);
        
        card_email=new JTextField(16);
            card_email.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "CARD EMAIL", TitledBorder.LEFT, TitledBorder.TOP));
        card_email.setBounds(30,310,250,50);
        card_email.setForeground(Color.BLACK);
        card_email.setBackground(Color.WHITE);
        
        
        card_conform_btn =new JLabel("CONFIRM PAYMENT");
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
        
        
        
//        payment verify panel
        payment_verify=new JPanel();
        payment_verify.setBounds(width/3, 0, 854, height);
        payment_verify.setLayout(null);
        payment_verify.setVisible(true);
        payment_verify.setBackground(bg);
        co.add(payment_verify);
        
        
        
        
        
        
        
        
        
        
        cargo.setVisible(true);
        cargo_information.add(cargo_from);
        cargo_information.add(cargo_to);
        cargo_information.add(cargo_mode);
        setSize(width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new cargo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj_source=e.getSource();
        if(obj_source==cargo_next){
            cargo.setVisible(false);
            cargo_information.setVisible(true);
        }else if(obj_source==cargo_back){
            cargo.setVisible(true);
            cargo_information.setVisible(false);
        }else if(obj_source==card_conform_btn){
            System.err.println(PAYMETHOD.getSelectedItem());
            if(!card_no.getText().equals("")){
                if(!card_month.getText().equals("")){
                    if(!card_ccv.getText().equals("")){
                        if(!card_holder.getText().equals("")){
                            if(!card_email.getText().equals("")){
                                String c_from=cargo_from.getSelectedItem().toString();
                                String c_to=cargo_to.getSelectedItem().toString();
                                String c_mode=cargo_mode.getSelectedItem().toString();
                                String c_date=d.format(cargo_date.getDate());
                                String c_com=commodity.getSelectedItem().toString();
                                String c_quan=cargo_quantity.getText();
                                String c_container=container_type.getSelectedItem().toString();
                                String c_gross_weight=cargo_gross_wt.getText();
                                String c_gross_mt=cargo_gross_mt.getSelectedItem().toString();
                                System.err.println("fn");
                                String g_weight=c_gross_weight+c_gross_mt;
                                Date d1=new Date();
                                LocalDate date1 = LocalDate.parse(d.format(d1));
                                LocalDate date2 = date1.plusDays(dbo.days(c_from, c_to, c_mode));
                                System.err.println(date2);
                                String d3=c_date;
                                String d4=date2.toString();
                                Date pay_date=new Date();
                                SendOtp otp=new SendOtp();
                                int n=otp.otp_generate(card_email.getText());
                                String name=JOptionPane.showInputDialog(null,"Enter OTP");
                                if(Integer.parseInt(name)==n){
                                    JOptionPane.showMessageDialog(null,"PAYMENT SUCCESSFULL" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                                    billing_id_rand=customer_rand.nextInt(999999)+100000;
                                    transaction_id_rand=customer_rand.nextInt(999999)+100000;
                                    dbo.cargo_db(256, Integer.parseInt(transport_id.getText()), c_mode, c_from, c_to, c_com,c_quan, c_container, g_weight, c_date, d4);
                                    bill=new Pdf();
                                    bill.writeUsingIText(card_holder.getText(), card_email.getText(), billing_id_rand, c_container, c_from, c_to, pay_amounts);
                                    dbo.transaction(256, transaction_id_rand, Integer.parseInt(transport_id.getText()), billing_id_rand, pay_amounts,d.format(pay_date) , PAYMETHOD.getSelectedItem().toString());
                                    dbo.billing(256, billing_id_rand, transaction_id_rand,card_holder.getText() , card_email.getText());
                                    System.err.println(billing_id_rand);
                                    cargo.setVisible(true);
                                    payment.setVisible(false);
                                }else{
                                    clearpaymentField();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"ENTER ALL DETIALS" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"ENTER ALL DETIALS" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"ENTER ALL DETIALS" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"ENTER ALL DETIALS" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"ENTER ALL DETIALS" ,"PAYMENT", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else if(obj_source==cargo_finish){
            if(!cargo_from.getSelectedItem().equals("")){
                if(!cargo_to.getSelectedItem().equals("")){
                    if(!cargo_mode.getSelectedItem().equals("")){
                        if(!cargo_date.equals("")){
                            if(!commodity.getSelectedItem().equals("")){
                                if(!cargo_quantity.getText().equals("")){
                                    if(!container_type.getSelectedItem().equals("")){
                                        if(!cargo_gross_wt.getText().equals("")){
                                            if(!cargo_gross_mt.getSelectedItem().equals("")){
                                                transport_id_rand=customer_rand.nextInt(999999)+100000;
                                                transport_id.setText(Integer.toString(transport_id_rand));
                                                payment.setVisible(true);
                                                double amount=dbo.price(cargo_from.getSelectedItem().toString(), cargo_to.getSelectedItem().toString(), cargo_mode.getSelectedItem().toString());
                                                pay_amounts=amount* Double.parseDouble(cargo_quantity.getText());
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
    public void clearpaymentField(){
        card_no.setText("");
        card_month.setText("");
        card_ccv.setText("");
        card_holder.setText("");
        card_email.setText("");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
