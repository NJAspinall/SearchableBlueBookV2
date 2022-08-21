package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UBReader extends SteelReader {

    public static String DIMPROPS = "src/main/java/com/searchablebluebookv2/data/universalBeams/UB-secpropsdimsprops.csv";





    public UBReader() {

    }



    //method to read dimension and properties

    public List<Section> readDimensionsAndProperties() {

        List<Section> sections = new ArrayList<>();

        int count = 0;

        try {
            File myObj = new File(DIMPROPS);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine().trim();

                /* ignore first 9 lines which is just supplementary
                information about the source of the data */
                if((count >= 10) && (count <= 117)) {
                    List<String> line = new LinkedList<String>(Arrays.asList(data.split(",")));

                    if(line.size() >= 5) { //do not read empty lines or lines containing notes and comments
                        try {

                            /* Logic to get data from each line */

                            //Create a new UniversalBeam object and set the Designations
                            UniversalBeam newBeam = new UniversalBeam(line.get(0), line.get(1));


                            //Dimensions are stored within an object
                            List<String> dimens = line.subList(2, 17);
                            newBeam.dimensions = new Dimensions(dimens);
                            //Properties are stored within an object
                            List<String> props = line.subList(17, 30);
                            newBeam.properties = new Properties(props);


                            sections.add(newBeam);


                            /*
                            System.out.println("'dimens' contents: ");
                            for(String s : dimens) {
                                System.out.println(s);
                            }

                            System.out.println("'props' contents: ");
                            for(String s : props) {
                                System.out.println(s);
                            }
                            */



                            //return list of UniversalBeam objects


                        } catch (InstantiationError e) {
                            e.printStackTrace();
                            //System.out.println("Cannot instantiate 'Designation' object");
                        }
                    }
                }
                count++;

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The Universal Beams file could not be found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown Error!");
            e.printStackTrace();
        }

        return sections;
    }

    //method to read detailing and fire parameters


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
