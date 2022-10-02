package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.Log;
import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.FireParameters;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;
import com.searchablebluebookv2.sections.UniversalColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class's purpose is to format data into required Steel Section objects and
 * return them to the Controller as needed as well as providing an interface to run
 * operations on the various objects to read data from them.
 *
 * @author Nathan Aspinall
 */
public class Populator {

    SteelReader reader;
    ReaderFactory rFactory;



    List<String> preDesList;
    List<String> subDesList;

    List<UniversalBeam> ubList;

    List<UniversalColumn> ucList;

    Log log;


    protected Image img1;
    protected Image img2;
    protected Image img3;



    /**
     * Constructor
     */
    public Populator(Log log) {
        this.rFactory = new ReaderFactory();
        this.log = log;
    }








    /**
     * Return the list of loaded images dependent on the Steel shape selected
     *
     * @return list of Images
     */
    public List<Image> getImages() {
        return new ArrayList<>(List.of(img1, img2, img3));
    }






    /**
     * Returns a list of current preDesignations dependent on which steel shape
     * has been selected. <br><br> The list is populated as the objects are read, so it
     * will always contain the list of the most recently loaded objects.
     *
     * @return List<String> - a list of PreDesignations e.g. '1016 x 305'
     */
    public List<String> getPreDesList() {
        return preDesList;
    }


