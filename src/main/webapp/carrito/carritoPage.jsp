<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Context path JS -->
    <script>
        var contextPath = "<%=request.getContextPath()%>";
    </script>

    <!-- Bootstrap y jQuery -->
    <link href="<%=request.getContextPath()%>/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/assets/js/jquery/jquery-3.6.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/carrito.js"></script>
    

    <style>
        body {
            background-color: #f8f9fa;
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>

<div class="container py-5">
    <h1 class="mb-4 text-center">Carrito de Compras</h1>

<div id="contenedorCarrito"></div>
</div>


</body>
</html>
