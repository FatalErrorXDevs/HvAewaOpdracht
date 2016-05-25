<%-- 
    Document   : slider
    Created on : 3-nov-2015, 10:34:45
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="colorpicker colorpicker-${singleFilter.id}">
    <form:hidden path="filterGroups[${gi.index}].filters[${fi.index}].value" class="colorpicker-data-${singleFilter.id}" id="data-sel-${singleFilter.id}" value="" />
    <div class="overlay">
    </div>
</div>
    <a href="javascript:resetButton('${singleFilter.id}') ">Leeg maken</a>

<script>
    function resetButton($filterid)
    {
        $('.colorpicker-data-'+$filterid).val('');
        $('.colorpicker-'+$filterid).css("background", 'none');
        formSubmit();
    }
    $(function () {
        var dialogHidden_${singleFilter.id} = $('.colorpicker-data-${singleFilter.id}').colorpicker({
            parts: 'full',
            swatches: 'pantone',
            swatchesWidth: 250,
            buttonImageOnly: true,
            colorFormat: 'RGB',
            swatchesTitle: 'Pantone',
            init: function (event, color) {
                $('.colorpicker-${singleFilter.id}').css("background", color.formatted);
            },
            select: function (event, color) {
                $('.colorpicker-${singleFilter.id}').css("background", color.formatted);
                formSubmit();
            }
        });
        $('.colorpicker-${singleFilter.id}').click(function (e) { 
            e.stopPropagation();
            dialogHidden_${singleFilter.id}.colorpicker('open');
        });
    });
</script>