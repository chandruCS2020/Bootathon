package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import passwordHash.Passwordhashing;

public class DBOperations {
    
    List<String> list1,list2,list3;
    
    public List<String> getFroms(){
        try{
            Connection con = Dbconnection.Db_connection.getConnection();
            Statement st=con.createStatement();

            list1 = new ArrayList<>();
            
            ResultSet rs=st.executeQuery("select distinct froms from transport");

            while(rs.next()){
                list1.add(rs.getString("froms"));
                System.err.println("hi");
            }
        }
        catch(Exception e) {
            System.out.println("     "+e);
        }
        return list1;
    }
    
    public List<String> getTos(){
        try{
            Connection con = Dbconnection.Db_connection.getConnection();
            Statement st=con.createStatement();

            list2 = new ArrayList<>();

            ResultSet rs=st.executeQuery("select distinct tos from transport");

            while(rs.next()){
                list2.add(rs.getString("tos"));
            }
        }
        catch(Exception e) {
            System.out.println("     "+e);
        }
        return list2;
    }    
    
    public List<String> getMode(String from,String to){
        try{
            Connection con = Dbconnection.Db_connection.getConnection();
            Statement st=con.createStatement();

            list3 = new ArrayList<>();
            
            ResultSet rs=st.executeQuery("select mode from transport where froms='"+from+"' and tos='"+to+"'");

            while(rs.next()){
                list3.add(rs.getString("mode"));
            }
        }
        catch(Exception e) {
            System.out.println("     "+e);
        }
        return list3;
    }      
    public int days(String from,String to,String mode){
        int days=0;
        try{
            Connection con = Dbconnection.Db_connection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select day from transport where froms='"+from+"' and tos='"+to+"' and mode='"+mode+"'");

            while(rs.next()){
                days=rs.getInt("day");
            }
        }
        catch(Exception e) {
            System.out.println("     "+e);
        }
        return days;
    }
    public double price(String from,String to,String mode){
        double amount=0;
        try{
            Connection con = Dbconnection.Db_connection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select amount from transport where froms='"+from+"' and tos='"+to+"' and mode='"+mode+"'");

            while(rs.next()){
                amount=rs.getDouble("amount");
            }
        }
        catch(Exception e) {
            System.out.println("     "+e);
        }
        return amount;
    }
    public void cargo_db(int c_id,int t_id,String mode,String from,String to
            ,String commodity,String quantity,String c_type,String g_wieght,String date,String e_date)
    {
        try {
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("insert into cargo values(?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, c_id);
            st.setInt(2, t_id);
            st.setString(3, mode);
            st.setString(4,from );
            st.setString(5, to);
            st.setString(6, commodity);
            st.setInt(7, Integer.parseInt(quantity));
            st.setString(8, c_type);
            st.setString(9, g_wieght);
            st.setString(10,date);
            st.setString(11, e_date);
            st.executeUpdate();
            System.err.println("cargo done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        
        
    }
    public void transaction(int c_id,int t_id,int tp_id,int b_id,double amount,String date,String payment)
    {
        try {
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?)");
            st.setInt(1, c_id);
            st.setInt(2, t_id);
            st.setInt(3, tp_id);
            st.setInt(4,b_id );
            st.setDouble(5, amount);
            st.setString(6, date);
            st.setString(7, payment);
            st.executeUpdate();
            System.err.println("transaction done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        
        
    }
    public void billing(int c_id,int b_id,int t_id,String bill_name,String bill_email)
    {
        FileInputStream fis;
        try {
            String filename = "D:\\New folder\\ezhil.pdf";
            File image = new File(filename);
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("insert into billing values(?,?,?,?,?,?)");
            st.setInt(1, c_id);
            st.setInt(2,b_id );
            st.setInt(3, t_id);
            st.setString(4, bill_name);
            st.setString(5, bill_email);
            fis = new FileInputStream(image);
            st.setBinaryStream(6, (InputStream)fis, (int)(image.length()));
            st.executeUpdate();
            System.err.println("billing done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        
        
    }
    public void update(String uname,String name,String email,String pass,String phone,String address,int id)
    {
        passwordHash.Passwordhashing sc=new Passwordhashing();
        try {
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE cargo_admin SET customer_uname=?,customer_name = ?,customer_email=?,customer_pass=?,customer_phone=?,customer_address=? WHERE customer_id = ?");
            st.setString(1, uname);
            st.setString(2, name);
            st.setString(3, email);
            st.setString(4,sc.encrypt(pass));
            st.setLong(5, Long.parseLong(phone));
            st.setString(6, address);
            st.setInt(7, id);
            st.executeUpdate();
            System.err.println("update done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        
        
    }
    public void delete(int id)
    {
        passwordHash.Passwordhashing sc=new Passwordhashing();
        try {
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("delete from cargo_admin where customer_id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            System.err.println("update done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        
        
    }
    public int update_pass(String pass,String email){
        passwordHash.Passwordhashing sc=new Passwordhashing();
        try {
            Connection con = Dbconnection.Db_connection.getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE cargo_admin SET customer_pass = ? WHERE customer_email= ?");
            st.setString(1, pass);
            st.setString(2, email);
            st.executeUpdate();
            System.err.println("update forget password done");

            
        } catch (Exception e) {
            System.out.println("     "+e);
        }
        return 1;
    }
    public static void main(String[] args) {
        new DBOperations();
    }
    
}
