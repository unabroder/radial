<%-- 
    Document   : validacion
    Created on : 11-29-2019, 03:41:01 PM
    Author     : roberto.alferesusam
--%>
<%
    HttpSession sesion = request.getSession();
    String nombre;
    if (sesion.getAttribute("usuario") != null) {
        nombre = sesion.getAttribute("usuario").toString();
    } else {
        response.sendRedirect("index.jsp");
    }
%>
