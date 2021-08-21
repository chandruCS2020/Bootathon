package Customer;
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
import java.util.regex.Pattern;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;
import javax.swing.JOptionPane;
public class signInForm extends JFrame implements ActionListener,ItemListener{
    Container co;
    private String outcome;
    int otp;

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
        ImageIcon image =new ImageIcon("src\\images\\Background.jpg");
        JLabel commentlabel = new JLabel(image);
        commentlabel.setSize(width/2, height);
        img_pane.add(commentlabel);
        co.add(img_pane);    
//        jpanel 2
        JPanel signIn=new JPanel();
        signIn.setBounds(width/2,0,width/2,height);
        signIn.setBackground(new Color(233,239,248));
        signIn.setLayout(null);
//        jpanel 2 title
        JLabel Title=new JLabel("Cargo Logistics Management");
        Title.setFont(new java.awt.Font("Harlow Solid Italic", 2, 45));
        Title.setBounds(20,20 , 600, 70);
        Title.setHorizontalAlignment(JLabel.CENTER);
        signIn.add(Title);
//        jpanel 2 name section
        ImageIcon uname_img=new ImageIcon("src\\images\\user.png");
        JLabel User_img=new JLabel(uname_img);
        User_img.setBounds(50,120,50,50);
        User_img.setHorizontalAlignment(JLabel.CENTER);
        User_img.setVerticalAlignment(JLabel.CENTER);
        signIn.add(User_img);
        
        JLabel name=new JLabel("Name");
        name.setBounds(120, 120, 150, 50);
        name.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(name);
        
        
        JTextField tuname=new JTextField();
        tuname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder = tuname.getBorder();
        Border redBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder = BorderFactory.createCompoundBorder(redBorder, oldBorder);
        tuname.setBorder(newBorder);
        tuname.setBackground(new Color(233,239,248));
        tuname.setBounds(290, 125, 280, 35);
        tuname.setFont(new java.awt.Font("Arial",25,18));
        signIn.add(tuname);
        
        ImageIcon Mobile_img=new ImageIcon("src\\images\\phone.png");
        JLabel mobile_i=new JLabel(Mobile_img);
        mobile_i.setBounds(50,180,50,50);
        mobile_i.setHorizontalAlignment(JLabel.CENTER);
        mobile_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(mobile_i);
        
        JLabel Mobile=new JLabel("Mobile Number");
        Mobile.setBounds(120, 180, 150, 50);
        Mobile.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(Mobile);
        
        JTextField tmobile=new JTextField();
        tmobile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b1 = tmobile.getBorder();
        Border rb1 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border nb1 = BorderFactory.createCompoundBorder(rb1, b1);
        tmobile.setBorder(nb1);
        tmobile.setBackground(new Color(233,239,248));
        tmobile.setBounds(290, 185, 280, 35);
        tmobile.setFont(new java.awt.Font("Arial",25,18));
        
        JLabel mobile_validate=new JLabel("* invalid phone number");
        mobile_validate.setBounds(290, 220,280, 20);
        mobile_validate.setForeground(Color.red);
        mobile_validate.setVisible(false);
        signIn.add(mobile_validate);
        tmobile.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
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
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
    
    });
        signIn.add(tmobile);
        
        ImageIcon user_img=new ImageIcon("src\\images\\user_id.png");
        JLabel used_id=new JLabel(user_img);
        used_id.setBounds(50,250,50,50);
        used_id.setHorizontalAlignment(JLabel.CENTER);
        used_id.setVerticalAlignment(JLabel.CENTER);
        signIn.add(used_id);
        
        JLabel user=new JLabel("User Name");
        user.setBounds(120, 250, 150, 50);
        user.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(user);
        
        JTextField tuser=new JTextField();
        tuser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b2 = tuser.getBorder();
        Border rb2 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border nb2= BorderFactory.createCompoundBorder(rb2, b2);
        tuser.setBorder(nb2);
        tuser.setBackground(new Color(233,239,248));
        tuser.setBounds(290, 250, 280, 35);
        tuser.setFont(new java.awt.Font("Arial",25,18));
        
        JLabel user_validate=new JLabel("* User name already exist");
        user_validate.setBounds(290, 290,280, 20);
        user_validate.setForeground(Color.red);
        user_validate.setVisible(false);
        signIn.add(user_validate);
        signIn.add(tuser);
        
        
        ImageIcon email_img =new ImageIcon("src\\images\\email.png");
        JLabel Email_i=new JLabel(email_img);
        Email_i.setBounds(50,320,50,50);
        Email_i.setHorizontalAlignment(JLabel.CENTER);
        Email_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(Email_i);
        
        JLabel email=new JLabel("Email");
        email.setBounds(120, 320, 150, 50);
        email.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(email);
        
        JTextField temail=new JTextField();
        temail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b3 = temail.getBorder();
        Border rb3 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border nb3 = BorderFactory.createCompoundBorder(rb3, b3);
        temail.setBorder(nb3);
        temail.setBackground(new Color(233,239,248));
        
        temail.setBounds(290, 320, 280, 35);
        temail.setFont(new java.awt.Font("Arial",25,18));
//        invalid email
        JLabel valid_email=new JLabel("* Invalid Email Address");
        valid_email.setBounds(290, 355, 280, 35);
        valid_email.setForeground(Color.red);
        valid_email.setVisible(false);
        signIn.add(valid_email);
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
        signIn.add(temail);
        
        
        
        
