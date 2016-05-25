<%-- 
    Document   : accounts
    Created on : 1-okt-2015, 15:07:30
    Author     : Bert
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
                        <c:choose>
                            <c:when test="${Category.isNew()}">
                                <h1><spring:message code="categories.add"/></h1>
                            </c:when>
                            <c:otherwise>
                                <h1><spring:message code="categories.edit"/></h1>
                            </c:otherwise>
                        </c:choose> 

                        <%@include file="../../includes/Message.jsp" %> 

                        <spring:url value="${saveURL}" var="userActionUrl" />

                        <form:form class="form-horizontal" method="post" 
                                   modelAttribute="Category" commandName="Category" action="${userActionUrl}">

                            <form:errors path="*" class="error" element="p" />

                            <form:hidden path="id" /> 
                            <form:hidden path="dataType" /> 

                            <fieldset> 
                                <legend><img class="small-16" src="<c:url value="/resources/images/categories/small_${Category.getDataType().getId()}.png" />" alt="Input type" /> <spring:message code="input.names.name${Category.getDataType().getId()}"  text="Unknown (${Category.getDataType().getId()})" />:</legend>  
                                <div class="info-text">
                                    <img src="<c:url value="/resources/images/categories/large_${Category.getDataType().getId()}.png" />" style="border: 1px solid #DDDDDD;"/>
                                    <p><spring:message code="input.names.description${Category.getDataType().getId()}"  text="Unknown (${Category.getDataType().getId()})" /></p>
                                </div>
                            </fieldset>

                            <fieldset> 
                                <legend><spring:message code="global.general.information"/>:</legend> 
                                <spring:bind path="name">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="categories.model.name" var="name"/>
                                        <label class="control-label">${name}:</label>
                                        <div>
                                            <form:input path="name" type="text" class="form-control" 
                                                        id="Name" placeholder="${name}" />
                                            <form:errors path="name" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                                <spring:bind path="description">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="categories.model.description" var="description"/>
                                        <label class="control-label">${description}:</label>
                                        <div>
                                            <form:textarea path="description" type="text" class="form-control" 
                                                           id="description" placeholder="${description}" />
                                            <form:errors path="description" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                                <spring:bind path="unit">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="categories.model.unit" var="unit"/>
                                        <label class="control-label">${unit}:</label>
                                        <div>
                                            <form:input path="unit" type="text" class="form-control" 
                                                           id="unit" placeholder="${unit}" />
                                            <form:errors path="unit" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                                <spring:bind path="tab">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="categories.model.tab" var="tab"/>
                                        <label class="control-label">${tab}:</label>
                                        <div>
                                            <form:select items="${Tabs}" itemLabel="name" itemValue="id" path="tab" class="form-control" 
                                                         id="tab" />
                                            <form:errors path="tab" class="error" />
                                        </div>
                                    </div>
                                </spring:bind> 
                            </fieldset>

                            <fieldset> 
                                <legend><spring:message code="global.completion"/>:</legend> 
                                <spring:bind path="weight">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <spring:message code="categories.model.weight" var="weight"/>
                                        <label class="control-label">${weight}:</label>
                                        <div>
                                            <form:hidden path="weight" class="form-control" 
                                                         id="weight-value" placeholder="${weight}" />


                                            <style>
                                                .slider-wrapper
                                                {
                                                    margin: 56px;
                                                } 
                                            </style>
                                            <div class="slider-wrapper">
                                                <div id="double-label-slider"></div>
                                            </div> 
                                            <form:errors path="weight" class="error" />
                                            <div class="info"><spring:message code="categories.model.weight.description"/></div>
                                        </div>
                                    </div>
                                </spring:bind> 
                                <script>
                                    var doubleLabels = [
                                        "<i>1</i><span>Niet belangrijk</span>",
                                        "<i>2</i><span></span>",
                                        "<i>3</i><span></span>",
                                        "<i>4</i><span></span>",
                                        "<i>5</i><span></span>",
                                        "<i>6</i><span></span>",
                                        "<i>7</i><span></span>",
                                        "<i>8</i><span></span>",
                                        "<i>9</i><span></span>",
                                        "<i>10</i><span>Erg belangrijk</span>",
                                    ];

                                    $("#double-label-slider")
                                            .slider({
                                                max: 10,
                                                min: 1,
                                                value: 5,
                                                animate: 400,
                                                slide: function (event, ui) {
                                                    $("#weight-value").val(ui.value);
                                                }
                                            })
                                            .slider("pips", {
                                                rest: "label",
                                                labels: doubleLabels
                                            });
                                </script>

                            </fieldset>

                            <fieldset>
                                <legend><spring:message code="global.extra.options"/></legend>
                                <table>
                                    <spring:bind path="filter">
                                        <tr>
                                            <td width="200">
                                                <spring:message code="category.model.filter" var="filter"/>
                                                <label class="control-label">${filter}:</label>
                                            </td>
                                            <td>
                                                <spring:message code="global.yes" var="yes"/>
                                                <spring:message code="global.no" var="no"/>
                                                <form:radiobutton path="filter" class="form-control" 
                                                                  id="filter" value="0" label="${no}" />  
                                                <form:radiobutton path="filter" class="form-control" 
                                                                  id="filter" value="1" label="${yes}" /> 
                                                <form:errors path="filter" class="error" />
                                            </td>
                                        </tr>
                                    </spring:bind> 
                                    <c:if test="${Category.getDataType().isMultipleItems()}">
                                        <spring:bind path="userInput">
                                            <tr>
                                                <td width="200">
                                                    <spring:message code="category.model.userInput" var="filter"/>
                                                    <label class="control-label">${filter}:</label>
                                                </td>
                                                <td> 
                                                    <form:radiobutton path="userInput" class="form-control" 
                                                                      id="userInput" value="0" label="Nee" />  
                                                    <form:radiobutton path="userInput" class="form-control" 
                                                                      id="userInput" value="1" label="Ja" /> 
                                                    <form:errors path="userInput" class="error" />
                                                </td>
                                            </tr>
                                        </spring:bind>
                                            <spring:bind path="reviewRequired">
                                            <tr>
                                                <td width="200">
                                                    <spring:message code="category.model.reviewRequired" var="reviewRequired"/>
                                                    <label class="control-label">${reviewRequired}:</label>
                                                </td>
                                                <td> 
                                                    <form:radiobutton path="reviewRequired" class="form-control" 
                                                                      id="userInput" value="0" label="Nee" />  
                                                    <form:radiobutton path="reviewRequired" class="form-control" 
                                                                      id="userInput" value="1" label="Ja" /> 
                                                    <form:errors path="reviewRequired" class="error" />
                                                </td>
                                            </tr>
                                        </spring:bind>
                                    </c:if>
                                </table>
                            </fieldset>
                            <input type="submit" value="<spring:message code="global.save"/>" />
                        </form:form>
                    </div>
                </div>
            </div>
            <%@include file="../../includes/Footer.jsp" %>
        </div>
    </body>
</html>
