/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.FrecuenciaDao;
import dao.ProductoraDao;
import dao.RadioDao;
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
import modelo.ProductoraBean;
import modelo.RadioBean;

/**
 *
 * @author roberto.alferesusam
 */
public class RadioServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    RadioDao radao = new RadioDao(conexion);
    ProductoraDao prodao = new ProductoraDao(conexion);
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
        List<RadioBean> lista = radao.consultar();
        List<FrecuenciaBean> frec = fredao.consultar();
        List<ProductoraBean> pro = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("frec", frec);
        request.setAttribute("productora", pro);
        rd = request.getRequestDispatcher("radio.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<RadioBean> lista = radao.consultarById(id);
        List<FrecuenciaBean> frec = fredao.consultar();
        List<ProductoraBean> pro = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("frec", frec);
        request.setAttribute("productora", pro);
        rd = request.getRequestDispatcher("radio.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idfre = Integer.parseInt(request.getParameter("idfrecuencia"));
        int idproduct = Integer.parseInt(request.getParameter("idproductora"));
        String nombre = request.getParameter("nombre");
        RadioBean radio = new RadioBean(0);
        FrecuenciaBean frecuencia = new FrecuenciaBean(idfre);
        ProductoraBean productora = new ProductoraBean(idproduct);
        radio.setIdproductora(productora);
        radio.setIdfrecuencia(frecuencia);
        radio.setNombre(nombre);
        res = radao.validar(radio);
        if (res) {
            msg = "la radio ya esta registrada";
            List<RadioBean> lista = radao.consultar();
            List<FrecuenciaBean> frec = fredao.consultar();
            List<ProductoraBean> pro = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("frec", frec);
            request.setAttribute("productora", pro);
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("radio.jsp");
            rd.forward(request, response);
        } else {
            res = radao.guardar(radio);
            if (res) {
                msg = "radio registrada";
            } else {
                msg = "radio no registrada";
            }

            List<RadioBean> lista = radao.consultar();
            List<FrecuenciaBean> frec = fredao.consultar();
            List<ProductoraBean> pro = prodao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("frec", frec);
            request.setAttribute("productora", pro);
            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("radio.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int idradio = Integer.parseInt(request.getParameter("idradio"));
        int idfre = Integer.parseInt(request.getParameter("idfrecuencia"));
        int idproduct = Integer.parseInt(request.getParameter("idproductora"));
        String nombre = request.getParameter("nombre");
        RadioBean radio = new RadioBean(idradio);
        FrecuenciaBean frecuencia = new FrecuenciaBean(idfre);
        ProductoraBean productora = new ProductoraBean(idproduct);
        radio.setIdproductora(productora);
        radio.setIdfrecuencia(frecuencia);
        radio.setNombre(nombre);

        res = radao.actualizar(radio);
        if (res) {
            msg = "radio actualizada";
        } else {
            msg = "radio no actualizada";
        }

        List<RadioBean> lista = radao.consultar();
        List<FrecuenciaBean> frec = fredao.consultar();
        List<ProductoraBean> pro = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("frec", frec);
        request.setAttribute("productora", pro);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("radio.jsp");
        rd.forward(request, response);

    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        res = radao.eliminar(id);
        if (res) {
            msg = "radio eliminada";
        } else {
            msg = "radio no eliminada";
        }

        List<RadioBean> lista = radao.consultar();
        List<FrecuenciaBean> frec = fredao.consultar();
        List<ProductoraBean> pro = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("frec", frec);
        request.setAttribute("productora", pro);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("radio.jsp");
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
            Logger.getLogger(RadioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RadioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
