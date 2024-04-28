/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuong.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cuong.registration.RegistrationDAO;
import cuong.registration.RegistrationDTO;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {
 private final String LOGIN_PAGE ="login.html";
 private final String SEARCH_PAGE="search.jsp";
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
        String url =LOGIN_PAGE;
        try {
       //1. Get cookies
       HttpSession session= request.getSession(false);
       if(session !=null){
       Cookie[] cookies= request.getCookies();
       if(cookies  !=null){
           //2. check authentication
           Cookie lastCookie= cookies[cookies.length - 1]; //cách này dùng để test với riêng chương trình này khi cookie chỉ dành cho việc check login, trong các trường hợp khác nên sử dụng dòng for để lấy và check
           String username= lastCookie.getName();
           String password=lastCookie.getValue();
           //--> call DAO
           //2.1 new DAO
           RegistrationDAO dao = new RegistrationDAO();
           //2.2 call method of DAO
           RegistrationDTO result= dao.checkLogin(username, password);
           //3. process result 
           if(result!=null){
               url = SEARCH_PAGE;
           }
       }
       }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(NamingException ex){
            ex.printStackTrace();
        }finally{
            response.sendRedirect(url);
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


    




 
