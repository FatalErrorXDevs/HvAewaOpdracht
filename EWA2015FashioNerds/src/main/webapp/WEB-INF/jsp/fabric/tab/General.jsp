<%-- 
    Document   : General
    Created on : 17-dec-2015, 7:24:23
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table width="100%" cellspacing="10">
    <tr>
        <td width="25%"><p>Created:</p></td>
        <td><p><fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${fabric.getCreatedDate().toDate()}" /></p></td>
    </tr>
    <tr>
        <td><p>By:</p></td>
        <td><p><c:set var="Account" value="${fabric.getCreatedBy()}" scope="request" /> 
                <jsp:include page="../profile/TinyProfile.jsp" /></p></td>
    </tr>
    <tr>
        <td><p>Updated:</p></td>
        <td><p><fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${fabric.getLastModifiedDate().toDate()}" /></p></td>
    </tr> 
    <tr>
        <td><p>By:</p></td>
        <td><p><c:set var="Account" value="${fabric.getLastModifiedBy()}" scope="request" /> 
                <jsp:include page="../profile/TinyProfile.jsp" /></p></td>
    </tr>
    <tr>
        <td><p>These people worked on it:</p></td>
        <td>
            <c:forEach var="User" items="${fabric.getWorkedOn()}">
                <c:set var="Account" value="${User}" scope="request" /> 
                <jsp:include page="../profile/TinyProfile.jsp" /></td>
            </c:forEach>
    </tr>
</table>
<hr />