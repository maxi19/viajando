export class Reservable {
	constructor(data) {
		this.data = data;
	}

	renderizar() {
		console.log(this.data);

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

		// Input para cantidad
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

		// Botón quitar
		html += `
			<div class="d-flex justify-content-between mt-3">
				<button class="btn btn-danger btn-sm quitar-del-carrito"
					data-id="${this.data.id}" data-tipo="${tipo}">
					Quitar del carrito
				</button>
			</div>
		`;

		html += `
				</div>
			</div>
		</div>
		`;

		return html;
	}
}
