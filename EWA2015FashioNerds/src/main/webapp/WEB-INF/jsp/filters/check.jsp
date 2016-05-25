<%-- 
    Document   : slider
    Created on : 3-nov-2015, 10:34:45
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="filter-checkboxes">
    <c:if test="${singleFilter.isAndOption()}">
        <form:checkbox path="filterGroups[${gi.index}].filters[${fi.index}].useAsAnd" onhange="formSubmit();"  class="switch" />
    </c:if>
    <form:checkboxes path="filterGroups[${gi.index}].filters[${fi.index}].selectedValues" 
                     items="${singleFilter.items}" 
                     itemValue="id"
                     onclick="formSubmit();"
                     itemLabel="name" element="div"/>
</div>
<script>
    $(function () {
        $(".switch").switchButton({
            on_label: 'And',
            off_label: 'Or',
            callback: function () {
                formSubmit();
            }
        });
    })
</script>