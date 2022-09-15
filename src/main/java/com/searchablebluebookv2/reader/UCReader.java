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
    public static String DIMPROPS = "src/main/java/com/searchablebluebookv2/data/universalColumns/UC-secpropsdimsprops.csv";

    //Fire Parameters and Detailing table (csv file)
    public static String FIREDETAILS = "src/main/java/com/searchablebluebookv2/data/universalColumns/UC-secpropsdetailingfire.csv";




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
            File file = new File("src/main/resources/com/searchablebluebookv2/images/columns-dims.png");
            img1 = new Image(file.toURI().toString());

            file = new File("src/main/resources/com/searchablebluebookv2/images/columns-detail.png");
            img2 = new Image(file.toURI().toString());

            file = new File("src/main/resources/com/searchablebluebookv2/images/columns-axis.png");
            img3 = new Image(file.toURI().toString());


            if(img1.isError()) {
                System.out.println("img1 error.");
            }
            if(img2.isError()) {
                System.out.println("img2 error.");
            }
            if(img3.isError()) {
                System.out.println("img3 error.");
            }


        }
        catch(NullPointerException e) {
            log.addStackTrace(e);
        }
    }




















    /**
     * Get the first image
     *
     * @return png image
     */
    @Override
    public Image getImg1() {
        return img1;
    }

    /**
     * Get the second image
     *
     * @return png image
     */
    @Override
    public Image getImg2() {
        return img2;
    }

    /**
     * Get the third image
     *
     * @return png image
     */
    @Override
    public Image getImg3() {
        return img3;
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
        List<List<String>> sections = new ArrayList<>();

        int count = 0;

        try {
            File file = new File(FIREDETAILS);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine().trim();

                 /* ignore first 9 lines which is just supplementary
                information about the source of the data */
                if((count >= 9) && (count <= 55)) {
                    List<String> line = new LinkedList<>(Arrays.asList(data.split(",")));

                    if(line.size() >= 5) { //do not read empty lines or lines containing notes and comments
                        sections.add(line);
                    }
                }
                count++;
            }
        }
        catch (FileNotFoundException e) {
            log.addLog("The 'Fire Detailing' file could not be found.");
            log.addStackTrace(e);
        } catch(Exception e) {
            log.addLog("Unknown Error! Please restart the application.");
            log.addStackTrace(e);
        }

        return sections;
    }
}
