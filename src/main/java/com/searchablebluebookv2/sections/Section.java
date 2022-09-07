package com.searchablebluebookv2.sections;

import java.util.ArrayList;
import java.util.List;

public class Section {

    protected String preDes;
    protected String subDes;






    /***
     * Constructor
     * @param preDes
     * @param subDes
     */
    public Section(String preDes, String subDes) {
        this.preDes = preDes;
        this.subDes = subDes;
    }







    public String getPreDesignation() {
        return preDes;
    }







    public String getSubDesignation() {
        return subDes;
    }






}
