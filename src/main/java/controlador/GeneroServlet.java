/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.GeneroDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GeneroBean;

/**
 *
 * @author roberto.alferesusam
 */
public class GeneroServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    GeneroDao gendao = new GeneroDao(conexion);

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
        List<GeneroBean> lista = gendao.consultar();

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("genero.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<GeneroBean> lista = gendao.consultarById(id);

        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("genero.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String genero = request.getParameter("genero");
        GeneroBean gen = new GeneroBean(0);
        gen.setGenero(genero);
        res = gendao.validar(gen);
        if (res) {
            msg = "Ya existe el genero";
            request.setAttribute("msg", msg);
            List<GeneroBean> lista = gendao.consultar();

            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("genero.jsp");
            rd.forward(request, response);
        } else {
            res = gendao.guardar(gen);
            if (res) {
                msg = "genero registrado";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<GeneroBean> lista = gendao.consultar();
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("genero.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idgenero = Integer.parseInt(request.getParameter("idgenero"));
        String genero = request.getParameter("genero");
        GeneroBean gen = new GeneroBean(idgenero);
        gen.setGenero(genero);
        res = gendao.validar(gen);
        if (res) {
            msg = "Ya existe el genero";
            request.setAttribute("msg", msg);
            List<GeneroBean> lista = gendao.consultar();

            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("genero.jsp");
            rd.forward(request, response);
        } else {
            res = gendao.guardar(gen);
            if (res) {
                msg = "genero actualizado";
            } else {
                msg = "Error al actualizar";
            }
            request.setAttribute("msg", msg);
            List<GeneroBean> lista = gendao.consultar();
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("genero.jsp");
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = gendao.eliminar(id);
        if (res) {
            msg = "genero eliminado";
        } else {
            msg = "genero no eliminado";
        }
        request.setAttribute("msg", msg);
        List<GeneroBean> lista = gendao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("genero.jsp");
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
