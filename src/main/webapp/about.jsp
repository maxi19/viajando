	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
	<link rel="icon" href="<%=request.getContextPath()%>/images/icon.svg">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Información sobre Viajando.com, tu plataforma de viajes y vuelos.">
<meta name="author" content="Tu Nombre o Nombre de la Empresa">
<meta name="generator" content="Tu Generador o Astro v5.9.2">
<title>Acerca de Viajando.com</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/album/">
<script src="<%=request.getContextPath()%>/assets/js/color-modes.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/sweetalert2.all.min.js"></script>
<link href="<%=request.getContextPath()%>/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/customjs/viajandoScripts.js"></script>
<script src="<%=request.getContextPath()%>/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/estilo-img.css">
<%-- Si imagen.js es un script general, mantenlo. Si es específico de alguna imagen, evalúa si es necesario aquí --%>
<script src="<%=request.getContextPath()%>/imagen.js" defer></script> 

<link rel="stylesheet" href="<%=request.getContextPath()%>/style/estilosCarousel.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;600;700&display=swap" rel="stylesheet">

<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";
</script>
<meta name="theme-color" content="#712cf9">

<style>
/* Estilos Bootstrap específicos (si son necesarios aquí, sino, pueden ir en tu CSS global) */
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

/* ---------------------------------------------------- */
/* Estilos Específicos de la página "Acerca de" (Modo Claro/Oscuro) */
body {
    font-family: 'Roboto', sans-serif;
    background-color: var(--bs-body-bg);
    color: var(--bs-body-color);
    transition: background-color 0.3s ease, color 0.3s ease;
}

/* Modo Oscuro para Body */
[data-bs-theme="dark"] body {
    background-color: #1a1a1a;
    color: #f0f2f5;
}

/* Modo Claro para Body */
[data-bs-theme="light"] body {
    background-color: #f8f9fa;
    color: #212529;
}

