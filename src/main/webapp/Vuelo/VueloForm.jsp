<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Agregar Vuelo</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript">
		var contextPath = '<%=request.getContextPath()%>';
	</script>

	<script src="<%= request.getContextPath() %>/scripts/AgregarVuelo.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="bg-light d-flex align-items-center min-vh-100">

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-10 col-lg-8">
				<div class="card shadow-lg">
					<div class="card-header bg-primary text-white text-center">
						<h4 class="mb-0">Agregar Nuevo Vuelo</h4>
					</div>
					<div class="card-body">
						<form method="post" action="<%= request.getContextPath() %>/crearVuelo" id="formVuelo">
							<div class="row g-3">
								<div class="col-md-6">
									<label for="destino" class="form-label">Destino</label>
									<input type="text" class="form-control" id="destino" name="destino" required>
								</div>
								<div class="col-md-3">
									<label for="ida" class="form-label">Fecha Ida</label>
									<input type="date" class="form-control" id="ida" name="ida" required>
								</div>
								<div class="col-md-3">
									<label for="vuelta" class="form-label">Fecha Vuelta</label>
									<input type="date" class="form-control" id="vuelta" name="vuelta" required>
								</div>
								<div class="col-md-3">
									<label for="horaIda" class="form-label">Hora Ida</label>
									<input type="time" class="form-control" id="horaIda" name="horaIda" required>
								</div>
								<div class="col-md-3">
									<label for="horaVuelta" class="form-label">Hora Vuelta</label>
									<input type="time" class="form-control" id="horaVuelta" name="horaVuelta" required>
								</div>
								<div class="col-md-3">
									<label for="precio" class="form-label">Precio</label>
									<input type="number" class="form-control" id="precio" name="precio" required>
								</div>
								<div class="col-md-3">
									<label for="estrellas" class="form-label">Estrellas</label>
									<input type="number" step="0.1" class="form-control" id="estrellas" name="estrellas" required>
								</div>
							</div>
							<hr>
							<div class="text-end">
								<button type="submit" class="btn btn-success">Agregar Vuelo</button>
							</div>
						</form>
					</div>
					<div class="card-footer text-muted text-center">
						Viajando · Administración de vuelos
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>