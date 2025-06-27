$(function () {
  // Cargar destinos al iniciar
  $.ajax({
    url: contextPath + '/destinos', // Asegurate que esta URL devuelva JSON
    method: 'GET',
    dataType: 'json',
    success: function (destinos) {
      const $select = $('#cmbDestino');
      $.each(destinos, function (i, destino) {
        $select.append(`<option value="${destino.id}">${destino.nombre}</option>`);
      });
    },
    error: function () {
      Swal.fire({
        title: 'Error',
        text: 'No se pudieron cargar los destinos',
        icon: 'error'
      });
    }
  });

  // Enviar formulario
    $("#btn-confirmar1").click(function(e) {
      e.preventDefault();
      var form = $('#formVuelos')[0];
      var data = new FormData(form);	
	
	   $.ajax({
	        url: contextPath + '/crearVuelo',
	        type: 'POST',
	        enctype: 'multipart/form-data',
	        data: data,
	        processData: false,
	        contentType: false,
	        cache: false,
	        success: function(data) {
	          Swal.fire({
	            position: 'center',
	            title: 'Guardado con exito!',
	            text: 'El vuelo se ha creado exitosamente',
	            icon: 'success',
	            showConfirmButton: false,
	            timer: 1500
	          });

	          setTimeout(function() {
	            window.location.href = contextPath + '/LeerDatosVuelos';
	          }, 1500);
	        },
	        error: function(xhr, status, error) {
	          Swal.fire({
	            title: 'Error',
	            text: 'No se pudo crear el vuelo',
	            icon: 'error'
	          });
	        }
	      });
	    });
	  });