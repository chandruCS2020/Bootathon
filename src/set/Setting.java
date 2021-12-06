/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package set;

import Bean.JBean;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import passwordHash.Passwordhashing;

/**
 *
 * @author chandru
 */
public class Setting extends JPanel implements ActionListener,KeyListener,MouseListener{
    JLabel settings_title,title_img;
    JLabel update,delete;
    JLabel uname_validate,pass_validate,mobile_validate;
    JLabel s_uname,s_pass,s_email,s_address,s_name,s_mobile,si_uname,si_pass,si_email,si_address,si_name,si_mobile;
    JTextField ts_uname,ts_email,ts_name,ts_mobile;
    JPasswordField ts_pass;
    JTextArea ts_address;
    JCheckBox eye;
    JBean ob1=new JBean();
    Connection con = Dbconnection.Db_connection.getConnection();
    Passwordhashing sc=new Passwordhashing();
    Border b = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
    int width=1280;
    int height=768;
    Passwordhashing s=new Passwordhashing();
    public Setting(JBean ob) {
        //ob.setCustomer_email("chandru");
        setBounds(width/3, 0, 854, height);
        setBackground(new Color(233,239,248));
        title_img=new JLabel(new ImageIcon("src\\images\\settings.png"));
        title_img.setBounds(10, 20, 50, 50);
        add(title_img);
        System.err.println(ob.getCustomer_email());
        
        settings_title=new JLabel("SETTINGS");
        settings_title.setBounds(70, 20, 200, 50);
        settings_title.setFont(new Font("Arial", Font.BOLD, 35));
        add(settings_title);
        
        
        si_uname=new JLabel(new ImageIcon("src\\images\\user_id.png"));
        si_uname.setBounds(10, 100, 50,50);
        add(si_uname);
        
        s_uname=new JLabel("USERNAME");
        s_uname.setBounds(70, 100, 200, 50);
        s_uname.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_uname);
        
        ts_uname=new JTextField(ob.getCustomer_uname());
        ts_uname.setBounds(280, 100, 300, 45);
        ts_uname.setBorder(BorderFactory.createEmptyBorder());
        ts_uname.setFont(new Font("arial", Font.BOLD, 20));
        ts_uname.setBackground(new Color(233,239,248));
        ts_uname.setBorder(b);
        ts_uname.setEnabled(true);
        add(ts_uname);
        
        uname_validate=new JLabel("* user name already exist");
        uname_validate.setBounds(280, 145, 300, 20);
        uname_validate.setForeground(Color.red);
        uname_validate.setVisible(false);
        add(uname_validate);
        
        si_name=new JLabel(new ImageIcon("src\\images\\user.png"));
        si_name.setBounds(10, 170, 50,50);
        add(si_name);
        
