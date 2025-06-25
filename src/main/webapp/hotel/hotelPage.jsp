<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.viajando.domain.Hotel"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="ISO-8859-1">
<title>Ver Lista de hoteles</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="<%=request.getContextPath()%>/scripts/jquery-3.6.4.js"></script>

<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>

<script src="<%=request.getContextPath()%>/scripts/eliminarHotel.js"></script>
<script src="<%=request.getContextPath()%>/scripts/hotel.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

	<span> <a class="btn btn-primary" data-toggle="modal"
		data-target=".bd-example-modal-lg"> Nueva Excursion </a>
	</span>

	<div class="container mt-4">
		<a class="navbar-brand" href="#">Hotel</a>
		<div class="card-group homeitem mt-4" id="contenedorHotel"></div>
	</div>

</body>


<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/localization/messages_es.min.js"></script>

<script src="<%=request.getContextPath()%>/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/agregarHotel.js"></script>


<!-- FORMULARIO -->
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="container mt-4">

				<h1>Excursion</h1>
				<form class="form" id="formHotel" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre" required
							placeholder="Ingrese el nombre del hotel">
					</div>

					<div class="form-group">
						<label for="destino">Destino:</label> <input type="text"
							class="form-control" id="destino" name="destino" required
							placeholder="Ingrese el destino">
					</div>

					<div class="form-group">
						<label for="destino">Destino Values:</label> <input type="text"
							class="form-control" id="destino" name="destino" required
							placeholder="Ingrese el destino">
					</div>

                    <div class="form-group">
						<label for="estrellas">Estrellas:</label> <input type="number"
							step="0.1" class="form-control" id="estrellas" name="estrellas"
							required placeholder="Ingrese las estrellas">
					</div>
				

					<div class="form-group">
						<label for="precio">Precio:</label> <input type="text"
							class="form-control" id="precio" name="precio" required
							placeholder="Ingrese el precio">
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
			$("#formHotel").validate({
		s		rules: {
					nombre: {
						required: true,
						minlength: 2
					},
					destino_id: {
						required: true,
						minlength: 2
					},
					destino_values: {
						required: true,
						minlength: 2
					},
					estrellas: {
						required: true,
						number: true,
					    min: 0,
					    max: 5
					}
					precio: {
						required: true,
						number: true,
					    min: 0
					},
					
					
				},
				messages: {
					nombre: {
						required: "Por favor, ingrese un nombre",
						minlength: "El nombre debe tener al menos 2 caracteres"
					},
					destino_id: {
						required: "Por favor, ingrese un destino",
						minlength: "El destino debe tener al menos 2 caracteres"
					},
					destino_values: {
						required: "Por favor, ingrese un destino",
						minlength: "El destino debe tener al menos 2 caracteres"
					},
					estrellas: {
						required: "Por favor, ingrese una calificación de estrellas",
						number: "Por favor, ingrese un número válido",
						min: "Debe ser al menos 0",
						max: "No puede ser mayor a 5"
					}
					precio: {
						required: "Por favor, ingrese un precio",
						number: "Por favor, ingrese un número válido",
						min: "El precio no puede ser negativo"
					},
					
					
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
				if ($("#formHotel").valid()) {
				    $("#formHotel").submit();
				}
			});
		});
	</script>
</html>