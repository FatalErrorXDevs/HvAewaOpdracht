<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <div class="content">
            <%@include file="includes/Header.jsp" %>

            <div class="main-content">
                <div class="page">
                    <div class="wrapper">
                        <div class="logo"></div>
                        <div class="login-block">
                            <h1><spring:message code="accounts.password.reset"/></h1>

                            <%@include file="includes/Message.jsp" %>

                            <form action="${pageContext.request.contextPath}/SavePassword" method="post">
                                <p><spring:message code="accounts.password.reset.request"/></p>
                                <br/><spring:message code="accounts.model.password"/>:
                                <input type="password" name="password"> 
                                <br/><spring:message code="accounts.model.password.confirm"/>:
                                <input type="password" name="password2">
                                <br/>
                                <input type="submit" value="<spring:message code="accounts.password.reset.save"/>"> </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="includes/Footer.jsp" %>
        </div>
    </body>
</html>