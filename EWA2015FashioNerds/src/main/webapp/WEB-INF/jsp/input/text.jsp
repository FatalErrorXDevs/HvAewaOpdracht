<%-- 
    Document   : text
    Created on : 7-dec-2015, 22:18:10
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:bind path="data">
    <form:hidden path="tempData[${DataIndex}].categoryId"/>
    <form:hidden path="tempData[${DataIndex}].dataId"/>
    <form:input type="text" path="tempData[${DataIndex}].value" id="data-sel-${categoryIndex}-${tabIndex}-" items="${category.getData()}" itemLabel="name" itemValue="id" multiple="false" />  
    <form:errors path="data" class="error" /> 
</spring:bind> 