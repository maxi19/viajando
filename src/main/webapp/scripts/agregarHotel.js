$(function () {
  const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));

  // Cargar destinos al abrir el modal del hotel
  $('#modalHotel').on('show.bs.modal', function () {
    console.log("Abriendo modal hotel");

    const $select = $('#cmbDestinoHotel');
    $select.empty().append('<option selected disabled>Seleccione un destino...</option>');

    $.ajax({
      url: contextPath + '/listarDestinos',
      method: 'GET',
      dataType: 'json',
      success: function (destinos) {
        $.each(destinos, function (i, destino) {
          $select.append(`<option value="${destino.id}">${destino.nombre}, ${destino.pais}</option>`);
        });
        console.log("Destinos cargados en hotel:", destinos);
      },
      error: function (xhr) {
        console.error("Error cargando destinos hotel:", xhr.responseText);
        Swal.fire({
          title: 'Error',
          text: 'No se pudieron cargar los destinos',
          icon: 'error'
        });
      }
    });
  });

  // ✅ Enviar formulario de hotel
  $('#btn-confirmar-hotel').click(function (e) {
    e.preventDefault();

    const form = $('#formHotel')[0];
    const data = new FormData(form);

    $.ajax({
      url: contextPath + '/crearHotel',
      type: 'POST',
      enctype: 'multipart/form-data',
      data: data,
      processData: false,
      contentType: false,
      cache: false,
      success: function (data) {
        Swal.fire({
          position: 'center',
          title: 'Guardado con éxito!',
          text: 'El hotel se ha creado exitosamente',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        });

        setTimeout(function () {
          window.location.href = contextPath + '/LeerDatosHotel';
        }, 1500);
      },
      error: function (xhr, status, error) {
        console.error("Error al crear hotel:", xhr.responseText);
        Swal.fire({
          title: 'Error',
          text: 'No se pudo crear el hotel',
          icon: 'error'
        });
      }
    });
  });
});
