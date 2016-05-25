<%-- 
    Document   : extra
    Created on : 5-jan-2016, 20:08:08
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li>
    <input id="data-${extra.getId()}" name="data" multiple="false" type="checkbox" value="${extra.getId()}">
    <label for="data-${extra.getId()}">${extra.getValue()} 
        <c:choose>
            <c:when test="${(extra.getCategory().isReviewRequired() && !extra.isReviewed())}">
                <img class="logo" title="Value must be reviewed" src="<c:url value="/resources/images/flag_r.png" />" alt="Must be reviewed" />
            </c:when>
            <c:when test="${(extra.getCategory().isReviewRequired() && extra.isReviewed())}">
                <img class="logo" title="Value is reviewed" src="<c:url value="/resources/images/flag_g.png" />" alt="OK" />
            </c:when>  
        </c:choose>
    </label></li>