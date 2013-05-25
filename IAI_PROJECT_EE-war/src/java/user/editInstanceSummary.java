/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "editInstanceSummary", urlPatterns = {"/editInstanceSummary"})
public class editInstanceSummary extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //wczytywanie parametrow z servletu
            String parameters[] = {"parents", "has_nurs", "form", "children", "housing", "finance", "social", "health, class"};
            String parametersValues[] = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersValues[i] = request.getParameter(parameters[i]);
            }
//            //utworzenie nowej instancji
//            Instance inst = new Instance(1.0, vals);
//            dataset.add(inst);
//            dataset.setClassIndex(data.numAttributes() - 1);
            //przepisz parametry z post'a


            //wstaw na odpowiednie miejsce badz zrob merge

            //wyswietl komunikat zwrotny


            request.getRequestDispatcher("editInstanceSummary.jsp").forward(request, response);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
