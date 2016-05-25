<%-- 
    Document   : Table
    Created on : 16-dec-2015, 14:45:23
    Author     : Bert
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="results">    
    <tr> 
        <th width="150"></th>
        <th width="32"></th>
        <th><spring:message code="categories.model.name"/></th>
    </tr>
    <c:forEach items="${categoryList}" var="item">  


        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/Admin/Categories/<c:out value="${item.getId()}" />/Edit/">
                    <spring:message code="global.edit"/>
                </a>
                - 
                <a class="confirm-delete" data-name="<c:out value="${list.getName()}" />" 
                   href="${pageContext.request.contextPath}/Admin/Categories/<c:out value="${item.getId()}" />/Delete/">
                    <spring:message code="global.delete"/>
                </a></td>
            <td><img class="small-16" src="<c:url value="/resources/images/categories/small_${item.getDataType().getId()}.png" />" alt="Input type" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/Admin/Categories/<c:out value="${item.getId()}" />/Details/">
                    <c:out value="${item.getName()}" />
                </a>
            </td>
        </tr>

    </c:forEach>  
</table>