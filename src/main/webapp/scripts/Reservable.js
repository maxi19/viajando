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

        // Solo si es excursión, mostrar cantidad de personas
		if (this.data.tipo === "Excursion") {
		    html += `
		        <div class="form-group">
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