.about-container {
    padding: 60px 20px;
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
    margin-top: 50px;
    margin-bottom: 50px;
    max-width: 900px;
    margin-left: auto;
    margin-right: auto;
    border: 1px solid;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

/* About Container para Modo Oscuro */
[data-bs-theme="dark"] .about-container {
    background-color: #212529;
    border-color: rgba(255, 255, 255, 0.1);
}

/* About Container para Modo Claro */
[data-bs-theme="light"] .about-container {
    background-color: #ffffff;
    border-color: rgba(0, 0, 0, 0.1);
}

.about-header {
    font-size: 3.5rem;
    text-align: center;
    margin-bottom: 40px;
    font-weight: 700;
    letter-spacing: -0.03em;
    text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
}

/* About Header para Modo Oscuro */
[data-bs-theme="dark"] .about-header {
    color: #e0e6ed;
}

/* About Header para Modo Claro */
[data-bs-theme="light"] .about-header {
    color: #343a40;
}

.about-section {
    margin-bottom: 40px;
}

.about-section h2 {
    font-size: 2rem;
    color: #0d6efd; /* Azul de Bootstrap se mantiene */
    margin-bottom: 20px;
    font-weight: 600;
    border-bottom: 2px solid;
    padding-bottom: 10px;
}

/* H2 de la sección para Modo Oscuro */
[data-bs-theme="dark"] .about-section h2 {
    border-bottom-color: rgba(13, 110, 253, 0.3);
}

/* H2 de la sección para Modo Claro */
[data-bs-theme="light"] .about-section h2 {
    border-bottom-color: rgba(13, 110, 253, 0.2);
}

.about-section p {
    font-size: 1.1rem;
    line-height: 1.8;
}

/* Párrafos para Modo Oscuro */
[data-bs-theme="dark"] .about-section p {
    color: #adb5bd;
}

/* Párrafos para Modo Claro */
[data-bs-theme="light"] .about-section p {
    color: #495057;
}

.about-section ul {
    list-style: none;
    padding: 0;
}

.about-section ul li {
    font-size: 1.1rem;
    line-height: 1.8;
    margin-bottom: 10px;
}

/* Li para Modo Oscuro */
[data-bs-theme="dark"] .about-section ul li {
    color: #adb5bd;
}

/* Li para Modo Claro */
[data-bs-theme="light"] .about-section ul li {
    color: #495057;
}

.about-section ul li strong {
    color: #0d6efd; /* Mantener un azul para destacar */
}

.about-section .icon {
    vertical-align: middle;
    margin-right: 10px;
    font-size: 1.5rem;
    color: #0d6efd; /* El color del icono se mantiene azul primario */
}

.team-member {
    text-align: center;
    margin-bottom: 30px;
}

.team-member img {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid #0d6efd; /* Borde de la imagen se mantiene azul */
    margin-bottom: 15px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.team-member h4 {
    font-size: 1.3rem;
    margin-bottom: 5px;
}

/* Team Member H4 para Modo Oscuro */
[data-bs-theme="dark"] .team-member h4 {
    color: #e0e6ed;
}

/* Team Member H4 para Modo Claro */
[data-bs-theme="light"] .team-member h4 {
    color: #343a40;
}

.team-member p {
    font-size: 0.95rem;
}

/* Team Member P para Modo Oscuro */
[data-bs-theme="dark"] .team-member p {
    color: #adb5bd;
}

/* Team Member P para Modo Claro */
[data-bs-theme="light"] .team-member p {
    color: #495057;
}

/* Botón "Volver al inicio" (mismo tratamiento que en FAQ) */
.btn-secondary {
    background-color: var(--bs-secondary);
    border-color: var(--bs-secondary);
    color: var(--bs-white);
    padding: 12px 25px;
    font-size: 1rem;
    border-radius: 8px;
    transition: all 0.3s ease;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.btn-secondary:hover {
    background-color: var(--bs-secondary-hover);
    border-color: var(--bs-secondary-hover);
    transform: translateY(-1px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.4);
}

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
[data-bs-theme="light"] .dark-footer a:hover { color: #212529; text-decoration: none; }
</style>
</head>
<body>

    <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
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
    <div class="container about-container">
        <h1 class="about-header">Acerca de Viajando.com</h1>

        <div class="about-section">
            <h2><span class="material-symbols-outlined icon">info</span>Nuestra Misión</h2>
            <p>En Viajando.com, nuestra misión es simplificar la planificación de tus viajes, ofreciendo una plataforma intuitiva y completa donde puedas reservar vuelos, encontrar alojamiento, organizar excursiones y armar paquetes turísticos personalizados. Nos esforzamos por brindarte la mejor experiencia de usuario, garantizando seguridad, eficiencia y acceso a una amplia variedad de opciones para cualquier destino del mundo. Queremos que tu aventura comience desde el momento en que nos eliges.</p>
        </div>

        <div class="about-section">
            <h2><span class="material-symbols-outlined icon">trending_up</span>Nuestros Valores</h2>
            <ul>
                <li><span class="material-symbols-outlined icon">check_circle</span><strong>Confianza:</strong> Garantizamos transacciones seguras y protección de tus datos personales.</li>
                <li><span class="material-symbols-outlined icon">check_circle</span><strong>Innovación:</strong> Constantemente buscamos mejorar y añadir nuevas funcionalidades para tu comodidad.</li>
                <li><span class="material-symbols-outlined icon">check_circle</span><strong>Accesibilidad:</strong> Queremos que planificar un viaje sea fácil para todos, en cualquier momento y lugar.</li>
                <li><span class="material-symbols-outlined icon">check_circle</span><strong>Soporte:</strong> Nuestro equipo está listo para ayudarte en cada paso de tu viaje.</li>
            </ul>
        </div>

        <div class="about-section">
            <h2><span class="material-symbols-outlined icon">code</span>Tecnologías Utilizadas</h2>
            <p>Nuestro proyecto se desarrolla utilizando un conjunto de tecnologías robustas y modernas para asegurar la mejor performance y experiencia de usuario:</p>
            <ul>
                <li><span class="material-symbols-outlined icon">web</span><strong>Backend:</strong> Java con JSP y Servlets, ofreciendo una lógica de negocio sólida y escalable.</li>
                <li><span class="material-symbols-outlined icon">data_usage</span><strong>Base de Datos:</strong> MySQL, para una gestión eficiente y segura de la información de vuelos, hoteles y usuarios.</li>
                <li><span class="material-symbols-outlined icon">palette</span><strong>Frontend:</strong> HTML5, CSS3, JavaScript y Bootstrap 5 para un diseño responsivo, moderno y una interfaz de usuario amigable.</li>
                <li><span class="material-symbols-outlined icon">library_add</span><strong>Librerías Adicionales:</strong> jQuery y SweetAlert2 para interacciones dinámicas y alertas atractivas.</li>
            </ul>
        </div>
        
        

<div class="about-section">
  <h2><span class="material-symbols-outlined icon">group</span> Nuestro Equipo</h2>
  <div class="row row-cols-1 row-cols-md-3 g-4">

    <div class="col team-member">
      <h4>Castro William</h4>
      <p><strong>Rol:</strong> Backend - Módulo de Vuelos</p>
      <p><strong>Tareas:</strong> Desarrollo completo del backend de vuelos</p>
      <p><strong>Motivo:</strong> Afinidad con datos estructurados y lógica técnica</p>
    </div>

    <div class="col team-member">
      <h4>Martino Brisa</h4>
      <p><strong>Rol:</strong> Backend - Excursiones</p>
      <p><strong>Tareas:</strong> Alta de excursiones, destinos y paquetes para cliente</p>
      <p><strong>Motivo:</strong> Interés en integrar distintos servicios</p>
    </div>

    <div class="col team-member">
      <h4>Figueredo Miguel</h4>
      <p><strong>Rol:</strong> Lógica - Paquetes y reservas</p>
      <p><strong>Tareas:</strong> Reservas, carrito de compras, lógica de negocio</p>
      <p><strong>Motivo:</strong> Facilidad para unir frontend y backend</p>
    </div>

    <div class="col team-member">
      <h4>Chavez Ariel</h4>
      <p><strong>Rol:</strong> Frontend - Hotel y Reserva</p>
      <p><strong>Tareas:</strong> Formularios de reserva, carrito de pago, diseño general</p>
      <p><strong>Motivo:</strong> Afinidad con diseño visual y experiencia de usuario</p>
    </div>

    <div class="col team-member">
      <h4>Equise Natalia</h4>
      <p><strong>Rol:</strong> Analista funcional</p>
      <p><strong>Tareas:</strong> Especificaciones, listado de hoteles</p>
      <p><strong>Motivo:</strong> Capacidad para convertir necesidades en tareas técnicas</p>
    </div>

  </div>
</div>

        <div class="text-center mt-5">
            <a href="<%=request.getContextPath()%>/galeria.jsp" class="btn btn-secondary">Volver al inicio</a>
        </div>
    </div>
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
	<script src="<%=request.getContextPath()%>/scripts/excursion.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/hotel.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/vuelo.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/contadorCarrito.js"></script>

</body>
</html>