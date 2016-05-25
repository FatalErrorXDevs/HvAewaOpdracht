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



                        <script>
                            $(function () {
                                $("#tabs").tabs();
                            });
                        </script>



                        <div id="tabs">
                            <ul>
                                <li><a href="#tabs-1">accounts</a></li>
                            </ul>

                            <div id="tabs-1">
                                <table class="results">

                                    <table class="results" id="results">
                                        <tr>  
                                            <th width="10%">User: </th>
                                            <th width="15%">Delete :</th>

                                        </tr>   
                                        <c:forEach items="${list}" var="item">  

                                            <tr> 


                                                <td>
                                                    <c:out value="${item.getUsername()}" />  
                                                </td>
                                                <c:choose>
                                                    <c:when test="${item.getId() == accountId}">
                                                        <td>
                                                            can't delete owner
                                                        </td>
                                                    <br />
                                                </c:when>    
                                                <c:otherwise>
                                                    <td>
                                                        <div class="buttons">
                                                            <div class="add button">
                                                                <a href="${pageContext.request.contextPath}/Projects/${projectId}/${item.getId()}/deleteAccountFromProject/">
                                                                    <button>Delete</button>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </td>

                                                    <br />
                                                </c:otherwise>
                                            </c:choose>

                                            </tr>

                                        </c:forEach>  
                                    </table>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="../includes/Footer.jsp" %>
            </div>
    </body>
</html>