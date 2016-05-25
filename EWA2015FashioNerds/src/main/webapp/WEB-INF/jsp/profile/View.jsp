<%-- 
    Document   : View
    Created on : 5-nov-2015, 10:14:33
    Author     : Floris van Lent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title><spring:message code="profile.view.title"/></title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>
            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <%@include file="../includes/Message.jsp" %>
                        <h1><spring:message code="profile.view.title"/></h1>
 
                        <table width="100%"> 
                            <tr>
                                <td width="216">
                                    <img src="${profile.getGravatar()}" alt="Gravatar Failed" style="width:200px; height:200px;"/>
                                </td>
                                <td>
                                    <table class="profile" width="100%">
                                        <tr>
                                            <th width="20%"><spring:message code="profile.view.nickname"/>: </th>
                                            <td><c:out value="${profile.getNickname()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.fullName"/>: </th>
                                            <td><c:out value="${profile.getFullName()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.age"/>: </th>
                                            <td><c:out value="${profile.getAge()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.organisation"/>: </th>
                                            <td><c:out value="${profile.getOrganisation()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.location"/>: </th>
                                            <td><c:out value="${profile.getLocation()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.workFunction"/>: </th>
                                            <td><c:out value="${profile.getWorkFunction()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.phoneNumber"/>: </th>
                                            <td><c:out value="${profile.getPhoneNumber()}"/></td>
                                        </tr> 
                                        <tr>
                                            <th><spring:message code="profile.view.email"/>: </th>
                                            <td><c:out value="${profile.getEmailAddress()}"/></td>
                                        </tr>
                                        <tr>
                                            <th><spring:message code="profile.view.bio"/>: </th>
                                            <td><c:out value="${profile.getBio()}"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>