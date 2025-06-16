$(function() { 

	$("#btn-confirmar").click(function(e) {
		e.preventDefault();
		var nombre = $("#nombre").val();
		var descripcion = $("#descripcion").val();
		var fecha_inicio = $("#fecha_inicio").val();
		var fecha_fin = $("#fecha_fin").val();
		var precio = $("#precio").val();
		var destino = $("#destino").val();
		var estrellas = $("#estrellas").val();


		$.ajax({
			url: contextPath + '/crearExcursion',
			dataType: 'json',
			success: function(data) {
				Swal.fire({
					title: 'Guardado con exito!',
					text: 'La excursion se ha creado exitosamente',
					icon: 'success',
					position: 'top-end',
					showConfirmButton: false,
					timer: 1500
				});

				// Redirigir después del mensaje (espera 1.5 segundos)
				setTimeout(function() {
					window.location.href = contextPath + '/LeerDatosExcursion';
				}, 1500);
			},
			error: function(xhr, status, error) {
				Swal.fire({
					title: 'Error',
					text: 'No se pudo crear la excursion',
					icon: 'error'
				});
			},
			data: {
				nombre: nombre,
				descripcion: descripcion,
				fecha_inicio: fecha_inicio,
				fecha_fin: fecha_fin,
				precio: precio,
				destino: destino,
				estrellas: estrellas
			},
			cache: true,
			type: 'post'
		});
	});
});
/**
 * 
 */