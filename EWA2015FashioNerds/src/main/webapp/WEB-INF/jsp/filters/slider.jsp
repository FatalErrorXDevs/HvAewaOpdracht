<%-- 
    Document   : slider
    Created on : 3-nov-2015, 10:34:45
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
    <form:hidden path="filterGroups[${gi.index}].filters[${fi.index}].minValue" cssClass="slider-${singleFilter.id}-Value" data-index="0" />
    <form:hidden path="filterGroups[${gi.index}].filters[${fi.index}].maxValue" cssClass="slider-${singleFilter.id}-Value" data-index="1" />
<!--    <input type="hidden" class="slider-${singleFilter.id}-Value" data-index="0" value="${singleFilter.minValue}" />
    <input type="hidden" class="slider-${singleFilter.id}-Value" data-index="1" value="${singleFilter.maxValue}" />-->
</div>
<br /> 
<div id="slider-${singleFilter.id}" class="slider"> 
</div>

<script>
    $(document).ready(function () {
        $("#slider-${singleFilter.id}").slider({
            min: ${singleFilter.minSelectableValue},
            max: ${singleFilter.maxSelectableValue},
            step: ${singleFilter.stepSize},
            range: true,
            values: [${singleFilter.minValue}, ${singleFilter.maxValue}],
            stop    : function(e, ui) {
                formSubmit();
            },  
            slide: function (event, ui) {

                minValue = ui.values[0];
                maxValue = ui.values[ui.values.length - 1];

                for (var i = 0; i < ui.values.length; ++i) {
                    var valueField = $("input.slider-${singleFilter.id}-Value[data-index=" + i + "]");
                    $(valueField).val(ui.values[i]);  
                    if (minValue > ui.values[i])
                    {
                        $("#slider-${singleFilter.id}").slider("values", i, minValue + 1);
                    }
                    if (maxValue < ui.values[i] && i !== (ui.values.length - 1))
                    {
                        $("#slider-${singleFilter.id}").slider("values", i, maxValue - 1);
                    }
                }
            }
        }).slider("pips").slider("float");

        $("input.slider-${singleFilter.id}-Value").change(function () {
            var $this = $(this);
            $("#slider-${singleFilter.id}").slider("values", $this.data("index"), $this.val());
        });
    });

</script>