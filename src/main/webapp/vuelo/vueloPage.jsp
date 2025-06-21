<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.viajando.domain.Vuelo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Vuelos</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="<%=request.getContextPath()%>/scripts/jquery-3.6.4.js"></script>

<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>
	';
</script>

<script src="<%=request.getContextPath()%>/scripts/vuelo.js"></script>
</head>
<body>

	<div class="container mt-4">
		<a class="navbar-brand" href="#">Vuelos</a>
		<div class="card-group homeitem mt-4" id="contenedorVuelo"></div>
	</div>

</body>
</html>