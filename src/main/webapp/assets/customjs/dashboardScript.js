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
      $(rowObject).find(".operar").html(
        $("<button>").attr({
          "class" :"btn btn-danger"
        }).text("Eliminar")
      );

    }


$(document).ready(function () {
    cargarGrilla();
});


})