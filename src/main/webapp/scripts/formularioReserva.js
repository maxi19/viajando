$(document).ready(function () {
	// Carga el formulario dinámico como ya tenías
	$.ajax({
		url: contextPath + "/formularioReservaData",
		method: "GET",
		dataType: "json",
		success: function (data) {
			const contenedor = $("#contenedorFormularios");

			data.forEach((p, i) => {
				const formHtml = `
					<div class="card mb-3 p-3 bg-light">
						<h5 class="card-title">Persona ${i + 1} para ${p.nombre_servicio}</h5>
						<div class="row">
							<div class="col-md-3">
								<label>Nombre</label>
								<input type="text" name="nombre[]" class="form-control" required>
							</div>
							<div class="col-md-3">
								<label>Apellido</label>
								<input type="text" name="apellido[]" class="form-control" required>
							</div>
							<div class="col-md-3">
								<label>DNI</label>
								<input type="number" name="dni[]" class="form-control" required>
							</div>
							<div class="col-md-3">
								<label>Sexo</label>
								<select name="sexo[]" class="form-control" required>
									<option value="">Seleccionar</option>
									<option value="Masculino">Masculino</option>
									<option value="Femenino">Femenino</option>
									<option value="Otro">Otro</option>
								</select>
							</div>
						</div>
						<input type="hidden" name="tipo_servicio[]" value="${p.tipo}">
						<input type="hidden" name="servicio_id[]" value="${p.servicio_id}">
					</div>
				`;
				contenedor.append(formHtml);
			});
		},
		error: function () {
			alert("Error al cargar el formulario de pasajeros");
		}
	});

	// Intercepta el submit del formulario y envía con AJAX en JSON
	$("#formReserva").on("submit", function (e) {
		e.preventDefault(); // Evita el envío normal del form

		const total = $("input[name='nombre[]']").length;
		const reservas = [];

		for (let i = 0; i < total; i++) {
			reservas.push({
				nombre: $("input[name='nombre[]']").eq(i).val(),
				apellido: $("input[name='apellido[]']").eq(i).val(),
				dni: $("input[name='dni[]']").eq(i).val(),
				sexo: $("select[name='sexo[]']").eq(i).val(),
				tipoServicio: $("input[name='tipo_servicio[]']").eq(i).val(),      // ✅ corregido
				servicio_id: parseInt($("input[name='servicio_id[]']").eq(i).val()) // ✅ aseguramos que sea número
			});
		}

		$.ajax({
			url: contextPath + "/confirmarReserva",
			method: "POST",
			contentType: "application/json", // Importante
			data: JSON.stringify(reservas),
			success: function () {
				window.location.href = contextPath + "/reserva/confirmacion.jsp?ok";
			},
			error: function () {
				alert("Error al confirmar la reserva");
			}
		});
	});
});