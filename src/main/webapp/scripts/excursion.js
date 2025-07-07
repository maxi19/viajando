var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

class Excursion {
	constructor(id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, destino_value, estrellas, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
		this.destino_id = destino_id;
		this.destino_value = destino_value;
		this.estrellas = estrellas;
		this.imagen = imagen;
	}

	renderizar() {
		const estrellas = this.generarEstrellas();

		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Excursion" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Excursion N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Fecha inicio:</strong> ${this.fecha_inicio}</p>
						<p class="card-text"><strong>Fecha fin:</strong> ${this.fecha_fin}</p>
						<p class="card-text"><strong>Destino:</strong> ${this.destino_value}</p>
						<p class="card-text"><strong>Precio</strong> $${this.precio}</p>
						
						<p class="card-text estrellas">${estrellas}</p>

						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito-excursion" data-id="${this.id}" data-type="EXCURSION">Carrito</button>
							</div>
							<small class="text-body-secondary">9 mins</small>
						</div>
					</div>
				</div>
			</div>
		`;
	}

	generarEstrellas() {
		const rating = parseFloat(this.estrellas);
		let html = '';
		const fullStars = Math.floor(rating);
		const halfStar = rating - fullStars >= 0.5;
		const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

		for (let i = 0; i < fullStars; i++) {
			html += '<i class="fas fa-star" style="color: gold; text-shadow: 0 0 1px white, 0 0 2px white;"></i>';
		}
		if (halfStar) {
			html += '<i class="fas fa-star-half-alt" style="color: gold; text-shadow: 0 0 1px white, 0 0 2px white;"></i>';
		}
		for (let i = 0; i < emptyStars; i++) {
			html += '<i class="far fa-star" style="color: gold;"></i>';
		}

		return html;
	}

	renderizarTabla() {
		return `
			<tr>
				<td>${this.id}</td>
				<td>${this.nombre}</td>
				<td>${this.descripcion}</td>
				<td>${this.fecha_inicio}</td>
				<td>${this.fecha_fin}</td>
				<td>$${this.precio}</td>
				<td>${this.destino_id}</td>
				<td>${this.destino_value}</td>
				<td>${this.estrellas}</td>
				<td>
					<button class="btn btn-danger" data-id="${this.id}" onClick="eliminarExcursion(this)">Eliminar</button>
				</td>
			</tr>
		`;
	}
}

function cargarListadoExcursion() {
	$.ajax({
		url: contextPath + "/excursionController",
		method: "GET",
		cache: false,
		success: function(response) {
			$('#contenedorExcursion').empty();
			$('#tablaExcursion').empty();

			response.forEach(m => {
				const excursion = new Excursion(
					m.id, m.nombre, m.descripcion,
					m.fecha_inicio, m.fecha_fin,
					m.precio, m.destino.id, `${m.destino.nombre}, ${m.destino.pais}`, m.estrellas,
					m.imagen
				);

				$('#contenedorExcursion').append(excursion.renderizar());
				$('#tablaExcursion').append(excursion.renderizarTabla());
			});

			$('.ver-mas-btn').click(function () {
				const id = $(this).data("id");
				const excursion = response.find(e => e.id === id);
				if (!excursion) {
					console.error("Excursión no encontrada");
					return;
				}
				const estrellas = new Excursion().generarEstrellas.call({ estrellas: excursion.estrellas });

				const html = `
					<div class="row">
						<div class="col-md-6">
							<img src="${contextPath}/images/${excursion.imagen}" class="img-fluid" alt="Imagen Excursion">
						</div>
						<div class="col-md-6">
							<h5>${excursion.nombre}</h5>
							<p><strong>Destino:</strong> ${excursion.destino.nombre}, ${excursion.destino.pais}</p>
							<p><strong>Descripción:</strong> ${excursion.descripcion}</p>
							<p><strong>Desde:</strong> ${excursion.fecha_inicio}</p>
							<p><strong>Hasta:</strong> ${excursion.fecha_fin}</p>
							<p><strong>Precio:</strong> $${excursion.precio}</p>
							<p class="estrellas">${estrellas}</p>
						</div>
					</div>
				`;

				$('#modalContent').html(html);
				const modal = new bootstrap.Modal(document.getElementById('modalExcursion'));
				modal.show();
			});
		},
		error: function(xhr) {
			console.error("Error al obtener la excursión:", xhr);
			$('#contenedorExcursion').html('<p>Error al cargar las excursiones.</p>');
		}
	});
}

// Evento para botón "Agregar al carrito"
$(document).on("click", ".boton-carrito-excursion", function () {
	const id = $(this).data("id");
	const type = $(this).data("type");

	Swal.fire({
		title: '¿Agregar al carrito?',
		text: "¿Deseás agregar esta excursión al carrito?",
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
					actualizarContadorCarrito();
					
					Swal.fire({
						title: 'Agregado al carrito',
						icon: 'success',
						showCancelButton: true,
						confirmButtonText: 'Ver carrito',
						cancelButtonText: 'Seguir navegando',
					}).then((choice) => {
						if (choice.isConfirmed) {
							window.location.href = contextPath + '/carrito/carritoPage.jsp';
						}
					});
				},
				error: function() {
					Swal.fire('Error', 'No se pudo agregar al carrito.', 'error');
				}
			});
		}
	});
});

$(document).ready(function () {
	cargarListadoExcursion();
});
