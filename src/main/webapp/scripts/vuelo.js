var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

class Vuelo {
	constructor(id, nombre, fecha_inicio, fecha_fin, hora_ida, hora_vuelta, precio, destino_id, destino_value, estrellas, id_avion, imagen) {
		this.id = id;
		this.nombre = nombre;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.hora_ida = hora_ida;
		this.hora_vuelta = hora_vuelta;
		this.precio = precio;
		this.destino_id = destino_id;
		this.destino_value = destino_value;
		this.estrellas = estrellas;
		this.id_avion = id_avion;
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
					<img src="${contextPath}/images/${this.imagen}" class="card-img-top" alt="Imagen Vuelo" style="width: 100%; height: 250px; object-fit: cover;">
					<div class="card-body">
						<h5 class="card-title">Vuelo N° ${this.id}</h5>
						<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
						<p class="card-text"><strong>Fecha ida:</strong> ${this.fecha_inicio} ${this.hora_ida}</p>
						<p class="card-text"><strong>Fecha vuelta:</strong> ${this.fecha_fin} ${this.hora_vuelta}</p>
						<p class="card-text"><strong>Destino:</strong> ${this.destino_value}</p>
						<p class="card-text"><strong>Precio:</strong> $${this.precio}</p>
						<p class="card-text estrellas">${estrellas}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn-vuelo" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito-vuelo" data-id="${this.id}" data-type="VUELO">Carrito</button>
							</div>
							<small class="text-body-secondary">Avión ID: ${this.id_avion}</small>
						</div>
					</div>
				</div>
			</div>
		`;
	}
}

// Cargar todos los vuelos al iniciar
function cargarListadoVuelo() {
	$.ajax({
		url: contextPath + "/vueloController",
		method: "GET",
		cache: false,
		success: function (response) {
			renderizarVuelos(response);
		},
		error: function () {
			$('#contenedorVuelo').html('<div class="alert alert-danger">Error al cargar los vuelos.</div>');
		}
	});
}

// Buscar vuelos por fecha
$('#form-fecha-vuelo').on('submit', function (e) {
	e.preventDefault();

	const fecha_inicio = $(this).find('[name="fecha_inicio"]').val();
	const fecha_fin = $(this).find('[name="fecha_fin"]').val();

	$.ajax({
		url: contextPath + "/buscarVuelo",
		method: "GET",
		data: { fecha_inicio, fecha_fin },
		success: function (response) {
			renderizarVuelos(response);
		},
		error: function () {
			$('#contenedorVuelo').html('<div class="alert alert-danger">Error al buscar vuelos.</div>');
		}
	});
});

// Renderizar vuelos
function renderizarVuelos(data) {
	$('#contenedorVuelo').empty();

	if (data.length === 0) {
		$('#contenedorVuelo').html('<div class="alert alert-warning">No hay vuelos disponibles.</div>');
		return;
	}

	data.forEach(v => {
		const vuelo = new Vuelo(
			v.id, v.nombre,
			v.fecha_inicio, v.fecha_fin,
			v.hora_ida, v.hora_vuelta,
			v.precio, v.destino.id,
			`${v.destino.nombre}, ${v.destino.pais}`,
			v.estrellas, v.id_avion, v.imagen
		);
		$('#contenedorVuelo').append(vuelo.renderizar());
	});

	// Ver más modal
	$('.ver-mas-btn-vuelo').off().on('click', function () {
		const id = $(this).data("id");
		const vuelo = data.find(v => v.id === id);
		if (!vuelo) return;

		const estrellas = new Vuelo().generarEstrellas.call({ estrellas: vuelo.estrellas });

		const html = `
			<div class="row">
				<div class="col-md-6">
					<img src="${contextPath}/images/${vuelo.imagen}" class="img-fluid" alt="Imagen Vuelo">
				</div>
				<div class="col-md-6">
					<h5>${vuelo.nombre}</h5>
					<p><strong>Destino:</strong> ${vuelo.destino.nombre}, ${vuelo.destino.pais}</p>
					<p><strong>Fecha ida:</strong> ${vuelo.fecha_inicio} ${vuelo.hora_ida}</p>
					<p><strong>Fecha vuelta:</strong> ${vuelo.fecha_fin} ${vuelo.hora_vuelta}</p>
					<p><strong>Avión ID:</strong> ${vuelo.id_avion}</p>
					<p><strong>Precio:</strong> $${vuelo.precio}</p>
					<p class="estrellas">${estrellas}</p>
				</div>
			</div>
		`;

		$('#modalVueloContent').html(html);
		new bootstrap.Modal(document.getElementById('modalVuelo')).show();
	});

	// Agregar al carrito
	$('.boton-carrito-vuelo').off().on('click', function () {
		const id = $(this).data("id");
		const type = $(this).data("type");

		Swal.fire({
			title: '¿Agregar al carrito?',
			text: "¿Deseás agregar este vuelo al carrito?",
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
						actualizarContadorCarrito();

						Swal.fire({
							title: 'Vuelo agregado',
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
						Swal.fire('Error', 'No se pudo agregar el vuelo al carrito.', 'error');
					}
				});
			}
		});
	});
}

// Eliminar vuelo (desde tabla)
function eliminarVuelo(boton) {
	const id = $(boton).data("id");

	Swal.fire({
		title: '¿Eliminar vuelo?',
		text: "Esta acción no se puede deshacer",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Sí, eliminar',
		cancelButtonText: 'Cancelar'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "GET",
				url: contextPath + "/VueloEliminar",
				data: { id: id },
				dataType: "json",
				success: function (response) {
					Swal.fire('Eliminado', response.mensaje, 'success');
					cargarListadoVuelo(); // Recarga la tabla y tarjetas
				},
				error: function () {
					Swal.fire('Error', 'No se pudo eliminar el vuelo.', 'error');
				}
			});
		}
	});
}

// Al cargar la página
$(document).ready(function () {
	cargarListadoVuelo();
});
