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
                    <label class="h3 text-white">Personal</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Productora</th>
                                <th>Cargo</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Personal</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="per">
                            <tr>
                                <td >${per.idpersonal}</td>
                                <td id="tele">${per.idproductora.numbre}</td>
                                <td id="tele">${per.idcargo.cargo}</td>
                                <td >${per.nombre}</td>
                                <td >${per.apellido}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${per.idpersonal}', '${per.idproductora.idproductora}', '${per.idcargo.idcargo}', '${per.nombre}', '${per.apellido}', '${per.dui}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Personal?action=eliminar&id=', ${per.idpersonal})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Personal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Personal?action=guardar" method="POST" class="form">
                        <label>Compañia Productora</label>
                        <select name="idproductora" class="form-control">
                            <option>Seleccione una productora</option>
                            <c:forEach items="${listapro}" var="pro">
                                <option value="${pro.idproductora}">${pro.numbre}</option>
                            </c:forEach>
                        </select>
                        <label>Seleccione el cargo</label>
                        <select name="idcargo" class="form-control">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${listacargo}" var="cargo">
                                <option value="${cargo.idcargo}">${cargo.cargo}</option>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" class="form-control">
                        <label>Apellido</label>
                        <input name="apellido" required="true" type="text" class="form-control">
                        <label>Numero unico de identificación</label>
                        <input name="dui" required="true" type="text" class="form-control">
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Personal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Personal?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idpersonal" id='id'>
                        <label>Compañia Productora</label>
                        <select name="idproductora" class="form-control" id="idproductora">
                            <option>Seleccione una productora</option>
                            <c:forEach items="${listapro}" var="pro">
                                <c:choose>
                                    <c:when test="${pro.idproductora == idproductora}">
                                        <option value="${pro.idproductora}" selected="true">${pro.numbre}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${pro.idproductora}">${pro.numbre}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Seleccione el cargo</label>
                        <select name="idcargo" class="form-control" id="idcargo">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${listacargo}" var="cargo">
                                <c:choose>
                                    <c:when test="${cargo.idcargo == idcargo}">
                                        <option value="${cargo.idcargo}">${cargo.cargo}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${cargo.idcargo}">${cargo.cargo}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" class="form-control" id="nombre">
                        <label>Apellido</label>
                        <input name="apellido" required="true" type="text" class="form-control" id="apellido">
                        <label>Numero unico de identificación</label>
                        <input name="dui" required="true" type="text" class="form-control" id="dui">
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
    function editar(id, idproductora, idcargo, nombre, apellido, dui) {
        document.getElementById("id").value = id;
        document.getElementById("idproductora").value = idproductora;
        document.getElementById("idcargo").value = idcargo;
        document.getElementById("nombre").value = nombre;
        document.getElementById("apellido").value = apellido;
        document.getElementById("dui").value = dui;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>
