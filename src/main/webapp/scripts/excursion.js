// Variable global para almacenar excursiones
let excursionesData = [];

class Excursion {
	constructor(id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
		this.destino = destino;
		this.estrellas = estrellas;
		this.imagen = imagen;
	}

	renderizar() {
		return `
			<div class="col">
				<div class="card shadow-sm">
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Excursion" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Excursion N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Fecha inicio:</strong> ${this.fecha_inicio}</p>
						<p class="card-text"><strong>Fecha fin:</strong> ${this.fecha_fin}</p>
						<p class="card-text"><strong>Precio:</strong> ${this.precio}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-SM btn-outline-secondary boton-carrito" data-id="${this.id}" data-type='EXCURSION' ">Carrito</button>
								</div>
							<small class="text-body-secondary">9 mins</small>
						</div>
					</div>
				</div>
			</div>
		`;
	}
}

function cargarListadoExcursion() {
	$.ajax({
		url: contextPath + "/excursionController",
		method: "GET",
		cache: false,
		success: function(response) {
			console.log(response);

			$('#contenedorExcursion').empty();
			excursionesData = response; // Guardamos la lista global para usarla en el modal

			response.forEach(m => {
				const excursion = new Excursion(m.id, m.nombre, m.descripcion, m.fecha_inicio, m.fecha_fin, m.precio, m.destino, m.estrellas, m.imagen);
				$('#contenedorExcursion').append(excursion.renderizar());
			});

			$('.boton-carrito').click(function() {
				const id = $(this).data("id");
				const type = $(this).data("type");
				console.log(id, type);

				$.ajax({
					type: "GET",
					url: contextPath + '/carrito.do',
					data: { id, type },
					dataType: "json",
					success: function(response) {
						console.log("Agregado al carrito", response);
					}
				});
			});
		},
		error: function(xhr) {
			console.log("Error al obtener las excursiones:", xhr);
			$('#contenedorExcursion').html('<p>Error al cargar las excursiones.</p>');
		}
	});
}

$(document).ready(function() {
	cargarListadoExcursion();
});

$(document).on('click', '.ver-mas-btn', function () {
	const id = $(this).data('id');
	const excursion = excursionesData.find(e => e.id === id);

	if (excursion) {
		$('#modalContent').html(`
			<img src="${contextPath}/images/${excursion.imagen}" class="img-fluid mb-3" style="max-height: 300px; object-fit: cover;">
			<h4>${excursion.nombre}</h4>
			<p><strong>Descripción:</strong> ${excursion.descripcion}</p>
			<p><strong>Fecha inicio:</strong> ${excursion.fecha_inicio}</p>
			<p><strong>Fecha fin:</strong> ${excursion.fecha_fin}</p>
			<p><strong>Destino:</strong> ${excursion.destino}</p>
			<p><strong>Precio:</strong> ${excursion.precio}</p>
			<p><strong>Estrellas:</strong> ${excursion.estrellas}</p>
		`);

		const modal = new bootstrap.Modal(document.getElementById('modalExcursion'));
		modal.show();
	}
});