//        password section
        ImageIcon pass_img =new ImageIcon("src\\images\\password.png");
        JLabel pass_i=new JLabel(pass_img);
        pass_i.setBounds(50,380,50,50);
        pass_i.setHorizontalAlignment(JLabel.CENTER);
        pass_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(pass_i);
       
        JLabel pass=new JLabel("Password");
        pass.setBounds(120, 380, 150, 50);
        pass.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(pass);
        
        JPasswordField tpass=new JPasswordField();
        tpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border oldBorder1 = tpass.getBorder();
        Border redBorder1 = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
        Border newBorder1 = BorderFactory.createCompoundBorder(redBorder1, oldBorder1);
        tpass.setBorder(newBorder1);
        tpass.setBackground(new Color(233,239,248));
        tpass.setForeground(Color.black);
        tpass.setBounds(290, 395, 280, 20);
        tpass.setFont(new java.awt.Font("Arial",25,18));
        
        JLabel pass_validate=new JLabel("Enter a weak Password");
        pass_validate.setBounds(290, 420, 280, 20);
        pass_validate.setForeground(Color.red);
        pass_validate.setVisible(false);
        signIn.add(pass_validate);
        
        tpass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                System.out.println(Pattern.matches(password_regex,"Chandru@123"));
                if (!Pattern.matches(password_regex,text)) {
                    pass_validate.setText("Enter a weak Password");
                    pass_validate.setForeground(Color.red);
                   pass_validate.setVisible(true);
                   tpass.setForeground(Color.red);
                    return;
                } else {
                    
                    pass_validate.setText("Entered strong password");
                    pass_validate.setForeground(Color.green);
                    tpass.setForeground(Color.black);
                } 
                
            }
            
});
        signIn.add(tpass);
        
        JCheckBox checkpass = new JCheckBox();
        checkpass.setIcon(new ImageIcon("src\\images\\eyeclose.png"));
        checkpass.setSelectedIcon(new ImageIcon("src\\images\\eyeopen.png"));
        checkpass.addActionListener(ae -> {
         JCheckBox c = (JCheckBox) ae.getSource();
         tpass.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        checkpass.setBounds(575, 385, 50, 25);
        checkpass.setFont(new java.awt.Font("Arial",5,15));
        checkpass.setBackground(new Color(233,239,248));
        signIn.add(checkpass);
        
        ImageIcon address_img =new ImageIcon("src\\images\\address.png");
        JLabel address_i=new JLabel(address_img);
        address_i.setBounds(50,450,50,50);
        address_i.setHorizontalAlignment(JLabel.CENTER);
        address_i.setVerticalAlignment(JLabel.CENTER);
        signIn.add(address_i);
        
         JLabel address=new JLabel("Address");
        address.setBounds(120, 450, 150, 50);
        address.setFont(new java.awt.Font("Arial",25,20));
        signIn.add(address);
        
        JTextArea tarea=new JTextArea();  
        JScrollPane tareas=new JScrollPane(tarea);
        tarea.setLineWrap(true);
        tareas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tareas.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Border b4 = tareas.getBorder();
        Border rb4 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
        Border nb4 = BorderFactory.createCompoundBorder(rb4, b4);
        tareas.setBorder(nb4);
        tarea.setBackground(new Color(233,239,248));
        tarea.setForeground(Color.black);
        tareas.setBounds(290, 465, 280, 130);
        tareas.setFont(new java.awt.Font("Arial",25,18));
        tareas.setVisible(true);
        signIn.add(tareas);
        
        
        JButton create=new JButton("Create");
        create.setBounds(290, 620, 175, 35);
        create.setFont(new java.awt.Font("Arial",Font.BOLD,15));
        create.setForeground(Color.white);
        create.setBackground(Color.blue);
        create.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        create.setHorizontalAlignment(JLabel.CENTER);
        create.setVerticalAlignment(JLabel.CENTER);
        create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                create.setBackground(new Color(92, 112, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                create.setBackground(Color.BLUE);
            }
        });
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
			// Construct data
			String apiKey = "apikey=" + "NTk1MzQ1NDQ0NjYyNDQ1MjM1NGIzMDM3MzYzMDVhNzE=";
                        Random rand=new Random();
                        otp=rand.nextInt(9999999);
                        String name=tuname.getText();
                        System.out.println(name+" "+otp);
			String message = "&message=" + "Hey "+name+" your otp is "+otp;
			String sender = "&sender=" + "Cargo";
			String numbers = "&numbers=" + tmobile.getText();
                        
                        System.out.println(tmobile.getText());
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
                        System.out.println(data);
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
                                 JOptionPane.showMessageDialog(null, "otp send successfully");
			}
			rd.close();
			                JOptionPane.showMessageDialog(null, "otp send successfully");
//			return stringBuffer.toString();
		} catch (Exception ae) {
			JOptionPane.showMessageDialog(null, "Error sms"+ae);
                        JOptionPane.showMessageDialog(null, "Error "+ae);
//			return "Error "+ae;
		}
            }
            
        });
        signIn.add(create);
        
        
        JLabel Login_link=new JLabel("Already have an account?");
        Login_link.setBounds(290, 670, 150, 30);
        Login_link.setForeground(Color.BLUE.darker());
        signIn.add(Login_link);
        
        JLabel Login_btn=new JLabel("Log in");
        Login_btn.setBounds(440, 670, 150, 30);
        Login_btn.setForeground(Color.red);
        Login_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Login_btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   
                    setVisible(false);
                    loginForm sc=new loginForm();
                    sc.setVisible(true);
                     dispose();
                }

            });
        signIn.add(Login_btn);
        
        
        
        
        
        co.add(signIn);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cargo Logistics Management");
        setSize(width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        validate();
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