<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.viajando.domain.Vuelo" %>
<%
    List<Vuelo> vuelos = (List<Vuelo>) request.getAttribute("vuelos");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lista de Vuelos</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="<%= request.getContextPath() %>/scripts/EliminarVuelo.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="container py-4">
	
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h2>Vuelos Disponibles</h2>
		<a href="<%= request.getContextPath() %>/Vuelo/VueloForm.jsp" class="btn btn-success">Agregar Vuelo</a>
	</div>

	<table class="table table-striped table-hover">
		<thead class="table-dark">
			<tr>
				<th>ID</th>
				<th>Destino</th>
				<th>Ida</th>
				<th>Vuelta</th>
				<th>Hora Ida</th>
				<th>Hora Vuelta</th>
				<th>Precio</th>
				<th>Estrellas</th>
				<th>Acción</th>
			</tr>
		</thead>
		<tbody>
			<% if (vuelos != null && !vuelos.isEmpty()) {
				for (Vuelo vuelo : vuelos) { %>
					<tr>
						<td><%= vuelo.getId() %></td>
						<td><%= vuelo.getDestino() %></td>
						<td><%= vuelo.getIda() %></td>
						<td><%= vuelo.getVuelta() %></td>
						<td><%= vuelo.getHoraIda() %></td>
						<td><%= vuelo.getHoraVuelta() %></td>
						<td>$<%= vuelo.getPrecio() %></td>
						<td><%= vuelo.getEstrellas() %></td>
						<td>
							<button onclick="eliminarVuelo(this)" data-id="<%= vuelo.getId() %>" class="btn btn-sm btn-danger"> Eliminar </button>
						</td>
					</tr>
			<%	}
			} else { %>
				<tr>
					<td colspan="9" class="text-center">No hay vuelos disponibles.</td>
				</tr>
			<% } %>
		</tbody>
	</table>

</body>
</html>