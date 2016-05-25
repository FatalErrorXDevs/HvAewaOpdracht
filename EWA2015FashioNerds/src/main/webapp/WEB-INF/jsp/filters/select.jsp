<%-- 
    Document   : slider
    Created on : 3-nov-2015, 10:34:45
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="filter-select">

    <spring:message code="global.filters.noPreferences" var="select"/>
    <form:select path="filterGroups[${gi.index}].filters[${fi.index}].value" onchange="formSubmit();"> 
        <form:options value="NONE" label="${select}" />
        <form:options value="NONE" label="------------------------" disabled="disabled" /> 
        <form:options items="${singleFilter.items}" /> 
    </form:select>
    
</div>