function alerta_eliminar(action, id) {
    Swal.fire({
        title: 'Esta seguro que desea eliminar?',
        text: "Esta accion no se podra deshacer!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
        if (result.value) {
            window.location.href = action + id;
        }
    });
}

