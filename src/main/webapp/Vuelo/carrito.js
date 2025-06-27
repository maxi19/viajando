class Reservable {
	constructor(data) {
		this.data = data;
	}

	renderizar() {
		let html = `
			<div class="col-md-4">
				<div class="card mb-4 shadow-sm">
		`;

		if (this.data.imagen) {
			html += `
				<img src="${contextPath}/images/${this.data.imagen}" class="card-img-top" alt="Imagen" style="height: 200px; object-fit: cover;">
			`;
		}

		html += `<div class="card-body">`;

		for (const key in this.data) {
			if (key === "imagen") continue;
			const label = key.replace(/_/g, " ").toUpperCase();
			html += `<p class="card-text"><strong>${label}:</strong> ${this.data[key]}</p>`;
		}

		// Si es una Excursion, agregar input de cantidad
		if (this.data.tipo === "Excursion") {
			html += `
				<div class="form-group mt-3">
					<label for="cantidad_${this.data.id}">Cantidad de personas:</label>
					<input type="number" class="form-control cantidad-personas" 
						data-id="${this.data.id}" data-tipo="excursion" 
						min="1" value="1">
				</div>
			`;
		}

		html += `
				</div>
			</div>
		</div>
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
			contenedor.empty();
			data.forEach(obj => {
				const item = new Reservable(obj);
				contenedor.append(item.renderizar());
			});

			if (data.length > 0) {
				contenedor.append(`
					<div class="text-center mt-4">
						<button onclick="enviarCantidadesYRedirigir()" class="btn btn-primary btn-lg">
							Reservar
						</button>
					</div>
				`);
			}
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

$(document).ready(function () {
	cargarCarrito();
});