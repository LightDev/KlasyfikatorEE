/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import ejbpackage.MyClassifier;
import java.io.File;
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
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "addInstance", urlPatterns = {"/addInstance"})
public class addInstance extends HttpServlet {
    
    @EJB
    private MyClassifier myClassifier;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String parameters[] = {"parents", "has_nurs", "form", "children", "housing", "finance", "social", "health"};
            String parametersValues[] = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersValues[i] = request.getParameter(parameters[i]);
            }
            Instances data = myClassifier.initData();
            double[] vals = new double[data.numAttributes()];
            System.out.println("" + data.numAttributes());
            for (int i = 0; i < data.numAttributes() - 1; i++) {
                vals[i] = Double.valueOf(parametersValues[i]);
            }
            Instance inst = new Instance(1.0, vals);
            data.add(inst);
            
            String fileName = "C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery.data.arff";
            // A File object to represent the filename
            WekaHelper w = new WekaHelper();
            w.removeFile(fileName);
            File newArff = new File("C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery.data.arff");
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(newArff);
            //saver.setDestination(newArff);
            saver.writeBatch();
            System.out.println("Dodano nowa instancje");
            
            request.getRequestDispatcher("addInstance.jsp").forward(request, response);
            
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
            Logger.getLogger(addInstance.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(addInstance.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(addInstance.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(addInstance.class
                    .getName()).log(Level.SEVERE, null, ex);
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