function enviarFormularioPaquete(urlDestino) {
  const form = $('#formPaquete')[0];
  const data = new FormData(form);

  $('#cmbHotel').prop('required', $('#input-hotel').is(':visible'));
  $('#cmbVuelo').prop('required', $('#input-vuelo').is(':visible'));
  $('#cmbExcursion').prop('required', $('#input-excursion').is(':visible'));

  if (!form.checkValidity()) {
    form.reportValidity();
    return;
  }

  $.ajax({
    url: contextPath + urlDestino,
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
		location.reload();
      });
    },
    error: function (xhr) {
      console.error("Error:", xhr);
      Swal.fire({
        title: 'Error',
        text: 'No se pudo crear el paquete.',
        icon: 'error'
      });
    }
  });
}

$(document).ready(function() {
// Depende de cada botón es a dónde te lleva:
$('#btn-confirmar-paquete').on('click', function (e) {
  e.preventDefault();
  enviarFormularioPaquete('/crearPaqueteCompleto');
});

$('#btn-armar-paquete').on('click', function (e) {
  e.preventDefault();
  enviarFormularioPaquete('/crearPaqueteSimple');
});
});