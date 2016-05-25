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
        <title>stoffen overzicht!</title>
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
                            <div class="info">
                                <div class="title"><h1><c:out value="${fabric.getFabricName()}" /></h1></div>
                                <div class="date"><p><fmt:formatDate pattern="YYYY-MM-DD HH:MM" value="${fabric.getCreatedDate().toDate()}" /></p></div>
                            </div>
                            <div class="buttons">
                                <div class="edit button">
                                    <a href="${pageContext.request.contextPath}/Fabrics/${fabric.getId()}/edit/">
                                        <button>Contribute</button>
                                    </a>
                                </div>
                                <div class="export button"> 
                                    <ul>
                                        <li><a href="javascript:return false;">Export <div class="down">&#x25BC;</div></a> 
                                            <ul>
                                                <li><a href="${pageContext.request.contextPath}/Fabrics/Export/${fabric.getId()}/">PDF</a></li>
                                                <li><a download="fabricData.xls" href="#" onclick="return ExcellentExport.excel(this, 'results', 'Fabric');">Excel</a></li>
                                                <li><a download="fabricData.csv" href="#" onclick="return ExcellentExport.csv(this, 'results');">CSV</a></li>
                                            </ul>
                                        </li>
                                    </ul> 
                                </div>

                                <c:choose>
                                    <c:when test="${fabric.hasFavorite(myAccount)}">
                                        <div class="favorite button">
                                            <a href="${pageContext.request.contextPath}/Fabrics/Favorites/${fabric.getId()}/Remove/">
                                                <button>Remove as favorite</button>  
                                            </a>                                  
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="favorite button isFavorite">
                                            <a href="${pageContext.request.contextPath}/Fabrics/Favorites/${fabric.getId()}/Add/">
                                                <button>Mark as favorite</button>  
                                            </a>                                  
                                        </div>



                                    </c:otherwise>
                                </c:choose>
                                <div class="project button">
                                    <a href="${pageContext.request.contextPath}/Projects/${fabric.getId()}/list">
                                        <button>Save in project</button>  
                                    </a>                                  
                                </div>
                            </div>
                        </div>

                        <script>
                            $(function () {
                                $("#tabs").tabs();
                            });
                        </script>
                        <div class="status large">
                            <div class="fill" style="width: ${fabric.getCompleteness(categories)}%;"></div> 
                            <div class="line" style="left: ${fabric.getCompleteness(categories)}%;"></div>
                            <div class="label" style="left: ${fabric.getCompleteness(categories)}%;">${fabric.getCompleteness(categories)}%</div>
                        </div>

                        <div id="tabs">
                            <ul>
                                <c:forEach items="${Tabs}" var="tab">
                                    <li><a href="#tabs-${tab.getId()}"><c:out value="${tab.getName()}"/></a></li>
                                    </c:forEach>
                            </ul>
                            <c:forEach varStatus="tabStatus" items="${Tabs}" var="tab">
                                    <div id="tabs-${tab.getId()}">

                                        <c:if test="${tab.isGeneralTab()}">
                                            <%@include file="tab/General.jsp" %> 
                                        </c:if>

                                        <table width="100%" cellspacing="10"> 
                                            <c:forEach varStatus="categoryStatus" items="${tab.getCategories()}" var="category">
                                                <tr>
                                                    <td width="25%"><c:out value="${category.getName()}"/>:</td>
                                                    <td> 
                                                        <c:set var="Category" value="${category}" scope="request" /> 
                                                        <c:set var="Data" value="${fabric.getFabricDataByCategory(category)}" scope="request" /> 
                                                        <%@include file="tab/DataInfo.jsp"%> 
                                                    </td>
                                                    <td>                                            
                                                    </td>
                                                </tr> 
                                            </c:forEach>

                                        </table>
                                    </div>
                                </c:forEach>
                        </div>


                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>