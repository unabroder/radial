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
                    <label class="h3 text-white">Emision</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Programa</th>
                                <th>Fecha</th>
                                <th>Hora Inicio</th>
                                <th>Duracion</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Emision</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="emi">
                            <tr>
                                <td >${emi.idemision}</td>
                                <td id="tele">${emi.idprograma.nombre}</td>
                                <td id="tele">${emi.fecha}</td>
                                <td id="tele">${emi.horainicio}</td>
                                <td id="tele">${emi.duracion}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${emi.idemision}', '${emi.idprograma.idprograma}', '${emi.fecha}', '${emi.horainicio}', '${emi.duracion}', '${emi.repeticion}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Emision?action=eliminar&id=', ${emi.idemision})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Emison</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Emision?action=guardar" method="POST" class="form">
                        <label>Programa</label>
                        <select name="idprograma" class="form-control">
                            <option>Seleccione un programa</option>
                            <c:forEach items="${programa}" var="pro">
                                <option value="${pro.idprograma}">${pro.nombre}</option>
                            </c:forEach>
                        </select>
                        <label>Fecha</label>
                        <input name="fecha" required="true" type="date" class="form-control">
                        <label>Hora Inicio</label>
                        <input type="time" name="horainicio" class="form-control">
                        <label>Duracion</label>
                        <input type="time" name="duracion" class="form-control">
                        <label>Repeticion</label>
                        <select name="repeticion" class="form-control">
                            <option>Seleccione una opcion</option>
                            <option>SI</option>
                            <option>NO</option>
                        </select>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Emision</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Emision?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idemision" id='id'>
                        <label>Programa</label>
                        <select name="idprograma" class="form-control" id="idprograma">
                            <option>Seleccione un programa</option>
                            <c:forEach items="${programa}" var="pro">
                                <option value="${pro.idprograma}">${pro.nombre}</option>
                            </c:forEach>
                        </select>
                        <label>Fecha</label>
                        <input name="fecha" required="true" type="date" class="form-control" id="fecha">
                        <label>Hora Inicio</label>
                        <input type="time" name="horainicio" class="form-control" id="hora">
                        <label>Duracion</label>
                        <input type="time" name="duracion" class="form-control" id="duracion">
                        <label>Repeticion</label>
                        <select name="repeticion" class="form-control" id="repeticion">
                            <option>Seleccione una opcion</option>
                            <option>SI</option>
                            <option>NO</option>
                        </select>
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
    function editar(id, idprograma, fecha, hora, duracion, repeticion) {
        document.getElementById("id").value = id;
        document.getElementById("idprograma").value = idprograma;
        document.getElementById("fecha").value = fecha;
        document.getElementById("hora").value = hora;
        document.getElementById("duracion").value = duracion;
        document.getElementById("repeticion").value = repeticion;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>
