<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
    HttpSession sesion = request.getSession();
    String nombre;
    int tipo;
    if (sesion.getAttribute("usuario") != null) {
        nombre = sesion.getAttribute("usuario").toString();
    } else {
        response.sendRedirect("/index.jsp");
    }
%>
<jsp:include page="guest/head.jsp"></jsp:include>
<jsp:include page="guest/nav.jsp"></jsp:include>
    <section>
        <div class="container-fluid">
            <div class="row text-center">

                <div class="col-12 mt-3">
                    <label class="h3 text-white">Compañias Productoras</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Productora</th>
                                <th>RFC</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Productora</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="pro">
                            <tr>
                                <td >${pro.idproductora}</td>
                                <td id="tele">${pro.numbre}</td>
                                <td id="tele">${pro.rfc}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${pro.idproductora}', '${pro.numbre}', '${pro.rfc}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Productora?action=eliminar&id=', ${pro.idproductora})"><a><i class="fas fa-trash-alt"></i></a></button></td>
                            </tr>
                        </c:forEach>   
                    </tbody>
                </table>
            </div>
            <div class="col">
                <div class="alert alert-success" role="alert">
                    ${msg}
                </div>            
            </div>
        </div> 
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Productora</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Productora?action=guardar" method="POST" class="form">
                        <label>Productora</label>
                        <input name="nombre" required="true" type="text" class="form-control">
                        <label>Numero de Registro Unico</label>
                        <input name="rfc" required="true" type="text" class="form-control">
                        <button type="submit" class="btn btn-primary mt-2 ">Guardar</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Productora</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Productora?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idproductora" id="id">
                        <label>Productora</label>
                        <input name="nombre" required="true" id="nombre" type="text" class="form-control">
                        <label>Numero de Registro Unico</label>
                        <input name="rfc" required="true" type="text"  id="rfc" class="form-control">
                        <button type="submit" class="btn btn-primary mt-2 ">Actualizar</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function editar(id, nombre, rfc) {
        document.getElementById("id").value = id;
        document.getElementById("nombre").value = nombre;
        document.getElementById("rfc").value = rfc;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>
