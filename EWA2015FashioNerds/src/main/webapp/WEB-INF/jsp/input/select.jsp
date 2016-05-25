<%-- 
    Document   : text
    Created on : 7-dec-2015, 22:18:10
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:bind path="data">
    <form:select path="data" multiple="false" data-placeholder="Select ${category.getName()}" class="chosen-select user-input-${category.getId()}">
        <form:option value=""/>
        <c:forEach items="${category.getData()}" var="dataItem">  
            <c:choose>
                <c:when test="${(category.isReviewRequired() && !dataItem.isReviewed())}">
                    <c:url var="image" value="/resources/images/flag_r.png" />
                </c:when>
                <c:when test="${(category.isReviewRequired() && dataItem.isReviewed())}">
                    <c:url var="image" value="/resources/images/flag_g.png" />
                </c:when>  
            </c:choose>
            <form:option  data-img-src="${image}" label="${dataItem.getValue()}" value="${dataItem.getId()}" />
        </c:forEach>
    </form:select>
    <form:errors path="data" class="error" /> 
</spring:bind>