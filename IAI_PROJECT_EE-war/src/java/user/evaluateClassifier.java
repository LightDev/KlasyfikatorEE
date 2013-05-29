package user;

import ejbpackage.MyClassifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 *
 * @author drgeek
 */
@WebServlet(urlPatterns = {"/evaluateClassifier"})
public class evaluateClassifier extends HttpServlet {

//    @EJB
//    private MyClassifier myClassifier;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String percentSplit = String.valueOf(Integer.valueOf((String) request.getParameter("percent_split")));
            double dPercentSplit = Double.valueOf(percentSplit) / 100;
            //int iPercentSplit = (int) Math.ceil(dPercentSplit);
            String beanName = "java:global/IAI_PROJECT_EE/IAI_PROJECT_EE-ejb/MyClassifier";
            MyClassifier bean = ((MyClassifier) InitialContext.doLookup(beanName));
            Instances allData = bean.initData();
            int instNum = allData.numInstances();
            double dtrainInstNum = Math.ceil(instNum * dPercentSplit);
            int trainInstNum = (int) dtrainInstNum;
            int testInstNum = instNum - trainInstNum;
//            System.out.println("dPercentSplit " + dPercentSplit);
//            System.out.println("dtrain " + dtrainInstNum);
//            System.out.println("instNum " + instNum);
//            System.out.println("trainInstNum " + trainInstNum);
//            System.out.println("testInstNum " + testInstNum);

            Instances testData = new Instances("testData", bean.createInstance(), 0);
            bean.setClassIndex(testData);

            int j = 2;
            for (int i = trainInstNum - 1; i < instNum - 1; i++) {
                int all = allData.numInstances() - j;
                System.out.println("i " + i);
                System.out.println("alldatanum " + all);
                testData.add(allData.instance(all));
                ++j;
                allData.delete(all);
            }

            String fileName = "C:\\Users\\drgeek\\Desktop\\Data Mining\\PS_IAI\\IAI_PROJECT\\train.arff";
            WekaHelper w = new WekaHelper();
            w.saveArffFile(fileName, allData);

            fileName = "C:\\Users\\drgeek\\Desktop\\Data Mining\\PS_IAI\\IAI_PROJECT\\test.arff";
            w.saveArffFile(fileName, testData);

            //ewaluacja
            System.out.println("allDataNum " + allData.numInstances());
            Evaluation eval = new Evaluation(allData);
            eval.evaluateModel(bean.initClassifier(), testData);
//            Evaluation eval = new Evaluation(testData);
//            eval.evaluateModel(bean.initClassifier(), allData);

            String evaluationOutput = eval.toSummaryString("Statyki z ewaluacji", false);
            request.setAttribute("evaluationOutput", evaluationOutput);
            request.getRequestDispatcher("evaluateClassifierSummary.jsp").forward(request, response);

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
            Logger.getLogger(evaluateClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(evaluateClassifier.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(evaluateClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(evaluateClassifier.class.getName()).log(Level.SEVERE, null, ex);
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
