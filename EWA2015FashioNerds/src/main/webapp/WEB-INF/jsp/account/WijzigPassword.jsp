<%--
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert, Tom
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
        <title>wijzig password</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <spring:url value="/Admin/Accounts/UpdatePassword" var="userActionUrl" />
                        <h1><spring:message code="menu.accounts.editpass"/></h1>
                        <form class="form-horizontal" method="post"
                              action="${userActionUrl}" id="passwordForm">

                            <fieldset>
                                <div>
                                    <input type="hidden" id="id" name="id" value="${id}" /> 
                                    <spring:message code="accounts.model.password" var="password"/>
                                    <label class="control-label">${password}:</label>
                                    <div>
                                        <input type="text" id="password" name="password"/>
                                        <input type="button" onclick="generateCode($('#password'))" value="<spring:message code="global.generate"/>" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </div>
                                </div>
                                <input type="submit" value="<spring:message code="global.save"/>" />
                            </fieldset>

                        </form>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>