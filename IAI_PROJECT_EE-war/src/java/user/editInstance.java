/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "editInstance", urlPatterns = {"/editInstance"})
public class editInstance extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //pobierz aktualny zbior instancji

            Instances data;
            data = new Instances(new BufferedReader(new FileReader("C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery.data.arff")));
            //Stworz tymczasowa instancje i pobierz ja cala
            WekaHelper wekaHelper = new WekaHelper();
            Instances dataset = new Instances("nursery", wekaHelper.createInstance(), 0);

            String id = request.getParameter("id_to_edit");
            double[] vals = new double[dataset.numAttributes()];
            System.out.println("" + dataset.numAttributes());
            System.out.println("ID" + id);
            System.out.println("data " + data.numInstances());
            for (int i = 0; i < dataset.numAttributes(); i++) {
                vals[i] = data.instance(Integer.valueOf(id)).value(i);
                System.out.println(vals[i]);
            }
            request.setAttribute("id", id);
            for (int i = 0; i < dataset.numAttributes(); i++) {
                request.setAttribute(data.attribute(i).name(), String.valueOf((int) vals[i]));
            }
            request.getRequestDispatcher("editInstance.jsp").forward(request, response);
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
