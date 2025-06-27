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
						<p class="card-text"><strong>Precio:</strong> ${this.precio}</p>
						<div class="d-flex justify-content-between align-items-center">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-outline-secondary ver-mas-btn" data-id="${this.id}">Ver más</button>
								<button class="btn btn-SM btn-outline-secondary boton-carrito-excursion" data-id="${this.id}" data-type='EXCURSION' ">Carrito</button>
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
		            <td>${this.fecha_inicio}</td>
		            <td>${this.fecha_fin}</td>
		            <td>$${this.precio}</td>
		            <td>${this.destino_id}</td>
					<td>${this.destino_value}</td>
		            <td>${this.estrellas}</td>
		            <td>
		                <button class="btn btn-danger" data-id="${this.id}" onClick="myFunction(this)">Eliminar</button>
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
				console.log(response);
				$('#contenedorExcursion').empty();

				response.forEach(m => {
					console.log("Destino recibido:", m.destino);
				  const excursion = new Excursion(
				    m.id, m.nombre, m.descripcion,
				    m.fecha_inicio, m.fecha_fin,
				    m.precio, m.destino.id, `${m.destino.nombre}, ${m.destino.pais}`, m.estrellas,
				    m.imagen
				  );

				  // Renderiza tarjeta
				  $('#contenedorExcursion').append(excursion.renderizar());

				  // Renderiza fila en tabla
				  $('#tablaExcursion').append(excursion.renderizarTabla());

				  });

				$('.boton-carrito-excursion').click(function () {
					const id = $(this).data("id");
					const type = $(this).data("type");

					$.ajax({
						type: "GET",
						url: contextPath + '/carrito.do',
						data: { id: id, type: type },
						dataType: "json",
						success: function(response) {
							console.log("Agregado al carrito:", response);
						}
					});
				});
			},
			error: function(xhr) {
				console.error("Error al obtener la excursión:", xhr);
				$('#contenedorExcursion').html('<p>Error al cargar las excursiones.</p>');
			}
		});
	}



	$(document).ready(function () {
		// Llamá el que necesites según el JSP actual
		cargarListadoExcursion(); // Para galería.jsp
		//cargarExcursionesTabla(); // Para dashboard.jsp
	});