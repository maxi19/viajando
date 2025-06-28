
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
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

<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
	rel="stylesheet">


<script type="text/javascript">
							var contextPath="<%=request.getContextPath()%>
	";
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
			<div class="container">
				<a href="#" class="navbar-brand d-flex align-items-center"> <span
					class="material-symbols-outlined me-2" style="font-size: 24px;">flight</span>
					<strong>Viajando.com</strong>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarHeader"
					aria-controls="navbarHeader" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
	</header>

<style>
  /* Contenedor del mensaje que queda sobre el carrusel */
  #mensajeBienvenida {
    position: absolute;
    top: 20px;       /* ajustá la posición vertical */
    left: 50%;
    transform: translateX(-50%);
    z-index: 10;     /* encima del carrusel */
    background-color: rgba(0, 0, 0, 0.05); /* fondo blanco semitransparente */
    padding: 20px;
    border-radius: 8px;
    max-width: 600px;
    text-align: center;
  }

#carouselExampleIndicators .carousel-inner {
  height: 400px;
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

  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <div id="mensajeBienvenida">
      <h1>BIENVENIDO</h1>
      <button type="button" class="btn btn-primary" id="btn-verCarrito">
        Ver carrito <span class="badge text-bg-secondary">4</span>
      </button>
      <p >tu plataforma confiable
        para organizar y reservar tu próxima aventura. Nos especializamos
        en ofrecer una experiencia simple, rápida y segura para que puedas
        planificar viajes a cualquier destino del mundo.</p>
      <p>
        <a href="#" class="btn btn-primary my-2" id="btn-init-paquete">Arma
          tu paquete</a> <a href="#" class="btn btn-secondary my-2">Secondary
          action</a>
      </p>
    </div>

    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0"
        class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img class="d-block w-100" src="../images/paris.png" alt="First slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="/images/tokyo.jpg" alt="Second slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="/images/misiones_argentina.png" alt="Third slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="/images/new_york.png" alt="Third slide">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only"></span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only"></span>
    </a>
  </div>




		</section>
		<h1 class="NombreServicio">Excursiones</h1>
		<div class="album py-5 bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"
					id="contenedorExcursion">
					<!--  -->

				</div>
			</div>
		</div>

		<h1 class="NombreServicio">Vuelos</h1>
		<div class="album py-5 bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"
					id="contenedorVuelo">

					<!--  -->

				</div>
			</div>
		</div>

		<h1 class="NombreServicio">Hoteles</h1>
		<div class="album py-5 bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"
					id="contenedorHotel">





					<!--  -->

				</div>
			</div>
		</div>







	</main>
	<footer class="text-body-secondary py-5">
		<div class="container">
			<p class="float-end mb-1">
				<a href="#">Back to top</a>
			</p>
			<p class="mb-1">Album example is &copy; Bootstrap, but please
				download and customize it for yourself!</p>
			<p class="mb-0">
				New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
					href="../getting-started/introduction">getting started guide</a>.
			</p>
		</div>
	</footer>
	<script
		src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"
		class="astro-vvvwv3sm"></script>
	<script src="<%=request.getContextPath()%>/scripts/excursion.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/hotel.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/vuelo.js"></script>


















	<!-- Modal de Excursión -->
	<div class="modal fade" id="modalExcursion" tabindex="-1"
		aria-labelledby="modalExcursionLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalExcursionLabel">Detalle de
						Excursión</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Cerrar"></button>
				</div>
				<div class="modal-body" id="modalContent">
					<!-- Contenido dinámico se inyecta aquí -->
				</div>
			</div>
		</div>
	</div>




</body>
</html>