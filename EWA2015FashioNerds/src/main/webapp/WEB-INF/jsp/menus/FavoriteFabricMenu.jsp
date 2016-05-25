<%-- 
    Document   : FabricSearch
    Created on : 9-okt-2015, 10:33:57
    Author     : Bert
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="searchMenu">
    <form action="${pageContext.request.contextPath}/Fabrics/Search" method="post">
        <input class="searchBox" type="search" <c:if test="${search!=null}">value="${search}"</c:if> placeholder="<spring:message code="global.search"/>" name="s" />
            <input class="searchSubmit" type="submit" value=""/>
        </form>
    </div> 
    <ul class="side-menu">
        <li class="menu"><div class="tag"><spring:message code="menu"/></div>
        <ul>
            <li class="menu"><a href="${pageContext.request.contextPath}/Favorites/">Favorite fabrics</a></li>
        </ul>
    </li>
</ul>