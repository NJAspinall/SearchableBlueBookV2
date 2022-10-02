package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.sections.OpenRolledSection;
import com.searchablebluebookv2.sections.Section;
import javafx.scene.image.Image;

import java.util.List;


/**
 * This abstract Class will be used to subclass reader classes unique to each Steel Shape and its unique csv files
 *
 * @author Nathan Aspinall
 */
public abstract class SteelReader {


    public abstract Image getImg1();
    public abstract Image getImg2();
    public abstract Image getImg3();




    public abstract List<List<String>> readDimensionsAndProperties();


}
