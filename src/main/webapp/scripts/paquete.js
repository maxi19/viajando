var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

class Paquete {
	constructor(id, nombre, descripcion, hotel_id, hotel_value, vuelo_id, vuelo_value, excursion_id, excursion_value, estrellas, personas, precio, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hotel_id = hotel_id;
		this.hotel_value = hotel_value;
		this.vuelo_id = vuelo_id;
		this.vuelo_value = vuelo_value;
		this.excursion_id = excursion_id;
		this.excursion_value = excursion_value;
		this.estrellas = estrellas;
		this.personas = personas;
		this.precio = precio;
		this.imagen = imagen;
	}

	generarEstrellas() {
		const rating = parseFloat(this.estrellas);
		let html = '';
		const fullStars = Math.floor(rating);
		const halfStar = rating - fullStars >= 0.5;
		const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

		for (let i = 0; i < fullStars; i++) {
			html += '<i class="fas fa-star" style="color: gold;"></i>';
		}
		if (halfStar) {
			html += '<i class="fas fa-star-half-alt" style="color: gold;"></i>';
		}
		for (let i = 0; i < emptyStars; i++) {
			html += '<i class="far fa-star" style="color: gold;"></i>';
		}

		return html;
	}

	renderizar() {
		const estrellas = this.generarEstrellas();

		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Paquete" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Paquete N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Hotel:</strong> ${this.hotel_value}</p>
						<p class="card-text"><strong>Vuelo:</strong> ${this.vuelo_value}</p>
						<p class="card-text"><strong>Excursión:</strong> ${this.excursion_value}</p>
						<p class="card-text"><strong>Precio:</strong> $${this.precio}</p>
						<p class="card-text estrellas">${estrellas}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn-paquete" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito-paquete" data-id="${this.id}" data-type="PAQUETE">Carrito</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		`;
	}
}

// Cargar paquetes
function cargarListadoPaquete() {
	$.ajax({
		url: contextPath + "/paqueteController",
		method: "GET",
		cache: false,
		success: function (response) {
			if ($('#contenedorPaquete').length) $('#contenedorPaquete').empty();

			response.forEach(p => {
				const paquete = new Paquete(
					p.id,
					p.nombre,
					p.descripcion,
					(p.hotel ? p.hotel.id : "-"),
					(p.hotel ? p.hotel.nombre : "No incluye hotel"),
					(p.vuelo ? p.vuelo.id : "-"),
					(p.vuelo ? p.vuelo.nombre : "No incluye vuelo"),
					(p.excursion ? p.excursion.id : "-"),
					(p.excursion ? p.excursion.nombre : "No incluye excursión"),
					p.estrellas,
					p.personas,
					p.precio,
					p.imagen
				);

				$('#contenedorPaquete').append(paquete.renderizar());
			});

			$('.ver-mas-btn-paquete').off().on('click', function () {
				const id = $(this).data("id");
				const paquete = response.find(p => p.id === id);
				if (!paquete) return;

				const estrellas = new Paquete().generarEstrellas.call({ estrellas: paquete.estrellas });

				const html = `
					<div class="row">
						<div class="col-md-6">
							<img src="${contextPath}/images/${paquete.imagen}" class="img-fluid" alt="Imagen Paquete">
						</div>
						<div class="col-md-6">
							<h5>${paquete.nombre}</h5>
							<p><strong>Descripción:</strong> ${paquete.descripcion}</p>
							<p><strong>Hotel:</strong> ${paquete.hotel_value}</p>
							<p><strong>Vuelo:</strong> ${paquete.vuelo_value}</p>
							<p><strong>Excursión:</strong> ${paquete.excursion_value}</p>
							<p><strong>Estrellas:</strong> ${estrellas}</p>
							<p><strong>Personas:</strong> ${paquete.personas}</p>
							<p><strong>Precio:</strong> $${paquete.precio}</p>
						</div>
					</div>
				`;

				$('#modalPaqueteContent').html(html);
				new bootstrap.Modal(document.getElementById('modalPaquete')).show();
			});
		},
		error: function (xhr) {
			console.error("Error al obtener los paquetes:", xhr);
			$('#contenedorPaquete').html('<p>Error al cargar los paquetes.</p>');
		}
	});
}

// Modal y botón de agregar al carrito
$(document).on("click", ".boton-carrito-paquete", function () {
	const id = $(this).data("id");
	const type = $(this).data("type");

	Swal.fire({
		title: '¿Agregar al carrito?',
		text: "¿Deseás agregar este paquete al carrito?",
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: 'Sí, agregar',
		cancelButtonText: 'Cancelar'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "GET",
				url: contextPath + "/carrito.do",
				data: { id: id, type: type },
				dataType: "json",
				success: function () {
					// actualizarContadorCarrito(); // Descomentar si implementás esta función

					Swal.fire({
						title: 'Paquete agregado',
						icon: 'success',
						showCancelButton: true,
						confirmButtonText: 'Ver carrito',
						cancelButtonText: 'Seguir navegando'
					}).then(choice => {
						if (choice.isConfirmed) {
							window.location.href = contextPath + '/carrito/carritoPage.jsp';
						}
					});
				},
				error: function () {
					Swal.fire('Error', 'No se pudo agregar el paquete al carrito.', 'error');
				}
			});
		}
	});
});

$(document).ready(function () {
	cargarListadoPaquete();
});
