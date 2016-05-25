<%-- 
    Document   : detailsPage
    Created on : Nov 8, 2015, 2:17:22 PM
    Author     : Tom Prins
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html> 
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Fabric Data</title>
        <script type="text/javascript" src="<c:url value="/resources/js/excellentexport.js" />"></script>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <%@include file="../includes/Message.jsp" %>


                        <div class="top">   
                            <div class="top"> 
                                <div class="info">
                                    <div class="title"><h1><c:out value="${project.getProjectName()}" /></h1></div>
                                    <div class="date"><p><c:out value="${project.getProjectDescription()}" /></p></div>
                                </div>
                            </div>
                            <%--
                            // Werk van Yassine en Ternel
                            <div class="buttons">
                                <div class="add button">
                                    <a href="${pageContext.request.contextPath}/Projects/${id}/Overzicht">
                                        <button>Overzicht accounts</button>
                                    </a>
                                </div>
                                <div class="add button">
                                    <a href="${pageContext.request.contextPath}/Fabrics/${id}/AddNewFabricToProject">
                                        <button>Add fabric to project</button>
                                    </a>
                                </div>
                                <div class="add button">
                                    <a href="${pageContext.request.contextPath}/Admin/Accounts/${id}/addAccountToProject">
                                        <button>Join account to project</button>
                                    </a>
                                </div>
                            </div>--%>


                        </div>

                        <script>
                            $(function () {
                                $("#tabs").tabs();
                            });
                        </script>



                        <div id="tabs">
                            <ul>
                                <li><a href="#tabs-1">Projects</a></li>
                            </ul>

                            <div id="tabs-1"> 

                                <table class="results" id="results">
                                    <tr>  
                                        <th width="5%"></th>
                                        <th width="13%"><spring:message code="fabrics.model.status"/>:</th>
                                        <th width="12%"><spring:message code="fabrics.model.name"/>:</th>
                                        <th width="15%"><spring:message code="fabrics.model.composition"/>:</th> 
                                        <th width="10%"><spring:message code="fabrics.model.weight"/>:</th> 
                                        <th width="15%"><spring:message code="fabrics.model.drape"/>:</th>
                                        <th width="5%"><spring:message code="fabrics.model.color"/>:</th> 
                                        <th width="15%">Delete Fabric:</th>

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
                                            <td>
                                                <div class="buttons">
                                                    <div class="add button">
                                                        <a href="${pageContext.request.contextPath}/Projects/${id}/${item.getId()}/deletefromprojects/">
                                                            <button>Delete</button>
                                                        </a>
                                                    </div>
                                                </div>
                                            </td>

                                        </tr>

                                    </c:forEach>  
                                </table> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div> 
        <script>
            $(".pie").each(function () {
                $(this).peity("pie");
            });
        </script>
    </body>
</html>