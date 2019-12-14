<style>
    .fondo-menu{
        background: #833ab4;  /* fallback for old browsers */
        background: -webkit-linear-gradient(to right, #fcb045, #fd1d1d, #833ab4);  /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to right, #fcb045, #fd1d1d, #833ab4); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    }
</style>
<div class="row">
    <div class="col-12 fondo-menu">
        <nav class="nav nav-pills nav-justified pt-3 pb-2 pl-2 mt-3 ">
            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle text-white text-weight-bold" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Productoras</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="Radio?action=consultar">Radios</a>
                    <a class="dropdown-item" href="Telefono?action=consultar">Telefonos</a>
                    <a class="dropdown-item" href="Productora?action=consultar">Productoras</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="Frecuencia?action=consultar">Frecuencia</a>
                </div>
            </li>
       
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white text-weight-bold" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Programas</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="Genero?action=consultar">Genero</a>
                    <a class="dropdown-item" href="Programa?action=consultar">Programas</a>
                    <a class="dropdown-item" href="Emision?action=consultar">Emision</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="Encuesta?action=consultar">Encuesta</a>
                </div>
            </li>
            <button class="btn btn-warning mr-3 rounded"><a class="nav-item nav-link  text-white" href="Login?action=logout"><i class="fas fa-user-lock"></i></a></button>
        </nav>
    </div>
</div>
