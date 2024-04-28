package cuong.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cuong.registration.RegistrationDAO;
import cuong.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // không được sửa , ko sửa bất kì tham số
        response.setContentType("text/html;charset=UTF-8");

        //lưu ý sửa như thế này.
        PrintWriter out = response.getWriter();

//        // lấy Parameters 
//
//        String username = request.getParameter("txtUsername");
//        String password = request.getParameter("txtPassword");
//   
//        String url = INVALID_PAGE;
//        try {
//
//            //2. Call model - DAO 
//            //2.1 New DAO
//            RegistrationDAO dao = new RegistrationDAO();
//            //2.2 call method of  DAO
//            boolean result = dao.checkLogin(username, password);
//
//            //3. process
//            if (result) {
//                url = SEARCH_PAGE;
//                HttpSession session = request.getSession(); // chi luu username va value ko luu password.
////                    Cookie cookie=new Cookie(username, password);
////                    cookie.setMaxAge(60*5);
////                    response.addCookie(cookie);
//            }//end username 
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } finally {
////            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
//        }

//0. get current context
ServletContext context=this.getServletContext();
        Properties siteMaps=(Properties) context.getAttribute("SITEMAPS");
        //1.get all parameters
        String url= INVALID_PAGE;
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        try{
            RegistrationDAO dao= new RegistrationDAO();
            RegistrationDTO result= dao.checkLogin(username, password);
            if(result != null){
                url = SEARCH_PAGE;
                HttpSession session= request.getSession();//login thanh cong phai tao session
                session.setAttribute("USER_INFO", result);
            }
        }catch (SQLException ex) {
          ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
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
