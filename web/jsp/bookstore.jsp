<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : bookstore
    Created on : Oct 26, 2023, 10:37:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1> Book Store</h1>
        <form action="DispatchServlet">
            <c:set var="store" value="${sessionScope.STORE}" />
            Choose your book <select name="ddlBook">
                <c:if test="${not empty store}">
                    <c:forEach var="productDTO" items="${store}">
                        <option>${productDTO.productName}</option>
                    </c:forEach>
                </c:if>


            </select>
            <input type="text" name="bookQuantity" value="" />

            <input type="submit" value="Add Book to Your Cart " name="btAction" />

            <input type="submit" value="View Your Cart " name="btAction" />

        </form>
    </body>
</html>
