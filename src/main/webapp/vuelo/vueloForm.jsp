<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario Vuelo</title>

<script src="<%=request.getContextPath()%>/scripts/jquery/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>

<script type="text/javascript">
			var contextPath='<%=request.getContextPath()%>
	';
</script>

<script src="<%=request.getContextPath()%>/scripts/agregarVuelo.js"></script>

</head>
<body>

	<form class="form">
		<div>
			<label>Destino:</label> <input type="text" id="destino"
				name="destino" placeholder="Ingresa destino">
		</div>

		<div>
			<label>Ida:</label> <input type="date" id="ida" name="ida"
				placeholder="Ingresa fecha ida">
		</div>

		<div>
			<label>Vuelta:</label> <input type="date" id="vuelta" name="vuelta"
				placeholder="Ingresa fecha vuelta">
		</div>

		<div>
			<label>Hora de ida:</label> <input type="time" id="hora_ida"
				name="hora_ida" placeholder="Ingresa la hora de ida">
		</div>

		<div>
			<label>Hora de vuelta:</label> <input type="time" id="hora_vuelta"
				name="hora_vuelta" placeholder="Ingresa la hora de vuelta">
		</div>

		<div>
			<label>Estrellas:</label> <input type="number" id="estrellas"
				name="estrellas" placeholder="Ingresa las estrellas">
		</div>

		<div>
			<label>Precio:</label> <input type="number" id="precio" name="precio"
				placeholder="Ingresa el precio">
		</div>

	</form>
</body>
</html>