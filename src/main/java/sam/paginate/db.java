package sam.paginate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public class db {
    public static Connection mycon(){
        Connection con ;
     
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");
            return con;

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e);
            return null;
        }
     
    }
}
