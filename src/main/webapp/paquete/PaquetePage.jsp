<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.*"%>
<%@page import="com.viajando.domain.Paquete"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ver lista paquete</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="<%=request.getContextPath()%>/scripts/jquery-3.6.4.js"></script>

<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>

<script src="<%=request.getContextPath()%>/scripts/eliminarPaquete.js"></script>
<script src="<%=request.getContextPath()%>/scripts/paquete.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

	<span>
	<a class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Nuevo Paquete</a>
</span>

<!-- CONTENEDOR -->
<div class="container mt-4">
	<a class="navbar-brand" href="#">Paquete</a>
	<div class="card-group homeitem mt-4" id="contenedorPaquete"></div>
</div>

</body>


<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/localization/messages_es.min.js"></script>

<script src="<%=request.getContextPath()%>/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/agregarPaquete.js"></script>


<!-- FORMULARIO -->
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="container mt-4">
				<h1>Crear Paquete</h1>
				<form class="form" id="formPaquete" method="post" enctype="multipart/form-data">

					<div class="form-group">
						<label for="nombre">Nombre:</label>
						<input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese el nombre del paquete">
					</div>

					<div class="form-group">
						<label for="descripcion">Descripción:</label>
						<input type="text" class="form-control" id="descripcion" name="descripcion" required placeholder="Ingrese una descripción">
					</div>

					<div class="form-group">
						<label for="hotel_id">ID Hotel:</label>
						<input type="number" class="form-control" id="hotel_id" name="hotel_id" required placeholder="Ingrese ID del hotel">
					</div>

					<div class="form-group">
						<label for="vuelo_id">ID Vuelo:</label>
						<input type="number" class="form-control" id="vuelo_id" name="vuelo_id" required placeholder="Ingrese ID del vuelo">
					</div>

					<div class="form-group">
						<label for="excursion_id">ID Excursión:</label>
						<input type="number" class="form-control" id="excursion_id" name="excursion_id" required placeholder="Ingrese ID de la excursión">
					</div>

					<div class="form-group">
						<label for="estrellas">Estrellas:</label>
						<input type="number" step="0.1" class="form-control" id="estrellas" name="estrellas" required placeholder="Estrellas (0 a 5)">
					</div>

					<div class="form-group">
						<label for="personas">Cantidad de Personas:</label>
						<input type="number" class="form-control" id="personas" name="personas" required placeholder="Ingrese cantidad de personas">
					</div>

					<div class="form-group">
						<label for="precio">Precio:</label>
						<input type="number" class="form-control" id="precio" name="precio" required placeholder="Ingrese el precio">
					</div>

					<div class="form-group">
						<label for="imagen">Imagen:</label>
						<input type="file" class="form-control" id="imagen" name="imagen" required>
					</div>

					<button type="submit" class="btn btn-primary" id="btn-confirmar">Confirmar</button>
				</form>
			</div>
		</div>
	</div>
</div>

					
				<script>
$(document).ready(function () {
	$("#formPaquete").validate({
		rules: {
			nombre: { required: true, minlength: 2 },
			descripcion: { required: true, minlength: 10 },
			hotel_id: { required: true, number: true },
			vuelo_id: { required: true, number: true },
			excursion_id: { required: true, number: true },
			estrellas: { required: true, number: true, min: 0, max: 5 },
			personas: { required: true, number: true, min: 1 },
			precio: { required: true, number: true, min: 0 },
			imagen: { required: true }
		},
		messages: {
			nombre: { required: "Ingrese un nombre", minlength: "Al menos 2 caracteres" },
			descripcion: { required: "Ingrese una descripción", minlength: "Al menos 10 caracteres" },
			hotel_id: "Ingrese un ID válido de hotel",
			vuelo_id: "Ingrese un ID válido de vuelo",
			excursion_id: "Ingrese un ID válido de excursión",
			estrellas: {
				required: "Ingrese estrellas",
				number: "Debe ser un número",
				min: "Mínimo 0", max: "Máximo 5"
			},
			personas: {
				required: "Ingrese cantidad de personas",
				number: "Debe ser un número",
				min: "Mínimo 1 persona"
			},
			precio: {
				required: "Ingrese el precio",
				number: "Debe ser numérico",
				min: "No puede ser negativo"
			},
			imagen: "Seleccione una imagen"
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
		if ($("#formPaquete").valid()) {
			$("#formPaquete").submit();
		}
	});
});
</script>

</html>