package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.Controller;
import com.searchablebluebookv2.Log;
import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/***
 * This class will read various CSV files containing information for Universal Beams.
 *
 * @author Nathan Aspinall
 */
public class UBReader extends SteelReader {

    Log log;


    //Dimensions & Properties table (csv file)
    public static String DIMPROPS = "src/main/java/com/searchablebluebookv2/data/universalBeams/UB-secpropsdimsprops.csv";

    //Fire Parameters and Detailing table (csv file)
    public static String FIREDETAILS = "src/main/java/com/searchablebluebookv2/data/universalBeams/UB-secpropsdetailingfire.csv";


    //TODO: Read diagram images to be displayed in the UI

    /***
     * Constructor
     */
    public UBReader(Log log) {
        this.log = log;
    }



    //method to read dimension and properties

    /***
     * Method to read the dimensions and properties of UniversalBeams line by line into
     * a 2D array.
     * @return 2D List of rows of data
     */
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
                if((count >= 10) && (count <= 117)) {
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


    /***
     * Method to return the information from the Fire Parameters file
     *
     * @return 2D List of rows of data
     */
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
                if((count >= 9) && (count <= 117)) {
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

    //method to read effective section properties


    //method to read axial compression with S275


    //method to read axial compression with S355


    //method to read buckling resistance moment with S275


    //method to read buckling resistance moment with S355


    //method to read web bearing with S275


    //method to read web bearing with S355


    //method to read axial force with S275


    //method to read Axial force with S355

}
