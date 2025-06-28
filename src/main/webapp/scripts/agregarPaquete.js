$(function () {
	// Cargar excursiones al iniciar
	  $.ajax({
	    url: contextPath + '/listarExcursiones', // Asegurate que esta URL devuelva JSON
	    method: 'GET',
	    dataType: 'json',
	    success: function (excursiones) {
	      const $select = $('#cmbExcursion');
	      $.each(excursiones, function (i, excursion) {
	        $select.append(`<option value="${excursion.id}">${excursion.nombre}</option>`);
	      });
	    },
	    error: function () {
	      Swal.fire({
	        title: 'Error',
	        text: 'No se pudieron cargar las excursiones',
	        icon: 'error'
	      });
	    }
	  });
	
	 //cargar hoteles
	 $.ajax({
	     url: contextPath + '/listarHoteles',
	     method: 'GET',
	     dataType: 'json',
	     success: function (hoteles) {
	       const $select = $('#cmbHotel');
	       $.each(hoteles, function (i, hotel) {
	         $select.append(`<option value="${hotel.id}">${hotel.nombre}</option>`);
	       });
	     },
	     error: function () {
	       Swal.fire({
	         title: 'Error',
	         text: 'No se pudieron cargar los hoteles',
	         icon: 'error'
	       });
	     }
	   });
	   
	   
	   //cargar vuelos
	    $.ajax({
	        url: contextPath + '/listarVuelos',
	        method: 'GET',
	        dataType: 'json',
	        success: function (vuelos) {
	          const $select = $('#cmbVuelo');
	          $.each(vuelos, function (i, vuelo) {
	            $select.append(`<option value="${vuelo.id}">${vuelo.nombre}</option>`);
	          });
	        },
	        error: function () {
	          Swal.fire({
	            title: 'Error',
	            text: 'No se pudieron cargar los vuelos',
	            icon: 'error'
	          });
	        }
	      });
	
	$("#btn-confirmar").click(function (e) {
		e.preventDefault();

		var form = $('#formPaquete')[0]; // obtenemos el form real
		var data = new FormData(form);   // creamos FormData con todos los campos (incluyendo imagen)

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
