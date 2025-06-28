function eliminarHotel(data) {
    const value = $(data).attr("data-id");

    Swal.fire({
        title: "¿Estás seguro de eliminar el hotel?",
        text: "¡El hotel será eliminado de la base de datos!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "get",
                url: contextPath + "/HotelEliminar?id=" + value,
                dataType: "json",
                success: function (response) {
                    Swal.fire({
                        title: "¡Eliminado!",
                        text: response.mensaje,
                        icon: "success",
                        showConfirmButton: true,
                    }).then(resp => {
                        if (resp.isConfirmed) {
                            location.reload();
                        }
                    });
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        title: "Error",
                        text: "No se pudo eliminar el hotel",
                        icon: "error"
                    });
                }
            });
        }
    });
}