/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.CargoDao;
import dao.PersonalDao;
import dao.ProductoraDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CargoBean;
import modelo.PersonalBean;
import modelo.ProductoraBean;

/**
 *
 * @author roberto.alferesusam
 */
public class PersonalServlet extends HttpServlet {

    RequestDispatcher rd;
    boolean res;
    String msg;
    Conexion conexion = new Conexion();
    PersonalDao perdao = new PersonalDao(conexion);
    ProductoraDao prodao = new ProductoraDao(conexion);
    CargoDao cardao = new CargoDao(conexion);

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
        List<PersonalBean> lista = perdao.consultar();
        List<ProductoraBean> listapro = prodao.consultar();
        List<CargoBean> listacargo = cardao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listacargo", listacargo);
        rd = request.getRequestDispatcher("personal.jsp");
        rd.forward(request, response);
    }

    protected void consultarById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<PersonalBean> lista = perdao.consultarById(id);
        List<ProductoraBean> listapro = prodao.consultar();
        List<CargoBean> listacargo = cardao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listacargo", listacargo);
        rd = request.getRequestDispatcher("personal.jsp");
        rd.forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idproductora = Integer.parseInt(request.getParameter("idproductora"));
        int idcargo = Integer.parseInt(request.getParameter("idcargo"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dui = request.getParameter("dui");
        System.out.println(idproductora + " " + idcargo + " " + nombre + " " + apellido + " " + dui);
        PersonalBean per = new PersonalBean(0);
        ProductoraBean productora = new ProductoraBean(idproductora);
        CargoBean cargo = new CargoBean(idcargo);
        per.setIdproductora(productora);
        per.setIdcargo(cargo);
        per.setNombre(nombre);
        per.setApellido(apellido);
        per.setDui(dui);
        res = perdao.validar(per);
        if (res) {
            msg = "Ya existe el personal";
            request.setAttribute("msg", msg);
            List<PersonalBean> lista = perdao.consultar();
            List<ProductoraBean> listapro = prodao.consultar();
            List<CargoBean> listacargo = cardao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listacargo", listacargo);
            rd = request.getRequestDispatcher("personal.jsp");
            rd.forward(request, response);
        } else {
            res = perdao.guardar(per);
            if (res) {
                msg = "Persona registrada";
            } else {
                msg = "Error al guardar";
            }
            request.setAttribute("msg", msg);
            List<PersonalBean> lista = perdao.consultar();
            List<ProductoraBean> listapro = prodao.consultar();
            List<CargoBean> listacargo = cardao.consultar();
            request.setAttribute("lista", lista);
            request.setAttribute("listapro", listapro);
            request.setAttribute("listacargo", listacargo);
            rd = request.getRequestDispatcher("personal.jsp");
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idpersonal = Integer.parseInt(request.getParameter("idpersonal"));
        int idproductora = Integer.parseInt(request.getParameter("idproductora"));
        int idcargo = Integer.parseInt(request.getParameter("idcargo"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dui = request.getParameter("dui");

        PersonalBean per = new PersonalBean(idpersonal);
        ProductoraBean productora = new ProductoraBean(idproductora);
        CargoBean cargo = new CargoBean(idcargo);
        per.setIdproductora(productora);
        per.setIdcargo(cargo);
        per.setNombre(nombre);
        per.setApellido(apellido);
        per.setDui(dui);

        res = perdao.actualizar(per);
        if (res) {
            msg = "Persona actualizada";
        } else {
            msg = "Error al guardar";
        }
        request.setAttribute("msg", msg);
        List<PersonalBean> lista = perdao.consultar();
        List<ProductoraBean> listapro = prodao.consultar();
        List<CargoBean> listacargo = cardao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listacargo", listacargo);
        rd = request.getRequestDispatcher("personal.jsp");
        rd.forward(request, response);
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        res = perdao.eliminar(id);
        if (res) {
            msg = "Persona  eliminada";
        } else {
            msg = "Persona no eliminada";
        }
        request.setAttribute("msg", msg);
        List<PersonalBean> lista = perdao.consultar();
        List<ProductoraBean> listapro = prodao.consultar();
        List<CargoBean> listacargo = cardao.consultar();
        request.setAttribute("lista", lista);
        request.setAttribute("listapro", listapro);
        request.setAttribute("listacargo", listacargo);
        rd = request.getRequestDispatcher("personal.jsp");
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
