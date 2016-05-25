<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert, Tom Prins
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Accounts</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <h1><spring:message code="accounts.list.title"/></h1>
                        <p><spring:message code="global.results.showing" arguments="${list.size()}"/></p>

                        <%@include file="../includes/Message.jsp" %> 

                        <table class="results">    
                            <tr> 
                                <th width="20%"><spring:message code="global.options"/></th>
                                <th width="40%"><spring:message code="accounts.model.username"/></th>
                                <th><spring:message code="accounts.model.role"/></th>
                            </tr>
                            <c:forEach items="${list}" var="list">  
                                <tr>
                                    <td width="100"> <a href="${pageContext.request.contextPath}/Admin/Accounts/Edit/${list.getId()}"><c:out value="Edit" /></a> - <a href="${pageContext.request.contextPath}/Admin/Accounts/Editpass/${list.getId()}"><c:out value="Edit password" /></a> 
                                    <td>
                                        <c:out value="${list.getUsername()}" />
                                    </td>
                                    <td>
                                        <c:catch var= "catchException">
                                            <c:out value="${list.role()}" /> </c:catch>
                                    </td> 
                                </tr>  
                            </c:forEach>  
                        </table>


                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>
