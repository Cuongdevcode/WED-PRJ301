<%-- 
    Document   : search
    Created on : Sep 28, 2023, 2:30:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page import="java.util.List"%>
<%@page import="registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER_INFO.fullname} <%-- attribute moi luu trucon session thi luu tru arribute --%>
        </font>
        <h1> Search Page</h1>
        <form

            Search Page <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/> <br/>
            <input type="submit" value="Search" name="btAction" />
            <input type="submit" value="Logout" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty searchValue}">
                <table border="i">
                    <thead><tr>
                            <td>No.</td>
                            <td>USername</td>
                            <td>Password</td>
                            <td>Full Name</td>
                            <td>Role</td>
                            <td>Delete</td>
                            <td>Update</td>

                        </tr></thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                        </form>
                        <tr>
                            <td>${counter.count}
                            </td>
                            <td>${dto.username}
                                <input type="hidden" value="${dto.username}" name="txtUsername" />
                            </td>
                            <td>${dto.password} 
                                <input type="password" name="txtPassword" value="${dto.password}" />
                                <input type="hidden" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>${dto.fullname} 

                                <%-- <input type="text" name="${dto.fullname}" value="txtFullname" /> --%>
                            </td>
                            <td>${dto.role} <input type="checkbox" name="checkAdmin" value="ON"
                                                   <c:if test="${dto.role}">
                                                       checked="checked"
                                                   </c:if>
                                                   />
                                <
                            </td>

                            <td>
                                <c:url var="urlRewriting" value="DispatchServlet" >
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="LastSearchvalue" value="${param.searchValue}"/>

                                </c:url>
                                <a href="${urlRewriting}">Delete</a>

                            </td>
                            <td>

                                <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2> not found </h2>
        </c:if>

    </c:if>
</body>
</html>
<%-- <%
   Cookie[] cookies =request.getCookies();
   if(cookies != null){
       Cookie lasCookie= cookies(cookies.length - 1);
       String username=lasCookie.getName();
   
 
 %>
 <font color="red">
 
 Welcome, <%= username %>
 </font>
 
 
 
 <h1>Search Page</h1>
 <form action ="DispatchServlet"> 
     
     Search Value <input type="text" name="txtSearchValue"
                         value ="<%= request.getParameter("txtSearchValue") %>" 
                         
                         />
     <input type="submit" value="Search" name="btAction" /><br> 
    
 </form>
  <% 

            // trong jsp phải check null vì nhiều khi nhập url tới thẳng search.jsp 
            String searchValue= request.getParameter("txtSearchValue");
            if(searchValue !=null){
                List<RegistrationDTO> result=(List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            } //end search




                                    %>
       if(result != null) { //search has one more records.
   
        <table border="i">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    
                </tr>
            </thead>
            <tbody>
                <% 
                      int count=0;
                      for (RegistrationDTO dto: result){
                          String urlRewriting ="DispatchServlet"
                          +"?btAction=delete"
                          +"&pk" + dto.getUsername()
                          + "&lastSearchvalue" +searchValue;
                          
                      

                    %>
                     <tr>
                         <td>
                             <%=  ++count %> </td>
                    
                         <td><%= dto.getUsername() %>
                         </td>
                    <td><%= dto.getPassword() %></td>
                    <td><%= dto.getFullname() %></td>
                    <td><%= dto.isRole() %></td>
                    <td> <a href="">Delete</a> </td>
                </tr>
                <%
                    

                    %>
            </tbody>
                
        </table>
        
        <%
        }else{
        %>
        
    </body>
</html>--%>
