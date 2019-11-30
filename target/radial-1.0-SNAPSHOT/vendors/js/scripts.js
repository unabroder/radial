// Empty JS for your own code to be here
var msg = document.getElementById("mensaje").value;
if (msg.length > 0) {
    if (msg === "existe") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El usuario ya existe!',
        });
    } else {
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'se registro exitosamente',
            showConfirmButton: false,
            timer: 1500
        });
    }

    if (msg === 'si') {
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Se modifico la clave',
            showConfirmButton: false,
            timer: 1500
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'No se modifico la clave!',
        });
    }

    if (msg === "login") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Usuario y/o clave incorrecto!',
        });
    }
}
