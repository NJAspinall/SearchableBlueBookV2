package com.searchablebluebookv2.models;

import java.util.List;

public class Properties {

    //Second moment of area
    public String areaY;
    public String areaZ;


    //Radius of gyration
    public String radiusY;
    public String radiusZ;


    //Elastic modulus
    public String elasticY;
    public String elasticZ;

    //Plastic modulus
    public String plasticY;
    public String plasticZ;

    public String buckling;

    public String torsionalI;

    public String warpingC;

    public String torsionalC;

    public String areaOfSection;



    public Properties(List<String> props) {

        this.areaY = props.get(0);
        this.areaZ = props.get(1);

        this.radiusY = props.get(2);
        this.radiusZ = props.get(3);

        this.elasticY = props.get(4);
        this.elasticZ = props.get(5);

        this.plasticY = props.get(6);
        this.plasticZ = props.get(7);

        this.buckling = props.get(8);

        this.torsionalI = props.get(9);

        this.warpingC = props.get(10);

        this.torsionalC = props.get(11);

        this.areaOfSection = props.get(12);
    }

}
