package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * This class's purpose is to format data into required Steel Section objects and
 * return them to the Controller as needed.
 *
 * @author Nathan Aspinall
 */
public class Populator {

    SteelReader reader;
    ReaderFactory rFactory;



    List<String> preDesList;
    List<String> subDesList;

    List<UniversalBeam> ubList;


    /***
     * Constructor
     */
    public Populator() {
        this.rFactory = new ReaderFactory();
    }




    /***
     * Returns a list of current preDesignations dependent on which steel shape
     * has been selected. The list is populated as teh objects are read, so it
     * will always contain the list of the most recently loaded objects.#
     *
     * @return List<String> - a list of PreDesignations e.g. '1016 x 305'
     */
    public List<String> getPreDesList() {
        return preDesList;
    }


    /***
     * TODO: Test this method, may need to cast to UB via switch statement
     * Takes the given Steel object and returns a list of the names
     * of all the fields within.
     * @param section
     * @return
     */
    public List<Field> getFields(Section section) {
        //Return all field names from the Steel object as a List
        return Arrays.asList(section.getClass().getFields());
    }






    /***
     * Formats the returned data from the UniversalBeams file into usable UB objects.
     * Also populates the list of pre-designations.
     */
    public void populateUniversalBeams() {
        reader = rFactory.createReader("Universal Beams (UB)");
        List<List<String>> sections = reader.readDimensionsAndProperties();

        ubList = new ArrayList<>();

        preDesList = new ArrayList<>();


        String preDes = "";

        /* Logic to get data from each line */
        for(List<String> line : sections) {
            //Create a new UniversalBeam object and set the Designations

            if(!line.get(0).isBlank() || !line.get(0).equals("")) {
                preDes = line.get(0);
                preDesList.add(preDes);
            }

            //Instantiate Steel object
            UniversalBeam newBeam = new UniversalBeam(preDes, line.get(1));

            /* Store basic Section Properties */

            //Dimensions are stored within an object
            List<String> dimens = line.subList(2, 17);
            newBeam.dimensions = new Dimensions(dimens);
            //Properties are stored within an object
            List<String> props = line.subList(17, 30);
            newBeam.properties = new Properties(props);

            ubList.add(newBeam);
        }


        /*Display for testing */
        int i = 0;
        for(UniversalBeam beam : ubList) {
            i++;
            System.out.println(i+". ");
            System.out.println(beam.getPreDesignation());
            System.out.println(beam.getSubDesignation());
        }
    }




    /***
     * Returns the list of usable UB objects
     * @return List<UniversalBeam>
     */
    public List<UniversalBeam> getUniversalBeams() {
        return ubList;
    }






    public List<String> getSubDesignations() {

        return new ArrayList<String>();
    }


}
