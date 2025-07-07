$(function() {

    function cargarGrilla() {
        $.ajax({
            type: "get",
            url: contextPath +'/destinos.do',
            dataType: "json",
            success: function (response) {
              populateTable(response);
            }
        });
    }

    
    function populateTable(data){
				
        $("#destinos-grilla tbody").find("tr:gt(0)").remove(); 

        $.each(data, function(i, v) {

          if (i == 0) {
            setDataOnRow($("#destinos-grilla tbody").find("tr").first(), v);

          } else {

            var clonnedRow = $($("#destinos-grilla tbody").find("tr").first()).clone();
            setDataOnRow(clonnedRow, v);

            $("#destinos-grilla tbody").append(clonnedRow);

          }
        });
            
    };
    
    function setDataOnRow(rowObject, v) {
      var id = v.id;  
      var nombre = v.nombre;
      var pais = v.pais;
      var precio = v.precio;
      $(rowObject).find(".id").html(id);
      $(rowObject).find(".nombre").html(nombre);
      $(rowObject).find(".pais").html(pais);
      $(rowObject).find(".precio").html(precio);
	   var btnEliminar = $("<button>")
	      .attr({
	        "class": "btn btn-danger btn-sm",
	        "data-id": id
	      })
	      .text("Eliminar")
	      .click(function() {
	        myFunction(this);
	      });

	    $(rowObject).find(".operar").html(btnEliminar);
	  }

	function myFunction(data){
	    
	    value =  $(data).attr("data-id");
	    
	    //agregamos sweet alert
	    Swal.fire({
	        title: "Esta seguro de Eliminar el Destino?",
	        text: "El destino sera eliminada de la base!",
	        icon: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#3085d6",
	        cancelButtonColor: "#d33",
	        confirmButtonText: "Si, eliminar!"
	      }).then((result) => {
	        if (result.isConfirmed) {
	            //agregamos ajax
	            $.ajax({
	                type: "get",
	                url: contextPath+"/destinoEliminar?id="+value,
	                dataType: "json",
	                success: function (response) {
	                    //agregamos sweet alert
	                    Swal.fire({
	                        title: "Eliminado!",
	                        text: "El destino se elimino correctamente.",
	                        icon: "success",
	                        showConfirmButton: true,
	                      }).then(response =>{
	                        if (response.isConfirmed) {
	                          location.reload();
	                        }
	                      })
	                      //fin sweet alert

	                  
	                }
	            });
	            //fin ajax
	         
	        }
	      });
	}
	
	
$(document).ready(function () {
    cargarGrilla();
});


})