package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
     * has been selected. <br><br> The list is populated as the objects are read, so it
     * will always contain the list of the most recently loaded objects.
     *
     * @return List<String> - a list of PreDesignations e.g. '1016 x 305'
     */
    public List<String> getPreDesList() {
        return preDesList;
    }


    /***
     * TODO: Test this method, may need to cast to UB via switch statement. <br><br>
     * Takes the given Steel object and returns a list of the names
     * of all the fields within. <br>
     * @param section
     * @return
     */
    public List<Field> getFields(Section section) {
        //list to hodl the fields
        List<Field> fields = new ArrayList<>();

        //get the Class name of the object
        String typeName = section.getClass().getName();
        //class names are separated from their superclasses by '.'
        List<String> tp = Arrays.asList(typeName.split("\\."));
        //get just the class name, not the full package
        typeName = tp.get(tp.size() -1);

        System.out.println("TypeName: " +typeName);

        //TODO: change this method so it can go inside of the nested objects and get their field names too.

        //Test against the object's Class name
        switch (typeName) {
            case "UniversalBeam" -> {
                    //cast to Universal Beam
                    UniversalBeam ub = (UniversalBeam) section;
                    //get all fields and add set the list of fields this method will return
                    fields = Arrays.asList(ub.getClass().getFields());
            }

            //TODO: add more statements for each object type

            case "UniversalColumns" -> {

            }
        }

        return fields;
    }






    /***
     * Formats the returned data from the UniversalBeams file into usable UB objects.<br>
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

            System.out.println("Dimensions");
            int x = 0;
            for(String s : dimens) {
                x++;
                System.out.println(x +". "+s);
            }

            newBeam.dimensions = new Dimensions(dimens);

            //Properties are stored within an object
            List<String> props = line.subList(17, 30);

            System.out.println("Properties");
            int i = 0;
            for(String s : props) {
                i++;
                System.out.println(i +". "+s);
            }
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
