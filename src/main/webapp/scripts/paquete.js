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

	renderizar() {
		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Paquete" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Paquete N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Descripción:</strong> ${this.descripcion}</p>
						<p class="card-text"><strong>Hotel:</strong> ${this.hotel_value}</p>
						<p class="card-text"><strong>Vuelo:</strong> ${this.vuelo_value}</p>
						<p class="card-text"><strong>Excursión:</strong> ${this.excursion_value}</p>
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
				<td>${this.hotel_value}</td>
				<td>${this.vuelo_id}</td>
				<td>${this.vuelo_value}</td>
				<td>${this.excursion_id}</td>
				<td>${this.excursion_value}</td>
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
		success: function(response) {
			console.log("Entró al success");
			console.log("Paquetes cargados:", response);

			if ($('#contenedorPaquete').length) {
				$('#contenedorPaquete').empty();
			}
			if ($('#tablaPaquete').length) {
				$('#tablaPaquete').empty();
			}

			response.forEach(m => {
				const hotelNombre = (m.hotel && m.hotel.nombre) ? m.hotel.nombre : "No incluye hotel";
				const vueloNombre = (m.vuelo && m.vuelo.nombre) ? m.vuelo.nombre : "No incluye vuelo";
				const excursionNombre = (m.excursion && m.excursion.nombre) ? m.excursion.nombre : "No incluye excursión";

				const paquete = new Paquete(
					m.id,
					m.nombre,
					m.descripcion,
					m.hotel ? m.hotel.id : "-",
					m.hotel ? m.hotel.nombre : "No incluye hotel",
					m.vuelo ? m.vuelo.id : "-",
					m.vuelo ? m.vuelo.nombre : "No incluye vuelo",
					m.excursion ? m.excursion.id : "-",
					m.excursion ? m.excursion.nombre : "No incluye excursión",
					m.estrellas,
					m.personas,
					m.precio,
					m.imagen
				);

				console.log("HOTEL", m.hotel);
				console.log("VUELO", m.vuelo);
				console.log("EXCURSION", m.excursion);

				if ($('#contenedorPaquete').length) {
					$('#contenedorPaquete').append(paquete.renderizar());
				}

				if ($('#tablaPaquete').length) {
					$('#tablaPaquete').append(paquete.renderizarTabla());
				}
			});

			// Carrito
			$('.boton-carrito').click(function() {
				const id = $(this).data("id");
				const type = $(this).data("type");

				$.ajax({
					type: "GET",
					url: contextPath + '/carrito.do',
					data: { id: id, type: type },
					dataType: "json",
					success: function(response) {
						console.log("Paquete agregado al carrito:", response);
					},
					error: function(xhr) {
						console.error("Error al agregar el paquete al carrito:", xhr);
					}
				});
			});
		},
		error: function(xhr) {
			console.error("Error al obtener los paquetes:", xhr);
			alert("Error al cargar los paquetes");

			if ($('#contenedorPaquete').length) {
				$('#contenedorPaquete').html('<p>Error al cargar los paquetes.</p>');
			}
		}
	});
}

$(document).ready(function() {
	cargarListadoPaquete();
});
