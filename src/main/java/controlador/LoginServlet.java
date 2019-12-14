/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import conexion.Hash;
import dao.LoginDao;
import dao.TipoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Login;
import modelo.TipoUsuario;

/**
 *
 * @author roberto.alferesusam
 */
public class LoginServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    LoginDao logD = new LoginDao(conexion);
    TipoDao tipoDao = new TipoDao(conexion);

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
            case "acceso":
                acceso(request, response);
                break;
            case "registrar":
                registrate(request, response);
                break;
            case "logout":
                salir(request, response);
                break;
        }
    }

    protected void acceso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        Login log = new Login(0);
        Hash hash = new Hash();
        String pass = hash.convertirSHA256(clave);
        log.setUsuario(usuario);
        log.setClave(pass);

        res = logD.login(log);
        if (res) {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuario", log.getUsuario());
            System.out.println(log.getUsuario());
            response.sendRedirect("admin.jsp");
        } else {
            msg = "login";
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

    }

    protected void registrate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TipoUsuario> lista = tipoDao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("RegistrarUsuario.jsp");
        rd.forward(request, response);
    }

    protected void salir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("index.jsp");
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
