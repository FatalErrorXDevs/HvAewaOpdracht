<%-- 
    Document   : input
    Created on : 9-dec-2015, 12:44:51
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br/>
<a onclick="openForm(${category.getId()}, ${tab.getId()});" href="#">Voeg een nieuwe waarde toe.</a>
 