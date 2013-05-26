/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import ejbpackage.MyClassifier;
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

/**
 *
 * @author drgeek
 */
@WebServlet(name = "editInstance", urlPatterns = {"/editInstance"})
public class editInstance extends HttpServlet {

    @EJB
    private MyClassifier myClassifier;

    private void editInstance(HttpServletRequest request, String id) throws IOException, ClassNotFoundException, Exception {
        Instances data = myClassifier.initData();

        double[] vals = new double[myClassifier.getAttributeNum()];

        for (int i = 0; i < myClassifier.getAttributeNum(); i++) {
            vals[i] = data.instance(Integer.valueOf(id)).value(i);
        }
        String sId = String.valueOf(Integer.valueOf(id) + 1);
        request.setAttribute("id", sId);
        for (int i = 0; i < myClassifier.getAttributeNum(); i++) {
            request.setAttribute(data.attribute(i).name(), String.valueOf((int) vals[i]));
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String id = String.valueOf(Integer.valueOf(request.getParameter("id_to_edit")) - 1);
            editInstance(request, id);
            request.getRequestDispatcher("editInstance.jsp").forward(request, response);

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
            Logger.getLogger(editInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Map<String, String> messages = new HashMap<String, String>();

            String id = request.getParameter("id_to_edit").trim();
            myClassifier.initData();
//            if ( !id.isEmpty()) {
//                id.trim();
//            }
            if (id == null || id.trim().isEmpty() || Integer.valueOf(id.trim()) <= 0 || Integer.valueOf(id.trim()) > myClassifier.getInstanceNum()) {
                messages.put("id", "Podaj id do edytowania");
            }
            if (!messages.isEmpty()) {
                response.sendRedirect("adminIndex.jsp");
            } else {
                processRequest(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
