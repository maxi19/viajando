const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

function eliminarVuelo(data) {
    const idVuelo = $(data).attr("data-id");

    Swal.fire({
        title: "¿Estás seguro?",
        text: "¡El vuelo será eliminado de la base de datos!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "GET",
                url: contextPath + "/eliminarVuelo?id=" + idVuelo,
                success: function () {
                    Swal.fire({
                        title: "Eliminado",
                        text: "El vuelo fue eliminado correctamente.",
                        icon: "success"
                    }).then(() => {
                        location.reload();
                    });
                },
                error: function () {
                    Swal.fire("Error", "No se pudo eliminar el vuelo.", "error");
                }
            });
        }
    });
}