<%-- 
    Document   : List
    Created on : 9-nov-2015, 9:48:59
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${filter.userFilter}">
    <p><a href="#" onclick="$('.showExtra').toggle();">Show applied filters</a></p>
    <ul class="showExtra" style="display:none;">
        <c:forEach items="${filter.getFilters()}" var="singleFilter"> 
            <c:if test="${!singleFilter.getTag().isEmpty()}">
                <li>${singleFilter.getTag()}</li>
                </c:if>
            </c:forEach>  
    </ul>
</c:if>

<div class="top"> 
    <div class="info">
        <div class="title"><h1><spring:message code="fabrics.list.title"/></h1></div>
        <div class="date"><p><spring:message code="global.results.showing" arguments="${list.size()}"/></p></div>
    </div>
    <div class="buttons">
        <div class="add button">
            <a href="${pageContext.request.contextPath}/Fabrics/Add/">
                <button>Add</button>
            </a>
        </div>

        <div class="export button"> 
            <ul>
                <li><a href="javascript:return false;">Export <div class="down">&#x25BC;</div></a> 
                    <ul>
                        <li><a download="results.xls" href="#" onclick="return ExcellentExport.excel(this, 'results', 'Fabrics');">Excel</a></li>
                        <li><a download="results.csv" href="#" onclick="return ExcellentExport.csv(this, 'results');">CSV</a></li>
                    </ul>
                </li>
            </ul> 
        </div> 

    </div>
</div>
<div class="info-wrapper"> 
    <div class="sort">
        <form:form modelAttribute="filterModel" commandName="FabricFilterModel" id="filterFormSort" action="${filterUrl}" method="post"> 
            <p><spring:message code="global.sort"/>: <form:select path="selectedSort" items="${filter.sortItems}" onChange="$(this).parent().parent().submit();" itemLabel="name" itemValue="id"/></p>
        </form:form>
    </div>
</div>
<table class="results" id="results">
    <tr> 
        <th width="5%"></th>
        <th width="13%"><spring:message code="fabrics.model.status"/>:</th>
        <th width="12%"><spring:message code="fabrics.model.name"/>:</th>
        <th width="15%"><spring:message code="fabrics.model.composition"/>:</th> 
        <th width="10%"><spring:message code="fabrics.model.weight"/>:</th> 
        <th width="15%"><spring:message code="fabrics.model.drape"/>:</th>
        <th width="5%"><spring:message code="fabrics.model.color"/>:</th> 
    </tr>   
    <c:forEach items="${list}" var="item">
        <c:set var="Color" value="false" scope="request" />  

        <tr> 
            <td><a href="${pageContext.request.contextPath}/Fabrics/detailsPage/${item.getId()}">View</a></td>
            <td> 
                <span class="pie" data-peity='{ "fill": ["#C20114", "#DDDDDD"]}'>${item.getCompleteness(categories)}/100</span> 
                <span class="small-status">${item.getCompleteness(categories)}%</span>
            </td>
            <td>
                <c:out value="${item.getFabricName()}" />
            </td>
            <td>
                <c:set var="Data" value="${item.getFabricDataByCategory(22)}" scope="request" /> 
                <%@include file="tab/DataInfo.jsp"%>  
            </td>
            <td>
                <c:set var="Data" value="${item.getFabricDataByCategory(17)}" scope="request" /> 
                <%@include file="tab/DataInfo.jsp"%>g/m<sup>2</sup>
            </td>
            <td>
                <c:set var="Data" value="${item.getFabricDataByCategory(27)}" scope="request" /> 
                <%@include file="tab/DataInfo.jsp"%>%
            </td>
            <td>
                <c:set var="Data" value="${item.getFabricDataByCategory(19)}" scope="request" /> 
                <c:set var="Color" value="true" scope="request" /> 
                <%@include file="tab/DataInfo.jsp"%>
            </td>
        </tr>


    </c:forEach>  
</table>
<script>
    $(".pie").each(function () {
        $(this).peity("pie");
    });
</script>
