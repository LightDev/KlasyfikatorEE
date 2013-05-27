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
import weka.core.Instances;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "classifyNewInstance", urlPatterns = {"/classifyNewInstance"})
public class classifyNewInstance extends HttpServlet {

    @EJB
    private MyClassifier myClassifier;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //out.println(request.getParameter("parents"));
            //System.out.println(request.getParameter("parents"));
            String parameters[] = {"parents", "has_nurs", "form", "children", "housing", "finance", "social", "health"};
            String parametersValues[] = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersValues[i] = request.getParameter(parameters[i]);

            }
            myClassifier.initData();
            String options[] = {};
            myClassifier.initClassifier(options);

            Instances dataset = new Instances("nursery", myClassifier.createInstance(), 0);

            //wczytywanie parametrow z servletu
            double[] vals = new double[dataset.numAttributes()];
            System.out.println("" + dataset.numAttributes());
            for (int i = 0; i < dataset.numAttributes() - 1; i++) {
                vals[i] = Double.valueOf(parametersValues[i]);
            }

//            Evaluation eval = new Evaluation(data);
//            eval.evaluateModel(cls, data);
//            String evalInfo = eval.toSummaryString("Statyki z ewaluacji", false);
//            request.setAttribute("evaluation", evalInfo);
            request.setAttribute("class", myClassifier.classifyOneInstance(dataset, vals));
            request.getRequestDispatcher("classificationResult.jsp").forward(request, response);

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
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
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
