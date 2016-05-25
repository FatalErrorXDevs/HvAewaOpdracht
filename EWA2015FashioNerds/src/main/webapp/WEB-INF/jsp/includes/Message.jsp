<%-- 
    Document   : Message
    Created on : 9-okt-2015, 20:53:45
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty msg}">
    <div class="alert alert-${css}" role="alert"> 
        ${msg}
    </div>
</c:if>