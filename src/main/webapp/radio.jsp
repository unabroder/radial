<%-- 
    Document   : radio
    Created on : 12-03-2019, 09:35:28 AM
    Author     : roberto.alferesusam
--%>




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

    <section class="bg-info fondo">
    <jsp:include page="guest/nav.jsp"></jsp:include>
        <h1>Radio</h1>
    </section>

<jsp:include page="guest/footer.jsp"></jsp:include>