<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>

<script>
	var contextPath = "<%=request.getContextPath()%>";
</script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/formularioReserva.js"></script>

	<meta charset="UTF-8">
	<title>Formulario de Reserva</title>
	<link href="<%=request.getContextPath()%>/assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
	<h2 class="mb-4">Datos de los pasajeros</h2>
	<form action="<%=request.getContextPath()%>/confirmarReserva" method="post" id="formReserva">
		<div id="contenedorFormularios"></div>

		<div class="text-center mt-4">
			<button type="submit" class="btn btn-success btn-lg">Confirmar Reserva</button>
		</div>
	</form>
</div>


</body>
</html>