package ejbpackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

//@EJB
//komponent sesyjny bezstanowy 
//@Stateless
@Stateful
//        (
//        //name = "XXX" //mappedName = "SSS"
//        )
@LocalBean // nie zdefiniowanlismy interfacu bo wywolania lokalne inaczej blad
public class MyClassifier {

    private Instances data;
    private Classifier cls;
    private String dataPath = "C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery.data.arff";
    //private String dataPath = "C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nurseryNew.data.arff";
    private String modelPath = "C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery_model.model";
    private int instanceNum;
    private int attributeNum;

    //@PostConstruct
    public Instances initData() throws IOException, ClassNotFoundException, Exception {
        BufferedReader buf = new BufferedReader(new FileReader(getDataPath()));
        data = new Instances(buf);
        data.setClassIndex(data.numAttributes() - 1);
        setInstanceNum(data.numInstances());
        setAttributeNum(data.numAttributes());
        buf.close();
        return data;
    }

    public Classifier initClassifier() throws IOException, ClassNotFoundException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getModelPath()));
        cls = (Classifier) ois.readObject();
        //cls.setOptions(options);
        cls.buildClassifier(data);
        return cls;
    }

    public Classifier initClassifier(String[] options) throws IOException, ClassNotFoundException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getModelPath()));
        cls = (Classifier) ois.readObject();
        cls.setOptions(options);
        cls.buildClassifier(data);
        return cls;
    }

    public void setClassIndex(Instances data) {
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
    }

    public Instances classifyOneInstance(Instances dataset, double[] vals) throws Exception {
        //utworzenie nowej instancji
        Instance inst = new Instance(1.0, vals);
        dataset.add(inst);
        dataset.setClassIndex(data.numAttributes() - 1);
        return dataset;
    }

    public String getClassName(Instances dataset, double[] vals) throws Exception {
        double clsLabel = cls.classifyInstance(dataset.instance(0));
        dataset.instance(0).setClassValue(clsLabel);

        String instanceClass = dataset.attribute(data.classIndex()).value((int) clsLabel);
        instanceClass = instanceClass.equals("not_recom") ? "Nie wskazane"
                : (instanceClass.equals("recommend") ? "Wskazane"
                : (instanceClass.equals("very_recom") ? "Bardzo wskazane"
                : (instanceClass.equals("priority") ? "Konieczne"
                : (instanceClass.equals("spec_prior") ? "Absolutnie niezbêdne" : "lol"))));
        return instanceClass;
    }

    public FastVector createInstance() {
//1. parents        usual, pretentious, great_pret
//2. has_nurs       proper, less_proper, improper, critical, very_crit
//3. form           complete, completed, incomplete, foster
//4. children       1, 2, 3, more
//5. housing        convenient, less_conv, critical
//6. finance        convenient, inconv
//7. social         non-prob, slightly_prob, problematic
//8. health         recommended, priority, not_recom
//9. class          not_recom, recommend, very_recom, priority, spec_prior

        FastVector atts = new FastVector();

        FastVector attVals1 = new FastVector();
        attVals1.addElement("usual");
        attVals1.addElement("pretentious");
        attVals1.addElement("great_pret");
        atts.addElement(new Attribute("parents", attVals1));

        FastVector attVals2 = new FastVector();
        attVals2.addElement("proper");
        attVals2.addElement("less_proper");
        attVals2.addElement("improper");
        attVals2.addElement("critical");
        attVals2.addElement("very_crit");
        atts.addElement(new Attribute("has_nurs", attVals2));

        FastVector attVals3 = new FastVector();
        attVals3.addElement("complete");
        attVals3.addElement("completed");
        attVals3.addElement("incomplete");
        attVals3.addElement("foster");
        atts.addElement(new Attribute("form", attVals3));

        FastVector attVals4 = new FastVector();
        attVals4.addElement("1");
        attVals4.addElement("2");
        attVals4.addElement("3");
        attVals4.addElement("more");
        atts.addElement(new Attribute("children", attVals4));

        FastVector attVals5 = new FastVector();
        attVals5.addElement("convenient");
        attVals5.addElement("less_conv");
        attVals5.addElement("critical");
        atts.addElement(new Attribute("housing", attVals5));

        FastVector attVals6 = new FastVector();
        attVals6.addElement("convenient");
        attVals6.addElement("inconv");
        atts.addElement(new Attribute("finance", attVals6));

        FastVector attVals7 = new FastVector();
        attVals7.addElement("non-prob");
        attVals7.addElement("slightly_prob");
        attVals7.addElement("problematic");
        atts.addElement(new Attribute("social", attVals7));

        FastVector attVals8 = new FastVector();
        attVals8.addElement("recommended");
        attVals8.addElement("priority");
        attVals8.addElement("not_recom");
        atts.addElement(new Attribute("health", attVals8));

        FastVector attValsClass = new FastVector();
        attValsClass.addElement("not_recom");
        attValsClass.addElement("recommend");
        attValsClass.addElement("very_recom");
        attValsClass.addElement("priority");
        attValsClass.addElement("spec_prior");
        atts.addElement(new Attribute("class", attValsClass));
        return atts;
    }

    /**
     * @return the dataPath
     */
    public String getDataPath() {
        return dataPath;
    }

    /**
     * @param dataPath the dataPath to set
     */
    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * @return the modelPath
     */
    public String getModelPath() {
        return modelPath;
    }

    /**
     * @param modelPath the modelPath to set
     */
    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    /**
     * @return the instanceNum
     */
    //@Deprecated
    public int getInstanceNum() {
        return instanceNum;
    }

    /**
     * @param instanceNum the instanceNum to set
     */
    public void setInstanceNum(int instanceNum) {
        this.instanceNum = data.numInstances();
    }

    /**
     * @return the attributeNum
     */
    public int getAttributeNum() {
        return attributeNum;
    }

    /**
     * @param attributeNum the attributeNum to set
     */
    public void setAttributeNum(int attributeNum) {
        this.attributeNum = data.numAttributes();
    }
}
