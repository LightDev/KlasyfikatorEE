/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import ejbpackage.MyClassifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.classifiers.Classifier;
import weka.classifiers.rules.PART;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "buildClassifier", urlPatterns = {"/buildClassifier"})
public class buildClassifier extends HttpServlet {

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
    @EJB
    private MyClassifier myClassifier;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String params[] = {};
            myClassifier.initData();
            Classifier cls = myClassifier.initClassifier();

            PART updateCls = (PART) cls;
            request.setAttribute("binarySplits", updateCls.getBinarySplits());
            request.setAttribute("confidenceFactor", updateCls.getConfidenceFactor());
            request.setAttribute("debug", updateCls.getDebug());
            request.setAttribute("minNumObj", updateCls.getMinNumObj());
            request.setAttribute("numFolds", updateCls.getNumFolds());
            request.setAttribute("reducedErrorPruning:", updateCls.getReducedErrorPruning());
            request.setAttribute("seed", updateCls.getSeed());
            request.setAttribute("unprunned", updateCls.getUnpruned());
            System.out.println("getUn " + updateCls.getUnpruned());
            request.getRequestDispatcher("buildClassifier.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buildClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(buildClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buildClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(buildClassifier.class.getName()).log(Level.SEVERE, null, ex);
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
