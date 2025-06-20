<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.viajando.domain.Excursion"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Lista de Excursiones</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="<%=request.getContextPath()%>/scripts/jquery-3.6.4.js"></script>

<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>

<script src="<%=request.getContextPath()%>/scripts/eliminarExcursion.js"></script>
<script src="<%=request.getContextPath()%>/scripts/excursion.js"></script>

</head>
<body>

	<div class="container mt-4">
		<a class="navbar-brand" href="#">Excursion</a>
		<div class="card-group homeitem mt-4" id="contenedorExcursion"></div>
	</div>

</body>
</html>
