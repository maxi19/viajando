<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Paquetes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
				var contextPath="<%=request.getContextPath()%>";
</script>


</head>
<body>

<div class="container mt-4">
    <h1>Mis Paquetes</h1>

    <ul class="list-group" id="listaPaquetes">
        <!-- Los paquetes se cargan acá -->
    </ul>
</div>

<script src="${pageContext.request.contextPath}/scripts/paquete.js"></script>
</body>
</html>
