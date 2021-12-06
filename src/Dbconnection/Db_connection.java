/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chandru
 */
public class Db_connection {
    private static Connection connection;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargo","root", "Chandru@123");
                        connection.setAutoCommit(true);
		}
		catch (Exception e) {
			System.out.println(" "+e);
		}
	}
	public static Connection getConnection()
	{
		return connection;
	}
        public static void main(String[] args) {
        
    }
}
