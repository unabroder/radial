<%-- 
    Document   : admin
    Created on : 11-28-2019, 01:44:11 PM
    Author     : roberto.alferesusam
--%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="guest/head.jsp"></jsp:include>
    <section class="bg-info fondo">

    <jsp:include page="guest/nav.jsp"></jsp:include>

        <h1 class="text-center">Radios</h1>
    </section>

<jsp:include page="guest/footer.jsp"></jsp:include>
