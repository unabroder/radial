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
                    <label class="h3 text-white">Encuesta</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Total</th>
                                <th>Aprobacion</th>
                                <th>Rechazo</th>
                                <th>Indiferencia</th>
                                <th>Emision</th>
                                <th>Programa</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Encuesta</button></th>
                            </tr>
                        </thead>
                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="enc">
                            <tr>
                                <td >${enc.idencuesta}</td>
                                <td >${enc.total}</td>
                                <td >${enc.aprobacion}</td>
                                <td >${enc.rechazo}</td>
                                <td >${enc.indiferencia}</td>
                                <td > ${enc.idemision.fecha}</td>
                                <td > ${enc.idprograma.nombre}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${enc.idencuesta}', '${enc.idemision.idemision}', '${enc.idprograma.idprograma}', '${enc.total}', '${enc.aprobacion}', '${enc.rechazo}', '${enc.indiferencia}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Encuesta?action=eliminar&id=', ${enc.idencuesta})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Encuesta</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Encuesta?action=guardar" method="POST" class="form">
                        <label>Fecha de la emision</label>
                        <select name="idemision" class="form-control">
                            <option>Seleccione un fecha de emision</option>
                            <c:forEach items="${listaemi}" var="emi">
                                <option value="${emi.idemision}">${emi.fecha  }</option>
                            </c:forEach>
                        </select>
                        <label>Programa</label>
                        <select name="idprograma" class="form-control">
                            <option>Seleccione un programa</option>
                            <c:forEach items="${listapro}" var="pro">
                                <option value="${pro.idprograma}">${pro.nombre}</option>
                            </c:forEach>
                        </select>
                        <label>Total de encuestados</label>
                        <input name="total" required="true" type="number" class="form-control">
                        <label>Cantidad de Aprobaciones</label>
                        <input type="number" name="aprobacion" class="form-control">
                        <label>Cantidad de Rechazos</label>
                        <input type="number" name="rechazo" class="form-control">
                        <label>Cantidad de indiferencias</label>
                        <input type="number" name="indiferencia" class="form-control">    
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Encuesta</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Encuesta?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idencuesta" id='id'>
                        <label>Fecha de la emision</label>
                        <select name="idemision" class="form-control" id="idemision">
                            <option>Seleccione un fecha de emision</option>
                            <c:forEach items="${listaemi}" var="emi">
                                <c:choose>
                                    <c:when test="${emi.idemision == idemision}">
                                        <option value="${emi.idemision}" selected="true">${emi.fecha  }</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${emi.idemision}">${emi.fecha  }</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Programa</label>
                        <select name="idprograma" class="form-control" id="idprograma">
                            <option>Seleccione un programa</option>
                            <c:forEach items="${listapro}" var="pro">
                                <c:choose>
                                    <c:when test="${pro.idprograma == idprograma}">
                                        <option value="${pro.idprograma}" selected="true">${pro.nombre}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${pro.idprograma}">${pro.nombre}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Total de encuestados</label>
                        <input name="total" required="true" type="number" class="form-control" id="total">
                        <label>Cantidad de Aprobaciones</label>
                        <input type="number" name="aprobacion" class="form-control" id="aprobacion">
                        <label>Cantidad de Rechazos</label>
                        <input type="number" name="rechazo" class="form-control" id="rechazo">
                        <label>Cantidad de indiferencias</label>
                        <input type="number" name="indiferencia" class="form-control" id="indiferencia"> 
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
    function editar(id, idemision, idprograma, total, aprobacion, rechazo, indiferencia) {
        document.getElementById("id").value = id;
        document.getElementById("idemision").value = idemision;
        document.getElementById("idprograma").value = idprograma;
        document.getElementById("total").value = total;
        document.getElementById("aprobacion").value = aprobacion;
        document.getElementById("rechazo").value = rechazo;
        document.getElementById("indiferencia").value = indiferencia;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>