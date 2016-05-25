<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <%@include file="../includes/Message.jsp" %> 
                        <h1><spring:message code="header.welcome"/></h1>
                        <p><spring:message code="statistics.title"/>:</p>

                        <table width="100%" class="stats-table" cellSpacing="12">
                            <tr>
                                <td width="33.33%"><strong><spring:message code="statistics.users"/>:</strong>
                                    <div class="stats">
                                        <c:out value="${numAccount}" />
                                    </div>
                                </td>
                                <td width="33.33%"><strong><spring:message code="menu.fabrics"/>:</strong>
                                    <div class="stats">
                                        <c:out value="${numFabric}" />
                                    </div></td>
                                <td width="33.33%"><strong><spring:message code="statistics.projects"/>:</strong>
                                    <div class="stats">
                                        <c:out value="${numProject}" />
                                    </div>
                                </td>
                            </tr>
                        </table>                        
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>
