package com.searchablebluebookv2.sections;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;

public class UniversalBeam extends OpenRolledSection {

    public Dimensions dimensions;
    public Properties properties;


    /***
     * Constructor
     * @param preDes The first identifying element of the Steel shape e.g. '1016 x 305'
     * @param subDes The Second identifying element of the Steel Shape e.g. 'x 584'. This
     *               represents the mass per metre of the Steel beam shape
     */
    public UniversalBeam(String preDes, String subDes) {
        super(preDes, subDes);
    }







    /** Getters for Designation & sub-Designation */
    @Override
    public String getPreDesignation() {
        return super.getPreDesignation();
    }

    @Override
    public String getSubDesignation() {
        return super.getSubDesignation();
    }




    /** Getter for Dimensions Object */
    public Dimensions getDimensions() {
        //More fields are found within this object
        return dimensions;
    }

    /** Getter for Properties Object */
    public Properties getProperties() {
        //More fields are found within this object
        return properties;
    }
}
