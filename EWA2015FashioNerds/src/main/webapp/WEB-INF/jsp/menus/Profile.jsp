<%-- 
    Document   : Profile
    Created on : 17-dec-2015, 11:38:11
    Author     : Floris van Lent
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${(profile.getAccount().getId().equals(myAccount.getId()))}">
    <ul class="side-menu">
        <li class="menu"><div class="tag"><spring:message code="menu"/></div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/Profile/Edit"><spring:message code="profile.edit"/></a></li>

                <li><a href="https://signup.wordpress.com/signup/?ref=oauth2&oauth2_redirect=36c874cc5b7990b7a19b610b46c8b3b2%40https%3A%2F%2Fpublic-api.wordpress.com%2Foauth2%2Fauthorize%2F%3Fclient_id%3D1854%26response_type%3Dcode%26blog_id%3D0%26state%3Df34281e279b2b83785da3991b71c97078fc599046ca2e92e23f6c1cab9a7b562%26redirect_uri%3Dhttps%253A%252F%252Fen.gravatar.com%252Fconnect%252F%253Faction%253Drequest_access_token%26jetpack-code%26jetpack-user-id%3D0%26action%3Doauth2-login&wpcom_connect=1" target="_blank"><spring:message code="profile.gravatar"/></a></li>
            </ul>
        </li>
    </ul>
</c:if>