var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

class Hotel {
	constructor(id, nombre, destino_id, destino_value, estrellas, precio, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.destino_id = destino_id;
		this.destino_value = destino_value;
		this.estrellas = estrellas;
		this.precio = precio;
		this.imagen = imagen;
	}

	renderizar() {
		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Hotel" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Hotel N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Destino:</strong> ${this.destino_value}</p>
						<p class="card-text"><strong>Estrellas:</strong> ${this.estrellas}</p>
						<p class="card-text"><strong>Precio:</strong> $${this.precio}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito-hotel" data-id="${this.id}" data-type="HOTEL">Carrito</button>
							</div>
							<small class="text-body-secondary">hotel</small>
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
				<td>${this.destino_id}</td>
				<td>${this.destino_value}</td>
				<td>${this.estrellas}</td>
				<td>$${this.precio}</td>
				<td>
					<button class="btn btn-danger" data-id="${this.id}" onClick="eliminarHotel(this)">Eliminar</button>
				</td>
			</tr>
		`;
	}
}


function cargarListadoHotel() {
	$.ajax({
		url: contextPath + "/hotelController",
		method: "GET",
		cache: false,
		success: function(response) {
			console.log("Hoteles recibidos:", response);
			$('#contenedorHotel').empty();

			response.forEach(m => {
				console.log("Destino recibido:", m.destino);

				const hotel = new Hotel(
					m.id,
					m.nombre,
					m.destino.id,
					`${m.destino.nombre}, ${m.destino.pais}`,
					m.estrellas,
					m.precio,
					m.imagen
				);

				$('#contenedorHotel').append(hotel.renderizar());

				if ($('#tablaHotel').length) {
					$('#tablaHotel').append(hotel.renderizarTabla());
				}
			});

			// Botón carrito con SweetAlert
			$('.boton-carrito-hotel').click(function () {
				const id = $(this).data("id");
				const type = $(this).data("type");

				Swal.fire({
					title: '¿Agregar al carrito?',
					text: "¿Deseás agregar este hotel al carrito?",
					icon: 'question',
					showCancelButton: true,
					confirmButtonText: 'Sí, agregar',
					cancelButtonText: 'Cancelar'
				}).then((result) => {
					if (result.isConfirmed) {
						$.ajax({
							type: "GET",
							url: contextPath + '/carrito.do',
							data: { id: id, type: type },
							dataType: "json",
							success: function(response) {
								Swal.fire({
									title: 'Hotel agregado',
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
							error: function(xhr) {
								Swal.fire('Error', 'No se pudo agregar el hotel al carrito.', 'error');
							}
						});
					}
				});
			});
		},
		error: function(xhr) {
			console.error("Error al obtener los hoteles:", xhr);
			$('#contenedorHotel').html('<p>Error al cargar los hoteles.</p>');
		}
	});
}

$(document).ready(function () {
	cargarListadoHotel();
});

