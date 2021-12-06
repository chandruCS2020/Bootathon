/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;

/**
 *
 * @author chandru
 */
public class JBean implements Serializable{
    private String customer_name,customer_phone,customer_email,customer_password,customer_address,customer_uname,customer_Uid;
    private int customer_id;
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_phone() {
        return customer_phone;
    }
    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
    public String getCustomer_email() {
        return customer_email;
    }
    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
    public String getCustomer_password() {
        return customer_password;
    }
    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }
    public String getCustomer_address() {
        return customer_address;
    }
    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
    public String getCustomer_uname() {
        return customer_uname;
    }
    public void setCustomer_uname(String customer_uname) {
        this.customer_uname = customer_uname;
    }
    public String getCustomer_Uid() {
        return customer_Uid;
    }
    public void setCustomer_Uid(String customer_Uid) {
        this.customer_Uid = customer_Uid;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    
}
