<%@page import="com.ventas.entity.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.ventas.entity.Item"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Confirmar Compra</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="style/style.css">
<link rel="stylesheet"	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<script type="text/javascript">
	var contextPath="<%=request.getContextPath()%>";
</script>
<script src="<%=request.getContextPath()%>/scripts/jquery/jquery-3.6.4.min.js"></script>
<script src="<%=request.getContextPath()%>/scripts/jquery/jquery1.13.2-ui.js"></script>
<script src="<%=request.getContextPath()%>/scripts/confirmar-compra.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/scripts/bootstrap/css/bootstrap.min.css">

</head>

<body>
	<div class="container">
		<aside>

			<div class="top">
				<div class="close" id="close_btn">
					<span class="material-symbols-sharp"> close </span>
				</div>
			</div>

			<div class="sidebar">
				<a href="<%=request.getContextPath()%>/productos"> <span
					class="material-symbols-sharp">grid_view </span>
					<h3>Productos</h3>
				</a>
				<a href="<%=request.getContextPath()%>/logOut"><span
					class="material-symbols-outlined">Logout</span></a>
			</div>

		</aside>
		<main>
			<h1>FatimaSportShop</h1>
			<section class="producto-section">
				<table id="tableProduct">
					<thead>
						<tr>
							<th width="30%" class="center">Producto</th>
							<th width="12%" class="center">Precio</th>
							<th width="12%" class="center">Cantidad</th>
							<th width="25%" class="center">Operacion</th>
						</tr>
					</thead>
					<tbody>
						<tr class="">
							<td width="30%" class="nombre">test</td>
							<td width="12%" class="precio">test</td>
							<td width="25%" class="cantidad">test</td>
							<td width="25%" class="detalles"></td>
						</tr>
					</tbody>
				</table>
						
				<input type="button"  class ="btn btn-warning" id="btn-confirmar" value="Confirmar Compra" />
						
				
			</section>
			<section class="addres-information">
				<h3>Datos de entrega:</h3>
				<form class="form-inline">
					<div class="form-group col-md-12">
						<label>Nombre y apellido envio </label> 
						<input type="text" name="nombre" placeholder="Destinatario">
					</div>
					<div class ="form-group col-md-8">
						<label>Direccion </label>
						<input type="text" name="direccion" placeholder="Direccion">
					</div>
					<div class ="form-group col-md-4">
						<label>Cp </label>
						<input type="text" name="cp" placeholder="Codigo Postal">
					</div>
					<div class ="form-group col-md-12">
						<label>Contacto </label>
						<input type="text" name="contacto" placeholder="telefono">
					</div>

					<div class ="row">
						<input type="button" class="btn btn-danger" id="btn-confirmar-compra" value="confirmar compra">
					</div>
				</form>
			</section>
			<section class="pago-section" >


			</section>
	
			
			
			
		</main>
		</div>
		<div class="right">
			<div class="top">
				<button id="menu_bar">
					<span class="material-symbols-sharp">menu</span>
				</button>

				<div class="theme-toggler">
					<span class="material-symbols-sharp active">light_mode</span> <span
						class="material-symbols-sharp">dark_mode</span>
				</div>
			</div>
		</div>

	<script src="scripts/script.js"></script>
</body>
</html>
