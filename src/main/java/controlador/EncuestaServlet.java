/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.EmisionDao;
import dao.EncuestaDao;
import dao.ProgramaDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.EmisionBean;
import modelo.EncuestaBean;
import modelo.ProgramaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class EncuestaServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    EncuestaDao endao = new EncuestaDao(conexion);
    EmisionDao emidao = new EmisionDao(conexion);
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
        List<EncuestaBean> lista = endao.consultar();
        List<ProgramaBean> listapro = prodao.consultar();
        List<EmisionBean> listaemi = emidao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listaemi", listaemi);
        rd = request.getRequestDispatcher("encuesta.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<EncuestaBean> lista = endao.consultarById(id);
        List<ProgramaBean> listapro = prodao.consultar();
        List<EmisionBean> listaemi = emidao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listaemi", listaemi);
        rd = request.getRequestDispatcher("encuesta.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idemision = Integer.parseInt(request.getParameter("idemision"));
        System.out.println("idemision = " + idemision);
        int idprograma = Integer.parseInt(request.getParameter("idprograma"));
        int total = Integer.parseInt(request.getParameter("total"));
        int aprobacion = Integer.parseInt(request.getParameter("aprobacion"));
        int rechazo = Integer.parseInt(request.getParameter("rechazo"));
        int indiferencia = Integer.parseInt(request.getParameter("indiferencia"));
        EncuestaBean encuesta = new EncuestaBean(0);
        EmisionBean emision = new EmisionBean(idemision);
        ProgramaBean programa = new ProgramaBean(idprograma);
        encuesta.setIdemision(emision);
        encuesta.setIdprograma(programa);
        encuesta.setTotal(total);
        encuesta.setAprobacion(aprobacion);
        encuesta.setRechazo(rechazo);
        encuesta.setIndiferencia(indiferencia);
        int operacion = total - (aprobacion + rechazo + indiferencia);
        System.out.println("total = " + total + " opr = " + operacion);
        if (operacion == 0) {
            res = endao.guardar(encuesta);
            if (res) {
                msg = "Encuesta registrada";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<EncuestaBean> lista = endao.consultar();
            List<ProgramaBean> listapro = prodao.consultar();
            List<EmisionBean> listaemi = emidao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listaemi", listaemi);
            rd = request.getRequestDispatcher("encuesta.jsp");
            rd.forward(request, response);
        } else {
            msg = "Los datos no concuerdan con el total de encuestados";
            request.setAttribute("msg", msg);
            List<EncuestaBean> lista = endao.consultar();
            List<ProgramaBean> listapro = prodao.consultar();
            List<EmisionBean> listaemi = emidao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listaemi", listaemi);
            rd = request.getRequestDispatcher("encuesta.jsp");
            rd.forward(request, response);
        }

    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idencuesta = Integer.parseInt(request.getParameter("idencuesta"));
        int idemision = Integer.parseInt(request.getParameter("idemision"));
        int idprograma = Integer.parseInt(request.getParameter("idprograma"));
        int total = Integer.parseInt(request.getParameter("total"));
        int aprobacion = Integer.parseInt(request.getParameter("aprobacion"));
        int rechazo = Integer.parseInt(request.getParameter("rechazo"));
        int indiferencia = Integer.parseInt(request.getParameter("indiferencia"));
        EncuestaBean encuesta = new EncuestaBean(idencuesta);
        EmisionBean emision = new EmisionBean(idemision);
        ProgramaBean programa = new ProgramaBean(idprograma);
        encuesta.setIdemision(emision);
        encuesta.setIdprograma(programa);
        encuesta.setTotal(total);
        encuesta.setAprobacion(aprobacion);
        encuesta.setRechazo(rechazo);
        encuesta.setIndiferencia(indiferencia);
        int operacion = total - (aprobacion + rechazo + indiferencia);
        if (operacion == 0) {
            res = endao.guardar(encuesta);
            if (res) {
                msg = "Encuesta actualizada";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<EncuestaBean> lista = endao.consultar();
            List<ProgramaBean> listapro = prodao.consultar();
            List<EmisionBean> listaemi = emidao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listaemi", listaemi);
            rd = request.getRequestDispatcher("encuesta.jsp");
            rd.forward(request, response);
        } else {
            msg = "No se puede modificar por inconsistencias del total de encuestados";
            request.setAttribute("msg", msg);
            List<EncuestaBean> lista = endao.consultar();
            List<ProgramaBean> listapro = prodao.consultar();
            List<EmisionBean> listaemi = emidao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listaemi", listaemi);
            rd = request.getRequestDispatcher("encuesta.jsp");
            rd.forward(request, response);
        }

    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = endao.eliminar(id);
        if (res) {
            msg = "Encuesta eliminada";
        } else {
            msg = "Encuesta no eliminada";
        }
        request.setAttribute("msg", msg);
        List<EncuestaBean> lista = endao.consultar();
        List<ProgramaBean> listapro = prodao.consultar();
        List<EmisionBean> listaemi = emidao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listaemi", listaemi);
        rd = request.getRequestDispatcher("encuesta.jsp");
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
