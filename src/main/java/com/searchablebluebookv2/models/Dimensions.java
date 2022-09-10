package com.searchablebluebookv2.models;

import java.util.List;


/**
 * This class holds the columns from the 'Dimensions' subheading of the Sections
 * 'Properties and Dimensions' csv.
 *
 * @author Nathan Aspinall
 */
public class Dimensions {

    public String massPerMetre;

    public String depthOfSection;

    public String widthOfSection;

    //Thickness
    public String webThickness;
    public String flangeThickness;

    public String rootRadius;

    public String depthBetweenFillets;

    //Ratios for local buckling
    public String ratioWeb;
    public String ratioFlange;

    //Dimensions for end detailing
    public String endClearance;
    public String NNotch;
    public String nNotch;

    //Surface Area
    public String surfaceAreaPerMetre;
    public String surfaceAreaPerTonne;


    /**
     * Constructor
     *
     * @param dimensions - List of all dimensions taken from the table for this row
     */
    public Dimensions(List<String> dimensions) {

        this.massPerMetre = dimensions.get(1);

        this.widthOfSection = dimensions.get(2);

        this.depthOfSection = dimensions.get(3);

        this.webThickness = dimensions.get(4);
        this.flangeThickness = dimensions.get(5);

        this.rootRadius = dimensions.get(6);

        this.depthBetweenFillets = dimensions.get(7);

        this.ratioWeb = dimensions.get(8);
        this.ratioFlange = dimensions.get(9);

        this.endClearance = dimensions.get(10);
        this.NNotch = dimensions.get(11);
        this.nNotch = dimensions.get(12);

        this.surfaceAreaPerMetre = dimensions.get(13);
        this.surfaceAreaPerTonne = dimensions.get(14);

    }

}
