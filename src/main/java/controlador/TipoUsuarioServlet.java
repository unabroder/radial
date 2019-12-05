/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.TipoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoUsuario;

/**
 *
 * @author roberto.alferesusam
 */
public class TipoUsuarioServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    TipoDao tipodao = new TipoDao(conexion);

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
        List<TipoUsuario> lista = tipodao.consultar();

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("tipousuario.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<TipoUsuario> lista = tipodao.consultarById(id);

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("tipousuario.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipousuario = request.getParameter("tipousuario");
        TipoUsuario tp = new TipoUsuario(0);
        tp.setTipo(tipousuario);
        res = tipodao.validar(tp);
        if (res) {
            msg = "Ya existe el tipousuario";
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipodao.consultar();

            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("tipousuario.jsp");
            rd.forward(request, response);
        } else {
            res = tipodao.guardar(tp);
            if (res) {
                msg = "Tipo de usuario registrado";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipodao.consultar();
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("tipousuario.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idtipousuario = Integer.parseInt(request.getParameter("idtipo"));
        String tipousuario = request.getParameter("tipousuario");
        TipoUsuario tp = new TipoUsuario(idtipousuario);
        tp.setTipo(tipousuario);
        res = tipodao.validar(tp);
        if (res) {
            msg = "Ya existe el tipousuario";
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipodao.consultar();

            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("tipousuario.jsp");
            rd.forward(request, response);
        } else {
            res = tipodao.actualizar(tp);
            if (res) {
                msg = "Tipo de usuario actualizado";
            } else {
                msg = "Error al actualizar";
            }
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipodao.consultar();
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("tipousuario.jsp");
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = tipodao.eliminar(id);
        if (res) {
            msg = "Tipo de usuario eliminado";
        } else {
            msg = "Tipo de usuario no eliminado";
        }
        request.setAttribute("msg", msg);
        List<TipoUsuario> lista = tipodao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("tipousuario.jsp");
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
