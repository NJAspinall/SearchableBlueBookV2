package com.searchablebluebookv2.reader;


import com.searchablebluebookv2.Log;

/***
 * The purpose of this interface is to control the creation of a reader for each file,
 * which all contain different headings and various data.
 */
public interface ReaderFactoryIF {

    /***
     * Returns a new Reader object specific to a Steel shape
     *
     * @param discrim - String, identifies which shape the reader is for.
     * @return SteelReader - returns an object of the necessary reader
     */
    SteelReader createReader(String discrim, Log log);

}
