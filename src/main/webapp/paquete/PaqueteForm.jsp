 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Formulario Para Crear Paquetes</title>

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
	<script src="<%=request.getContextPath()%>/scripts/agregarPaquete.js"></script>
	
	<!-- SweetAlert -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<form class="form" id="formExcursion" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="nombre">Nombre:</label>
			<input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese el nombre del paquete">
		</div>

		<div class="form-group">
			<label for="descripcion">Descripción:</label>
			<input type="text" class="form-control" id="descripcion" name="descripcion" required placeholder="Ingrese una descripcion">
		</div>

		<div class="form-group">
			<label for="fecha_inicio">Fecha de Inicio:</label>
			<input type="date" class="form-control" id="fecha_inicio" name="fecha_inicio" required>
		</div>

		<div class="form-group">
			<label for="fecha_fin">Fecha de Fin:</label>
			<input type="date" class="form-control" id="fecha_fin" name="fecha_fin" required>
		</div>

		<div class="form-group">
			<label for="precio">Precio:</label>
			<input type="text" class="form-control" id="precio" name="precio" required placeholder="Ingrese el precio">
		</div>

		<div class="form-group">
			<label for="destino">Destino:</label>
			<input type="text" class="form-control" id="destino" name="destino" required placeholder="Ingrese el destino">
		</div>
		
		
		<div class="form-group">
			<label for="estrellas">Estrellas:</label>
			<input type="number" step="0.1" class="form-control" id="estrellas" name="estrellas" required placeholder="Ingrese las estrellas">
		</div>
		
		
		<div class="form-group">
			<label for="destino">Hotel:</label>
			<input type="text" class="form-control" id="hotel" name="hotel" required placeholder="Ingrese el hotel">
		</div>
		
		<div class="form-group">
			<label for="destino">Vuelo:</label>
			<input type="text" class="form-control" id="vuelo" name="vuelo" required placeholder="Ingrese el vuelo">
		</div>
		
		<div class="form-group">
			<label for="destino">Excursion:</label>
			<input type="text" class="form-control" id="excursion" name="excursion" required placeholder="Ingrese el excursion">
		</div>
		
		

		<button type="submit" class="btn btn-primary" id="btn-confirmar">Submit</button>
	</form>

	<!-- Validación con jQuery Validate -->
	<script>
		$(document).ready(function () {
			$("#formPaquete").validate({
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
					},
					hotel_id: {
						required: "Por favor, ingrese un hotel",
						minlength: "El destino debe tener al menos 2 caracteres"
					},
					vuelo_id: {
						required: "Por favor, ingrese un vuelo",
						minlength: "El destino debe tener al menos 2 caracteres"
					},
					excursion_id: {
						required: "Por favor, ingrese una excursion",
						minlength: "El destino debe tener al menos 2 caracteres"
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
				if ($("#formPaquete").valid()) {
				    $("#formPaquete").submit();
				}
			});
		});
	</script>
</body>
</html>
