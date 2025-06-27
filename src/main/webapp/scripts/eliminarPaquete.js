function myFunction(data) {
    const value = $(data).attr("data-id");

    // SweetAlert para confirmar eliminación
    Swal.fire({
        title: "¿Está seguro de eliminar el paquete?",
        text: "El paquete será eliminado de la base de datos.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {
            // AJAX para eliminar
            $.ajax({
                type: "GET",
                url: contextPath + "/PaqueteEliminar?id=" + value,
                dataType: "json",
                success: function (response) {
                    // Éxito: alerta y recarga
                    Swal.fire({
                        title: "¡Eliminado!",
                        text: "El paquete se eliminó correctamente.",
                        icon: "success",
                        showConfirmButton: true
                    }).then(response => {
                        if (response.isConfirmed) {
                            location.reload();
                        }
                    });
                },
                error: function (xhr) {
                    Swal.fire({
                        title: "Error",
                        text: "Ocurrió un error al eliminar el paquete.",
                        icon: "error"
                    });
                }
            });
        }
    });
}
