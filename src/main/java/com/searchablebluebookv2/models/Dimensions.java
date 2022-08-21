package com.searchablebluebookv2.models;

import java.util.List;

public class Dimensions {

    public String massPerMetre;

    public String depthOfSection;

    public String widthOfSection;

    //Thickness
    public String web;
    public String flange;

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
    public String areaPerMetre;
    public String areaPerTonne;


    /***
     * Constructor
     *
     * @param dimensions - List of all dimensions taken from the table for this row
     */
    public Dimensions(List<String> dimensions) {

        this.massPerMetre = dimensions.get(0);

        this.depthOfSection = dimensions.get(1);

        this.web = dimensions.get(2);
        this.flange = dimensions.get(3);

        this.rootRadius = dimensions.get(4);

        this.depthBetweenFillets = dimensions.get(5);

        this.ratioWeb = dimensions.get(6);
        this.ratioFlange = dimensions.get(7);

        this.endClearance = dimensions.get(8);
        this.NNotch = dimensions.get(9);
        this.nNotch = dimensions.get(10);

        this.areaPerMetre = dimensions.get(11);
        this.areaPerTonne = dimensions.get(12);

    }

}
