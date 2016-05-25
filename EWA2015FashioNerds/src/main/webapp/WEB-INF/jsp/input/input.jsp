<%-- 
    Document   : input
    Created on : 9-dec-2015, 12:44:51
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="category" value="${category}" scope="request" /> 
<c:set var="categoryIndex" value="${categoryStatus.index}" scope="request" /> 
<c:set var="tab" value="${tab}" scope="request" />
<c:set var="tabIndex" value="${tabStatus.index}" scope="request" />
<c:set var="DataIndex" value="${FabricModel.getTempDataIndexForCategoryId(category.getId())}" scope="request" /> 
<jsp:include page="${category.getInput().getInputFile()}" /> <c:out value="${category.getUnit()}"/>
<br /><c:out value="${category.getDescription()}"/>