<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Eliminar Vuelo</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container py-4">
	<h2>Eliminar Vuelo</h2>
	<form method="get" action="eliminarVuelo" class="w-50">
		<div class="mb-3">
			<label for="id" class="form-label">ID del Vuelo</label>
			<input type="number" class="form-control" id="id" name="id" required>
		</div>
		<button type="submit" class="btn btn-danger">Eliminar</button>
	</form>
</body>
</html>