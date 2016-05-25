<%-- 
    Document   : Error
    Created on : Oct 1, 2015, 10:12:15 AM
    Author     : tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Error</title>
    </head>
    <body>
        <div class="content">
            <%@include file="includes/Header.jsp" %>

            <div class="main-content"> 
                <div class="page">
                    <div class="wrapper">
                        <center>
                            <img class="logo" src="<c:url value="/resources/images/logo_error.png" />" alt="Logo" />
                            <h1><spring:message code="global.error"/></h1>
                        </center>
                    </div> 
                </div>
            </div>
            <%@include file="includes/Footer.jsp" %>
        </div>
    </body>
</html>
