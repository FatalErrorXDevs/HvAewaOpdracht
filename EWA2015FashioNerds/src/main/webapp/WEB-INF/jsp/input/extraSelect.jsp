<%-- 
    Document   : extra
    Created on : 5-jan-2016, 20:08:08
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(extra.getCategory().isReviewRequired() && !extra.isReviewed())}">
        <c:url var="image" value="/resources/images/flag_r.png" />
    </c:when>
    <c:when test="${(extra.getCategory().isReviewRequired() && extra.isReviewed())}">
        <c:url var="image" value="/resources/images/flag_g.png" />
    </c:when>  
</c:choose>
<option data-img-src="${image}" selected="selected" value="${extra.getId()}">${extra.getValue()}</option>