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
                    <label class="h3 text-white">Radios</label>
                    <hr class="my-2 bg-warning">
                    <table class="table table-hover border-bottom" >
                        <thead class="bg-warning">
                            <tr>
                                <th>#</th>
                                <th>Productora</th>
                                <th>Frecuencia</th>
                                <th>Radio</th>
                                <th colspan="2"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Agregar Radio</button></th>
                            </tr>
                        </thead>

                        <tbody class="bg-success">
                        <c:forEach items="${lista}" var="radio">
                            <tr>
                                <td >${radio.idradio}</td>
                                <td >${radio.idproductora.numbre}</td>
                                <td id="tele">${radio.idfrecuencia.frecuencia}&nbsp; ${radio.idfrecuencia.tipo} </td>
                                <td id="tele">${radio.nombre}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#update" onclick="editar('${radio.idradio}','${radio.idproductora.idproductora}', '${radio.idfrecuencia.idfrecuencia}', '${radio.nombre}')"><i class="fas fa-pencil-alt"></i></button>
                                </td>
                                <td><button class="btn btn-danger" onclick="alerta_eliminar('Radio?action=eliminar&id=', ${radio.idradio})"><a><i class="fas fa-trash-alt"></i></a></button></td>
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Agregar Radio</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Radio?action=guardar" method="POST" class="form">
                        <label>Compañia Productora</label>
                        <select name="idproductora" class="form-control">
                            <option>Seleccione una productora</option>
                            <c:forEach items="${productora}" var="pro">
                                <option value="${pro.idproductora}">${pro.numbre}</option>
                            </c:forEach>
                        </select>
                        <label>Seleccione una frecuencia</label>
                        <select name="idfrecuencia" class="form-control">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${frec}" var="fre">
                                <option value="${fre.idfrecuencia}">${fre.frecuencia}&nbsp;${fre.tipo}</option>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" class="form-control">
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Actualizar Frecuencia</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Radio?action=actualizar" method="POST" class="form">
                        <input type="hidden" name="idradio" id='id'>
                        <label>Compañia Productora</label>
                        <select name="idproductora" class="form-control" id="idproductora">
                            <option>Seleccione una productora</option>
                            <c:forEach items="${productora}" var="pro">
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
                        <label>Seleccione una frecuencia</label>
                        <select name="idfrecuencia" class="form-control" id="idfrecuencia">
                            <option>Seleccione un rol</option>
                            <c:forEach items="${frec}" var="fre">
                                <c:choose>
                                    <c:when test="${fre.idfrecuencia == idfrecuencia}">
                                        <option value="${fre.idfrecuencia}" selected="true">${fre.frecuencia}&nbsp;${fre.tipo}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${fre.idfrecuencia}">${fre.frecuencia}&nbsp;${fre.tipo}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <label>Nombre</label>
                        <input name="nombre" required="true" type="text" class="form-control" id="nombre">
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
    function editar(id, idproductora, idfrecuencia, nombre) {
        document.getElementById("id").value = id;
        document.getElementById("idproductora").value = idproductora;
        document.getElementById("idfrecuencia").value = idfrecuencia;
        document.getElementById("nombre").value = nombre;
    }
</script>
<script src="vendors/js/funciondelete.js"></script> 
<jsp:include page="guest/footer.jsp"></jsp:include>