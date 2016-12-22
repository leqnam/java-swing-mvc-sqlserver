/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nle28
 */
public class connectDB {
    public static Connection getConnection(){
        Connection conn = null;
        String database = "QLSanpham";
        try {
           // Class.forName("com.mysql.jdbc.Driver"); //My SQL
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + database, "root", "usbw");
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //MS SQL Server
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName="+ database +";", "sa", "P@ssword123");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Ket noi CSDL that bai","Thong bao",1); 
        }
        return conn;
    }
    
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
    
}
