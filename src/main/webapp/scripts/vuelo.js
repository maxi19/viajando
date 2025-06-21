var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));


class Vuelo {
	constructor(id, destino, ida, vuelta, precio, precio, estrellas, hora_ida, hora_vuelta) {
		this.id = id;
		this.destino = destino;
		this.ida = ida;
		this.vuelta = vuelta;
		this.precio = precio;
		this.estrellas = estrellas;
		this.hora_ida = hora_ida;
		this.hora_vuelta = hora_vuelta;
	}

	renderizar() {

		return `
			<div class="card m-2" style="width: 18rem;">
				<div class="card-body">	
					<h5 class="card-title">Vuelo N° ${this.id}</h5>
					<p class="card-text"><strong>Destino:</strong> ${this.destino}</p>
					<p class="card-text"><strong>Ida: </strong> ${this.ida}</p>
					<p class="card-text"><strong>Vuelta: </strong> ${this.vuelta}</p>
					<p class="card-text"><strong>Hora de Ida:</strong> ${this.hora_ida}</p>
					<p class="card-text"><strong>Hora de vuelta:</strong> ${this.hora_vuelta}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.precio}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.estrellas}</p>
					<button class="btn btn-danger" data-id="${this.id}%" onClick="myFunction(this)">eliminar</button>
					   </div>
			</div>
		`;
	}
}

function cargarListadoVuelo() {

	$.ajax({
		url: contextPath + "/VuelosController",
		method: "GET",
		cache: false,
		success: function(response) {

			$('#contenedorVuelo').empty();

			response.forEach(m => {
				const vuelo = new Vuelo(v.id, v.destino, v.ida, v.vuelta, v.precio, v.estrellas, v.hora_ida, v.hora_vuelta)

				$('#contenedorVuelo').append(vuelo.renderizar());
			});

		},
		error: function(xhr) {
			console.log("Error al obtener el vuelo:", xhr);
			$('#contenedorVuelo').html('<p>Error al cargar los vuelos.</p>');
		}
	});
}


$(document).ready(function() {
	cargarListadoVuelo();
});
