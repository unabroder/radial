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
                    <label class="h3 text-white">Programas</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Genero</th>
                                <th>Nombre</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Programa</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="pro">
                            <tr>
                                <td >${pro.idprograma}</td>
                                <td id="tele">${pro.idgenero.genero}</td>
                                <td id="tele">${pro.nombre}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${pro.idprograma}', '${pro.idgenero.idgenero}', '${pro.nombre}', '${pro.descripcion}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Programa?action=eliminar&id=', ${pro.idprograma})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Programa</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Programa?action=guardar" method="POST" class="form">

                        <label>Genero del programa</label>
                        <select name="idgenero" class="form-control">
                            <option>Seleccione un genero</option>
                            <c:forEach items="${genero}" var="gen">
                                <option value="${gen.idgenero}">${gen.genero}</option>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" class="form-control">
                        <label>Ingrese la descripcion</label>
                        <textarea class="form-control" name="descripcion"></textarea>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Programa</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Programa?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idprograma" id='id'>
                        <label>Genero del programa</label>
                        <select name="idgenero" class="form-control" id="idgenero">
                            <option>Seleccione un genero</option>
                            <c:forEach items="${genero}" var="gen">
                                <c:choose>
                                    <c:when test="${gen.idgenero == idgenero}">
                                        <option value="${gen.idgenero}" selected="true">${gen.genero}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${gen.idgenero}">${gen.genero}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" id="nombre" class="form-control">
                        <label>Ingrese la descripcion</label>
                        <textarea class="form-control" name="descripcion" id="desc"></textarea>
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
    function editar(id, idgenero, nombre, descripcion) {
        document.getElementById("id").value = id;
        document.getElementById("idgenero").value = idgenero;
        document.getElementById("nombre").value = nombre;
        document.getElementById("desc").value = descripcion;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>