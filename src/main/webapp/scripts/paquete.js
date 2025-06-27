var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

class Paquete {
	constructor(id, nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hotel_id = hotel_id;
		this.vuelo_id = vuelo_id;
		this.excursion_id = excursion_id;
		this.estrellas = estrellas;
		this.personas = personas;
		this.precio = precio;
		this.imagen = imagen;
	}

	renderizar() {
		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Paquete" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Paquete N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Descripción:</strong> ${this.descripcion}</p>
						<p class="card-text"><strong>Hotel ID:</strong> ${this.hotel_id}</p>
						<p class="card-text"><strong>Vuelo ID:</strong> ${this.vuelo_id}</p>
						<p class="card-text"><strong>Excursión ID:</strong> ${this.excursion_id}</p>
						<p class="card-text"><strong>Estrellas:</strong> ${this.estrellas}</p>
						<p class="card-text"><strong>Personas:</strong> ${this.personas}</p>
						<p class="card-text"><strong>Precio:</strong> $${this.precio}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito" data-id="${this.id}" data-type="PAQUETE">Carrito</button>
							</div>
							<small class="text-body-secondary">9 mins</small>
						</div>
					</div>
				</div>
			</div>
		`;
	}

	renderizarTabla() {
		return `
			<tr>
				<td>${this.id}</td>
				<td>${this.nombre}</td>
				<td>${this.descripcion}</td>
				<td>${this.hotel_id}</td>
				<td>${this.vuelo_id}</td>
				<td>${this.excursion_id}</td>
				<td>${this.estrellas}</td>
				<td>${this.personas}</td>
				<td>$${this.precio}</td>
				<td>
					<button class="btn btn-danger eliminar-paquete" data-id="${this.id}">Eliminar</button>
				</td>
			</tr>
		`;
	}
}

function cargarListadoPaquete() {
	$.ajax({
		url: contextPath + "/paqueteController",
		method: "GET",
		cache: false,
		success: function (response) {
			console.log(response);

			if ($('#contenedorPaquete').length) {
				$('#contenedorPaquete').empty();
			}
			if ($('#tablaPaquete').length) {
				$('#tablaPaquete').empty();
			}

			response.forEach(m => {
				const paquete = new Paquete(
					m.id, m.nombre, m.descripcion,
					m.hotel_id, m.vuelo_id, m.excursion_id,
					m.estrellas, m.personas, m.precio,
					m.imagen
				);

				if ($('#contenedorPaquete').len
