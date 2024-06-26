/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuong.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cuong.registration.RegistrationDAO;
import cuong.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
private final String SEARCH_PAGE="search.html";
private final String RESULT_SEARCH_PAGE="search.jsp";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= SEARCH_PAGE;
        //1.get all paremeters.
        String searchValue=request.getParameter("txtSearchValue");
        try{
        if(!searchValue.trim().isEmpty()){
            //2. call model/DAO
            //2.1 new DAO
            RegistrationDAO dao= new RegistrationDAO();
            //2.2 call Method of DAO
            dao.SearchLastName(searchValue);
            List<RegistrationDTO> result =dao.getAccounts();
            //3. process
            url= RESULT_SEARCH_PAGE;
            request.setAttribute("SEARCH_PAGE", result);  // dung foware
        }//end searchValue has valid value
        RequestDispatcher rd=request.getRequestDispatcher(url);
        rd.forward(request, response);
    } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(NamingException ex){
            ex.printStackTrace();
        }
        finally{
            RequestDispatcher rd =request.getRequestDispatcher(url);
            rd.forward(request, response);
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

    
