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
<title>FAQ</title>
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

<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;600;700&display=swap" rel="stylesheet">

<script type="text/javascript">
							var contextPath="<%=request.getContextPath()%>
	";
</script>
<meta name="theme-color" content="#712cf9">
<style>
/* Estilos Bootstrap específicos y generales que ya tenías */
.bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; -webkit-user-select: none; -moz-user-select: none; user-select: none; }
@media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }
.b-example-divider { width: 100%; height: 3rem; background-color: #0000001a; border: solid rgba(0, 0, 0, .15); border-width: 1px 0; box-shadow: inset 0 .5em 1.5em #0000001a, inset 0 .125em .5em #00000026; }
.b-example-vr { flex-shrink: 0; width: 1.5rem; height: 100vh; }
.bi { vertical-align: -.125em; fill: currentColor; }
.nav-scroller { position: relative; z-index: 2; height: 2.75rem; overflow-y: hidden; }
.nav-scroller .nav { display: flex; flex-wrap: nowrap; padding-bottom: 1rem; margin-top: -1px; overflow-x: auto; text-align: center; white-space: nowrap; -webkit-overflow-scrolling: touch; }
.btn-bd-primary { --bd-violet-bg: #712cf9; --bd-violet-rgb: 112.520718, 44.062154, 249.437846; --bs-btn-font-weight: 600; --bs-btn-color: var(--bs-white); --bs-btn-bg: var(--bd-violet-bg); --bs-btn-border-color: var(--bd-violet-bg); --bs-btn-hover-color: var(--bs-white); --bs-btn-hover-bg: #6528e0; --bs-btn-hover-border-color: #6528e0; --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb); --bs-btn-active-color: var(--bs-btn-hover-color); --bs-btn-active-bg: #5a23c8; --bs-btn-active-border-color: #5a23c8; }
.bd-mode-toggle { z-index: 1500; }
.bd-mode-toggle .bi { width: 1em; height: 1em; }
.bd-mode-toggle .dropdown-menu .active .bi { display: block !important; }

/* Estilos para el carrusel y el mensaje superpuesto */
#mensajeBienvenida {
  position: absolute;
  top: 100px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 10;
  background-color: rgba(0, 0, 0, 0.05); /* Muy transparente para el fondo */
  padding: 20px;
  border-radius: 8px;
  max-width: 800px;
  text-align: center;
  color: #fff; /* Asegura que el texto en el mensaje sea blanco */
}
#mensajeBienvenida h1 { font-size: 3rem; margin-bottom: 10px; }
#mensajeBienvenida p { font-size: 1.1rem; line-height: 1.6; }

#carouselExampleIndicators .carousel-inner { height: 400px; background-color: rgba(0, 0, 0, 0.55); }
#carouselExampleIndicators .carousel-item img {
  width: 100%;
  height: 400px;
  object-fit: cover;
  filter: brightness(50%) blur(3px);
  margin: 0;
  padding: 0;
  display: block;
}
.NombreServicio { padding: 35px; }

/* --- INICIO: CSS MEJORADO PARA PREGUNTAS FRECUENTES (MODO CLARO/OSCURO) --- */
  body {
    font-family: 'Roboto', sans-serif;
    font-size: 1rem;
  }

/* Fallback/Especificación para Modo Oscuro si var(--bs-body-bg) no es suficiente */
[data-bs-theme="dark"] body {
    background-color: #1a1a1a;
    color: #f0f2f5;
}

/* Especificación para Modo Claro */
[data-bs-theme="light"] body {
    background-color: #f8f9fa;
    color: #212529;
}

.faq-container {
    padding: 50px;
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2); /* Sombra común para ambos, ajusta transparencia si necesitas más diferencia */
    margin-top: 50px;
    margin-bottom: 50px;
    max-width: 900px;
    margin-left: auto;
    margin-right: auto;
    border: 1px solid; /* El color del borde se define por el tema */
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

/* Colores del FAQ Container para Modo Oscuro */
[data-bs-theme="dark"] .faq-container {
    background-color: #212529;
    border-color: rgba(255, 255, 255, 0.1);
}

/* Colores del FAQ Container para Modo Claro */
[data-bs-theme="light"] .faq-container {
    background-color: #ffffff;
    border-color: rgba(0, 0, 0, 0.1);
}

.accordion-item {
    margin-bottom: 18px;
    border: none;
    border-radius: 10px;
    overflow: hidden;
    transition: all 0.3s ease-in-out;
}

