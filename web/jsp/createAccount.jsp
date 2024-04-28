<%-- 
    Document   : createAccount
    Created on : Oct 26, 2023, 11:27:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1> Create Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.ERROR_CREATE}"/> 
            Username * <input type="text" name="txtUsername" value="${param.txtUsername}" />(6-30 characters) <br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
                
            </c:if>
                Password * <input type="password" name="txtPassword" value="" />(6-20 characters)<br/>
                <c:if test="${not empty errors.passwordLengthErr}">
                      <font color="red">
                      ${errors.passwordLengthErr}
                      </font><br/>
        </c:if>
                       Conform Password * <input type="password" name="txtConfirm" value="" />
                       <c:if test="${not empty errors.cofirmNotMatch}">
                           <font color="red">
                               ${errors.confirmNotMatch}
                           </font><br/>
             
        </c:if>
                           Fullname * <input type="text" name="txtFullname" value="" />
                           <c:if test="${not empty errors.fullNameLengthErr}">
                               <font color="red">
                                   ${erros.fullNameLengthErr}
                               </font><br/>
                               
                           </c:if>
                               <input type="submit" value="Create new account" name="btAction" />
                               <input type="reset" value="reset" />
    </form>
</body>
</html>
