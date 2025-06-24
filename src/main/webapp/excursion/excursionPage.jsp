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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

	<span> <a class="btn btn-primary" data-toggle="modal"
		data-target=".bd-example-modal-lg"> Nueva Excursion </a>
	</span>

	<div class="container mt-4">
		<a class="navbar-brand" href="#">Excursion</a>
		<div class="card-group homeitem mt-4" id="contenedorExcursion"></div>
	</div>

</body>


<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/localization/messages_es.min.js"></script>

<script src="<%=request.getContextPath()%>/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/agregarExcursion.js"></script>


<!-- FORMULARIO -->
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="container mt-4">

				<h1>Excursion</h1>
				<form class="form" id="formExcursion" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre" required
							placeholder="Ingrese el nombre de la excursion">
					</div>

					<div class="form-group">
						<label for="descripcion">Descripción:</label> <input type="text"
							class="form-control" id="descripcion" name="descripcion" required
							placeholder="Ingrese una descripcion">
					</div>

					<div class="form-group">
						<label for="fecha_inicio">Fecha de Inicio:</label> <input
							type="date" class="form-control" id="fecha_inicio"
							name="fecha_inicio" required>
					</div>

					<div class="form-group">
						<label for="fecha_fin">Fecha de Fin:</label> <input type="date"
							class="form-control" id="fecha_fin" name="fecha_fin" required>
					</div>

					<div class="form-group">
						<label for="precio">Precio:</label> <input type="text"
							class="form-control" id="precio" name="precio" required
							placeholder="Ingrese el precio">
					</div>

					<div class="form-group">
						<label for="destino">Destino:</label> <input type="text"
							class="form-control" id="destino" name="destino" required
							placeholder="Ingrese el destino">
					</div>

					<div class="form-group">
						<label for="estrellas">Estrellas:</label> <input type="number"
							step="0.1" class="form-control" id="estrellas" name="estrellas"
							required placeholder="Ingrese las estrellas">
					</div>

					<div class="form-group">
						<label for="imagen">Imagen:</label> <input type="file"
							class="form-control" id="imagen" name="imagen" required
							placeholder="Ingrese la imagen">
					</div>

					<button type="submit" class="btn btn-primary" id="btn-confirmar">Submit</button>
				</form>

				<!-- Validación con jQuery Validate -->
				<script>
				$(document).ready(function () {
					$("#formExcursion").validate({
						rules: {
							nombre: {
								required: true,
								minlength: 2
							},
							descripcion: {
								required: true,
								minlength: 10
							},
							fecha_inicio: {
								required: true,
								dateISO: true
							},
							fecha_fin: {
								required: true,
								dateISO: true
							},
							precio: {
								required: true,
								number: true,
							    min: 0
							},
							destino: {
								required: true,
								minlength: 2
							},
							estrellas: {
								required: true,
								number: true,
							    min: 0,
							    max: 5
							}
						},
						messages: {
							nombre: {
								required: "Por favor, ingrese un nombre",
								minlength: "El nombre debe tener al menos 2 caracteres"
							},
							descripcion: {
								required: "Por favor, ingrese una descripción",
								minlength: "La descripción debe tener al menos 10 caracteres"
							},
							fecha_inicio: {
								required: "Por favor, ingrese una fecha de inicio"
							},
							fecha_fin: {
								required: "Por favor, ingrese una fecha de finalización"
							},
							precio: {
								required: "Por favor, ingrese un precio",
								number: "Por favor, ingrese un número válido",
								min: "El precio no puede ser negativo"
							},
							destino: {
								required: "Por favor, ingrese un destino",
								minlength: "El destino debe tener al menos 2 caracteres"
							},
							estrellas: {
								required: "Por favor, ingrese una calificación de estrellas",
								number: "Por favor, ingrese un número válido",
								min: "Debe ser al menos 0",
								max: "No puede ser mayor a 5"
							}
						},	
						
						errorElement: "div",
						errorClass: "invalid-feedback",
						highlight: function (element) {
							$(element).addClass("is-invalid");
						},
						unhighlight: function (element) {
							$(element).removeClass("is-invalid");
						}
					});

					$("#btn-confirmar").click(function () {
						if ($("#formExcursion").valid()) {
						    $("#formExcursion").submit();
						}
					});
				});
			</script>
</html>