        s_name=new JLabel("NAME");
        s_name.setBounds(70, 170, 200, 50);
        s_name.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_name);
        
        ts_name=new JTextField(ob.getCustomer_name());
        ts_name.setBounds(280, 170, 300, 45);
        ts_name.setBorder(BorderFactory.createEmptyBorder());
        ts_name.setFont(new Font("arial", Font.BOLD, 20));
        ts_name.setBackground(new Color(233,239,248));
        ts_name.setBorder(b);
        ts_name.setEnabled(true);
        add(ts_name);
        
        si_email=new JLabel(new ImageIcon("src\\images\\email.png"));
        si_email.setBounds(10, 230, 50,50);
        add(si_email);
        
        s_email=new JLabel("EMAIL");
        s_email.setBounds(70, 240, 200, 50);
        s_email.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_email);
        
        ts_email=new JTextField(ob.getCustomer_email());
        ts_email.setBounds(280, 230, 300, 45);
        ts_email.setBorder(BorderFactory.createEmptyBorder());
        ts_email.setFont(new Font("arial", Font.BOLD, 20));
        ts_email.setBackground(new Color(233,239,248));
        ts_email.setBorder(b);
        ts_email.setEnabled(true);
        add(ts_email);
        
        si_pass=new JLabel(new ImageIcon("src\\images\\password.png"));
        si_pass.setBounds(10, 300, 50,50);
        add(si_pass);
        
        s_pass=new JLabel("PASSWORD");
        s_pass.setBounds(70, 300, 200, 50);
        s_pass.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_pass);
        
        ts_pass=new JPasswordField(s.decrypt(ob.getCustomer_password()));
        ts_pass.setBounds(280, 300, 300, 45);
        ts_pass.setBorder(BorderFactory.createEmptyBorder());
        ts_pass.setFont(new Font("arial", Font.BOLD, 20));
        ts_pass.setBackground(new Color(233,239,248));
        ts_pass.setBorder(b);
        ts_pass.setEnabled(true);
        ts_pass.addKeyListener(this);
        add(ts_pass);
        
        eye=new JCheckBox();
        eye.setIcon(new ImageIcon("src\\images\\eyeclose.png"));
        eye.setSelectedIcon(new ImageIcon("src\\images\\eyeopen.png"));
        eye.setBounds(590, 300, 50, 45);
        eye.addActionListener(this);
        add(eye);
        
        pass_validate=new JLabel();
        pass_validate.setBounds(280, 350, 300, 25);
        add(pass_validate);
        
        
        si_mobile=new JLabel(new ImageIcon("src\\images\\phone.png"));
        si_mobile.setBounds(10, 370, 50,50);
        add(si_mobile);
        
        s_mobile=new JLabel("PHONE");
        s_mobile.setBounds(70, 370, 200, 50);
        s_mobile.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_mobile);
        
        ts_mobile=new JTextField(ob.getCustomer_phone());
        ts_mobile.setBounds(280, 370, 300, 45);
        ts_mobile.setBorder(BorderFactory.createEmptyBorder());
        ts_mobile.setFont(new Font("arial", Font.BOLD, 20));
        ts_mobile.setBackground(new Color(233,239,248));
        ts_mobile.setBorder(b);
        ts_mobile.setEnabled(true);
        ts_mobile.addKeyListener(this);
        add(ts_mobile);
        
        mobile_validate=new JLabel("* enter invalid number");
        mobile_validate.setBounds(280, 415, 300, 25);
        mobile_validate.setVisible(false);
        add(mobile_validate);
        
        si_address=new JLabel(new ImageIcon("src\\images\\address.png"));
        si_address.setBounds(10, 455, 50,50);
        add(si_address);
        
        s_address=new JLabel("ADDRESS");
        s_address.setBounds(70, 455, 200, 50);
        s_address.setFont(new Font("Arial", Font.BOLD, 20));
        add(s_address);
        
        ts_address=new JTextArea(ob.getCustomer_address());  
        JScrollPane tareas=new JScrollPane(ts_address);
        ts_address.setLineWrap(true);
        tareas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tareas.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b4 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
        tareas.setBorder(b4);
        ts_address.setBackground(new Color(233,239,248));
        ts_address.setForeground(Color.black);
        tareas.setBounds(280, 455, 300, 130);
        tareas.setFont(new java.awt.Font("Arial",25,18));
        tareas.setVisible(true);
        add(tareas);
        
        update=new JLabel("UPDATE");
        update.setBounds(280, 600, 300, 55);
        update.setBackground(Color.GREEN);
        update.setOpaque(true);
        update.setFont(new Font("arial", Font.BOLD, 20));
        update.setHorizontalAlignment(JLabel.CENTER);
        update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        update.addMouseListener(this);
        update.setForeground(Color.WHITE);
        add(update);
        
        
        delete=new JLabel("DELETE ACCOUNT");
        delete.setBounds(620, 20, 200, 55);
        delete.setBackground(Color.red);
        delete.setOpaque(true);
        delete.setFont(new Font("arial", Font.BOLD, 15));
        delete.setHorizontalAlignment(JLabel.CENTER);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addMouseListener(this);
        delete.setForeground(Color.WHITE);
        add(delete);
        
        
        
        
        
        
        setLayout(null);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==eye){
            JCheckBox c = (JCheckBox) e.getSource();
            ts_pass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object obj=e.getSource();
        if(obj==ts_pass){
            String password_regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            if (!Pattern.matches(password_regex,text)) {
                pass_validate.setText("Requires 1 Uppercase,1 Symbol and 1 Number");
                pass_validate.setForeground(Color.red);
                pass_validate.setVisible(true);
                ts_pass.setForeground(Color.red);
                return;
            } else {

                pass_validate.setText("Entered strong password");
                pass_validate.setForeground(Color.green);
                ts_pass.setForeground(Color.black);
            } 
        }else if(obj==ts_mobile){
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
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==delete){
            delete.setForeground(Color.green);
        }else if(obj==update){
            update.setForeground(Color.red);
        }
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
        Object obj=e.getSource();
        if(obj==delete){
           delete.setBackground(new Color(242, 119, 119));
        }else if(obj==update){
           update.setBackground(new Color(138, 209, 136));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==delete){
           delete.setBackground(Color.RED);
        }else if(obj==update){
           update.setBackground(Color.GREEN);
        }
    }
}
