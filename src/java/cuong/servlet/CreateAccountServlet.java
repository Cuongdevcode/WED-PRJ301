/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cuong.registration.RegistrationDAO;
import cuong.registration.RegistrationDTO;
import cuong.registration.RegistrationErrorCreate;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
  private final String USER_LENGTH_ERROR = "Username is required tuping form 6 to 20 characters";
    private final String PASSWORD_LENGTH_ERROR = "Password is required tuping form 6 to 20 characters";
    private final String CONFIRM_ERRORS = "Confirm must match Password";
    private final String FULLNAME_LENGTH_ERROR = "Fullname is required tuping form 6 to 30 characters";
    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String USER_EXISTED_ERROR = "Username is existed";


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        //1. set all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        RegistrationErrorCreate errors = new RegistrationErrorCreate();
        boolean foundErr=false;
        String url= CREATE_ERROR_PAGE;
        try {
            //2. verity all parameters with users erros
            if(username.trim().length() <6 ||
                    username.trim().length() >20){
                foundErr=true;
                errors.setUsernameLengthError(USER_LENGTH_ERROR);
            }
            if(password.trim().length() <6 ||
                    password.trim().length() >20){
                foundErr=true;
                errors.setUsernameLengthError(PASSWORD_LENGTH_ERROR);
                
            }else if(!confirm.trim().equals(password.trim())){
                foundErr=true;
                errors.setConfirmNotMatched(CONFIRM_ERRORS);
                
                
            }
             if(fullname.trim().length() <6 ||
                    fullname.trim().length() >20){
                foundErr=true;
                errors.setUsernameLengthError(FULLNAME_LENGTH_ERROR);
                
             }
             if(foundErr){
                 request.setAttribute("CREATE_ERROR", errors);
             }else{
                 //3.call DAO
                   //3.1 New DAO
                   RegistrationDAO dao=new RegistrationDAO();
                   //3.2 Call method of DAO
                   RegistrationDTO account=new RegistrationDTO(
                   username, password, fullname, false);
                   boolean result=dao.createAccount(account);
                   //4.process
                   if(result){
                   url= LOGIN_PAGE;
                   }
                   
             }
        }
        catch(SQLException ex){
            String erroMess= ex.getMessage();
            log("CreateAccountServlet_SQL" + erroMess);
            if(erroMess.contains("duplicate")){
                
            }
            
           
        }
         catch(ClassNotFoundException ex){
            log("CreateAccountServlet_Naming" + ex.getMessage());
        }catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        }     finally{
            RequestDispatcher re= request.getRequestDispatcher(url);
            re.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
