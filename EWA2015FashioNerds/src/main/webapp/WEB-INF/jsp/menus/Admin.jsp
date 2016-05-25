<%-- 
    Document   : AccountMenu
    Created on : 8-okt-2015, 13:32:37
    Author     : Bert
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="side-menu">
    <li class="menu"><div class="tag"><spring:message code="menu"/></div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/Admin/Accounts/"><spring:message code="menu.accounts"/></a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Admin/Accounts/Add/">+</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Admin/Announcements/"><spring:message code="announcements"/></a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Admin/Announcements/Add/">+</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Admin/Categories/"><spring:message code="menu.categories"/></a>
                <ul>
                    <li><a href="#">+</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Admin/Tabs/"><spring:message code="menu.tabs"/></a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Admin/Tabs/Add/">+</a></li>
                </ul>
            </li>
        </ul>
    </li>

</ul>