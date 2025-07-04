$(function () {
  $("#formDestino").submit(function(e) {
    e.preventDefault();

    var form = $('#formDestino')[0];
    var data = new FormData(form);

    $.ajax({
      url: contextPath + '/crearDestino',
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
          text: 'El destino se ha creado exitosamente',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        });

        setTimeout(function() {
			location.reload();
        }, 1500);
      },
      error: function(xhr, status, error) {
        Swal.fire({
          title: 'Error',
          text: 'No se pudo crear el destino',
          icon: 'error'
        });
      }
    });
  });
});
