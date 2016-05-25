<%-- 
    Document   : Fabrics
    Created on : 9-okt-2015, 10:24:50
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">

        <title>Projects</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <c:choose>
                            <c:when test="${listId > '1'}">

                                <h1><spring:message code="projects.choose"/></h1>
                                <br />
                            </c:when>    
                            <c:otherwise>

                                <h1><spring:message code="projects"/></h1>
                                <br />
                            </c:otherwise>
                        </c:choose>


                        <p><spring:message code="global.results.showing" arguments="${list.size()}"/></p>
                        <%@include file="../includes/Message.jsp" %>

                        <table class="results">
                            <tr>  
                                <th width="20%"><spring:message code="projects"/>:</th>
                                <th width="45%"><spring:message code="projects.description"/>:</th>
                                <th width="15%"><spring:message code="projects.delete"/>:</th>
                                    <c:choose>
                                        <c:when test="${listId > '1'}">

                                        <th width="15%"><spring:message code="projects.add"/>:</th>

                                    <br />
                                </c:when>    
                                <c:otherwise>

                                    <br />
                                </c:otherwise>
                            </c:choose>

                            </tr>   
                            <c:forEach items="${list}" var="list" varStatus="count">  


                                <tr>
                                    <td>

                                        <a href="${pageContext.request.contextPath}/Projects/detailsPage/${list.getProjectID()}"><c:out value="${list.getProjectName()}" /></a>


                                    </td>
                                    <td>
                                        <c:out value="${list.getProjectDescription()}" />


                                    </td>

                                    <td>
                                        <div class="buttons">
                                            <div class="add button">
                                                <a href="${pageContext.request.contextPath}/Projects/<c:out value="${list.getProjectID()}" />/delete/">
                                                <button>Delete</button>
                                                </a>
                                            </div>
                                        </div>
                                    </td>

                                    <c:choose>
                                        <c:when test="${listId > '1'}">
                                            <td>
                                                <div class="buttons">
                                                    <div class="add button">
                                                        <a href="${pageContext.request.contextPath}/Projects/${list.getProjectID()}/${listId[count.index]}/addToProject">
                                                            <button>Add</button>
                                                        </a>
                                                    </div>
                                                </div>


                                            </td>
                                    </c:when>    
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                                </tr>

                            </c:forEach>  
                        </table>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>