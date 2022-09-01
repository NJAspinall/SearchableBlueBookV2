package com.searchablebluebookv2.reader;

public class ReaderFactory implements ReaderFactoryIF {

    /**
     * Return the reader to collect the correct information
     */
    @Override
    public SteelReader createReader(String discrim) {
        if(discrim.equals("Universal Beams (UB)")) {
            return new UBReader();
        }

        else {
            return null;
        }
    }
}
