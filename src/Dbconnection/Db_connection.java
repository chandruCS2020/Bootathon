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
			Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","chandru");
		}
		catch (Exception e) {
			System.out.println(" "+e);
		}
	}
	public static Connection getConnection()
	{
		return connection;
	}
}
