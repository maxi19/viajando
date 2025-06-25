	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	      
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Formulario Hotel</title>
	<!-- Estilos -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/estiloForm.css">
			
		<!-- jQuery -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
		
		<!-- Validación -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery/localization/messages_es.min.js"></script>
		
		<!-- Loading Overlay -->
		<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>
		
		<!-- Context Path JS -->
		<script type="text/javascript">
			var contextPath = '<%=request.getContextPath()%>';
		</script>
		
		<!-- Scripts Propios -->
		<script src="<%=request.getContextPath()%>/scripts/agregarHotel.js"></script>
		
		<!-- SweetAlert -->
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	
	</head>
	<body>
	
	<form class="form" id="formExcursion" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="nombre">Nombre:</label>
			<input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese el nombre del hotel">
		</div>

		<div class="form-group">
			<label for="descripcion">Destino:</label>
			<input type="text" class="form-control" id="destino" name="destino" required placeholder="Ingrese una destino">
		</div>

		<div class="form-group">
			<label for="fecha_inicio">Destino Value:</label>
			<input type="text" class="form-control" id="destino_value" name="destino_value" required placeholder="Ingrese un destino value">
		</div>

		<div class="form-group">
			<label for="fecha_fin">Fecha de Fin:</label>
			<input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese el nombre del hotel">
		</div>



	    <div class="form-group">
			<label for="estrellas">Estrellas:</label>
			<input type="number" step="0.1" class="form-control" id="estrellas" name="estrellas" required placeholder="Ingrese las estrellas">
		</div>
		


		<div class="form-group">
			<label for="precio">Precio:</label>
			<input type="text" class="form-control" id="precio" name="precio" required placeholder="Ingrese el precio">
		</div>

		
	
		<div class="form-group">
			<label for="imagen">Imagen:</label>
			<input type="file" class="form-control" id="imagen" name="imagen" required placeholder="Ingrese la imagen">
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
	
	
	




	</body>
	</html>