    /**
     * Method to get the fields stored within a nested object of the section Object.
     *
     * @param fields - The fields we want to find in the object
     * @param section - the object we want to search
     * @return nestedFields, a List of the fields found within the section Object
     */
    public List<Object> getNestedObjects(List<Field> fields, Section section) {

        //get List of fields  from the chosen object
        List<Object> nestedFields = new ArrayList<>();

        for(Field f : fields) {
            System.out.println(f.getName());
            //get the field (f) from the specified object (anonObject)
            // e.g f.get(anonObject);
            try {
                //the fields (f) in this case are nested objects containing more fields
                nestedFields.add(f.get(section));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return nestedFields;
    }







    /**
     * Takes the given Steel object and returns a list of all the fields within. <br>
     *
     * @param section the current object to get the fields from
     * @return A List of the objects fields
     */
    public List<Field> getFields(Section section) {
        //list to hold the fields
        List<Field> fields = new ArrayList<>();

        //get the Class name of the object
        String typeName = section.getClass().getSimpleName();

        //Test against the object's Class name
        switch (typeName) {
            case "UniversalBeam" -> {
                //cast to Universal Beam
                UniversalBeam ub = (UniversalBeam) section;
                //get all fields and add set the list of fields this method will return
                fields = Arrays.asList(ub.getClass().getFields());
            }
            case "UniversalColumn" -> {
                //cast to Universal Column
                UniversalColumn uc = (UniversalColumn) section;
                //get all fields and add set the list of fields this method will return
                fields = Arrays.asList(uc.getClass().getFields());
            }

            //TODO: add more statements for each object type
        }

        return fields;
    }







    /**
     * Formats the returned data from the UniversalBeams file into usable UB objects.<br>
     * Also populates the list of pre-designations.
     */
    public void populateUniversalBeams() {
        reader = rFactory.createReader("Universal Beams (UB)", log);
        List<List<String>> sections = reader.readDimensionsAndProperties();

        //list to hold read universal beams objects
        ubList = new ArrayList<>();
        //list of read pre-designation values to be returned to the Controller
        preDesList = new ArrayList<>();

        String preDes = "";

        /* Logic to get Dimensions and Properties from each line */
        for(List<String> line : sections) {
            //Create a new UniversalBeam object and set the Designations

            /* in the csv file only the first row of each preDesignation will actually
            hold the preDesignation value, so it needs to be assigned to the following
            valid rows (until the next PreDesignation is met)*/
            if(!line.get(0).isBlank() || !line.get(0).equals("")) {
                preDes = line.get(0);
                preDesList.add(preDes);
            }

            //Instantiate Steel object
            UniversalBeam newBeam = new UniversalBeam(preDes, line.get(1));


            /* Store basic Section Properties */

            //All cells under 'Dimensions' SubHeading are taken from the file
            List<String> dimens = line.subList(2, 17);

            //Dimensions are stored within an object
            newBeam.dimensions = new Dimensions(dimens);

            //All cells under 'Properties' SubHeading are taken from the file
            List<String> props = line.subList(17, 30);

            //Properties are stored within an object
            newBeam.properties = new Properties(props);

            ubList.add(newBeam);
        }


        /* Logic to get Fire and Detailing Information from each line */
        List<List<String>> fireParams = reader.readFireAndDetailing();


        int i = 0;
        //get sublist for relevant fields
        for(List<String> list : fireParams) {

            //if the object has the matching designation
            if(ubList.get(i).getPreDesignation().equals(list.get(0))) {
                if(ubList.get(i).getSubDesignation().equals(list.get(1))) {
                    //get all cells from the table that are not in the previous table
                    List<String> data = list.subList(8, 12);

                    //Fire Parameters are stored within an object in the Beam class
                    ubList.get(i).setFireParameters(new FireParameters(data));
                }
            }

        }

        //Get the diagrams for the Section type
        img1 = reader.getImg1();
        img2 = reader.getImg2();
        img3 = reader.getImg3();
    }








    /**
     * Formats the returned data from the UniversalColumns file into usable UC objects.<br>
     * Also populates the list of pre-designations.
     */
    public void populateUniversalColumns() {
        reader = rFactory.createReader("Universal Columns (UC)", log);
        List<List<String>> sections = reader.readDimensionsAndProperties();

        //list to hold read universal Columns objects
        ucList = new ArrayList<>();
        //list of read pre-designation values to be returned to the Controller
        preDesList = new ArrayList<>();

        String preDes = "";

        /* Logic to get Dimensions and Properties from each line */
        for(List<String> line : sections) {
            //Create a new UniversalColumn object and set the Designations

            /* in the csv file only the first row of each preDesignation will actually
            hold the preDesignation value, so it needs to be assigned to the following
            valid rows (until the next PreDesignation is met)*/
            if(!line.get(0).isBlank() || !line.get(0).equals("")) {
                preDes = line.get(0);
                preDesList.add(preDes);
            }

            //Instantiate Steel object
            UniversalColumn newColumn = new UniversalColumn(preDes, line.get(1));


            /* Store basic Section Properties */

            //All cells under 'Dimensions' SubHeading are taken from the file
            List<String> dimens = line.subList(2, 17);

            //Dimensions are stored within an object
            newColumn.dimensions = new Dimensions(dimens);

            //All cells under 'Properties' SubHeading are taken from the file
            List<String> props = line.subList(17, 30);

            //Properties are stored within an object
            newColumn.properties = new Properties(props);

            ucList.add(newColumn);
        }


        /* Logic to get Fire and Detailing Information from each line */
        List<List<String>> fireParams = reader.readFireAndDetailing();


        int i = 0;
        //get sublist for relevant fields
        for(List<String> list : fireParams) {

            //if the object has the matching designation
            if(ucList.get(i).getPreDesignation().equals(list.get(0))) {
                if(ucList.get(i).getSubDesignation().equals(list.get(1))) {
                    //get all cells from the table that are not in the previous table
                    List<String> data = list.subList(8, 12);

                    //Fire Parameters are stored within an object in the Column class
                    ucList.get(i).setFireParameters(new FireParameters(data));
                }
            }

        }

        //Get the diagrams for the Section type
        img1 = reader.getImg1();
        img2 = reader.getImg2();
        img3 = reader.getImg3();
    }








    /**
     * Returns the list of usable UB objects
     *
     * @return List of UniversalBeam objects
     */
    public List<UniversalBeam> getUniversalBeams() {
        return ubList;
    }



    /**
     * Returns the list of usable UC objects
     *
     * @return List of UniversalColumn objects
     */
    public List<UniversalColumn> getUniversalColumns() {
        return ucList;
    }


    /**
     * Get the list of SubDesignations
     *
     * @return String list of sub-designations
     */
    public List<String> getSubDesignations() {

        return new ArrayList<String>();
    }


}
