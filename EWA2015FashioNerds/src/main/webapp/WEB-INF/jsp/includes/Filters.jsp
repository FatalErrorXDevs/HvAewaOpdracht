<%-- 
    Document   : Filters
    Created on : 3-nov-2015, 12:05:31
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form:form modelAttribute="filterModel" commandName="FabricFilterModel" id="filterForm" action="${filterUrl}" method="post"> 
    <form:hidden path="userFilter" value="true" />
    <ul class="filters-list">
        <c:forEach items="${filter.filterGroups}" var="filterGroup" varStatus="gi">  
            <li class="group"> 
                <h2 class="title">${filterGroup.getName()}:</h2>
                <ul class="filters">
                    <c:forEach items="${filterGroup.filters}" var="singleFilter" varStatus="fi">  
                        <li class="filter">
                            <h3 class="title">${singleFilter.getName()}<span class="unit">${singleFilter.getUnit()}</span></h3>
                            <c:if test="${singleFilter.getDescription() != null}">
                                <div class="description">${singleFilter.getDescription()}</div>
                            </c:if>
                            <div class="filter-content"> 
                                <c:set var="singleFilter" value="${singleFilter}" scope="request" />
                                <c:set var="fi" value="${fi}" scope="request" />
                                <c:set var="gi" value="${gi}" scope="request" /> 
                                <jsp:include page="${singleFilter.view}" />
                            </div>
                        </li>
                    </c:forEach>  
                </ul>
            </li>
        </c:forEach>  
    </ul> 
    <a class="resetButton" href="${pageContext.request.contextPath}/Fabrics/ResetFilter">Reset filters</a>
</form:form>
<script>
    function formSubmit()
    {
        $("#filterForm").submit();
    }
</script>