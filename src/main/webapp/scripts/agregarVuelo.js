$(function() {

	$("#btn-confirmar").click(function(e) {
		e.preventDefault();
		var destino = $("#destino").val();
		var ida = $("#ida").val();
		var vuelta = $("#vuelta").val();
		var hora_ida = $("#hora_ida").val();
		var hora_vuelta = $("#hora_vuelta").val();
		var precio = $("#precio").val();
		var estrellas = $("#estrellas").val();


		$.ajax({
			url: contextPath + '/crearVuelo',
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
					window.location.href = contextPath + '/LeerDatosVuelos';
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
				destino: destino,
				ida: ida,
				vuelta: vuelta,
				hora_ida: hora_ida,
				hora_vuelta: hora_vuelta,
				precio: precio,
				estrellas: estrellas
			},
			cache: true,
			type: 'post'
		});
	});
});