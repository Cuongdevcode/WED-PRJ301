<%-- 
    Document   : viewcart
    Created on : Oct 12, 2023, 12:44:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1" cellspacing="2">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${items}" var="entry" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${entry.key}</td>
                                    <td>${entry.value}</td>
                                    <td>
                                        <input type="checkbox" name="checkItems" value="${item.key}" />
                                    </td>
                                </tr>
                                
                            </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="bookstore.jsp">Add more books </a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                    </td>
                                    
                                </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Check-out" name="btAction" />
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>Not found CART</h2>
            <a href="bookstore.jsp">Click here to Book Store</a>
        </c:if>
             </body>
</html>
        <%--<h1>Book Store</h1>
        <%
            //1. Customer goes to cart place
            if (session != null) {
                //2. Customer take his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="checkItem" value="<%= key%>" />
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="bookStore.jsp">Add more books to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Check-out" name="btAction" />
        </form>
        <%
                        return;
                    }//end items have existed
                }//end cart has existed
            }//cart place must be existed
        %>
        <h2>No cart is existed</h2>
        <a href="bookStore.jsp">Click here to return BookStore</a>
--%>
   
