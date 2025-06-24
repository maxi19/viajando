//var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));


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
		/*  const botonReservar = this.estado === "LIBRE"
					  ? `<button class="btn btn-success reservar-btn" data-id="${this.numero}">Reservar</button>`
					  : `<button class="btn btn-warning abrir-reserva-btn" data-id="${this.numero}">Agregar Pedido</button>`;
					  */
		return `
			<div class="card m-2" style="width: 18rem , height: 15rem; ">
			<img src="${contextPath}/images/${this.imagen}"  class="card-img-top" alt="Imagen Excursion" style="width: 100%; height: 250px; object-fit: cover;>
				<div class="card-body">	
					<h5 class="card-title">Excursion N° ${this.id}</h5>
					<p class="card-text"><strong>ID:</strong> ${this.nombre}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.descripcion}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.fecha_inicio.day} /${this.fecha_inicio.month} / ${this.fecha_inicio.year}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.fecha_fin}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.precio}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.destino}</p>
					<p class="card-text"><strong>Estado:</strong> ${this.estrellas}</p>
					<button class="btn btn-danger" data-id="${this.id}" onClick="myFunction(this)">Eliminar</button>
					<button class="btn btn-danger boton-carrito" data-id="${this.id}" data-type='EXCURSION' ">Carrito</button>
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
			   	 console.log(response);  // Revisar qué trae el campo estado
			$('#contenedorExcursion').empty();

			response.forEach(m => {
				const excursion = new Excursion(m.id, m.nombre, m.descripcion, m.fecha_inicio, m.fecha_fin, m.precio, m.destino, m.estrellas, m.imagen)
				
				$('#contenedorExcursion').append(excursion.renderizar());

		

			});
		   	
			$('.boton-carrito').click(function() {
			              const id = $(this).data("id");
						  const type = $(this).data("type");
			            console.log(id);
						console.log(type);
            
						$.ajax({
							type: "get",
							url: contextPath + '/carrito.do',
							data: {
								id : id,
								type : type
							},
							dataType: "json",
							success: function (response) {
								
							}
						});


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
			console.log("Error al obtener la excursion:", xhr);
			$('#contenedorExcursion').html('<p>Error al cargar las excursiones.</p>');
		}
	});
}


$(document).ready(function() {

	cargarListadoExcursion();




});

