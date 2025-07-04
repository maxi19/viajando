$(function () {
  // Cargar los destinos en el select
  function cargarSelectsVuelo() {
    $.ajax({
      url: contextPath + '/listarDestinos',
      method: 'GET',
      dataType: 'json',
      success: function (destinos) {
        const $selectDestino = $('#cmbDestinoVuelo');
        $selectDestino.empty().append('<option selected disabled>Seleccione un destino...</option>');
        $.each(destinos, function (i, destino) {
          $selectDestino.append(`<option value="${destino.id}">${destino.nombre}, ${destino.pais}</option>`);
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
  }

  // Cargar los aviones en el select
  function cargarSelectsAvion() {
    $.ajax({
      url: contextPath + '/ListasAvion',
      method: 'GET',
      dataType: 'json',
      success: function (aviones) {
        const $selectAvion = $('#cmbAvionVuelo');
        $selectAvion.empty().append('<option selected disabled>Seleccione un avión...</option>');
        $.each(aviones, function (i, avion) {
          $selectAvion.append(`<option value="${avion.id}">${avion.nombre} (Capacidad: ${avion.capacidad})</option>`);
        });
      },
      error: function () {
        Swal.fire({
          title: 'Error',
          text: 'No se pudieron cargar los aviones',
          icon: 'error'
        });
      }
    });
  }

  // Cargar ambos selects al abrir el modal
  $('#modalVuelo').on('show.bs.modal', function () {
    cargarSelectsVuelo();
    cargarSelectsAvion();
  });

  // Enviar formulario
  $("#btn-confirmar").click(function (e) {
    e.preventDefault();

    if (!$('#formVuelo').valid()) return;

    const form = $('#formVuelo')[0];
    const data = new FormData(form);

    $.ajax({
      url: contextPath + '/crearVuelo',
      type: 'POST',
      enctype: 'multipart/form-data',
      data: data,
      processData: false,
      contentType: false,
      cache: false,
      success: function () {
        Swal.fire({
          position: 'center',
          title: '¡Vuelo creado!',
          text: 'El vuelo se ha registrado correctamente',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        });

        setTimeout(function () {
          window.location.href = contextPath + '/LeerDatosVuelos';
        }, 1500);
      },
      error: function () {
        Swal.fire({
          title: 'Error',
          text: 'No se pudo crear el vuelo',
          icon: 'error'
        });
      }
    });
  });
});