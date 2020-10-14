/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.FrontController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.vmTela_Compilador;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Handle_Web", urlPatterns = {"/Handle_Web"})
public class Handle_Web extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            vmTela_Compilador tela = new vmTela_Compilador();
            //Encapsulando os dados da tela para enviar a controller
            tela.SetAction(request.getParameter("action"));

            tela.setCodigo(request.getParameter("txtCodigo"));
            String analise = request.getParameter("analise");
            if (analise != null) {
                tela.setLexica(analise.equals("Lexica"));
                tela.setSintatica(analise.equals("Sintatica"));
                tela.setSemantica(analise.equals("Semantica"));
                tela.setAssembly(analise.equals("Assembly"));
            }

            FrontController controller = new FrontController();
            controller.processRequest(tela);

            //tela.setCodigo(tela.getCodigo().replace("[erro]", "<div style='background-color: red'>"));
            //tela.setCodigo(tela.getCodigo().replace("[/erro]", "</div>"));

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("ResultAnalise", tela);
            rd.forward(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Handle_Web.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Handle_Web.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
