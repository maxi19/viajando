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

	renderizar() {
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
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-sm btn-outline-secondary boton-carrito-viaje" data-id="${this.id}" data-type="VUELO">Carrito</button>
							</div>
							<small class="text-body-secondary">Avión ID: ${this.id_avion}</small>
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
				<td>${this.fecha_inicio} ${this.hora_ida}</td>
				<td>${this.fecha_fin} ${this.hora_vuelta}</td>
				<td>${this.precio}</td>
				<td>${this.destino_id}</td>
				<td>${this.destino_value}</td>
				<td>${this.estrellas}</td>
				<td>${this.id_avion}</td>
				<td>
					<button class="btn btn-danger" data-id="${this.id}" onClick="eliminarVuelo(this)">Eliminar</button>
				</td>
			</tr>
		`;
	}
}

function cargarListadoVuelo() {
	$.ajax({
		url: contextPath + "/vueloController",
		method: "GET",
		cache: false,
		success: function (response) {
			console.log(response);
			$('#contenedorVuelo').empty();

			response.forEach(v => {
				const vuelo = new Vuelo(
					v.id, v.nombre,
					v.fecha_inicio, v.fecha_fin,
					v.hora_ida, v.hora_vuelta,
					v.precio, v.destino.id,
					`${v.destino.nombre}, ${v.destino.pais}`,
					v.estrellas, v.id_avion, v.imagen
				);

				// Renderiza tarjeta
				$('#contenedorVuelo').append(vuelo.renderizar());

				// Renderiza tabla (si existe)
				$('#tablaVuelo').append(vuelo.renderizarTabla());
			});

			// Manejo del botón carrito
			$('.boton-carrito-viaje').click(function () {
				const id = $(this).data("id");
				const type = $(this).data("type");

				$.ajax({
					type: "GET",
					url: contextPath + "/carrito.do",
					data: { id: id, type: type },
					dataType: "json",
					success: function (response) {
						console.log("Agregado al carrito:", response);
					}
				});
			});
		},
		error: function (xhr) {
			console.error("Error al obtener vuelos:", xhr);
			$('#contenedorVuelo').html('<p>Error al cargar los vuelos.</p>');
		}
	});
}

// Ejecutar al cargar
$(document).ready(function () {
	cargarListadoVuelo();
});
