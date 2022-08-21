package com.searchablebluebookv2.sections;

public class OpenRolledSection extends Section {

    public String preDesignation;

    public String subDesignation;

    /***
     * Constructor
     * @param preDes
     * @param subDes
     */
    public OpenRolledSection(String preDes, String subDes) {
        this.preDesignation = preDes;
        this.subDesignation = subDes;
    }

    /** @return preDesignation
     */
    public String getPreDesignation() {
        return preDesignation;
    }


    /** @return subDesignation
     */
    public String getSubDesignation() {
        return subDesignation;
    }
}
