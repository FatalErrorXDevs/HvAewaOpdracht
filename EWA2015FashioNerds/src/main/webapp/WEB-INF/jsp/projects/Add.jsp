<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
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
                        <h1>Add Project</h1>

                        <form action="${pageContext.request.contextPath}/Projects/Save">
                            <fieldset>
                                <table width="100%" border="0">
                                    <tr>
                                        <td width="25%"><spring:message code="projects.name"/>:</td>
                                        <td><input type="text" name="ProjectName" /></td>
                                    </tr> 

                                    <tr>
                                        <td><spring:message code="projects.description"/>:</td>
                                        <td><textarea name="ProjectDescription" rows="6" cols="47" ></textarea></td>

                                    </tr>
                                </table>
                            </fieldset>
                            <input type="submit" value="<spring:message code="global.save"/>" />
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>
