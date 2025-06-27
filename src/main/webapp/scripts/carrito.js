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
		},
		error: function (err) {
			console.error("Error al cargar el carrito", err);
		}
	});
}

$(document).ready(function () {
	cargarCarrito();
});