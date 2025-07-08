
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
	<link rel="icon" href="<%=request.getContextPath()%>/images/icon.svg">


<script>
  const contextPath = "<%=request.getContextPath()%>";
</script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Astro v5.9.2">
<title>Carrito de compras</title>
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

<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;600;700&display=swap" rel="stylesheet">


<script type="text/javascript">
							var contextPath="<%=request.getContextPath()%>";
</script>
<meta name="theme-color" content="#712cf9">
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
	</div>	<header data-bs-theme="dark">
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
							<li><a
								href="https://www.instagram.com/institutofatimasoldati/"
								class="text-white" target="_blank">Instagram</a></li>
							<li><a href="mailto:maximilianoguzman@fatimarem.edu.ar?" class="text-white">Email me</a></li>
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
<a href="<%=request.getContextPath()%>/carrito/carritoPage.jsp" class="nav-link text-white position-relative">
	<span class="material-symbols-outlined">shopping_cart</span>
	<span id="carrito-count" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger d-none">
		0
	</span>
</a>
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


<style>

html {
  font-size: 16px; /* base estándar */
}

body {
  font-family: 'Roboto', sans-serif;
  font-size: 1rem; /* 16px base */
  line-height: 1.6;
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
  padding: 35px;
  background-color: #1b1e21;
  }

</style>

<main>

    

	
    <script src="<%=request.getContextPath()%>/scripts/carrito.js"></script>


<!-- Contenido del carrito -->
    <h1 class="NombreServicio">Carrito de Compras</h1>
<div class="album py-5 bg-body-tertiary">
<div class="container">

<div id="contenedorCarrito"></div>
<div id="contenedorBotonReservar"></div>
</div>
</div>


<style>

#contenedorCarrito {
  margin-bottom: 0 !important;
  padding-bottom: 0 !important;
}

#contenedorBotonReservar {
  margin-top: 0 !important;
  padding-top: 0 !important;
}

  #contenedorBotonReservar > div {
  margin-top: 0 !important;
}

/* Título del carrito */


/* Contenedor del carrito */
#contenedorCarrito {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}



  /* Tema claro */
  :root[data-bs-theme='light'] .card {
    background-color: #ffffff;
    color: #212529;
  }

  /* Tema oscuro */
  :root[data-bs-theme='dark'] .card {
    background-color: #252d35;
    color: #f8f9fa;
  }



/* Imagen de la tarjeta */
.card-img-top {
    height: 200px;
    object-fit: cover;

}

/* Contenido de la tarjeta */
.card-body {
    padding: 15px;
}

/* Título del producto */


/* Precio */
.precio {
    font-size: 1.2rem;
    color: #198754;
    font-weight: bold;
}

/* Botones del carrito */
.btn-carrito {
    margin-top: 10px;
    width: 100%;
    font-weight: 500;
    transition: all 0.2s ease-in-out;
}

.btn-carrito:hover {
    opacity: 0.9;
}



/* Responsive para pantallas pequeñas */
@media (max-width: 768px) {
    .card {
        width: 90%;
    }
}

  .NombreServicio {
  padding: 35px
  }
  
  .centrar {

    display: flex;
    flex-wrap: wrap;
    justify-content: center;

}

</style>






	</main>
<footer class="text-body-secondary py-5 dark-footer">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Viajando.com &copy; 2025</p>
        <p class="mb-0">
            Para más información, visita nuestra <a href="<%=request.getContextPath()%>/about.jsp">página de Acerca de</a> o <a href="#">contáctanos</a>.
        </p>
    </div>
</footer>
	<script
		src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"
		class="astro-vvvwv3sm"></script>
	<script src="<%=request.getContextPath()%>/scripts/contadorCarrito.js"></script>



















</body>
</html>