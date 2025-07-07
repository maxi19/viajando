class Reservable {
	constructor(data) {
		this.data = data;
	}

	renderizar() {
		let html = `
		<div class="col-md-4" id="item-carrito-${this.data.id}-${this.data.tipo}">
			<div class="card shadow-sm">
		`;

		if (this.data.imagen) {
			html += `
				<img src="${contextPath}/images/${this.data.imagen}" class="card-img-top" alt="Imagen ${this.data.tipo}" style="width: 100%; height: 250px; object-fit: cover;">
			`;
		}

		html += `<div class="card-body">`;

		// Título con tipo e ID
		html += `<h5 class="card-title">${this.data.tipo} N° ${this.data.id}</h5>`;

		// Campos comunes
		if (this.data.nombre) html += `<p class="card-text"><strong>Nombre:</strong> ${this.data.nombre}</p>`;
		if (this.data.destino) html += `<p class="card-text"><strong>Destino:</strong> ${this.data.destino}</p>`;
		if (this.data.estrellas) html += `<p class="card-text"><strong>Estrellas:</strong> ${this.data.estrellas}</p>`;
		if (this.data.descripcion) html += `<p class="card-text"><strong>Descripción:</strong> ${this.data.descripcion}</p>`;
		if (this.data.fecha_inicio) html += `<p class="card-text"><strong>Desde:</strong> ${this.data.fecha_inicio}</p>`;
		if (this.data.fecha_fin) html += `<p class="card-text"><strong>Hasta:</strong> ${this.data.fecha_fin}</p>`;
		if (this.data.hora_ida) html += `<p class="card-text"><strong>Hora Ida:</strong> ${this.data.hora_ida}</p>`;
		if (this.data.hora_vuelta) html += `<p class="card-text"><strong>Hora Vuelta:</strong> ${this.data.hora_vuelta}</p>`;

		// Precio
		html += `<p class="card-text"><strong>Precio:</strong> $${this.data.precio}</p>`;

		// Cantidad de personas solo para vuelo o excursión
		const tipo = (this.data.tipo || "").toLowerCase();
		if (tipo === "excursion" || tipo === "vuelo") {
			html += `
				<div class="form-group mt-2">
					<label for="cantidad_${this.data.id}">Cantidad de personas:</label>
					<input type="number" class="form-control cantidad-personas"
						data-id="${this.data.id}" data-tipo="${tipo}"
						min="1" value="${this.data.cantidad || 1}">
				</div>
			`;
		}

		// Botones inferiores
		html += `
			<div class="d-flex justify-content-between align-items-center mt-3">
				<div class="btn-group">
					<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.data.id}">Ver más</button>
					<button class="btn btn-sm btn-outline-danger quitar-del-carrito" data-id="${this.data.id}" data-tipo="${this.data.tipo}">Quitar</button>
				</div>
				<small class="text-body-secondary">${this.data.tipo}</small>
			</div>
		`;

		html += `</div></div></div>`; // card-body, card, col
		return html;
	}
}

function cargarCarrito() {
	$.ajax({
		url: contextPath + "/carritoListado",
		method: "GET",
		dataType: "json",
		success: function (data) {
			const contenedor = $("#contenedorCarrito");
			contenedor.empty(); // Limpiar
			$("#contenedorBotonReservar").empty(); // Limpiar el contenedor del botón

			if (data.length === 0) {
				contenedor.append(`<div class="text-center mt-5"><h5>Tu carrito está vacío.</h5></div>`);
				// Ya no hay botón reservar porque carrito está vacío
				return;
			}

			// Renderizar los items del carrito
			data.forEach(obj => {
				const item = new Reservable(obj);
				contenedor.append(item.renderizar());
			});

			// Agregar botón reservar en contenedor separado
			$("#contenedorBotonReservar").append(`
				<div class="text mt-4">
					<button onclick="enviarCantidadesYRedirigir()" class="btn btn-primary btn-lg">
						Reservar
					</button>
				</div>
			`);
		},
		error: function (err) {
			console.error("Error al cargar el carrito", err);
		}
	});
}

function enviarCantidadesYRedirigir() {
	const cantidades = [];

	$(".cantidad-personas").each(function () {
		const id = $(this).data("id");
		const tipo = $(this).data("tipo");  // Obtener el tipo (vuelo, excursion)
		const cantidad = parseInt($(this).val());
		
		// Crear un identificador único combinando id y tipo
		const uniqueId = `${id}-${tipo}`;

		// Verificar si ya existe el servicio en la lista
		const existing = cantidades.find(item => item.uniqueId === uniqueId);
		if (existing) {
			// Si existe, sumar las cantidades
			existing.cantidad += cantidad;
		} else {
			// Si no existe, agregar nuevo objeto con uniqueId
			cantidades.push({ uniqueId, id, tipo, cantidad });
		}
	});

	console.log("Datos consolidados antes de enviar:", cantidades);  // Debugging

	$.ajax({
		url: contextPath + "/actualizarCantidades",
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(cantidades),
		success: function () {
			// Confirmamos la actualización con un GET
			$.get(contextPath + "/carritoListado", function (carrito) {
				console.log("Carrito actualizado:", carrito);
				// Ahora redirigimos a la página de formularios
				window.location.href = contextPath + "/reserva/formularioReserva.jsp";
			});
		},
		error: function () {
			alert("Ocurrió un error al actualizar las cantidades.");
		}
	});
}

// Eliminar del carrito
$(document).on("click", ".quitar-del-carrito", function () {
	const id = $(this).data("id");
	const tipo = $(this).data("tipo");

	$.ajax({
		url: contextPath + "/carritoListado",
		method: "POST",
		data: { id: id, tipo: tipo, action: "remove" },
		success: function () {
			$(`#item-carrito-${id}-${tipo}`).fadeOut(400, function () {
				$(this).remove();

				// Si no quedan items en el carrito
				if ($("#contenedorCarrito").children(".col-md-4").length === 0) {
					$("#contenedorBotonReservar").empty(); // quitar botón reservar
					$("#contenedorCarrito").append(`
						<div class="text-center mt-5">
							<h5>Tu carrito está vacío.</h5>
						</div>
					`);
				}
			});
		},
		error: function () {
			alert("Error al quitar del carrito.");
		}
	});
});

$(document).ready(function () {
	cargarCarrito();
});