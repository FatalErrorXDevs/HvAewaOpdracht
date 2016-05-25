<%-- 
    Document   : header
    Created on : 1-okt-2015, 9:23:06
    Author     : Bert
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header class="header">
    <a href="${pageContext.request.contextPath}/">
        <img class="logo" src="<c:url value="/resources/images/logo.png" />" alt="Logo" />
    </a>
    <div class="right">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p><spring:message code="header.welcome"/>: <strong>${pageContext.request.userPrincipal.name}</strong> - <a href="${pageContext.request.contextPath}/SignOut"><spring:message code="header.signOut"/></a></p>
            </c:if>
    </div>
</header>
<nav class="main-menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/"><spring:message code="menu.home"/></a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/"><spring:message code="menu.admin"/></a></li>
        <li><a href="${pageContext.request.contextPath}/Fabrics/Favorites/"><spring:message code="menu.favorites"/></a></li>
        <li><a href="${pageContext.request.contextPath}/Projects/">Projects</a></li>
        <li><a href="${pageContext.request.contextPath}/Profile/View"><spring:message code="header.profile"/></a></li> 
    </ul>
</nav>
