<%-- 
    Document   : announcements/Add
    Created on : 6-jan-2016, 05:19:45
    Author     : Floris van Lent, Bert Kooij
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
        <%@include file="../../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title><spring:message code="announcements"/></title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>
            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">

                        <c:choose>
                            <c:when test="${Announcement.isNew()}">
                                <h1><spring:message code="announcements.add"/></h1>
                            </c:when>
                            <c:otherwise>
                                <h1><spring:message code="announcements.edit"/></h1>
                            </c:otherwise>
                        </c:choose>

                        <%@include file="../../includes/Message.jsp" %> 

                        <spring:url value="/Admin/Announcements/Save/" var="userActionUrl" />

                        <form:form class="form-horizontal" method="post" modelAttribute="Announcement" commandName="Announcement" action="${userActionUrl}">

                            <form:errors path="*" class="error" element="p" />

                            <form:hidden path="announcementID"/> 

                            <fieldset> 
                                <legend><spring:message code="announcements.title"/>:</legend> 
                                <spring:bind path="title">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <div>
                                            <form:input path="title" type="text" class="form-control" id="Title" placeholder="${title}" />
                                            <form:errors path="title" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset>

                            <fieldset>
                                <legend><spring:message code="announcements.message"/>:</legend>
                                <spring:bind path="message">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <div>
                                            <form:textarea path="message" rows="12" cols="41" type="text"
                                                           class="form-control" id="Message" placeholder="${message}" />
                                            <form:errors path="message" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset>

                            <input type="submit" value="<spring:message code="global.save"/>" />

                        </form:form>
                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>
    </body>
</html>
