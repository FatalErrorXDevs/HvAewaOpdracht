<%-- 
    Document   : list
    Created on : 8-dec-2015, 10:53:55
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Tabs</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <h1><spring:message code="tabs.list.title"/></h1>
                        <p><spring:message code="global.results.showing" arguments="${list.size()}"/></p>
                        
                        <%@include file="../../includes/Message.jsp" %> 

                        <table class="results">    
                            <tr> 
                                <th width="10%"></th>
                                <th width="45%"><spring:message code="tabs.model.name"/></th>
                                <th><spring:message code="tabs.model.numberOfCategories"/></th> 
                            </tr>
                            <c:forEach items="${list}" var="list">  


                                <tr>
                                    <td width="100">
                                        <a href="${pageContext.request.contextPath}/Admin/Tabs/<c:out value="${list.getId()}" />/Edit/">
                                            <spring:message code="global.edit"/>
                                        </a>
                                        - 
                                        <a class="confirm-delete" data-name="<c:out value="${list.getName()}" />" 
                                           href="${pageContext.request.contextPath}/Admin/Tabs/<c:out value="${list.getId()}" />/Delete/">
                                            <spring:message code="global.delete"/>
                                        </a></td>
                                    <td>
                                        <c:out value="${list.getName()}" />
                                    </td>
                                    <td>
                                        <c:out value="${list.getCategories().size()}" />
                                    </td>
                                </tr>

                            </c:forEach>  
                        </table>


                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>
    </body>
</html>
