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
<div class="filter-radioboxes">
    <form:radiobuttons path="filterGroups[${gi.index}].filters[${fi.index}].value" 
                       items="${singleFilter.items}" 
                       itemValue="id" 
                       itemLabel="name"
                       onclick="formSubmit();"
                       element="div"/>
</div>