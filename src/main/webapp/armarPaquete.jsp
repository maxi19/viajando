
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
	<link rel="icon" href="<%=request.getContextPath()%>/images/icon.svg">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Astro v5.9.2">
<title>Viajando.com</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/album/">
	
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
	
<script src="<%=request.getContextPath()%>/assets/js/color-modes.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/js/sweetalert2.all.min.js"></script>
<link
	href="<%=request.getContextPath()%>/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/customjs/viajandoScripts.js"></script>
<script
	src="<%=request.getContextPath()%>/scripts/bootstrap/css/bootstrap.min.css"></script>
<script
	src="<%=request.getContextPath()%>/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="assets/css/estilo-img.css">
<script src="imagen.js" defer></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/style/estilosCarousel.css">
		<link rel="stylesheet"
	href="<%=request.getContextPath()%>/style/estiloGaleria.css">
	

<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
	rel="stylesheet">


<script type="text/javascript">
							var contextPath="<%=request.getContextPath()%>";
</script>
<meta name="theme-color" content="#712cf9">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem
	}
}

.b-example-divider {
	width: 100%;
	height: 3rem;
	background-color: #0000001a;
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em #0000001a, inset 0 .125em .5em #00000026
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh
}

.bi {
	vertical-align: -.125em;
	fill: currentColor
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch
}

.btn-bd-primary { -
	-bd-violet-bg: #712cf9; -
	-bd-violet-rgb: 112.520718, 44.062154, 249.437846; -
	-bs-btn-font-weight: 600; -
	-bs-btn-color: var(- -bs-white); -
	-bs-btn-bg: var(- -bd-violet-bg); -
	-bs-btn-border-color: var(- -bd-violet-bg); -
	-bs-btn-hover-color: var(- -bs-white); -
	-bs-btn-hover-bg: #6528e0; -
	-bs-btn-hover-border-color: #6528e0; -
	-bs-btn-focus-shadow-rgb: var(- -bd-violet-rgb); -
	-bs-btn-active-color: var(- -bs-btn-hover-color); -
	-bs-btn-active-bg: #5a23c8; -
	-bs-btn-active-border-color: #5a23c8
}

.bd-mode-toggle {
	z-index: 1500
}

.bd-mode-toggle .bi {
	width: 1em;
	height: 1em
}

.bd-mode-toggle .dropdown-menu .active .bi {
	display: block !important
}
</style>
</head>
<body>





	<div
		class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
		<button
			class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
			id="bd-theme" type="button" aria-expanded="false"
			data-bs-toggle="dropdown" aria-label="Toggle theme (auto)">
			<svg class="bi my-1 theme-icon-active" aria-hidden="true">
								<use href="#circle-half"></use></svg>
			<span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
		</button>
		<ul class="dropdown-menu dropdown-menu-end shadow"
			aria-labelledby="bd-theme-text">
			<li>
				<button type="button"
					class="dropdown-item d-flex align-items-center"
					data-bs-theme-value="light" aria-pressed="false">
					<svg class="bi me-2 opacity-50" aria-hidden="true">
										<use href="#sun-fill"></use></svg>
					Light
					<svg class="bi ms-auto d-none" aria-hidden="true">
										<use href="#check2"></use></svg>
				</button>
			</li>




			<li>
				<button type="button"
					class="dropdown-item d-flex align-items-center"
					data-bs-theme-value="dark" aria-pressed="false">
					<svg class="bi me-2 opacity-50" aria-hidden="true">
										<use href="#moon-stars-fill"></use></svg>
					Dark
					<svg class="bi ms-auto d-none" aria-hidden="true">
										<use href="#check2"></use></svg>
				</button>
			</li>
			<li>
				<button type="button"
					class="dropdown-item d-flex align-items-center active"
					data-bs-theme-value="auto" aria-pressed="true">
					<svg class="bi me-2 opacity-50" aria-hidden="true">
										<use href="#circle-half"></use></svg>
					Auto
					<svg class="bi ms-auto d-none" aria-hidden="true">
										<use href="#check2"></use></svg>
				</button>
			</li>
		</ul>
	</div>
	<header data-bs-theme="dark">
		<div class="collapse text-bg-dark" id="navbarHeader">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-md-7 py-4">
						<h4>Sobre nuestra pagina</h4>
						<p class="text-body-secondary">Nuestro sistema te permite
							solicitar vuelos contratar paquetes turísticos, agendar
							excursiones personalizadas y elegir entre una amplia variedad de
							hoteles, todo desde un mismo lugar.</p>
					</div>
					<div class="col-sm-4 offset-md-1 py-4">
						<h4>Contact</h4>
						<ul class="list-unstyled">
							<li><a href="#" class="text-white">Follow on X</a></li>
							<li><a
								href="https://www.instagram.com/institutofatimasoldati/?hl=es"
								class="text-white" target="_blank">Instagram</a></li>
							<li><a href="#" class="text-white">Email me</a></li>
						</ul>
						<button class="btn btn-primary" id="btn-login">Login</button>
					</div>
				</div>
			</div>
		</div>
		
<div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container d-flex justify-content-between align-items-center">
        <!-- IZQUIERDA: Marca -->
        <a href="<%=request.getContextPath()%>/" class="navbar-brand d-flex align-items-center">
            <span class="material-symbols-outlined me-2" style="font-size: 24px;">flight</span>
            <strong>Viajando.com</strong>
        </a>

        <!-- DERECHA: Links + botón toggle -->
        <div class="d-flex align-items-center gap-3">
            <a href="<%=request.getContextPath()%>/FAQ.jsp" class="nav-link text-white">FAQ</a>
            <a href="<%=request.getContextPath()%>/about.jsp" class="nav-link text-white">Acerca de</a>
            <a href="<%=request.getContextPath()%>/paquetes.jsp" class="nav-link text-white">Mis paquetes</a>
            <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                aria-controls="navbarHeader" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</div>

	</header>


<main>

<%
	// Recibo el parámetro opciones desde la URL
	String opciones = request.getParameter("opciones");
	String[] opcionesSeleccionadas = opciones != null ? opciones.split(",") : new String[0];
	%>

	<h1>Armar tu paquete</h1>
	<br>
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


	</main>
	<script
		src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"
		class="astro-vvvwv3sm"></script>
	<script src="<%=request.getContextPath()%>/scripts/agregarPaquete.js"></script>



</body>
</html>