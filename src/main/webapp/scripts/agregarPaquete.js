$(function () {
	$("#btn-confirmar").click(function (e) {
		e.preventDefault();

		const form = $('#formPaquete')[0]; // obtenemos el form real
		const data = new FormData(form);   // creamos FormData con todos los campos (incluyendo imagen)

		// Validación HTML5 (opcional, mejora UX)
		if (!form.checkValidity()) {
			form.reportValidity();
			return;
		}

		$.ajax({
			url: contextPath + '/crearPaquete',
			type: 'POST',
			enctype: 'multipart/form-data',
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			success: function () {
				Swal.fire({
					position: 'center',
					title: '¡Guardado con éxito!',
					text: 'El paquete se ha creado exitosamente.',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500,
					timerProgressBar: true
				}).then(() => {
					window.location.href = contextPath + '/LeerDatosPaquete';
				});
			},
			error: function (xhr, status, error) {
				console.error("Error:", xhr);
				Swal.fire({
					title: 'Error',
					text: 'No se pudo crear el paquete.',
					icon: 'error'
				});
			}
		});
	});
});
