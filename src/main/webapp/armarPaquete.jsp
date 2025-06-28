<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Armar Paquete</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container py-4">

	<%
	// Recibo el parámetro opciones desde la URL
	String opciones = request.getParameter("opciones");
	String[] opcionesSeleccionadas = opciones != null ? opciones.split(",") : new String[0];
	%>

	<h1>Armar tu paquete</h1>

	<form action="confirmar_paquete.jsp" method="post">

		<%
		for (String opcion : opcionesSeleccionadas) {
		%>

		<%
		if ("hotel".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-hotel" style="display: none;">
			<label for="cmbHotel" class="form-label">Elegí tu hotel</label> <select
				class="form-control" id="cmbHotel" name="hotel_id" required>
				<option selected disabled value="">Seleccione un hotel...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		if ("vuelo".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-vuelo" style="display: none;">
			<label for="cmbVuelo" class="form-label">Elegí tu vuelo</label> <select
				class="form-control" id="cmbVuelo" name="vuelo_id" required>
				<option selected disabled value="">Seleccione un vuelo...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		if ("excursion".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-excursion" style="display: none;">
			<label for="cmbExcursion" class="form-label">Elegí tu
				excursión</label> <select class="form-control" id="cmbExcursion"
				name="excursion_id" required>
				<option selected disabled value="">Seleccione una
					excursión...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		}
		%>

		<button type="submit" class="btn btn-primary">Confirmar
			paquete</button>

	</form>

	<script>
    // Obtener parámetros de URL para saber qué mostrar
    function getQueryParams() {
        const params = {};
        location.search.substr(1).split("&").forEach(function(item) {
            let [key, value] = item.split("=");
            if (key && value) {
                params[key] = decodeURIComponent(value);
            }
        });
        return params;
    }

    document.addEventListener('DOMContentLoaded', () => {
        const params = getQueryParams();
        // Espero que venga un parámetro opciones=hotel,vuelo por ejemplo
        const opciones = params.opciones ? params.opciones.split(',') : [];

        // Mostrar sólo los selects que el usuario eligió
        if (opciones.includes('hotel')) {
            document.getElementById('input-hotel').style.display = 'block';
            cargarHoteles();
        }
        if (opciones.includes('vuelo')) {
            document.getElementById('input-vuelo').style.display = 'block';
            cargarVuelos();
        }
        if (opciones.includes('excursion')) {
            document.getElementById('input-excursion').style.display = 'block';
            cargarExcursiones();
        }
    });

    function cargarHoteles() {
        fetch('/listarHoteles')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbHotel');
                data.forEach(hotel => {
                    let option = document.createElement('option');
                    option.value = hotel.id;
                    option.text = hotel.nombre;
                    select.add(option);
                });
            });
    }

    function cargarVuelos() {
        fetch('/listarVuelos')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbVuelo');
                data.forEach(vuelo => {
                    let option = document.createElement('option');
                    option.value = vuelo.id;
                    option.text = vuelo.nombre;
                    select.add(option);
                });
            });
    }

    function cargarExcursiones() {
        fetch('/listarExcursiones')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbExcursion');
                data.forEach(excursion => {
                    let option = document.createElement('option');
                    option.value = excursion.id;
                    option.text = excursion.nombre;
                    select.add(option);
                });
            });
    }
</script>

</body>
</html>
