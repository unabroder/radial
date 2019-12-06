/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.FrecuenciaDao;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.FrecuenciaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class FrecuenciaServlet extends HttpServlet {

    RequestDispatcher rd;
    String msg;
    boolean res;
    Conexion conexion = new Conexion();
    FrecuenciaDao fredao = new FrecuenciaDao(conexion);

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
            throws ServletException, IOException, ParseException {
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
        List<FrecuenciaBean> lista = fredao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("frecuencia.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<FrecuenciaBean> lista = fredao.consultarById(id);
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("frecuencia.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Double frecuencia = Double.parseDouble(request.getParameter("frecuencia"));
        String tipo = request.getParameter("tipo");
        FrecuenciaBean fre = new FrecuenciaBean(0);
        fre.setFrecuencia(frecuencia);
        fre.setTipo(tipo);
        res = fredao.validar(fre);
        if (res) {
            msg = "la frecuencia ya esta registrada";
            List<FrecuenciaBean> lista = fredao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("frecuencia.jsp");
            rd.forward(request, response);
        } else {
            res = fredao.guardar(fre);
            if (res) {
                msg = "frecuencia registrada";
            } else {
                msg = "frecuencia no registrada";
            }

            List<FrecuenciaBean> lista = fredao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("frecuencia.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int idfrecuencia = Integer.parseInt(request.getParameter("idfrecuencia"));
        Double frecuencia = Double.parseDouble(request.getParameter("frecuencia"));
        String tipo = request.getParameter("tipo");
        FrecuenciaBean fre = new FrecuenciaBean(idfrecuencia);
        fre.setFrecuencia(frecuencia);
        fre.setTipo(tipo);

        res = fredao.actualizar(fre);
        if (res) {
            msg = "frecuencia actualizada";
        } else {
            msg = "frecuencia no actualizada";
        }

        List<FrecuenciaBean> lista = fredao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("frecuencia.jsp");
        rd.forward(request, response);
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        res = fredao.eliminar(id);
        if (res) {
            msg = "Emision eliminada";
        } else {
            msg = "Emision no eliminada";
        }

        List<FrecuenciaBean> lista = fredao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("frecuencia.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(FrecuenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(FrecuenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
