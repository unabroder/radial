<%-- 
    Document   : radio
    Created on : 12-03-2019, 09:35:28 AM
    Author     : roberto.alferesusam
--%>




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
    <div class="row">
        <div class="col-12">
            <section class="bg-info fondo">
                <h1>Radio</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Productora</th>
                            <th>Frecuencia</th>
                            <th>Nombre</th>
                            <th colspan="2">Agregar</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lista}" var="ver">
                    <tr>
                        <td>${ver.idradio}</td>
                        <td>${ver.idproductora.numbre}</td>
                        <td>${ver.idfrecuencia.frecuencia}</td>
                        <td>${ver.nombre}</td>
                        <td>${ver.idradio}</td>
                        <td>${ver.idradio}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </div>
</div>
<jsp:include page="guest/footer.jsp"></jsp:include>