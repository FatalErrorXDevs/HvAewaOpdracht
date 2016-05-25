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

<div class="filter-date"> 
    <form:input path="filterGroups[${gi.index}].filters[${fi.index}].value" onchange="formSubmit();" cssClass="datepicker-field date-picker-${singleFilter.id}" /> 
     <script>
    $(document).ready(function () { 
        $(".date-picker-${singleFilter.id}").datepicker();
    });

    </script>
</div>