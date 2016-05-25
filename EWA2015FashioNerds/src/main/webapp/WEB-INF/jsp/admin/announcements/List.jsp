<%-- 
    Document   : announcements/List
    Created on : 6-jan-2016, 06:58:36
    Author     : Floris van Lent, Bert Kooij
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title><spring:message code="announcements"/></title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <h1><spring:message code="announcements"/></h1>
                        <p><spring:message code="global.results.showing" arguments="${list.size()}"/></p>

                        <%@include file="../../includes/Message.jsp" %> 

                        <table class="results">    
                            <tr> 
                                <th width="14%"></th>
                                <th width="25%"><spring:message code="announcements.title"/></th>
                                <th width="25%"><spring:message code="announcements.last.user"/></th> 
                                <th><spring:message code="announcements.last.date"/></th> 
                            </tr>

                            <c:forEach items="${list}" var="list">  
                                <tr>
                                    <td width="100">
                                        <a href="${pageContext.request.contextPath}/Admin/Announcements/<c:out value="${list.getId()}" />/Edit/">
                                            <spring:message code="global.edit"/>
                                        </a>
                                        - 
                                        <a class="confirm-delete" data-name="<c:out value="${list.getTitle()}" />" 
                                           href="${pageContext.request.contextPath}/Admin/Announcements/<c:out value="${list.getId()}" />/Delete/">
                                            <spring:message code="global.delete"/>
                                        </a></td>
                                    <td>
                                        <c:out value="${list.getTitle()}" />
                                    </td>
                                    <td>
                                        <c:set var="Account" value="${list.getLastModifiedBy()}" scope="request" /> 
                                        <jsp:include page="../../profile/TinyProfile.jsp" />
                                    </td>
                                    <td>
                                        <fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${list.getLastModifiedDate().toDate()}" /> 
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
