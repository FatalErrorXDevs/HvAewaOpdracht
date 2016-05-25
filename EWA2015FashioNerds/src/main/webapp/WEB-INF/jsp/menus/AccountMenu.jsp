<%-- 
    Document   : AccountMenu
    Created on : 8-okt-2015, 13:32:37
    Author     : Bert
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="searchMenu">
    <form action="${pageContext.request.contextPath}/Accounts/Search" method="get">
        <input class="searchBox" type="search" placeholder="<spring:message code="global.search"/>" <c:if test="${search!=null}">value="${search}"</c:if> name="s" />
            <input class="searchSubmit" type="submit" value=""/>
        </form>
    </div> 
    <ul class="side-menu">
        <li class="menu"><div class="tag"><spring:message code="menu"/></div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/Accounts/"><spring:message code="menu.accounts"/></a>
                <ul>
                    <li class="menu"><a href="${pageContext.request.contextPath}/Accounts/Add/">+</a></li>
                </ul>
            </li>
        </ul>
    </li>

</ul>