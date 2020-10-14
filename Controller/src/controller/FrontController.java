/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import model.*;

/**
 *
 * @author andreia
 */
public class FrontController {// extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public FrontController() {
    }

    public void processRequest(vmTela dados) throws Exception {
        try {

            // <editor-fold defaultstate="collapsed" desc="Codigo exemplo comentado">
           /* String action = request.getParameter("action");
            Action actionObject = null;

            if (action == null || action.equals("")) {
            response.sendRedirect("index.jsp");
            }


            actionObject = ActionFactory.create(action);

            if (actionObject != null) {
            actionObject.execute(request, response);
            }*/
            // </editor-fold>

            //Lendo a action requisitada pela tela
            String action = dados.GetAction();
            Action actionObject = null;

            if (action == null || action.equals("")) {
                throw new Exception("Action inválida");
            }

            //criando a action requisitada
            actionObject = ActionFactory.create(action);

            //executando a action
            if (actionObject != null) {
                actionObject.execute(dados);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            //out.close();
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
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    /*@Override
    public String getServletInfo() {
    return "Short description";
    }*/
// </editor-fold>
}
