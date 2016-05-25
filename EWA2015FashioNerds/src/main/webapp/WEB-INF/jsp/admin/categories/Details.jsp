<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:htmlEscape defaultHtmlEscape="true" /> 
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Categories</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper"> 
                        <h1><spring:message code="category"/>: <c:out value="${Category.getName()}" /></h1> 

                        <%@include file="../../includes/Message.jsp" %> 

                        <spring:url value="${saveURL}" var="userActionUrl" />

                        <%@include file="InputInformation.jsp" %>

                        <fieldset>
                            <legend><spring:message code="global.general.information"/>:</legend> 
                            <table width="100%">
                                <tr>
                                    <td width="180"><spring:message code="categories.model.name" />:</td>
                                    <td><c:out value="${Category.getName()}" /></td>
                                </tr>
                                <tr>
                                    <td width="180"><spring:message code="global.general.created" />:</td>
                                    <td><fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${Category.getCreatedDate().toDate()}" /></td>
                                </tr>
                                <tr>
                                    <td width="180"><spring:message code="global.general.createdBy" />:</td>
                                    <td>
                                        <c:set var="Account" value="${Category.getCreatedBy()}" scope="request" /> 
                                        <jsp:include page="/WEB-INF/jsp/profile/TinyProfile.jsp" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="180"><spring:message code="global.general.lastChange" />:</td>
                                    <td><fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${Category.getLastModifiedDate().toDate()}" /></td>
                                </tr>
                                <tr>
                                    <td width="180"><spring:message code="global.general.lastChangeBy" />:</td>
                                    <td>
                                        <c:set var="Account" value="${Category.getLastModifiedBy()}" scope="request" /> 
                                        <jsp:include page="/WEB-INF/jsp/profile/TinyProfile.jsp" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <c:if test="${Category.getDataType().isMultipleItems()}">
                            <%@include file="Properties.jsp" %>
                        </c:if>
                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>
    </body>
</html>
