
function cargarHoteles() {
  $.ajax({
    url: contextPath + '/listarHoteles',
    method: 'GET',
    dataType: 'json',
    success: function (hoteles) {
      const $select = $('#cmbHotel');
      $select.empty().append('<option value="">Seleccione un hotel...</option>');
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
}

function cargarVuelos() {
  $.ajax({
    url: contextPath + '/listarVuelos',
    method: 'GET',
    dataType: 'json',
    success: function (vuelos) {
      const $select = $('#cmbVuelo');
      $select.empty().append('<option value="">Seleccione un vuelo...</option>');
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
}

function cargarExcursiones() {
  $.ajax({
    url: contextPath + '/listarExcursiones',
    method: 'GET',
    dataType: 'json',
    success: function (excursiones) {
      const $select = $('#cmbExcursion');
      $select.empty().append('<option value="">Seleccione una excursión...</option>');
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
}

// === EVENTOS AL CARGAR LA PÁGINA ===
$(function () {
  // Checkbox toggles
  $('#chkHotel').on('change', function () {
    $('#input-hotel').toggle(this.checked);
	$('#cmbHotel').prop('required', this.checked);
    if (this.checked) cargarHoteles();
  });

  $('#chkVuelo').on('change', function () {
    $('#input-vuelo').toggle(this.checked);
	$('#cmbVuelo').prop('required', this.checked);
    if (this.checked) cargarVuelos();
  });

  $('#chkExcursion').on('change', function () {
    $('#input-excursion').toggle(this.checked);
	$('#cmbExcursion').prop('required', this.checked);
    if (this.checked) cargarExcursiones();
  });

  // Cargar datos cuando se abre el modal 
  $('#modalPaquete').on('show.bs.modal', function () {
    cargarHoteles();
    cargarVuelos();
    cargarExcursiones();
  });

  // Enviar formulario
  $('#btn-confirmar-paquete').on('click', function (e) {
    e.preventDefault();
	console.log("Se hizo clic en Confirmar Paquete");


    const form = $('#formPaquete')[0];
    const data = new FormData(form);

	$('#cmbHotel').prop('required', $('#input-hotel').is(':visible'));
	$('#cmbVuelo').prop('required', $('#input-vuelo').is(':visible'));
	$('#cmbExcursion').prop('required', $('#input-excursion').is(':visible'));

	// Validar el formulario
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
      error: function (xhr) {
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
