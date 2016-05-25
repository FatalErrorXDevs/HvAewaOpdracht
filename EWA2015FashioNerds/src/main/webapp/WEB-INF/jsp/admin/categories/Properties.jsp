<%-- 
    Document   : Properties
    Created on : 16-dec-2015, 18:50:13
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fieldset> 
    <legend>Eigenschappen:</legend>
    <c:if test="${Category.isReviewRequired()}">
        <div class="alert alert-info">Accepteer nieuwe eigenschappen door op een eigenschap te klikken.</div>
    </c:if>
    <ul class="items-category">
        <c:forEach items="${Category.getData()}" var="dataItem">  
            <c:choose>
                <c:when test="${(Category.isReviewRequired() && !dataItem.isReviewed())}">
                    <li class="review">
                    </c:when>
                    <c:otherwise>
                    <li class="accepted">
                    </c:otherwise> 
                </c:choose>
                <c:if test="${Category.isReviewRequired()}">
                    <a href="${pageContext.request.contextPath}/Admin/Categories/${dataItem.getId()}/Toggle/">
                    </c:if>
                    <c:out value="${dataItem.getValue()}"/>
                    <c:if test="${Category.isReviewRequired()}">
                    </a>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <table width="200">
        <tr>
            <td width="32"><div style="width: 24px; height: 23px; background: #6FFFA2;border: 1px solid #00D84C;"></div></td>
            <td>Accepted</td>
            <td width="32"><div style="width: 24px; height: 23px; background: #FF6F72; border: 1px solid #D80000;"></div></td>
            <td>Need to be reviewed</td>
        </tr>
    </table>
    <div class="add">
        <form:form class="form-horizontal" method="post" 
                   modelAttribute="Data" commandName="Data" action="${pageContext.request.contextPath}/Admin/Categories/${Category.getId()}/SaveData/">

            <form:errors path="*" class="error" element="p" />
            <form:hidden path="id" /> 
            <form:input type="text" path="value" />
            <input type="submit" value="Eigenschap toevoegen" />
        </form:form>
    </div>
</fieldset>