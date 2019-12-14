<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Radios SV</title>

        <meta name="description" content="Registra las radios de El Salvador">
        <meta name="author" content="raag">

        <link href="vendors/css/bootstrap.min.css" rel="stylesheet">
        <link href="vendors/css/style.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    </head>
    <body class="fondoinicio">

        <div class="container-fluid">
            <div class="row fila">
                <div class="col-lg-4">
                </div>
                <div class="col-lg-4">
                    <label class="form-control bg-primary text-white linea">
                        registrate
                    </label>
                    <form role="form" action="Usuario?action=guardar" method="POST">

                        <div class="form-group">
                            <label for="exampleInputEmail1">
                                Usuario
                            </label>
                            <input type="text" class="form-control" id="exampleInputEmail1" name="usuario" required="true">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">
                                Password
                            </label>
                            <input type="password" class="form-control" id="exampleInputPassword1" name="clave" required="true">
                        </div>
                 
                        <button type="submit" class="btn btn-primary pull-3 boton">
                            Registrar
                        </button>
                        <a href="index.jsp"><button type="button" class="btn btn-success text-white">
                                Regresar
                            </button></a>
                    </form>
                </div>
                <div class="col-lg-4">

                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12 col-lg-12 text-center">
                    <input type="hidden" id="mensaje" value="${msg}" >
                </div>
            </div>
        </div>

        <!--script src="vendors/js/jquery.min.js"></script>
        <script src="vendors/js/bootstrap.min.js"></script-->
        <script src="vendors/js/scripts.js"></script>

    </body>
</html>
