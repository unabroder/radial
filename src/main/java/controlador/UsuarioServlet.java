/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import conexion.Hash;
import dao.TipoDao;
import dao.UsuarioDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoUsuario;
import modelo.UsuarioBean;

/**
 *
 * @author roberto.alferesusam
 */
public class UsuarioServlet extends HttpServlet {

    String msg;
    boolean res;
    RequestDispatcher rd;
    Conexion conexion = new Conexion();
    UsuarioDao usuDao = new UsuarioDao(conexion);
    TipoDao tipoD = new TipoDao(conexion);

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
            case "guardar":
                guardar(request, response);
                break;
            case "restablecer":
                restablecer(request, response);
                break;
        }
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idtipo = Integer.parseInt(request.getParameter("idtipo"));
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        Hash hash = new Hash();
        String pass = hash.convertirSHA256(clave);
        UsuarioBean usu = new UsuarioBean(0);
        TipoUsuario tp = new TipoUsuario(idtipo);
        usu.setIdtipo(tp);
        usu.setUsuario(usuario);
        usu.setClave(pass);

        res = usuDao.validar(usu);
        if (res) {
            msg = "existe";
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipoD.consultar();
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("RegistrarUsuario.jsp");
            rd.forward(request, response);
        } else {
            res = usuDao.guardar(usu);
            if (res) {
                msg = "noexiste";
            } else {
                msg = "existe";
            }
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        int idtipo = Integer.parseInt(request.getParameter("idtipo"));
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        Hash hash = new Hash();
        String pass = hash.convertirSHA256(clave);
        UsuarioBean usu = new UsuarioBean(idusuario);
        TipoUsuario tp = new TipoUsuario(idtipo);
        usu.setIdtipo(tp);
        usu.setUsuario(usuario);
        usu.setClave(pass);

        res = usuDao.validar(usu);
        if (res) {
            msg = "existe";
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipoD.consultar();
            request.setAttribute("lista", lista);
            //rd = request.getRequestDispatcher("RegistrarUsuario.jsp");
            rd.forward(request, response);
        } else {
            res = usuDao.guardar(usu);
            if (res) {
                msg = "noexiste";
            } else {
                msg = "existe";
            }
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        res = usuDao.eliminar(id);
        if (res) {
            msg = "Usuario Eliminado";
            request.setAttribute("msg", msg);
            List<TipoUsuario> lista = tipoD.consultar();
            request.setAttribute("lista", lista);
            //rd = request.getRequestDispatcher("RegistrarUsuario.jsp");
            rd.forward(request, response);
        } else {
            msg = "Usuario no eliminado";
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UsuarioBean> lista = usuDao.consultar();
        List<TipoUsuario> tipo = tipoD.consultar();

    }

    protected void restablecer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        UsuarioBean usu = new UsuarioBean(0);
        usu.setUsuario(usuario);
        Hash hash = new Hash();
        String pass = hash.convertirSHA256(clave);
        usu.setClave(pass);

        res = usuDao.restablecer(usu);
        if (res) {
            msg = "si";
            request.setAttribute("msg", msg);

            rd = request.getRequestDispatcher("recuperarclave.jsp");
            rd.forward(request, response);
        } else {
            msg = "no";
            request.setAttribute("msg", msg);

            rd = request.getRequestDispatcher("recuperarclave.jsp");
            rd.forward(request, response);
        }
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
