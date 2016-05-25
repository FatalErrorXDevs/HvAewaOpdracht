<%-- 
    Document   : InputInformation
    Created on : 16-dec-2015, 15:01:54
    Author     : Bert
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fieldset> 
    <legend><img class="small-16" src="<c:url value="/resources/images/categories/small_${Category.getDataType().getId()}.png" />" alt="Input type" /> <spring:message code="input.names.name${Category.getDataType().getId()}"  text="Unknown (${Category.getDataType().getId()})" />:</legend>  
    <div class="info-text">
        <img src="<c:url value="/resources/images/categories/large_${Category.getDataType().getId()}.png" />"/>
        <p><spring:message code="input.names.description${Category.getDataType().getId()}"  text="Unknown (${Category.getDataType().getId()})" /></p>
    </div>
</fieldset>