/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.ResumenDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ResumenBean;

/**
 *
 * @author roberto.alferesusam
 */
public class ResumenServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    ResumenDao rendao = new ResumenDao(conexion);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "consultar":
                consultar(request, response);
                break;
            case "consultarById":
                consultarById(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
        }
    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ResumenBean> lista = rendao.consultar();

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<ResumenBean> lista = rendao.consultarById(id);

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nota = request.getParameter("resumen");
        ResumenBean resumen = new ResumenBean(0);
        resumen.setNota(nota);

        res = rendao.guardar(resumen);
        if (res) {
            msg = "Resumen registrado";
        } else {
            msg = "Error al guardar";
        }
        request.setAttribute("msg", msg);
        List<ResumenBean> lista = rendao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);

    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idresumen = Integer.parseInt(request.getParameter("idresumen"));
        String nota = request.getParameter("resumen");
        ResumenBean resumen = new ResumenBean(idresumen);
        resumen.setNota(nota);

        res = rendao.actualizar(resumen);
        if (res) {
            msg = "Resumen actualizado";
        } else {
            msg = "Error al actualizar";
        }
        request.setAttribute("msg", msg);
        List<ResumenBean> lista = rendao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);

    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = rendao.eliminar(id);
        if (res) {
            msg = "Resumen eliminado";
        } else {
            msg = "Telefono no eliminado";
        }
        request.setAttribute("msg", msg);
        List<ResumenBean> lista = rendao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
