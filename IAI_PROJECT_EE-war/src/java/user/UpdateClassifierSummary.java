package user;

import ejbpackage.MyClassifier;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import weka.core.Instances;

/**
 *
 * @author drgeek
 */
@WebServlet(urlPatterns = {"/updateClassifier"})
public class UpdateClassifierSummary extends HttpServlet {

    @EJB
    private MyClassifier myClassifier;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Instances data = myClassifier.initData();

//            String optionsNames[] = {"", "", "", "", "", "", "", "", "", ""};
//            String optionsValues[] = new String[optionsNames.length];
//            String options[] = new String[2 * optionsNames.length];
//            //pobieramy w petli 
//            for (int i = 0; i < optionsValues.length; i++) {
//                optionsValues[2 * i] = request.getParameter(optionsNames[i]);
//                options[i] = optionsNames[i];
//                options[2 * i + 1] = optionsValues[i];
//            }
//
//            Classifier cls = myClassifier.initClassifier(options);

            Classifier cls = myClassifier.initClassifier();
            PART updateCls = (PART) cls;
            updateCls.setBinarySplits(Boolean.valueOf(request.getParameter("binarySplits")));
            updateCls.setConfidenceFactor(Float.valueOf(request.getParameter("confidenceFactor")));
            updateCls.setDebug(Boolean.valueOf(request.getParameter("debug")));
            updateCls.setMinNumObj(Integer.valueOf(request.getParameter("minNumObj")));
            updateCls.setNumFolds(Integer.valueOf(request.getParameter("numFolds")));
            updateCls.setReducedErrorPruning(Boolean.valueOf(request.getParameter("reducedErrorPruning")));
            updateCls.setSeed(Integer.valueOf(request.getParameter("seed")));
            updateCls.setUnpruned(Boolean.valueOf(request.getParameter("unprunned")));
//            Enumeration options1 = ((PART) cls).enumerateMeasures();
//            float options1 = ((PART) cls).sgetConfidenceFactor();
//            System.out.println("c=" + options1);
//            while (options1.hasMoreElements()) {
//                System.out.println((options1.nextElement()));
//            }
//            for (options1 d : options1.values()) {
//                System.out.println(d);
//            }
//            //String options[] = cls.getOptions();
//            for (int i = 0; i < options.length; i++) {
//                System.out.println(options[i]);
//            }
//            out.print(cls.getOptions());


//            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(myClassifier.getModelPath()));
            oos.writeObject(updateCls);
            oos.flush();
            oos.close();
            //request.setAttribute("evaluationOutput", evaluationOutput);
            request.getRequestDispatcher("updateClassifierSummary.jsp").forward(request, response);


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
            Logger.getLogger(UpdateClassifierSummary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UpdateClassifierSummary.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateClassifierSummary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UpdateClassifierSummary.class.getName()).log(Level.SEVERE, null, ex);
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
