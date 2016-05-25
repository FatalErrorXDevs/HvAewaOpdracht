<%-- 
    Document   : Edit
    Created on : 26-nov-2015, 11:12:15
    Author     : Floris van Lent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:htmlEscape defaultHtmlEscape="true" /> 
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
                        <h1><spring:message code="profile.edit"/></h1>

                        <form:form method="POST" modelAttribute="editProfile" commandName="Profile" action="${pageContext.request.contextPath}/Profile/Edit">

                            <form:hidden path="profileID"/>
                            <fieldset>
                                <table class="profile" width="69%" border="0">
                                    <tr>
                                        <td><spring:message code="profile.view.nickname"/>: </td>
                                        <td><form:input path="nickname"/></td>
                                    </tr> 
                                    <tr>
                                        <td><spring:message code="profile.view.fullName"/>: </td>
                                        <td><form:input path="fullName"/></td>
                                    </tr> 
                                    <tr>
                                        <td><spring:message code="profile.view.age"/>: </td>
                                        <td><form:input path="age"/></td>
                                    </tr> 
                                    <tr>
                                        <td><spring:message code="profile.view.organisation"/>: </td>
                                        <td><form:input path="organisation"/></td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="profile.view.location"/>: </td>
                                        <td><form:input path="location"/></td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="profile.view.workFunction"/>: </td>
                                        <td><form:input path="workFunction"/></td>
                                    </tr> 
                                    <tr>
                                        <td><spring:message code="profile.view.phoneNumber"/>: </td>
                                        <td><form:input path="phoneNumber"/></td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="profile.view.email"/>: </td>
                                        <td><form:input path="emailAddress"/></td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="profile.view.bio"/>: </td>
                                        <td><form:textarea path="bio" rows="5" cols="35"/></td>
                                    </tr>
                                </table> 
                            </fieldset>
                            <input type="submit" value="<spring:message code="global.save"/>" />
                        </form:form>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>
