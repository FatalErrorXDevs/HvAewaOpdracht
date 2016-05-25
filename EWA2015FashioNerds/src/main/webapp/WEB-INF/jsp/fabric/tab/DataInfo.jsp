<%-- 
    Document   : DataInfo
    Created on : 17-dec-2015, 7:26:43
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
    <c:when test="${Category.getDataType().isMultipleItems()}">
        <c:if test="${Data.isEmpty()}">
            <span>-</span>
        </c:if>
        <ul>
            <c:forEach varStatus="categoryStatus" items="${Data}" var="dataItem"> 
                <c:choose>
                    <c:when test="${(Category.isReviewRequired() && !dataItem.isReviewed())}">
                        <li><span class="not-reviewed" title="Waarde wordt niet weergegeven. (Moet eerst worden goegekeurd door een onderzoeker.">Verborgen [?]</span></li>
                        </c:when>
                        <c:otherwise> 
                        <li><c:out value="${dataItem.getValue()}" /></li>
                        </c:otherwise>
                    </c:choose> 
                </c:forEach>
        </ul>
    </c:when>
    <c:when test="${(Category.getDataType() == 'COLOR')}">
        <c:if test="${(Data != null && Data.size() > 0)}">
            <div style='background: ${Data.get(0).toExport()}; width:24px; height:24px; border: 1px solid #CCCCCC; display:inline-block;'></div> 
            ${Data.get(0).toExport()}
        </c:if>
    </c:when>
    <c:when test="${Color}">
        <c:if test="${(Data != null && Data.size() > 0)}">
            <div style='background: ${Data.get(0).toExport()}; width:18px; height:18px; border: 1px solid #CCCCCC; display:inline-block;'></div>
        </c:if>
    </c:when>
    <c:otherwise> 
        <c:choose>
            <c:when test="${Data == null || Data.size() <= 0 || Data.get(0) == null || Data.get(0).isEmpty()}">
                <span>-</span>
            </c:when>
            <c:otherwise> 
                <c:out value="${Data.get(0).toExport()}" /></c:otherwise>
        </c:choose> 
    </c:otherwise>
</c:choose>