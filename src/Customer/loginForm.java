/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        JPanel i=new JPanel();
        i.setSize(500, 768);
        i.setLayout(null);
        i.setBackground(Color.red);
        ImageIcon pass_img =new ImageIcon("C:\\Users\\chandru\\Downloads\\icons8-sign-in-form-password-48.png");
        JLabel pass_i=new JLabel(pass_img);
        pass_i.setBounds(70, 70, 100, 50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        pass_i.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                    customer.signInForm s=new customer.signInForm();
                }

            });
        i.add(pass_i);
        
        co.add(i);
        

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
