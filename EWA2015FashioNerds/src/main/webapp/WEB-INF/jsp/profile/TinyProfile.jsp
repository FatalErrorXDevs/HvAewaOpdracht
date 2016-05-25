<%-- 
    Document   : TinyProfile
    Created on : 16-dec-2015, 16:59:26
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="tinyProfile">
    <a href="${pageContext.request.contextPath}/Profile/View/${Account.getProfile().getProfileID()}/">
        <table >
            <tr style="background: none;">
                <td><img src="${Account.getProfile().getGravatar()}" alt="Gravatar Failed" style="width:24px; height:24px; margin-right: 5px;"/></td>
                <td style="vertical-align: middle;">${Account.getUsername()}</td>
            </tr>
        </table>
    </a>
</div>

