/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import ejbpackage.MyClassifier;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "removeInstance", urlPatterns = {"/removeInstance"})
public class removeInstance extends HttpServlet {

    @EJB
    private MyClassifier myClassifier;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Instances data = myClassifier.initData();
            //String id = request.getParameter("id_to_remove");
            String id = String.valueOf(Integer.valueOf(request.getParameter("id_to_remove")) - 1);
            data.delete(Integer.valueOf(id));

            String fileName = myClassifier.getDataPath();
            WekaHelper w = new WekaHelper();
            w.removeFile(fileName);
            File newArff = new File(myClassifier.getDataPath());
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(newArff);
            //saver.setDestination(newArff); //dla 3.6.9 nieakutalne
            saver.writeBatch();
            System.out.println("Usunieto instancje nr " + id);

            for (int i = 0; i < myClassifier.getAttributeNum(); i++) {
                request.setAttribute(data.attribute(i).name(),
                        data.instance(Integer.valueOf(id)).stringValue(i));
            }
            String sId = String.valueOf(Integer.valueOf(id) + 1);
            request.setAttribute("id", sId);

            request.getRequestDispatcher("removeInstance.jsp").forward(request, response);

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(removeInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(removeInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Map<String, String> messages = new HashMap<String, String>();

            String id = request.getParameter("id_to_remove");
            myClassifier.initData();
//|| Integer.valueOf(id) > myClassifier.getInstanceNum()
//            if (id != null) {
//                id = id.trim();
//            }
            if (id == null || id.isEmpty() || Integer.valueOf(id) <= 0 || Integer.valueOf(id) > myClassifier.getInstanceNum()) {
                messages.put("id", "Podaj id do usuniecia");
            }
            if (!messages.isEmpty()) {
                response.sendRedirect("adminIndex.jsp");
            } else {
                processRequest(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(removeInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(removeInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
