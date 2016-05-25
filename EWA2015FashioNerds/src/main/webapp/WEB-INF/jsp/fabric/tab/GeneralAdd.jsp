<%-- 
    Document   : General
    Created on : 9-dec-2015, 11:45:12
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:htmlEscape defaultHtmlEscape="true" /> 
<table width="100%"> 
    <tr>
        <td width="20%"><spring:message code="global.date"/>:</td>
        <td>
            <fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${fabric.getCreatedDate()}" />
        </td>
        <td>                                            
        </td>
    </tr> 
    <tr>
        <td><spring:message code="global.author"/>:</td>
        <td><c:out value="${Fabric.getCreatedBy().getName()}" /></td>
        <td></td>
    </tr>
    <spring:bind path="FabricName">
        <tr>
            <td><spring:message code="fabrics.name"/>:</td>
            <td><form:input path="FabricName" type="text" /></td>
            <td><form:errors path="FabricName" class="error" /></td>
        </tr>
    </spring:bind>
</table>