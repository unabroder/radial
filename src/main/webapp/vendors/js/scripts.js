// Empty JS for your own code to be here
var msg = document.getElementById("mensaje").value;
console.log(msg);
if (msg.length > 0) {
    switch (msg) {
        case "login":
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Usuario y/o clave incorrecto!',
            });
            break;
        case "existe":
            if (msg === "existe") {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'El usuario ya existe!',
                });
            } else if(msg === "noexiste"){
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'se registro exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
            break;
        case "si":
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
            break;
    }
}