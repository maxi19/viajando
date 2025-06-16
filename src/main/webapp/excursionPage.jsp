
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@page import="java.util.*"%>
<%@page import="com.viajando.domain.Excursion"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Lista de Excursiones</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<script src="<%=request.getContextPath()%>/scripts/jquery/jquery.min.js"></script>

	<script type="text/javascript">
			var contextPath='<%=request.getContextPath()%>';
	</script>
	<script src="<%=request.getContextPath()%>/scripts/eliminarExcursion.js"> </script>


</head>
<body>

<div class="container mt-4">


<span>
		<a class="btn btn-primary"  href="<%=request.getContextPath()%>/excursionForm" > Nuevo </a> <!--lleva al formulario -->
</span>

<%  List<Excursion> excursion  = (List) request.getAttribute("excursion"); %> <!-- Obtiene la lista-->

<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Nombre</th>
      <th scope="col">Descripcion</th>
      <th scope="col">Fecha inicio</th>
      <th scope="col">Fecha Fin</th>
      <th scope="col">Precio</th>
      
    </tr>
  </thead>
  <tbody>
  
  <!-- Recorre la lista para poner los datos en la tabla -->
<%for(int i=0;i<excursion.size();i++){
	%>
	 
	<tr bgcolor="white">
	<td><%=excursion.get(i).getId() %></td>
	<td><%=excursion.get(i).getNombre() %></td>
	<td><%=excursion.get(i).getDescripcion()%></td>
	<td><%=excursion.get(i).getFecha_inicio()%></td>
	<td><%=excursion.get(i).getFecha_fin()%></td>
	<td><%=excursion.get(i).getPrecio()%></td>
	<td><%=excursion.get(i).getDestino()%></td>
	<td><%=excursion.get(i).getEstrellas()%></td>
	
	
	<td> 
		<a class="btn btn-primary"  href="<%=request.getContextPath()%>/loadExcursionForm?id=<%=excursion.get(i).getId()%>" > editar </a> <!-- lleva a LoadForm para editar un dato -->
	 	<button class="btn btn-danger"  data-id="<%=excursion.get(i).getId()%>"  onClick="myFunction(this)" > eliminar </button> <!-- lleva a un js para eliminar -->
	 </td>
<%		
}
%>
 </tbody>

</table>

</div>
</body>
</html>
