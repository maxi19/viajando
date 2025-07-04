// Obtener el contextPath desde la URL actual
var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

// Clase para representar un Paquete
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
								<button class="btn btn-sm btn-outline-secondary boton-carrito-paquete" data-id="${this.id}" data-type="PAQUETE">Carrito</button>
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
					<button class="btn btn-danger" data-id="${this.id}" onClick="eliminarPaquete(this)">Eliminar</button>
				</td>
			</tr>
		`;
	}

	renderizarLista() {
	    return `
	        <li class="list-group-item d-flex justify-content-between align-items-center">
	            <div>
	                <h5>${this.nombre}</h5>
	                <p class="mb-1">${this.descripcion}</p>
	                <p class="mb-1">
	                    <strong>Hotel:</strong> ${this.hotel_value} |
	                    <strong>Vuelo:</strong> ${this.vuelo_value} |
	                    <strong>Excursión:</strong> ${this.excursion_value}
	                </p>
	                <p class="mb-1"><strong>Precio:</strong> $${this.precio}</p>
	            </div>
	            <div>
	                <button class="btn btn-sm btn-outline-success boton-carrito-paquete" data-id="${this.id}" data-type="PAQUETE">Comprar</button>
	            </div>
	        </li>
	    `;
	}
}

function cargarListadoPaquete() {
	$.ajax({
		url: contextPath + "/paqueteController",
		method: "GET",
		cache: false,
		success: function(response) {
			if ($('#contenedorPaquete').length) $('#contenedorPaquete').empty();
			if ($('#tablaPaquete').length) $('#tablaPaquete').empty();
			if ($('#listaPaquetes').length) $('#listaPaquetes').empty();

			response.forEach(m => {
				const paquete = new Paquete(
					m.id,
					m.nombre,
					m.descripcion,
					m.hotel?.id || "-",
					m.hotel?.nombre || "No incluye hotel",
					m.vuelo?.id || "-",
					m.vuelo?.nombre || "No incluye vuelo",
					m.excursion?.id || "-",
					m.excursion?.nombre || "No incluye excursión",
					m.estrellas,
					m.personas,
					m.precio,
					m.imagen
				);

				if ($('#contenedorPaquete').length)
					$('#contenedorPaquete').append(paquete.renderizar());

				if ($('#tablaPaquete').length)
					$('#tablaPaquete').append(paquete.renderizarTabla());

				if ($('#listaPaquetes').length)
					$('#listaPaquetes').append(paquete.renderizarLista());
			});
		},
		error: function(xhr) {
			console.error("Error al obtener los paquetes:", xhr);
			Swal.fire('Error', 'No se pudieron cargar los paquetes.', 'error');
			if ($('#contenedorPaquete').length)
				$('#contenedorPaquete').html('<p class="text-danger">Error al cargar los paquetes.</p>');
		}
	});
}

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
				success: function (response) {
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

$(document).ready(function() {
	cargarListadoPaquete();
});
