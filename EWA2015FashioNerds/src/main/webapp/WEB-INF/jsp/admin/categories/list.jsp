<%-- 
    Document   : list
    Created on : 8-dec-2015, 10:53:55
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Categories</title>
    </head>
    <body>
        <div class="content">
            <%@include file="../../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <div class="top"> 
                            <div class="info">
                                <div class="title"><h1><spring:message code="categories.list.title"/></h1></div>
                                <div class="date"><p><spring:message code="global.results.showing" arguments="${InputTypesL}"/></p></div>
                            </div>
                            <div class="buttons">
                                <div class="add button">
                                    <a href="${pageContext.request.contextPath}/Fabrics/Add/" class="category_open">
                                        <button>Add</button>
                                    </a>
                                </div>
                            </div>
                        </div>  

                        <div id="category" class="well category-new-list">

                            <h2><spring:message code="categories.new"/></h2>
                            <p><spring:message code="categories.select"/>:</p>
                            <style>
                                .list-blocks
                                {
                                    overflow: hidden;
                                }
                                .list-blocks .block
                                {
                                    width: 25%;
                                    float: left;
                                }
                                .list-blocks .block .wrapper
                                {
                                    margin: 0px 12px 12px 0;
                                    border: 1px solid #DDDDDD;
                                    min-height: 180px;
                                    position: relative;
                                }
                                .list-blocks .block .wrapper a
                                {
                                    display: block;
                                    top: 0; left: 0; right: 0; bottom: 0;
                                    position: absolute;
                                    background: #FFFFFF;
                                }
                                .list-blocks .block .wrapper a:hover
                                {
                                    background: #EEEEEE;
                                }
                                .list-blocks .block .wrapper .title
                                {
                                    background: #DDDDDD; 
                                    color: #444444;
                                    position: absolute;
                                    bottom: 0px;
                                    width: 100%;
                                }
                                .list-blocks .block .wrapper .title .wrap
                                {
                                    padding: 8px;
                                    font-size: 0.9em;
                                }
                                .list-blocks .block .wrapper .small-icon
                                {
                                    width: 32px;
                                    height: 32px;
                                    top: 0; left: 0;
                                    background: #CCCCCC;
                                }
                                .list-blocks .block .wrapper .small-icon img
                                {
                                    width: 16px;
                                    height: 16px;
                                    margin: 8px;
                                }
                            </style>
                            <div class="list-blocks">
                                <c:forEach items="${InputTypes}" var="input">   
                                    <div class="block">
                                        <div class="wrapper">
                                            <a href="<spring:url value="/Admin/Categories/Add/${input.getId()}/" />">
                                                <div class="small-icon">
                                                    <img src="<c:url value="/resources/images/categories/small_${input.getId()}.png" />" alt="Input type" />
                                                </div>
                                                <div class="large-icon">
                                                    <img src="<c:url value="/resources/images/categories/large_${input.getId()}.png" />"/>
                                                </div>
                                                <div class="title">
                                                    <div class="wrap">
                                                        <spring:message code="input.names.name${input.getId()}"  text="Unknown (${input.getId()})" />
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach> 
                            </div>


                            <button class="category_close btn btn-default">Close</button>

                        </div>
                        <script>
                            $(document).ready(function () {

                                // Initialize the plugin
                                $('#category').popup({
                                    transition: 'all 0.3s'
                                });

                            });
                        </script>

                        <%@include file="../../includes/Message.jsp" %> 

                        <%@include file="Table.jsp" %> 


                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>
    </body>
</html>
