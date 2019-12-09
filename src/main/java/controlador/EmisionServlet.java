/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.EmisionDao;
import dao.ProgramaDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.EmisionBean;
import modelo.ProgramaBean;

/**
 *
 * @author PEDRINSKY
 */
public class EmisionServlet extends HttpServlet {

    RequestDispatcher rd;
    String msg;
    boolean res;
    Conexion conexion = new Conexion();
    EmisionDao emidao = new EmisionDao(conexion);
    ProgramaDao prodao = new ProgramaDao(conexion);
    SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");

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
        List<EmisionBean> lista = emidao.consultar();
        List<ProgramaBean> programa = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("programa", programa);
        rd = request.getRequestDispatcher("emision.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<EmisionBean> lista = emidao.consultarById(id);
        List<ProgramaBean> programa = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("programa", programa);
        rd = request.getRequestDispatcher("emision.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int idprograma = Integer.parseInt(request.getParameter("idprograma"));
        Date fecha = formato.parse(request.getParameter("fecha"));
        LocalTime horainicio = LocalTime.parse(request.getParameter("horainicio"));
        LocalTime duracion = LocalTime.parse(request.getParameter("duracion"));
        String repeticion = request.getParameter("repeticion");
        EmisionBean emi = new EmisionBean(0);
        ProgramaBean pro = new ProgramaBean(idprograma);
        emi.setIdprograma(pro);
        emi.setFecha(fecha);
        emi.setHorainicio(horainicio);
        emi.setDuracion(duracion);
        emi.setRepeticion(repeticion);
        res = emidao.guardar(emi);
        if (res) {
            msg = "Emision registrada";
        } else {
            msg = "Emision no registrada";
        }

        List<EmisionBean> lista = emidao.consultar();
        List<ProgramaBean> programa = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("programa", programa);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("emision.jsp");
        rd.forward(request, response);
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int idemision = Integer.parseInt(request.getParameter("idemision"));
        int idprograma = Integer.parseInt(request.getParameter("idprograma"));
        Date fecha = formato.parse(request.getParameter("fecha"));
        LocalTime horainicio = LocalTime.parse(request.getParameter("horainicio"));
        LocalTime duracion = LocalTime.parse(request.getParameter("duracion"));
        String repeticion = request.getParameter("repeticion");

        EmisionBean emi = new EmisionBean(idemision);
        ProgramaBean pro = new ProgramaBean(idprograma);
        emi.setIdprograma(pro);
        emi.setFecha(fecha);
        emi.setHorainicio(horainicio);
        emi.setDuracion(duracion);
        emi.setRepeticion(repeticion);

        res = emidao.actualizar(emi);
        if (res) {
            msg = "Emision actualizada";
        } else {
            msg = "Emision no actualizada";
        }

        List<EmisionBean> lista = emidao.consultar();
        List<ProgramaBean> programa = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("programa", programa);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("emision.jsp");
        rd.forward(request, response);
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        res = emidao.eliminar(id);
        if (res) {
            msg = "Emision eliminada";
        } else {
            msg = "Emision no eliminada";
        }

        List<EmisionBean> lista = emidao.consultar();
        List<ProgramaBean> programa = prodao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("programa", programa);
        request.setAttribute("msg", msg);
        rd = request.getRequestDispatcher("emision.jsp");
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
            System.out.println("Error: " + ex.getMessage());
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
            System.out.println("Error: " + ex.getMessage());
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
