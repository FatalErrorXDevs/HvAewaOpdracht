<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>FashioNerds</title>
    </head>
    <body>
        <div class="content">
            <%@include file="includes/Header.jsp" %>

            <div class="main-content">
                <div class="page">
                    <div class="wrapper">
                        <div class="logo"></div>

                        <div class="login-block">
                            <div class="login-left">
                                <div class="login-wrapper">
                                    <h1>Login</h1>
                                    <%@include file="includes/Message.jsp" %>

                                    <c:if test="${not empty error}">
                                        <div class="error">${error}</div>
                                    </c:if>

                                    <form name='loginForm' action="<c:url value='/login' />" method='POST'>

                                        <br/>Username:
                                        <input type="text" name="username">
                                        <br/>Password:
                                        <input type="password" name="password">
                                        <br/>
                                        <input name="submit" type="submit"
                                                               value="submit" />
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />

                                    </form>
                                </div>
                            </div> 
                            <div class="announcement-block">
                                <div class="login-wrapper">
                                    <h1>Announcements</h1>
                                </div> 
                                <c:forEach items="${list}" var="list">
                                    <div class="announcement">
                                        <h3><c:out value="${list.getTitle()}"/></h3>
                                        <p><c:out value="${list.getMessage()}"/></p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="includes/Footer.jsp" %>
        </div>
    </body>
</html>
