<%-- 
    Document   : 403
    Created on : 17-dec-2015, 11:54:02
    Author     : 403
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>403 ACCESS DENIED</title>
    </head>
    <body>
        <div class="content">
            <%@include file="includes/Header.jsp" %>

            <div class="main-content"> 
                <div class="wrapper">              
                    <center>
                        <img class="logo" src="<c:url value="/resources/images/logo_error.png" />" alt="Logo" />
                        <h1><spring:message code="403"/></h1>
                    </center>
                </div> 
            </div>
            <%@include file="includes/Footer.jsp" %>
        </div>
    </body>
</html>