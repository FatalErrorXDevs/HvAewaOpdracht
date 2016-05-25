<%-- 
    Document   : text
    Created on : 7-dec-2015, 22:18:10
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:bind path="data">
    <form:hidden path="tempData[${DataIndex}].categoryId"/>
    <form:hidden path="tempData[${DataIndex}].dataId"/>
    <div class="colorpicker colorpicker-${categoryIndex}-${tabIndex}">
        <form:hidden path="tempData[${DataIndex}].value" class="colorpicker-data-${categoryIndex}-${tabIndex}" id="data-sel-${categoryIndex}-${tabIndex}-" value="rgb(255,0,0)" />
        <div class="overlay">

        </div>
    </div>

    <script>
        $(function () {
            var dialogHidden_${categoryIndex}_${tabIndex} = $('.colorpicker-data-${categoryIndex}-${tabIndex}').colorpicker({
                parts: 'full',
                swatches: 'pantone',
                swatchesWidth: 250,
                buttonImageOnly: true,
                colorFormat: 'RGB',
                swatchesTitle: 'Pantone',
                init: function (event, color) {
                    $('.colorpicker-${categoryIndex}-${tabIndex}').css("background", color.formatted);
                },
                select: function (event, color) {
                    $('.colorpicker-${categoryIndex}-${tabIndex}').css("background", color.formatted);
                }
            });
            $('.colorpicker-${categoryIndex}-${tabIndex}').click(function (e) {
                e.stopPropagation();
                dialogHidden_${categoryIndex}_${tabIndex}.colorpicker('open');
            });
        });
    </script>
    <form:errors path="data" class="error" /> 
</spring:bind> 