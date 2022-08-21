package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.sections.OpenRolledSection;
import com.searchablebluebookv2.sections.Section;

import java.util.List;

public abstract class SteelReader {


    public abstract List<OpenRolledSection> readDimensionsAndProperties();

}
