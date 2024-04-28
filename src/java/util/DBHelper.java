package util;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable {
   public static Connection createConnection() throws /*ClassNotFoundException*/NamingException, SQLException{
        
        //1. Opem current Context
        Context currentContext =new InitialContext();
        //2. Look up tomcat context
        Context tomcatContext= (Context)currentContext.lookup("java:comp/env");
        //3. Look datasource
        DataSource ds= (DataSource)tomcatContext.lookup("SE1709");
        //4.Open Connection
        Connection con=  ds.getConnection();
        return con;
        
//        //1.Load Drivers
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Crete connection
//        String url="jdbc:sqlserver://localhost:1433;databaseName=SE1709";
//                //3.Open connection
//                Connection con =DriverManager.getConnection(url, "sa", "123cuongLO");
//               
//                return con;
    }
    
}
