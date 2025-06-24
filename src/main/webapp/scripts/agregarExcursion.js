$(function() { 

	$("#btn-confirmar").click(function(e) {
		e.preventDefault();
		var form = $('#formExcursion')[0]; // agarrás el form real
		var data = new FormData(form); // armás el objeto FormData


		$.ajax({
			url: contextPath + '/crearExcursion',
			type: 'POST',
			enctype: 'multipart/form-data',
			data: data,
			processData: false, // NO procesar datos
			contentType: false, // NO poner contentType automático
			cache: false,
			success: function(data) {
				Swal.fire({
					position: 'center', // este es el valor por defecto
					title: 'Guardado con exito!',
					text: 'La excursion se ha creado exitosamente',
					icon: 'success',
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
			}

		});
	});
});
/**
 * 
 */