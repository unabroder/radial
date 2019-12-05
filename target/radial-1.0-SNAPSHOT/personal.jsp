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
                    <label class="h3 text-white">Usuarios</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Tipo</th>
                                <th>Usuario</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Usuario</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="usu">
                            <tr>
                                <td >${usu.idusuario}</td>
                                <td id="tele">${usu.idtipo.tipo}</td>
                                <td id="tele">${usu.usuario}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${usu.idusuario}', '${usu.idtipo.idtipo}', '${usu.usuario}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Usuario?action=eliminar&id=', ${usu.idusuario})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Usuario</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Usuario?action=insert" method="POST" class="form">

                        <label>Rol del usuario</label>
                        <select name="idtipo" class="form-control">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${tipo}" var="tipo">
                                <option value="${tipo.idtipo}">${tipo.tipo}</option>
                            </c:forEach>
                        </select>
                        <label>Usuario</label>
                        <input name="usuario" required="true" type="text" class="form-control">
                        <label>Ingrese la clave</label>
                        <input name="clave" required="true" type="password" class="form-control">
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Usuario</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Usuario?action=update" method="POST" class="form">
                        <input type="hidden" name="idusuario" id='id'>
                        <label>Rol del usuario</label>
                        <select name="idtipo" class="form-control" id="idtipo">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${tipo}" var="tipo">
                                <c:choose>
                                    <c:when test="${tipo.idtipo == id}">
                                        <option value="${tipo.idtipo}">${tipo.tipo}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tipo.idtipo}">${tipo.tipo}</option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                        <label>Usuario</label>
                        <input name="usuario" required="true" type="text" class="form-control" id="usuario">
                        <label>Ingrese la clave</label>
                        <input name="clave" required="true" type="password" class="form-control" >
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
    function editar(id, idtipo, usuario) {
        document.getElementById("id").value = id;
        document.getElementById("idtipo").value = idtipo;
        document.getElementById("usuario").value = usuario;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>
