class Reservable {
	constructor(data) {
		this.data = data;
	}
	renderizar() {
	    let html = `
	        <div class="col-md-4" id="item-carrito-${this.data.id}-${this.data.tipo}">
	            <div class="card mb-4 shadow-sm">
	    `;

	    if (this.data.imagen) {
	        html += `
	            <img src="${contextPath}/images/${this.data.imagen}" class="card-img-top" alt="Imagen" style="height: 200px; object-fit: cover;">
	        `;
	    }

	    html += `<div class="card-body">`;

	    if (this.data.nombre) {
	        html += `<h5 class="card-title">${this.data.nombre}</h5>`;
	    }

	    html += `<p class="card-text"><strong>Tipo:</strong> ${this.data.tipo}</p>`;
	    if (this.data.destino) html += `<p class="card-text"><strong>Destino:</strong> ${this.data.destino}</p>`;
	    if (this.data.estrellas) html += `<p class="card-text"><strong>Estrellas:</strong> ${this.data.estrellas}</p>`;
	    if (this.data.fecha_inicio) html += `<p class="card-text"><strong>Desde:</strong> ${this.data.fecha_inicio}</p>`;
	    if (this.data.fecha_fin) html += `<p class="card-text"><strong>Hasta:</strong> ${this.data.fecha_fin}</p>`;
	    if (this.data.hora_ida) html += `<p class="card-text"><strong>Hora Ida:</strong> ${this.data.hora_ida}</p>`;
	    if (this.data.hora_vuelta) html += `<p class="card-text"><strong>Hora Vuelta:</strong> ${this.data.hora_vuelta}</p>`;
	    if (this.data.descripcion) html += `<p class="card-text"><strong>Descripción:</strong> ${this.data.descripcion}</p>`;

	    html += `<p class="card-text"><strong>Precio:</strong> $${this.data.precio}</p>`;

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

	    html += `
	        <div class="d-flex justify-content-between mt-3">
	            <button class="btn btn-danger btn-sm quitar-del-carrito"
	                data-id="${this.data.id}" data-tipo="${this.data.tipo}">
	                Quitar del carrito
	            </button>
	        </div>
	        </div></div></div>
	    `;

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

			if (data.length === 0) {
				contenedor.append(`<div class="text-center mt-5"><h5>Tu carrito está vacío.</h5></div>`);
				$("#botonReservarContainer").remove(); // 🔴 Asegura removerlo si ya estaba
				return;
			}

			// Renderizar los items del carrito
			data.forEach(obj => {
				const item = new Reservable(obj);
				contenedor.append(item.renderizar());
			});

			// Agregar botón reservar
			contenedor.append(`
				<div id="botonReservarContainer" class="text-center mt-4">
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
		const cantidad = parseInt($(this).val());
		cantidades.push({ id, cantidad });
	});

	$.ajax({
		url: contextPath + "/actualizarCantidades",
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(cantidades),
		success: function () {
			window.location.href = contextPath + "/reserva/formularioReserva.jsp";
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
				
				if ($("#contenedorCarrito").children(".col-md-4").length === 0) {
					$("#botonReservarContainer").remove();
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
