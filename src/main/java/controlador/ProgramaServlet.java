/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.GeneroDao;
import dao.ProgramaDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GeneroBean;
import modelo.ProgramaBean;

/**
 *
 * @author PEDRINSKY
 */
public class ProgramaServlet extends HttpServlet {

    RequestDispatcher rd;
    String msg;
    boolean res;
    Conexion conexion = new Conexion();
    GeneroDao gendao = new GeneroDao(conexion);
    ProgramaDao prodao = new ProgramaDao(conexion);

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

        List<GeneroBean> genero = gendao.consultar();
        List<ProgramaBean> lista = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("genero", genero);
        rd = request.getRequestDispatcher("progrmas.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<GeneroBean> genero = gendao.consultar();
        List<ProgramaBean> lista = prodao.consultarById(id);
        request.setAttribute("lista", lista);
        request.setAttribute("genero", genero);
        rd = request.getRequestDispatcher("programas.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idgenero = Integer.parseInt(request.getParameter("idgenero"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        ProgramaBean pro = new ProgramaBean(0);
        GeneroBean gen = new GeneroBean(idgenero);
        pro.setIdgenero(gen);
        pro.setNombre(nombre);
        pro.setDescripcion(descripcion);
        res = prodao.validar(pro);
        if (res) {
            msg = "Ya existe el programa";
            request.setAttribute("msg", msg);
            List<GeneroBean> genero = gendao.consultar();
            List<ProgramaBean> lista = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("genero", genero);
            rd = request.getRequestDispatcher("progrmas.jsp");
            rd.forward(request, response);
        } else {
            res = gendao.guardar(gen);
            if (res) {
                msg = "programa registrado";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<GeneroBean> genero = gendao.consultar();
            List<ProgramaBean> lista = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("genero", genero);
            rd = request.getRequestDispatcher("progrmas.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idprograma = Integer.parseInt(request.getParameter("idprograma"));
        int idgenero = Integer.parseInt(request.getParameter("idgenero"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        ProgramaBean pro = new ProgramaBean(idprograma);
        GeneroBean gen = new GeneroBean(idgenero);
        pro.setIdgenero(gen);
        pro.setNombre(nombre);
        pro.setDescripcion(descripcion);
        res = prodao.validar(pro);
        if (res) {
            msg = "Ya existe el programa";
            request.setAttribute("msg", msg);
            List<GeneroBean> genero = gendao.consultar();
            List<ProgramaBean> lista = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("genero", genero);
            rd = request.getRequestDispatcher("progrmas.jsp");
            rd.forward(request, response);
        } else {
            res = gendao.guardar(gen);
            if (res) {
                msg = "programa actualizado";
            } else {
                msg = "Error al actualizar";
            }
            request.setAttribute("msg", msg);
            List<GeneroBean> genero = gendao.consultar();
            List<ProgramaBean> lista = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("genero", genero);
            rd = request.getRequestDispatcher("progrmas.jsp");
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = prodao.eliminar(id);
        if (res) {
            msg = "programa eliminado";
        } else {
            msg = "programa no eliminado";
        }
        request.setAttribute("msg", msg);
        List<GeneroBean> genero = gendao.consultar();
        List<ProgramaBean> lista = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("genero", genero);
        rd = request.getRequestDispatcher("progrmas.jsp");
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
