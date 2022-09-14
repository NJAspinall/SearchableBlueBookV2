package com.searchablebluebookv2.models;

import java.util.List;

public class FireParameters {

    public String profileThreeSides;
    public String profileFourSides;

    public String boxThreeSides;
    public String boxFourSides;


    /**
     * Constructor
     *
     * @param line - List of each row of data
     */
    public FireParameters(List<String> line) {
        this.profileThreeSides = line.get(0);
        this.profileFourSides = line.get(1);

        this.boxThreeSides = line.get(2);
        this.boxFourSides = line.get(3);
    }


}
