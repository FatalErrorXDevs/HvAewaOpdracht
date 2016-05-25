
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
        <%@include file="../includes/Head.jsp" %>
        <meta charset="UTF-8">
        <title>Fabric</title>

        <style>
            .info-welcome
            {
                padding: 10px;
                background: #FFFFFF;
                border: 1px solid #DDDDDD;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <%@include file="../includes/Header.jsp" %>

            <div class="main-content">
                <%@include file="../includes/Left.jsp" %>
                <div class="page right">
                    <div class="wrapper">
                        <c:choose>
                            <c:when test="${FabricModel.isNew()}">
                                <h1 class="line"><spring:message code="menu.fabrics.add"/></h1>

                                <div class="info-welcome">
                                    <h2><spring:message code="fabrics.add.thanks.title"/></h2>
                                    <p><spring:message code="fabrics.add.thanks.message"/></p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h1><spring:message code="menu.fabrics.edit"/></h1>
                            </c:otherwise>
                        </c:choose>

                        <%@include file="../includes/Message.jsp" %> 




                        <form:form method="post" modelAttribute="FabricModel" commandName="FabricModel" 
                                   action="${pageContext.request.contextPath}/Fabrics/${projectId}/Save">


                            <form:errors path="*" class="error" element="p" />

                            <form:hidden path="Id"/>
                            <form:hidden path="createdDate"/>
                            <script>
                                $(function () {
                                    $("#tabs").tabs();
                                });
                            </script>
                            <div id="tabs">
                                <ul>
                                    <c:forEach items="${Tabs}" var="tab">
                                        <li><a href="#tabs-${tab.getId()}"><c:out value="${tab.getName()}"/></a></li>
                                        </c:forEach>
                                </ul>
                                <c:forEach varStatus="tabStatus" items="${Tabs}" var="tab">
                                    <div id="tabs-${tab.getId()}">

                                        <c:if test="${tab.isGeneralTab()}">
                                            <%@include file="tab/GeneralAdd.jsp" %> 
                                        </c:if>

                                        <table width="100%"> 
                                            <c:forEach varStatus="categoryStatus" items="${tab.getCategories()}" var="category">
                                                <tr>
                                                    <td width="20%"><c:out value="${category.getName()}"/>:</td>
                                                    <td> 
                                                        <%@include file="/WEB-INF/jsp/input/input.jsp" %> 
                                                        <c:if test="${(category.getDataType().isMultipleItems() && category.isUserInput())}">
                                                            <%@include file="/WEB-INF/jsp/input/userInput.jsp" %> 
                                                        </c:if>
                                                    </td>
                                                    <td>                                            
                                                    </td>
                                                </tr> 
                                            </c:forEach>

                                        </table>
                                    </div>
                                </c:forEach>
                                <script>
                                    function buildRadio()
                                    {
                                        $arr = [];
                                        var i = 0;
                                        $(".radio-li").each(function (e) {
                                            $(this).remove();
                                        });
                                        $(".function-radio").each(function (e) {
                                            $(this).parent().hide();
                                            var title = $(this).parent().find("label").html();
                                            var id = $(this).attr("data-group");

                                            $(this).addClass("data-checkId-" + i);
                                            $elm2 = '<li class="radio-li"><input class="radio-' + i + '" type="radio" name="check-' + id + '" onChange="selectCheckbox(this);" value="' + id + '" data-id="data-checkId-' + i + '" id="check-' + i + '" checked="' + $(this).is(':checked') + '" /><label for="check-' + i + '">' + title + '</label></li>';
                                            $(this).parent().parent().append($elm2);
                                            i++;
                                        });
                                    }
                                    buildRadio();
                                    function selectCheckbox(elm)
                                    {
//                                        console.log($(elm).attr("data-id"));
                                        $(elm).parent().parent().find("input[type=checkbox]").each(function () {
                                            $(this).prop("checked", false);
                                        });
                                        $("." + $(elm).attr("data-id")).prop("checked", true);
                                    }
                                    $(".chosen-select").chosenImage({allow_single_deselect: true});
                                </script>
                            </div>

                            <br />
                            <table width="500">
                                <tr>
                                    <td width="32"><img class="logo" title="Value is reviewed" src="<c:url value="/resources/images/flag_g.png" />" alt="OK" /></td>
                                    <td><spring:message code="fabrics.add.green"/></td>
                                    <td width="32"><img class="logo" title="Value must be reviewed" src="<c:url value="/resources/images/flag_r.png" />" alt="Must be reviewed" /></div></td>
                                    <td><spring:message code="fabrics.add.red"/></td>
                                </tr>
                            </table>
                            <br /> 
                            <input type="submit" value="<spring:message code="global.save"/>" name="name" />
                        </form:form>

                        <div id="categoryDataAdd" class="well">

                            <h2><spring:message code="fabrics.add.new"/></h2>


                            <form:form method="post" cssClass="formData" action="${pageContext.request.contextPath}/Fabrics/AddData/">

                                <input type="hidden" class="categoryData-catId" name="catId"/>
                                <input type="hidden" class="categoryData-tabId" name="tabId"/>
                                <input type="text" class="categoryData-value" name="value"/>

                                <input type="submit" value="Eigenschap toevoegen" />
                            </form:form>

                            <button class="categoryDataAdd_close btn btn-default">Close</button>
                        </div>

                        <script>
                            var responseId = "";
                            var responseElement = "";
                            $('#categoryDataAdd').popup({
                                transition: 'all 0.3s'
                            });
                            $(".formData").ajaxForm({
                                beforeSubmit: function (arr, $form, options) {
                                },
                                success: function (responseText, statusText, xhr, $form) {
//                                    $(responseId).html(responseText);
                                    $(responseElement).append($(responseText));
                                    $(responseElement).trigger("chosen:updated");
                                    buildRadio();
                                    $('.categoryData-value').val("");
                                    $('#categoryDataAdd').popup('hide');
                                },
                                error: function showResponse(responseText, statusText, xhr, $form) {

                                    $(responseId).html("<p>Something went wrong</p>");
                                }
                            });

                            function openForm($catId, $tabId)
                            {
                                $('#categoryDataAdd').popup('show');
                                $('.categoryData-catId').val($catId);
                                $('.categoryData-tabId').val($tabId);
                                responseId = ".response-" + $catId;
                                responseElement = ".user-input-" + $catId;
                                setTimeout(function () {
                                    $(".categoryData-value").focus();
                                }, 250);
                            }
                        </script>

                    </div>
                </div>
            </div>
            <%@include file="../includes/Footer.jsp" %>
        </div>
    </body>
</html>
