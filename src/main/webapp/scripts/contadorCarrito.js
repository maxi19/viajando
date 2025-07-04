function actualizarContadorCarrito() {
	$.ajax({
		url: contextPath + "/carritoListado",
		method: "GET",
		dataType: "json",
		success: function (data) {
			const total = data.length;
			const $contador = $("#carrito-count");

			if (total > 0) {
				$contador.text(total);
				$contador.removeClass("d-none");
			} else {
				$contador.addClass("d-none");
			}
		},
		error: function () {
			console.error("No se pudo actualizar el contador del carrito");
		}
	});
}

$(document).ready(function () {
	actualizarContadorCarrito();
});