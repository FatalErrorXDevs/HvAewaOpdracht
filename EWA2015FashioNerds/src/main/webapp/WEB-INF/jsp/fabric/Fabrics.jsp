<%-- 
    Document   : Fabrics
    Created on : 9-okt-2015, 10:24:50
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
    <head>
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Fabric Data</title>
        <script type="text/javascript" src="<c:url value="/resources/js/excellentexport.js" />"></script>
    </head>
    <body>
        <div class="content">
            <%@include file="/WEB-INF/jsp/includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">   
                        <%@include file="../includes/Message.jsp" %> 

                        <div class="searchResponse">
                            <spring:message code="global.loading"/>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>

            <script>

                function showLaden()
                {
                    $(".searchResponse").html('<spring:message code="global.loading"/>');
                }

                showLaden();
//                $.ajax({
//                    type: 'GET',
//                    url: '<c:url value="/Fabrics/SearchList" />',
//                    data: ''
//                }).done(function (data) {
//                    $(".searchResponse").html(data);
//                    addForm( $(".searchResponse").find("#filterFormSort"));
//                }).fail(function () {
//                    $(".searchResponse").html("Error");
//                });


                var successFunc = function (responseText, statusText, xhr, $form) {
                    $(".searchResponse").html(responseText);
                    addForm($(".searchResponse").find("#filterFormSort"));
                }

                function addForm(el)
                {
                    $(el).ajaxForm({
                        beforeSubmit: function (arr, $form, options) {
                            showLaden();
                        },
                        success: successFunc,
                        error: function showResponse(responseText, statusText, xhr, $form) {

                            $(".searchResponse").html("<p>Something went wrong!</p>");
                        }
                    });
                }

                addForm($('#filterForm'));
                setTimeout(function () {
                    $('#filterForm').submit();
                }, 1000);

            </script>
        </div>
    </body>
</html>