package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.sections.OpenRolledSection;
import com.searchablebluebookv2.sections.Section;

import java.util.List;


/**
 * This abstract Class will be used to subclass reader classes unique to each Steel Shape and its unique csv files
 *
 * @author Nathan Aspinall
 */
public abstract class SteelReader {


    public abstract List<List<String>> readDimensionsAndProperties();

    public abstract List<List<String>> readFireAndDetailing();

}
