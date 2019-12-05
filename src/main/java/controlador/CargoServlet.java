/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.CargoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CargoBean;

/**
 *
 * @author PEDRINSKY
 */
public class CargoServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    CargoDao cdao = new CargoDao(conexion);

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
        List<CargoBean> lista = cdao.consultar();
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("cargo.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<CargoBean> lista = cdao.consultarById(id);
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("cargo.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cargo = request.getParameter("cargo");
        CargoBean cargobean = new CargoBean(0);
        cargobean.setCargo(cargo);
        res = cdao.validar(cargobean);
        if (res) {
            msg = "El cargo ya existe";
            List<CargoBean> lista = cdao.consultar();
            request.setAttribute("msg", msg);
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("cargo.jsp");
            rd.forward(request, response);
        } else {
            res = cdao.guardar(cargobean);
            if (res) {
                msg = "Se registro el cargo";
            } else {
                msg = "No se registro el cargo";
            }
            List<CargoBean> lista = cdao.consultar();
            request.setAttribute("msg", msg);
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("cargo.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcargo = Integer.parseInt(request.getParameter("idcargo"));
        String cargo = request.getParameter("cargo");
        CargoBean cargobean = new CargoBean(idcargo);
        cargobean.setCargo(cargo);
        res = cdao.validar(cargobean);
        if (res) {
            msg = "El cargo ya existe";
            List<CargoBean> lista = cdao.consultar();
            request.setAttribute("msg", msg);
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("cargo.jsp");
            rd.forward(request, response);
        } else {
            res = cdao.actualizar(cargobean);
            if (res) {
                msg = "Se actualizo el cargo";
            } else {
                msg = "No se actualizo el cargo";
            }
            List<CargoBean> lista = cdao.consultar();
            request.setAttribute("msg", msg);
            request.setAttribute("lista", lista);
            rd = request.getRequestDispatcher("cargo.jsp");
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = cdao.eliminar(id);
        if (res) {
            msg = "Se elimino el cargo";
        } else {
            msg = "No se elimino el cargo";
        }
        List<CargoBean> lista = cdao.consultar();
        request.setAttribute("msg", msg);
        request.setAttribute("lista", lista);
        rd = request.getRequestDispatcher("cargo.jsp");
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
