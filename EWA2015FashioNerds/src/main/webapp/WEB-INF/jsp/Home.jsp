<%-- 
    Document   : Home
    Created on : Oct 1, 2015, 10:11:53 AM
    Author     : tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div class="content">
            <%@include file="includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <form action="searchFabric" method="post">
                            <br/>Search fabrics:
                            <input type="text" name="search">
                            <input type="submit" value="Submit"> 
                        </form>
                        <form action="addFabric" method="post">
                            <br/>Add fabric
                            <input type="text" name="addFabric">
                            <input type="submit" value="Submit"> 
                        </form>
                        <br/>view all
                        <form action="searchFabric" method="post">
                            <input type="text" name="viewAll">
                            <input type="submit" value="Submit"> 
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="includes/Footer.jsp" %>
        </div>
    </body>
</html>
