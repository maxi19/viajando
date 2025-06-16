<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Formulario para Ingresar una excursion</title>
<link rel="stylesheet" href="style/estiloForm.css">


<script
	src="<%=request.getContextPath()%>/scripts/jquery/jquery-3.6.4.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>

<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>

<script src="<%=request.getContextPath()%>/scripts/agregarExcursion.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- Sweet Alert -->



</head>
<body>

	<form class="form" id="formExcursion">
		<div class="form-group">
			<label for="nombre">Nombre:</label> <input type="text"
				class="form-control" id="nombre" required name="nombre"
				aria-describedby="emailHelp"
				placeholder="Ingrese el nombre de la excursion">
		</div>

		<div class="form-group">
			<label for="descripcion">Descripción:</label> <input type="text"
				class="form-control" id="descripcion" required name="descripcion"
				aria-describedby="emailHelp" placeholder="Ingrese una descripcion">
		</div>

		<div class="form-group">
			<label for="fecha_inicio">Fecha de Inicio:</label> <input type="date"
				class="form-control" id="fecha_inicio" name="fecha_inicio" required>
		</div>

		<div class="form-group">
			<label for="fecha_fin">Fecha de Fin:</label> <input type="date"
				class="form-control" id="fecha_fin" name="fecha_fin" required>
		</div>
		<div class="form-group">
			<label for="precio">Precio</label> <input type="text"
				class="form-control" id="precio" required name="precio" placeholder="Ingrese el precio">
		</div>

		<div class="form-group">
			<label for="destino">Destino:</label> <input type="text"
				class="form-control" id="destino" required name="destino" placeholder="Ingrese el destino">
		</div>
		
		<div class="form-group">
			<label for="estrellas">Estrellas:</label> <input type="number" step="0.1"
				class="form-control" id="estrellas" required name="estrellas" placeholder="Ingrese las estrellas">
		</div>

		<button type="button" class="btn btn-primary" id="btn-confirmar">Submit</button>
	</form>

</body>
</html>