/* Accordion Item para Modo Oscuro */
[data-bs-theme="dark"] .accordion-item {
    background-color: #2b3035;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}
[data-bs-theme="dark"] .accordion-item:hover {
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
    transform: translateY(-2px);
    background-color: #343a40;
}

/* Accordion Item para Modo Claro */
[data-bs-theme="light"] .accordion-item {
    background-color: #f0f2f5;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
[data-bs-theme="light"] .accordion-item:hover {
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
    transform: translateY(-2px);
    background-color: #e9ecef;
}

.accordion-header .accordion-button {
    background-color: transparent;
    font-size: 1.1rem;
    font-weight: 600;
    padding: 20px 30px;
    border-bottom: 1px solid;
    transition: all 0.3s ease;
    border-radius: 10px;
    text-align: left;
}

/* Accordion Button para Modo Oscuro */
[data-bs-theme="dark"] .accordion-header .accordion-button {
    color: #ced4da;
    border-bottom-color: rgba(255, 255, 255, 0.1);
}
[data-bs-theme="dark"] .accordion-header .accordion-button:not(.collapsed) {
    background-color: #0d6efd;
    color: #ffffff;
    border-bottom-color: #0d6efd;
    box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* Accordion Button para Modo Claro */
[data-bs-theme="light"] .accordion-header .accordion-button {
    color: #343a40;
    border-bottom-color: rgba(0, 0, 0, 0.1);
}
[data-bs-theme="light"] .accordion-header .accordion-button:not(.collapsed) {
    background-color: #0d6efd;
    color: #ffffff;
    border-bottom-color: #0d6efd;
    box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* Customizando la flecha del acordeón */
.accordion-header .accordion-button::after {
    font-size: 1.3rem;
    transition: transform 0.3s ease;
    filter: none;
}

/* Flecha del Acordeón para Modo Oscuro */
[data-bs-theme="dark"] .accordion-header .accordion-button::after {
    color: #adb5bd;
}
[data-bs-theme="dark"] .accordion-header .accordion-button:not(.collapsed)::after {
    color: #ffffff;
    transform: rotate(-180deg);
}

/* Flecha del Acordeón para Modo Claro */
[data-bs-theme="light"] .accordion-header .accordion-button::after {
    color: #6c757d;
}
[data-bs-theme="light"] .accordion-header .accordion-button:not(.collapsed)::after {
    color: #ffffff;
    transform: rotate(-180deg);
}

.accordion-body {
    padding: 25px 30px;
    font-size: 1rem;
    line-height: 1.7;
    border-top: 1px solid;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
}

/* Accordion Body para Modo Oscuro */
[data-bs-theme="dark"] .accordion-body {
    color: #adb5bd;
    background-color: #212529;
    border-top-color: rgba(255, 255, 255, 0.08);
}

/* Accordion Body para Modo Claro */
[data-bs-theme="light"] .accordion-body {
    color: #495057;
    background-color: #ffffff;
    border-top-color: rgba(0, 0, 0, 0.08);
}

.accordion-body strong {
    color: #0d6efd; /* Un azul primario de Bootstrap para destacar en ambos temas */
    font-weight: 700;
}

/* Estilo para el botón "Volver al inicio" */
.faq-container .btn-secondary {
    background-color: var(--bs-secondary);
    border-color: var(--bs-secondary);
    color: var(--bs-white);
    padding: 12px 25px;
    font-size: 1rem;
    border-radius: 8px;
    transition: all 0.3s ease;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.faq-container .btn-secondary:hover {
    background-color: var(--bs-secondary-hover);
    border-color: var(--bs-secondary-hover);
    transform: translateY(-1px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.4);
}

.accordion { width: 100%; }

/* --- FIN: CSS MEJORADO PARA PREGUNTAS FRECUENTES (MODO CLARO/OSCURO) --- */

/* Estilo del footer (general para ambas páginas) */
.dark-footer {
    padding: 20px 0;
    text-align: center;
    border-top: 1px solid;
    transition: background-color 0.3s ease, color 0.3s ease;
}

/* Footer para Modo Oscuro */
[data-bs-theme="dark"] .dark-footer {
    background-color: #1a1a1a !important;
    color: #f8f9fa;
    border-top-color: rgba(255, 255, 255, 0.1);
}
[data-bs-theme="dark"] .dark-footer a { color: #adb5bd; }
[data-bs-theme="dark"] .dark-footer a:hover { color: #ffffff; text-decoration: none; }

/* Footer para Modo Claro */
[data-bs-theme="light"] .dark-footer {
    background-color: #e9ecef !important;
    color: #343a40;
    border-top-color: rgba(0, 0, 0, 0.1);
}
[data-bs-theme="light"] .dark-footer a { color: #495057; }
[data-bs-theme="light"] .dark-footer a:hover { color: #212529; text-decoration: none; } /* Links blancos al pasar el mouse */
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



<main>

<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div id="mensajeBienvenida">
    <h1>Preguntas Frecuentes</h1>
    <p>tu plataforma confiable para organizar y reservar tu próxima aventura. Nos especializamos en ofrecer una experiencia simple, rápida y segura para que puedas planificar viajes a cualquier destino del mundo.</p>
  </div>

  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<%=request.getContextPath()%>/images/paris.png" class="d-block w-100" alt="París">
    </div>
    <div class="carousel-item">
      <img src="<%=request.getContextPath()%>/images/tokyo.jpg" class="d-block w-100" alt="Tokio">
    </div>
    <div class="carousel-item">
      <img src="<%=request.getContextPath()%>/images/misiones_argentina.png" class="d-block w-100" alt="Misiones, Argentina">
    </div>
    <div class="carousel-item">
      <img src="<%=request.getContextPath()%>/images/new_york.png" class="d-block w-100" alt="Nueva York">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Anterior</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Siguiente</span>
  </button>
</div>

</main>
		</section>

	</footer>
	<script
		src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"
		class="astro-vvvwv3sm"></script>
	<script src="<%=request.getContextPath()%>/scripts/excursion.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/hotel.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/vuelo.js"></script>

<div class="container faq-container">
    <div class="accordion" id="faqAccordion">
      <div class="accordion-item">
        <h2 class="accordion-header" id="faq1Heading">
          <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#faq1" aria-expanded="true" aria-controls="faq1">
            ¿Cómo puedo reservar un vuelo en Viajando.com?
          </button>
        </h2>
        <div id="faq1" class="accordion-collapse collapse show" aria-labelledby="faq1Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Solo tenés que buscar tu destino, seleccionar el vuelo deseado y completar tus datos personales y de pago. ¡Listo para viajar!
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq3Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq3" aria-expanded="false" aria-controls="faq3">
            ¿Es seguro ingresar mis datos en esta plataforma?
          </button>
        </h2>
        <div id="faq3" class="accordion-collapse collapse" aria-labelledby="faq3Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Absolutamente. Utilizamos certificados SSL y mecanismos de encriptación para proteger toda tu información personal y de pago.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq4Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq4" aria-expanded="false" aria-controls="faq4">
            ¿Qué métodos de pago aceptan?
          </button>
        </h2>
        <div id="faq4" class="accordion-collapse collapse" aria-labelledby="faq4Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Aceptamos tarjetas de crédito, débito, transferencias bancarias y billeteras virtuales como MercadoPago.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq5Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq5" aria-expanded="false" aria-controls="faq5">
            ¿Puedo contratar otros servicios además del vuelo?
          </button>
        </h2>
        <div id="faq5" class="accordion-collapse collapse" aria-labelledby="faq5Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Sí. En Viajando.com también podés contratar hoteles, excursiones y paquetes personalizados para tus viajes.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq6Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq6" aria-expanded="false" aria-controls="faq6">
            ¿Dónde puedo ver todos los vuelos disponibles?
          </button>
        </h2>
        <div id="faq6" class="accordion-collapse collapse" aria-labelledby="faq6Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            En la sección de <strong>Vuelos</strong> podés ver un listado completo de vuelos nacionales e internacionales disponibles, con fechas, precios y aerolíneas.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq7Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq7" aria-expanded="false" aria-controls="faq7">
            ¿Qué información incluye cada vuelo?
          </button>
        </h2>
        <div id="faq7" class="accordion-collapse collapse" aria-labelledby="faq7Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Cada vuelo incluye el número de vuelo, origen, destino, fecha de salida y llegada, aerolínea, precio y disponibilidad de asientos.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq8Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq8" aria-expanded="false" aria-controls="faq8">
            ¿Cómo funciona la combinación de vuelos con otros servicios?
          </button>
        </h2>
        <div id="faq8" class="accordion-collapse collapse" aria-labelledby="faq8Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Podés combinar vuelos con hoteles y excursiones al agregar todos los servicios al carrito y luego seleccionar <strong>“Armar paquete”</strong>. El sistema consolidará todo en una sola reserva.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq9Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq9" aria-expanded="false" aria-controls="faq9">
            ¿Qué sucede si el vuelo es cancelado por la aerolínea?
          </button>
        </h2>
        <div id="faq9" class="accordion-collapse collapse" aria-labelledby="faq9Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            En ese caso, te notificaremos por correo electrónico y se te ofrecerán opciones de reprogramación o reembolso, según la política de la aerolínea.
          </div>
        </div>
      </div>

      <div class="accordion-item">
        <h2 class="accordion-header" id="faq10Heading">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq10" aria-expanded="false" aria-controls="faq10">
            ¿Cómo accedo a mis reservas de vuelo después de comprarlas?
          </button>
        </h2>
        <div id="faq10" class="accordion-collapse collapse" aria-labelledby="faq10Heading" data-bs-parent="#faqAccordion">
          <div class="accordion-body">
            Ingresá a tu perfil y dirigite a la sección <strong>“Mis reservas”</strong>. Allí verás los detalles de todos los vuelos que hayas comprado.
          </div>
        </div>
      </div>

    </div>

    <div class="text-center mt-5">
      <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-secondary">Volver al inicio</a>
    </div>
  </div>


	<footer class="text-body-secondary py-5 dark-footer">
		<div class="container">
			<p class="float-end mb-1">
				<a href="#">Back to top</a>
			</p>
			<p class="mb-1">Viajando.com &copy; 2025</p>
			<p class="mb-0">
				Para más información, visita nuestra <a href="<%=request.getContextPath()%>/AcercaDe.jsp">página de Acerca de</a> o <a href="#">contáctanos</a>.
			</p>
		</div>
	</footer>
	
		<script src="<%=request.getContextPath()%>/scripts/contadorCarrito.js"></script>
	

</body>
</html>