package user;

import java.io.File;
import java.io.IOException;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class WekaHelper {

    public void saveArffFile(String fileName, Instances data) throws IOException {
        File newArff = new File(fileName);
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(newArff);
        //saver.setDestination(newArff); //dla 3.6.9 nieakutalne
        saver.writeBatch();
    }

    public void removeFile(String fileName) {
        File f = new File(fileName);
        //f.setWritable(true);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            throw new IllegalArgumentException(
                    "Delete: no such file or directory: " + fileName);
        }

        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + fileName);
        }

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new IllegalArgumentException(
                        "Delete: directory not empty: " + fileName);
            }
        }

        // Attempt to delete it
        boolean success = f.delete();
        // f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }

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
}
