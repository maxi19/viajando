/**
 * 
 */


//var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));


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
		/*  const botonReservar = this.estado === "LIBRE"
					  ? `<button class="btn btn-success reservar-btn" data-id="${this.numero}">Reservar</button>`
					  : `<button class="btn btn-warning abrir-reserva-btn" data-id="${this.numero}">Agregar Pedido</button>`;
					  */
		return `
			<div class="card m-2" style="width: 18rem;">
			<img src="${contextPath}/images/${this.imagen}"  class="card-img-top" alt="Imagen Excursion" style="width: 100%; height: 250px; object-fit: cover;>
				<div class="card-body">	
					<h5 class="card-title">Excursion N° ${this.id}</h5>
					<p class="card-text"><strong>Nombre:</strong> ${this.nombre}</p>
					<p class="card-text"><strong>Destino ID:</strong> ${this.destino_id }</p>
					<p class="card-text"><strong>Destino:</strong> ${this.destino_value } </p>
					<p class="card-text"><strong>Estrellas:</strong> ${this.estrellas}</p>
					<p class="card-text"><strong>Precio:</strong> ${ this.precio}</p>
					<button class="btn btn-danger" data-id="${this.id}%" onClick="myFunction(this)">eliminar</button>
					   </div>
			</div>
		`;
	}
}

function cargarListadoHotel() {

	$.ajax({
		url: contextPath + "/hotelController",
		method: "GET",
		cache: false,
		success: function(response) {
			   	 console.log(response);  // Revisar qué trae el campo estado
			$('#contenedorHotel').empty();

			response.forEach(m => {
				const hotel = new Hotel(m.id, m.nombre, m.destino_id, m.destino_value, m.estrellas, m.precio, m.imagen)
				
				$('#contenedorHotel').append(hotel.renderizar());
			});
		   	
			/*   $('.reservar-btn').click(function() {
							 const mesaId = $(this).data("id");
							 reservarMesa(mesaId);

						 });
						 
		
			$('.abrir-reserva-btn').click(function() {
							 const mesaId = $(this).data("id");
							 abrirPedidos(mesaId);
						 });
						 */

		},
		error: function(xhr) {
			console.log("Error al obtener el hotel:", xhr);
			$('#contenedorExcursion').html('<p>Error al cargar los hoteles.</p>');
		}
	});
}


$(document).ready(function() {
	cargarListadoHotel();
});
