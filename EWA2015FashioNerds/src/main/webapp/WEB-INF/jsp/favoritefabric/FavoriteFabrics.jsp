<%-- 
    Document   : Fabrics
    Created on : 9-okt-2015, 10:24:50
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Favorite Fabrics</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <h1><spring:message code= "fabrics.favorites.title"/></h1>
                        <p><spring:message code="global.results.showing" arguments="${list.size()}"/></p>

                        <%@include file="../includes/Message.jsp" %>
                        <table class="results" id="results">
                            <tr>  
                                <th width="15%"><spring:message code="fabrics.model.name"/>:</th>
                                <th width="15%"><spring:message code="fabrics.model.color"/>:</th> 
                                <th width="15%"><spring:message code="fabrics.model.weight"/>:</th> 
                                <th width="15%"><spring:message code="fabrics.model.state"/>:</th>
                                <th width="15%"><spring:message code="fabrics.model.composition"/>:</th>
                                <th width="15%"><spring:message code="fabrics.model.coating"/>:</th>
                                <th width="10%"><spring:message code="fabrics.model.status"/>:</th>
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
                                        <%@include file="../fabric/tab/DataInfo.jsp"%>  
                                    </td>
                                    <td>
                                        <c:set var="Data" value="${item.getFabricDataByCategory(17)}" scope="request" /> 
                                        <%@include file="../fabric/tab/DataInfo.jsp"%>g/m<sup>2</sup>
                                    </td>
                                    <td>
                                        <c:set var="Data" value="${item.getFabricDataByCategory(27)}" scope="request" /> 
                                        <%@include file="../fabric/tab/DataInfo.jsp"%>%
                                    </td>
                                    <td>
                                        <c:set var="Data" value="${item.getFabricDataByCategory(19)}" scope="request" /> 
                                        <c:set var="Color" value="true" scope="request" /> 
                                        <%@include file="../fabric/tab/DataInfo.jsp"%>
                                    </td>
                                </tr>

                            </c:forEach>  
                        </table>
                        <script>
                            $(".pie").each(function () {
                                $(this).peity("pie");
                            });
                        </script>

                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>