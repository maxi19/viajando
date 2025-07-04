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