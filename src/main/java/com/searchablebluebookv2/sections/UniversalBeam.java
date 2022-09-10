package com.searchablebluebookv2.sections;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.FireParameters;
import com.searchablebluebookv2.models.Properties;


/**
 * Holds the read data from the corresponding CSV files
 *
 * @author Nathan Aspinall
 */
public class UniversalBeam extends OpenRolledSection {

    /* Base Information - Section Dimensions and Properties */
    public Dimensions dimensions;
    public Properties properties;

    
    /* Fire Parameters and Detailing */
    public FireParameters fireParameters;
    

    /**
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


    /**
     * Setter for Dimensions
     * 
     * @param dimensions the new Dimensions Object
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }


    /** Getter for Properties Object 
     * 
     * @return Properties object 
     */
    public Properties getProperties() {
        //More fields are found within this object
        return properties;
    }


    /**
     * Setter for Properties
     * 
     * @param properties the new Properties object
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    
    
    
    
    /**
     * Getter for FireParameters object
     * 
     * @return - FireParameters object
     */
    public FireParameters getFireParameters() {
        return fireParameters;
    }


    /**
     * Setter for FireParameters
     *
     * @param fireParameters the new FireParameters object
     */
    public void setFireParameters(FireParameters fireParameters) {
        this.fireParameters = fireParameters;
    }
}
