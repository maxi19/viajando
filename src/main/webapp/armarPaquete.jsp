<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Armar Paquete</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/style/estiloPaquete.css">
	
	<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>
	<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	
	
	<script src="<%=request.getContextPath()%>/scripts/agregarPaquete.js"></script>
	
</head>
<body class="container py-4">

<header>
  <div class="titulo">
    <img class="icono" src="<%=request.getContextPath()%>/images/flight.svg" alt="Vuelo" style="font-size: 24px;">
    <strong class="texto">Viajando.com</strong>
  </div>
</header>

	<%
	// Recibo el parámetro opciones desde la URL
	String opciones = request.getParameter("opciones");
	String[] opcionesSeleccionadas = opciones != null ? opciones.split(",") : new String[0];
	%>

	<h1>Armar tu paquete</h1>
	
	
	<form class="form" id="formPaquete" method="post" enctype="multipart/form-data">
	
	 <div class="mb-4">
        <label for="nombre" class="form-label">Nombre del paquete</label>
        <input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese un nombre para su paquete">
    </div>

    <div class="mb-4">
        <label for="descripcion" class="form-label">Descripción</label>
        <input type="text" class="form-control" id="descripcion" name="descripcion" required placeholder="Ingrese una  descripcion para su paquete">
    </div>
    
    <div class="mb-4">
          <label for="personas"  class="form-label">Cantidad de personas:</label>
          <input type="number" class="form-control" id="personas" name="personas" required placeholder="Ingrese cantidad de personas">
        </div>


		<%
		for (String opcion : opcionesSeleccionadas) {
		%>

		<%
		if ("hotel".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-hotel" style="display: none;">
			<label for="cmbHotel" class="form-label">Elegí tu hotel</label> <select
				class="form-control" id="cmbHotel" name="hotel_id" required>
				<option selected disabled value="">Seleccione un hotel...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		if ("vuelo".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-vuelo" style="display: none;">
			<label for="cmbVuelo" class="form-label">Elegí tu vuelo</label> <select
				class="form-control" id="cmbVuelo" name="vuelo_id" required>
				<option selected disabled value="">Seleccione un vuelo...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		if ("excursion".equalsIgnoreCase(opcion)) {
		%>
		<div class="mb-4" id="input-excursion" style="display: none;">
			<label for="cmbExcursion" class="form-label">Elegí tu
				excursión</label> <select class="form-control" id="cmbExcursion"
				name="excursion_id" required>
				<option selected disabled value="">Seleccione una
					excursión...</option>
			</select>
		</div>
		<%
		}
		%>

		<%
		}
		%>

		<button type="submit" class="btn btn-primary" id="btn-armar-paquete">Confirmar
			paquete</button>

	</form>

	<script>
    // Obtener parámetros de URL para saber qué mostrar
    function getQueryParams() {
        const params = {};
        location.search.substr(1).split("&").forEach(function(item) {
            let [key, value] = item.split("=");
            if (key && value) {
                params[key] = decodeURIComponent(value);
            }
        });
        return params;
    }

    document.addEventListener('DOMContentLoaded', () => {
        const params = getQueryParams();
        // Espero que venga un parámetro opciones=hotel,vuelo por ejemplo
        const opciones = params.opciones ? params.opciones.split(',') : [];

        // Mostrar sólo los selects que el usuario eligió
        if (opciones.includes('hotel')) {
            document.getElementById('input-hotel').style.display = 'block';
            cargarHoteles();
        }
        if (opciones.includes('vuelo')) {
            document.getElementById('input-vuelo').style.display = 'block';
            cargarVuelos();
        }
        if (opciones.includes('excursion')) {
            document.getElementById('input-excursion').style.display = 'block';
            cargarExcursiones();
        }
    });

    function cargarHoteles() {
        fetch('/listarHoteles')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbHotel');
                data.forEach(hotel => {
                    let option = document.createElement('option');
                    option.value = hotel.id;
                    option.text = hotel.nombre;
                    select.add(option);
                });
            });
    }

    function cargarVuelos() {
        fetch('/listarVuelos')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbVuelo');
                data.forEach(vuelo => {
                    let option = document.createElement('option');
                    option.value = vuelo.id;
                    option.text = vuelo.nombre;
                    select.add(option);
                });
            });
    }

    function cargarExcursiones() {
        fetch('/listarExcursiones')
            .then(res => res.json())
            .then(data => {
                const select = document.getElementById('cmbExcursion');
                data.forEach(excursion => {
                    let option = document.createElement('option');
                    option.value = excursion.id;
                    option.text = excursion.nombre;
                    select.add(option);
                });
            });
    }
</script>
<script>
    $(document).ready(function () {
      $("#formPaquete").validate({
        rules: {
          nombre: {
            required: true,
            minlength: 2
          },
          descripcion: {
            required: true,
            minlength: 10
          },
          personas: {
            required: true,
            number: true,
            min: 1
          },
        },
        messages: {
          nombre: {
            required: "Por favor, ingrese un nombre",
            minlength: "El nombre debe tener al menos 2 caracteres"
          },
          descripcion: {
            required: "Por favor, ingrese una descripciï¿½n",
            minlength: "La descripciï¿½n debe tener al menos 10 caracteres"
          },
          personas: {
            required: "Por favor, ingrese la cantidad de personas",
            number: "Ingrese un nï¿½mero vï¿½lido",
            min: "Debe ser al menos 1"
          },

        },
        errorElement: "div",
        errorClass: "invalid-feedback",
        highlight: function (element) {
          $(element).addClass("is-invalid");
        },
        unhighlight: function (element) {
          $(element).removeClass("is-invalid");
        }
      });
    });
    
</script>

</body>
</html>
