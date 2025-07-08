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
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

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


			
<style>
  /* Contenedor del mensaje que queda sobre el carrusel */
  #mensajeBienvenida {
    position: absolute;
    top: 20px;       /* ajustá la posición vertical */
    left: 50%;
    transform: translateX(-50%);
    z-index: 10;     /* encima del carrusel */
    background-color: rgba(0, 0, 0, 0.05);
    padding: 20px;
    border-radius: 8px;
    max-width: 600px;
    text-align: center;
  }

#carouselExampleIndicators .carousel-inner {
  height: 400px;
      background-color: rgba(0, 0, 0, 0.55); 
  
}

#carouselExampleIndicators .carousel-item img {
  width: 100%;
  height: 400px;
  object-fit: cover;
  filter: brightness(50%) blur(3px);
  margin: 0;
  padding: 0;
  display: block;
  

}

  .NombreServicio {
  padding: 35px
  }

</style>

<main>


<script>
	var contextPath = "<%=request.getContextPath()%>";
</script>
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/formularioReserva.js"></script>

	<meta charset="UTF-8">
	<title>Formulario de Reserva</title>
	<link href="<%=request.getContextPath()%>/assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">

<a href="javascript:history.back()" class="btn btn-outline-secondary mb-3 d-inline-flex align-items-center">
  <i class="fas fa-arrow-left me-2"></i> Volver
</a>

	<h2 class="mb-4">Datos de los pasajeros</h2>
	<form action="<%=request.getContextPath()%>/confirmarReserva" method="post" id="formReserva">
		<div id="contenedorFormularios"></div>

		<div class="text-center mt-4">
			<button type="submit" class="btn btn-success btn-lg">Confirmar Reserva</button>
		</div>
	</form>
</div>

<style>
	#form-fecha-vuelo {
		background-color: var(--bs-body-bg);
		color: var(--bs-body-color);
		border-radius: 20px;
		box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
		padding: 30px;
		margin-bottom: 40px;
		border: 1px solid rgba(0, 0, 0, 0.1);
		transition: all 0.3s ease;
	}

	[data-bs-theme="dark"] #form-fecha-vuelo {
		box-shadow: 0 0 20px rgba(255, 255, 255, 0.05);
		border: 1px solid rgba(255, 255, 255, 0.1);
	}

	#form-fecha-vuelo .form-label {
		font-weight: bold;
		color: var(--bs-body-color);
	}

	#form-fecha-vuelo input[type="date"] {
		border-radius: 10px;
		border: 1px solid var(--bs-border-color, #ced4da);
		background-color: var(--bs-body-bg);
		color: var(--bs-body-color);
		box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
		transition: all 0.3s ease;
	}

	#form-fecha-vuelo input[type="date"]:focus {
		border-color: #0d6efd;
		box-shadow: 0 0 5px rgba(13, 110, 253, 0.5);
		outline: none;
	}

	#form-fecha-vuelo button {
		border-radius: 10px;
		padding: 10px 20px;
		background: linear-gradient(45deg, #0d6efd, #00c6ff);
		border: none;
		color: #fff;
		font-weight: bold;
		transition: background 0.3s ease, transform 0.2s ease;
	}

	#form-fecha-vuelo button:hover {
		background: linear-gradient(45deg, #0056b3, #0099cc);
		transform: scale(1.05);
	}
</style>

<style>
input.form-control,
select.form-control {
  background-color: var(--bs-body-bg);
  color: var(--bs-body-color);
  border: 1px solid var(--bs-border-color);
}

input.form-control:focus,
select.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 5px rgba(13, 110, 253, 0.5);
}

</style>








	</main>

	<script
		src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"
		class="astro-vvvwv3sm"></script>























</body>
</html>