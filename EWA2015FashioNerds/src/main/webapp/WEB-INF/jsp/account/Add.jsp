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
        <title>Accounts</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <c:choose>
                            <c:when test="${User.freshAccount()}">
                                <h1><spring:message code="menu.accounts.add"/></h1>
                            </c:when>
                            <c:otherwise>
                                <h1><spring:message code="menu.accounts.edit"/></h1>
                            </c:otherwise>
                        </c:choose>

                        <%@include file="../includes/Message.jsp" %> 

                        <spring:url value="/Admin/Accounts/Save" var="userActionUrl" />
                        <spring:url value="/Admin/Accounts/UpdateRole" var="userRoleURL" />

                        <form:form class="form-horizontal" method="post" 
                                   modelAttribute="User"  commandName="User" action="${userActionUrl}">

                            <form:errors path="*" class="error" element="p" />

                            <form:hidden path="id" />
                            <form:hidden path="enabled" />
                            <form:hidden path="requirePasswordChange" /> 

                            <fieldset> 
                                <legend><spring:message code="global.general.information"/>:</legend> 
                                <c:choose>
                                    <c:when test="${User.freshAccount()}">
                                        <spring:bind path="username">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <spring:message code="accounts.model.username" var="username"/>
                                                <label class="control-label">${username}:</label>
                                                <div>
                                                    <form:input path="username" type="text" class="form-control" 
                                                                id="username" placeholder="${username}"/>
                                                    <form:errors path="username" class="error" />
                                                </div>
                                            </div>
                                        </spring:bind> 
                                    </c:when>
                                    <c:otherwise>
                                        <spring:bind path="username">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <spring:message code="accounts.model.username" var="username"/>
                                                <label class="control-label">${username}:</label>
                                                <div>
                                                    <form:input path="username" type="text" class="form-control" 
                                                                id="username" placeholder="${username}" readonly="true" />
                                                    <form:errors path="username" class="error" />
                                                </div>
                                            </div>
                                        </spring:bind> 
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${User.freshAccount()}">
                                        <spring:bind path="password">
                                            <div class="${status.error ? 'has-error' : ''}">
                                                <spring:message code="accounts.model.password" var="password"/>
                                                <label class="control-label">${password}:</label>
                                                <div>
                                                    <form:input path="password" type="text" class="form-control" 
                                                                id="password" placeholder="${password}" />
                                                    <input type="button" onclick="generateCode($('#password'))" value="<spring:message code="global.generate"/>" />
                                                    <form:errors path="Password" class="error" />
                                                </div>
                                            </div>
                                        </spring:bind>
                                    </c:when>

                                    <c:otherwise>
                                        <p><em><spring:message code="accounts.password.change.notHere"/></em></p>
                                        <form:hidden path="Password"
                                                     id="Password" placeholder="${password}" />
                                    </c:otherwise>
                                </c:choose>

                            </fieldset>

                            <fieldset> 
                                <legend><spring:message code="accounts.model.language"/>:</legend> 
                                <spring:bind path="Language">
                                    <div class="${status.error ? 'has-error' : ''}">
                                        <label class="control-label"><spring:message code="accounts.model.language"/>:</label>
                                        <div class="">
                                            <form:select path="Language" class="form-control"> 
                                                <form:options items="${languageList}" />
                                            </form:select>
                                            <form:errors path="Language" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset> 
                                <c:forEach items="${userRole}" var="t">
                                   - ${t.role}
                                </c:forEach>
                            <fieldset>
                                <legend>Titel</legend>
                                <spring:bind path="userRole">
                                    <div class="${status.error ? 'has-error' : ''}">
                                        <label class="control-label"><spring:message code="accounts.model.role"/>:</label>
                                        <div class="">
                                            <form:checkboxes path="userRole" class="form-control" items="${rightList}" itemLabel="role" itemValue="userRoleId"/>
                                            <form:errors path="userRole" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset>
                            <input type="submit" value="<spring:message code="global.save"/>" />

                        </form:form>
                        <%--<form:form class="form-horizontal" method="post" 
                                   modelAttribute="Role"  commandName="Role" action="${userRoleURL}">
                            <c:choose>
                                <c:when test="${User.freshAccount()}">

                                </c:when>
                                <c:otherwise>
                                    <p><em>test123</em></p>
                                    <spring:bind path="role">
                                        <div class="${status.error ? 'has-error' : ''}">
                                            <label class="control-label"><spring:message code="accounts.model.role"/>:</label>
                                            <div class="">
                                                <form:select path="role" class="form-control">
                                                    <spring:message code="global.select" var="select"/>
                                                    <form:option value="NONE" label="--- ${select} ---" />
                                                    <form:options items="${rightList}" />
                                                </form:select>
                                                <form:errors path="role" class="error" />
                                            </div>
                                        </div>
                                    </spring:bind> 
                                    <spring:bind path="userID">
                                        <div class="${status.error ? 'has-error' : ''}">
                                            <spring:message code="accounts.model.password" var="password"/>
                                            <label class="control-label">UserID:</label>
                                            <div>
                                                <form:input path="userID" type="text" class="form-control" 
                                                            id="userID" value ="${User.getId()}" readonly="true" />
                                                <form:errors path="userID" class="error" />
                                            </div>
                                        </div>
                                    </spring:bind>
                                    <input type="submit" value="<spring:message code="global.save"/>" name=name" />
                                </c:otherwise>
                            </c:choose>

                        </form:form>--%>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>

        <script>
            <c:if test="${User.freshAccount() && !User.hasPassword()}">
        generateCode($('#Password'));
            </c:if>
        </script>
    </body>
</html>