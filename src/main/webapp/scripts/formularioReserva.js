$(document).ready(function () {
	// Carga el formulario dinámico
	$.ajax({
		url: contextPath + "/formularioReservaData",
		method: "GET",
		dataType: "json",
		success: function (data) {
			const contenedor = $("#contenedorFormularios");

			console.log("Data recibida:", data);
			data.forEach((p, i) => {
				console.log("Item recibido:", p);

				let selectButacaHtml = "";

				// Normalizamos tipo (por si viene "vuelo", "Vuelo", "VUELO", etc.)
				const tipoNormalizado = p.tipo.toLowerCase();

				if (tipoNormalizado === "vuelo") {
					selectButacaHtml = `
						<div class="col-md-12">
							<label>Seleccionar Butaca</label>
							<div id="contenedorButacasPersona${i}" class="d-flex flex-wrap mb-2"></div>
							<input type="hidden" name="butaca[]" id="butacaSeleccionada${i}" required>
						</div>
					`;
				}

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
							${selectButacaHtml}
						</div>
						<input type="hidden" name="tipo_servicio[]" value="${p.tipo}">
						<input type="hidden" name="servicio_id[]" value="${p.servicio_id}">
					</div>
				`;
				contenedor.append(formHtml);

				// Si es vuelo, cargar butacas
				if (tipoNormalizado === "vuelo") {
					cargarButacas(p.servicio_id, i);
				}
			});
		},
		error: function () {
			alert("Error al cargar el formulario de pasajeros");
		}
	});

	// Enviar reservas
	$("#formReserva").on("submit", function (e) {
		e.preventDefault();
		const total = $("input[name='nombre[]']").length;
		const reservas = [];

		for (let i = 0; i < total; i++) {
			reservas.push({
				nombre: $("input[name='nombre[]']").eq(i).val(),
				apellido: $("input[name='apellido[]']").eq(i).val(),
				dni: $("input[name='dni[]']").eq(i).val(),
				sexo: $("select[name='sexo[]']").eq(i).val(),
				tipoServicio: $("input[name='tipo_servicio[]']").eq(i).val(),
				servicio_id: parseInt($("input[name='servicio_id[]']").eq(i).val()),
				butaca: $("input[name='butaca[]']").eq(i).val()
			});
		}

		$.ajax({
			url: contextPath + "/confirmarReserva",
			method: "POST",
			contentType: "application/json",
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

// =============================
// ✅ Cargar butacas
// =============================
function cargarButacas(vueloId, indexPersona) {
	$.ajax({
		url: contextPath + "/butacasPorVuelo?vuelo_id=" + vueloId,
		method: "GET",
		dataType: "json",
		success: function (butacas) {
			const contenedor = $(`#contenedorButacasPersona${indexPersona}`);
			contenedor.empty();

			butacas.forEach(b => {
				// Verificar si la butaca ya fue seleccionada por otra persona
				let butacaYaSeleccionada = false;
				$("input[name='butaca[]']").each(function (i) {
					if (i !== indexPersona && $(this).val() == b.asiento) {
						butacaYaSeleccionada = true;
					}
				});

				const disponible = b.estado === "disponible" && !butacaYaSeleccionada;
				const color = disponible ? "#87CEFA" : "#cccccc";
				const clase = disponible ? "butaca-disponible" : "butaca-ocupada";
				const disabled = disponible ? "" : "pointer-events: none;";

				contenedor.append(`
					<div class="butaca ${clase}" 
						data-asiento="${b.asiento}"
						style="display:inline-block; width:40px; height:40px; background-color:${color}; text-align:center; line-height:40px; border-radius:4px; margin:5px; cursor:pointer; ${disabled}">
						${b.asiento}
					</div>
				`);
			});

			// Evento de selección
			contenedor.off("click").on("click", ".butaca-disponible", function () {
				const asiento = $(this).data("asiento");

				// Verificar que ninguna otra persona ya tenga esa butaca
				let duplicado = false;
				$("input[name='butaca[]']").each(function (i) {
					if (i !== indexPersona && $(this).val() == asiento) {
						duplicado = true;
					}
				});

				if (duplicado) {
					alert("⚠️ Esa butaca ya fue seleccionada por otra persona.");
					return;
				}

				// Desmarcar otras
				$(`#contenedorButacasPersona${indexPersona} .butaca-disponible`).css("background-color", "#87CEFA");
				$(this).css("background-color", "#4CAF50");
				$(`#butacaSeleccionada${indexPersona}`).val(asiento);
			});
		}
	});
}
