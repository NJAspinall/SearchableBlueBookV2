package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.Log;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UCReader extends SteelReader {

    /*
     * Log
     */
    Log log;

    /*
     * Images
     */
    public Image img1;
    public Image img2;
    public Image img3;



    /*
     * CSV files
     */

    //Dimensions & Properties table (csv file)
    public static String DIMPROPS = "src/main/java/com/searchablebluebookv2/data/universalBeams/UC-secpropsdimsprops.csv";

    //Fire Parameters and Detailing table (csv file)
    public static String FIREDETAILS = "src/main/java/com/searchablebluebookv2/data/universalBeams/UC-secpropsdetailingfire.csv.csv";




    /**
     * Constructor
     *
     * @param log The instance of the Log Class
     */
    public UCReader(Log log) {
        //load Log object
        this.log = log;

        //load images
        try {
            File file = new File("src/main/resources/com/searchablebluebookv2/images/beams-dims.png");
            img1 = new Image(file.toURI().toString());

            file = new File("src/main/resources/com/searchablebluebookv2/images/beams-detail.png");
            img2 = new Image(file.toURI().toString());

            file = new File("src/main/resources/com/searchablebluebookv2/images/beams-axis.png");
            img3 = new Image(file.toURI().toString());
        }
        catch(NullPointerException e) {
            log.addStackTrace(e);
        }
    }




















    @Override
    public Image getImg1() {
        return null;
    }

    @Override
    public Image getImg2() {
        return null;
    }

    @Override
    public Image getImg3() {
        return null;
    }


    /**
     * Method to read the dimensions and properties of UniversalColumns line by line into
     * a 2D array.
     * @return 2D List of rows of data
     */
    @Override
    public List<List<String>> readDimensionsAndProperties() {
        List<List<String>> sections = new ArrayList<>();

        int count = 0;

        try {
            File myObj = new File(DIMPROPS);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine().trim();

                /* ignore first 9 lines which is just supplementary
                information about the source of the data */
                if((count >= 10) && (count <= 56)) {
                    List<String> line = new LinkedList<>(Arrays.asList(data.split(",")));

                    if(line.size() >= 5) { //do not read empty lines or lines containing notes and comments
                        sections.add(line);
                    }
                }
                count++;

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            log.addLog("The 'Dimensions and Properties' file could not be found.");
            log.addStackTrace(e);
        } catch (Exception e) {
            log.addLog("Unknown Error! Please restart the application.");
            log.addStackTrace(e);
        }

        //return list of UniversalBeam objects
        return sections;
    }

    @Override
    public List<List<String>> readFireAndDetailing() {
        return null;
    }
}
