package com.searchablebluebookv2.models;

import java.util.List;

public class FireParameters {

    public String profile3Sides;
    public String profile4Sides;

    public String box3Sides;
    public String box4Sides;


    /**
     * Constructor
     *
     * @param line - List of each row of data
     */
    public FireParameters(List<String> line) {
        this.profile3Sides = line.get(0);
        this.profile4Sides = line.get(1);

        this.box3Sides = line.get(2);
        this.box4Sides = line.get(3);
    }


}
