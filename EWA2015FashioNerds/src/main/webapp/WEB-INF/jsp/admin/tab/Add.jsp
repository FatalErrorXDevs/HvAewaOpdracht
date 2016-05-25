<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert
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
        <title>Tabs</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <c:choose>
                            <c:when test="${Tab.isNew()}">
                                <h1><spring:message code="tabs.add"/></h1>
                            </c:when>
                            <c:otherwise>
                                <h1><spring:message code="tabs.edit"/></h1>
                            </c:otherwise>
                        </c:choose>

                        <%@include file="../../includes/Message.jsp" %> 

                        <spring:url value="/Admin/Tabs/Save/" var="userActionUrl" />

                        <form:form class="form-horizontal" method="post" 
                                   modelAttribute="Tab" commandName="Tab" action="${userActionUrl}">

                            <form:errors path="*" class="error" element="p" />

                            <form:hidden path="id" /> 

                            <fieldset> 
                                <legend><spring:message code="global.general.information"/>:</legend> 
                                <spring:bind path="name">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="tabs.model.name" var="name"/>
                                        <label class="control-label">${name}:</label>
                                        <div>
                                            <form:input path="name" type="text" class="form-control" 
                                                        id="Name" placeholder="${name}" />
                                            <form:errors path="name" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset>
                            <fieldset>
                                <legend><spring:message code="global.extra.options"/></legend>
                                <table>
                                    <spring:bind path="generalTab">
                                        <tr>
                                            <td width="200">
                                                <spring:message code="tabs.model.generalTab" var="generalTab"/>
                                                <label class="control-label">${generalTab}:</label>
                                            </td>
                                            <td> 
                                                <spring:message code="global.yes" var="yes"/>
                                                <spring:message code="global.no" var="no"/>
                                                <form:radiobutton path="generalTab" class="form-control" 
                                                                  id="generalTab" value="0" label="${no}" />  
                                                <form:radiobutton path="generalTab" class="form-control" 
                                                                  id="generalTab" value="1" label="${yes}" /> 
                                                <form:errors path="generalTab" class="error" />
                                            </td>
                                        </tr>
                                    </spring:bind> 
                                </table>
                            </fieldset>
                            <input type="submit" value="<spring:message code="global.save"/>" />
                        </form:form>
                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>

        <script>
            <c:if test="${Account.isNew() && !Account.hasPassword()}">
            generateCode($('#Password'));
            </c:if>
        </script>
    </body>
</html>
