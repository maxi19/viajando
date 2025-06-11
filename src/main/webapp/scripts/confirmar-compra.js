$(function() {

	$(document).ready(function () {
		$('.addres-information').hide();
		$.ajax({
			type: "post",
			url: contextPath + "/preventaData",
			data: "data",
			dataType: "json",
			success: function (response) {
				populateTable(response.items);
			}
		});





	});




	function populateTable(data){
				
			$("#tableProduct tbody").find("tr:gt(0)").remove(); 

			$.each(data, function(i, v) {

			  if (i == 0) {
			    setDataOnRow($("#tableProduct tbody").find("tr").first(), v);

			  } else {

			    var clonnedRow = $($("#tableProduct tbody").find("tr").first()).clone();
			    setDataOnRow(clonnedRow, v);

			    $("#tableProduct tbody").append(clonnedRow);

			  }
			});
				
		};
		
	function setDataOnRow(rowObject, v) {
	
		  var cantidad = v.cantidad;
		  var nombre = v.producto.nombre;
		  var precio = v.producto.precio;

		  
		  $(rowObject).find(".cantidad").html(cantidad);
		  $(rowObject).find(".nombre").html(nombre);
		  $(rowObject).find(".precio").html(precio);
		  $(rowObject).find(".detalles").append( 
			$('<input/>').attr({
			                type: "button",
							class :"btn btn-primary",
			                value: "Editar",
			                onclick: ""
			            })
		    );

 			$(rowObject).find(".detalles").append( 
			 $('<input/>').attr({
			                type: "button",
							class :"btn btn-danger",
			                value: "Eliminar",
			                onclick: ""
			            })
		    );
			
		
		}
	function agregarBotoSiguiente(){

	}



	$('#btn-confirmar').click(function (e) { 
	    $('.producto-section').fadeOut(500);
					    setTimeout(() => {
					        $('.addres-information').show(500).fadeIn();
					    }, 500);	
	});


	$('#btn-confirmar-compra').click(function (e) {
			$.ajax({
				type: "get",
				url: contextPath + "/createAndRedirect",
				data: "data",
				dataType: "json",
				success: function (response) {
					console.log(response)
						var link=$("<a />",{
							"href":response.url,
							"text":"hola mundo",
							"class" : "btn btn-warning"
						});
						$('.pago-section').append(link);
				}
			});

		
			
		    $('.addres-information').fadeOut(500);
					    setTimeout(() => {
						$('.pago-section').show(500).fadeIn();

					    }, 500);	
		
	});



	$(document).ready(function(){
	    $.ajax(
			{ url: contextPath + '/ventas',
	        	 dataType: 'JSON',
	        	success: function(data){
				
	        	}
	    });
			
	});
	
	
})