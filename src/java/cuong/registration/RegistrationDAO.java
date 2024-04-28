/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuong.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.Registration;
import util.DBHelper;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {
    public RegistrationDTO checkLogin(String username, String password) throws ClassNotFoundException, SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm= null;
        ResultSet rs=null;
       // boolean result=false;
       RegistrationDTO result=null;
        try{
         //1.Create connection
         con = DBHelper.createConnection();
         if (con !=null){
             
         
        //2.Create SQL String
        String sql ="SELECT lastName, isAdmin "
                +"FROM Registration "
                +"WHERE username = ? "// dau cham hoi danh tu trai sang phai va  so thu tu tinh tu so 1
                +"AND password = ? ";
        
        //3.Create Statement
        stm= con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        //4.Excute Query
        rs = stm.executeQuery();
        //5.Process
        if(rs.next()){
            //mapping
            //5.1 get data from result set
            String fullname=rs.getString("lastName");
            boolean role=rs.getBoolean("isAdmin");
            //5.2 set data to DTO
            result= new RegistrationDTO(username, null, fullname, role); // busoness ko luu password cua user
        }//username and password is exsisted
        } //endconection is opeded
        } finally{
        if( rs != null){
            rs.close();
        }
        if(stm !=null){
            stm.close();
        }
        if(con !=null){
            con.close();
        }
    } 
        return result;
    }
    private List<RegistrationDTO> accounts;
    public  List<RegistrationDTO> getAccounts(){
        return accounts;
    }
       
//         public void SearchLastName(String username, String password) throws ClassNotFoundException, NamingException, SQLException{
//        Connection con=null;
//        PreparedStatement stm= null;
//        ResultSet rs=null;
//        boolean result=false;
//        try{
//            
//        
//         //1.Create connection
//         con = DBHelper.createConnection();
//         if (con !=null){
//             
//         
//        //2.Create SQL String
//        String sql ="Select username "
//                +"From Registration "
//                +"Where username = ? "// dau cham hoi danh tu trai sang phai va  so thu tu tinh tu so 1
//                +"And password = ? ";
//        
//        //3.Create Statement
//        stm= con.prepareStatement(sql);
//        stm.setString(1, username);
//        stm.setString(2, password);
//        //4.Excute Query
//        rs = stm.executeQuery();
//        //5.Process
//        if(rs.next()){
//            result=true;
//        }//username and password is exsisted
//        } //endconection is opeded
//        }finally{
//            if(rs!= null){
//                rs.close();
//            }
//            if(stm !=null){
//                stm.close();
//            }
//                if(con!=null){
//                con.close();
//                }
//        }        
//      
//}

//    public void SearchLastName(String username/*, String password*/) 
//            throws ClassNotFoundException, NamingException, SQLException{
         public void SearchLastName(String searchValue /*, String password*/) 
            throws ClassNotFoundException, SQLException, NamingException{
        
        Connection con=null;
        PreparedStatement stm= null;
        ResultSet rs=null;
       // boolean result=false;
        try{
            
        
         //1.Create connection
         con = DBHelper.createConnection();
         if (con !=null){
             
         
        //2.Create SQL String
        String sql ="SELECT username,password, lastname, isAdmin "
                +"FROM Registration "
                +"WHERE lastname Like ? ";// dau cham hoi danh tu trai sang phai va  so thu tu tinh tu so 1
              
        
        //3.Create Statement
        stm= con.prepareStatement(sql);
        stm.setString(1, "%" +searchValue + "%");
        //stm.setString(2, password);
        //4.Excute Query
        rs = stm.executeQuery();
        //5.Process
        while (rs.next()){
           
            //mapping
            //5.1 Get data from result set
            String username=rs.getString("username");// lay cau lenh SQL o tren 
            String password=rs.getString("password");
            String fullname=rs.getString("lastname");
            boolean role= rs.getBoolean("isAdmin");
            
            //5.2 set data info DTO
          RegistrationDTO dto=new RegistrationDTO(username, password, fullname, role);
          //5.3 add DTO into List
          if(this.accounts == null){
              this.accounts= new ArrayList<>();
          }
          this.accounts.add(dto);
        }//username and password is exsisted
        } //endconection is opeded
        
        }finally{
            if(rs!= null){
                rs.close();
            }
            if(stm !=null){
                stm.close();
            }
                if(con!=null){
                con.close();
                }
        }        

   
         }
public boolean createAccount(RegistrationDTO account)
        throws ClassNotFoundException, SQLException, NamingException{
    Connection con=null;
        PreparedStatement stm= null;
        //esultSet rs=null;
        boolean result=false;
        try{
            
        
         //1.Create connection
         con = DBHelper.createConnection();
         if (con !=null){
             
         
        //2.Create SQL String
        String sql ="INSERT INTO Registration("
                + "username, password, lastname, isAdmin"  
                + ")VALUES("
                + "?, ?, ?,?"
                + ")";
                
                
        
        //3.Create Statement
        stm= con.prepareStatement(sql);
        stm.setString(1, account.getUsername());
        stm.setString(2, account.getPassword());
        stm.setString(3, account.getFullname());
        stm.setBoolean(4, account.isRole());
        //4.Excute Query
        int effectiveRows=stm.executeUpdate();
        //5.Process
        if(effectiveRows > 0){
            result = true;
        }//username and password is exsisted
        } //endconection is opeded
      
        }finally{
            
            
            if(stm !=null){
                stm.close();
            }
                if(con!=null){
                con.close();
                }
                
}
        return result;

}

    public boolean deleteAccount(String username) 
        throws SQLException, NamingException, ClassNotFoundException{ 
        Connection con=null;
        PreparedStatement stm= null;
      
        boolean result=false;
        try{
            //1.Create Connection
            con= DBHelper.createConnection();
            if(con!= null){
                //2. Create SQL String
                String sql="DELETE * FROM Registration"
                        + "WHERE username= ? ";
                //3.Create StatementObject 
                stm=con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Excute Querry
                int effectiveRows= stm.executeUpdate();
                //5.process
                if(effectiveRows > 0){
                    result= true;
                    
                }
                        
            }
        }finally{
            if(stm !=null){
                stm.close();
            }
            if(con !=null){
                con.close();
                
            }
            
        }
        return result;
    
    
}
    public boolean updateAccount(String username, String password, boolean isAdmin)
    throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try{
            //1. Create Connectin
            con =DBHelper.createConnection();
            if(con!=null){
                //2. Create SQL String
                String sql="UPDATE Registration "
                        + "SET password= ?,"
                        + "isAdmin= ?"
                        + "WHERE username= ?";
                //3.Create StatementObject
                stm=con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4.Excute Result
                int effectiveRows= stm.executeUpdate();
                //5. Process Result
                if(effectiveRows > 0){
                    result = true;
                }
                       
            }
        }finally{
            if(stm !=null){
                stm.close();
            }
            if(con !=null){
                con.close();
            }
            
        }
        return result;
        
    }
    }

    